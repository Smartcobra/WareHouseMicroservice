package in.jit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jit.exception.ResourceNotFoundException;
import in.jit.model.Grn;
import in.jit.model.GrnDTO;
import in.jit.model.GrnDtl;
import in.jit.model.GrnDtlDTO;
import in.jit.model.GrnPurchaseOrderDTO;
import in.jit.service.GrnService;

@RestController
@RequestMapping("/rest/grn")
public class GrnRestController {

	@Autowired
	private GrnService service;

	@GetMapping("/status")
	public ResponseEntity<List<GrnPurchaseOrderDTO>> getPurchaseStatus() {

		List<GrnPurchaseOrderDTO> purchaseOrderStatus = service.getPurchaseOrderStatus(false);

		return new ResponseEntity<List<GrnPurchaseOrderDTO>>(purchaseOrderStatus, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveGrn(@Valid @RequestBody Grn grn, BindingResult errors) {
		ResponseEntity<?> resp = null;
		try {
			if (errors.hasErrors()) {
				HashMap<String, String> errorsMap = new HashMap<String, String>();
				for (FieldError err : errors.getFieldErrors()) {
					errorsMap.put(err.getField(), err.getDefaultMessage());
				}
				resp = new ResponseEntity<HashMap<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
			} else {
				grn.setPurchaseStatus(false);
				Integer id = service.saveGrn(grn);
				service.convertPurchaseDtlToGrnDtl(id);
				resp = new ResponseEntity<String>("RECORD HAS BEEN SAVED WITH " + id, HttpStatus.OK);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("RECORD CAN'T BE SAVED WITH " + grn.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/all")
	public ResponseEntity<List<GrnDTO>> getData() {
		List<Grn> allGrns = service.getAllGrns();

		List<GrnDTO> list = new ArrayList<GrnDTO>();
		GrnDTO grnDTO = null;

		for (Grn grn : allGrns) {
			grnDTO = new GrnDTO();
			grnDTO.setId(grn.getId());
			grnDTO.setGrnCode(grn.getGrnCode());
			grnDTO.setDescription(grn.getDescription());
			grnDTO.setGrnType(grn.getGrnType());
			grnDTO.setOrderCode(grn.getOrderCode());
			grnDTO.setDescription(grn.getDescription());
			grnDTO.setPurchaseStatus(grn.isPurchaseStatus());

			list.add(grnDTO);

		}
		return new ResponseEntity<List<GrnDTO>>(list, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneGrn(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		if (service.isGrnExist(id)) {
			try {
				service.deleteGrn(id);
				resp = new ResponseEntity<String>("RECORD HAS BEEN DELETED WITH " + id, HttpStatus.OK);
			} catch (Exception e) {
				resp = new ResponseEntity<String>("RECORD CAN'T BE DELETED, IT USED ANOTHER OPERATIONS " + id, HttpStatus.BAD_REQUEST);
			}
		} else {
			resp = new ResponseEntity<String>("RECORD WITH " + id + " NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return resp;
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUom(@PathVariable Integer id, @Valid @RequestBody GrnDTO grnDTO) throws ResourceNotFoundException {

		ResponseEntity<String> resp = null;
		Grn grn = service.getOneGrn(id).orElseThrow(() -> new ResourceNotFoundException("GRN  not found for this id :: " + id));

		grn.setGrnCode(grnDTO.getGrnCode());
		grn.setGrnType(grnDTO.getGrnType());
		grn.setOrderCode(grn.getOrderCode());
		grn.setDescription(grn.getDescription());

		if (service.isGrnExist(id)) {
			try {
				service.updateGrn(grn);
				resp = new ResponseEntity<String>("RECORD HAS BEEN UPDATED WITH " + id, HttpStatus.OK);
			} catch (Exception e) {
				resp = new ResponseEntity<String>("RECORD CAN'T BE UPDATED, IT USED ANOTHER OPERATIONS " + id, HttpStatus.BAD_REQUEST);
			}
		} else {
			resp = new ResponseEntity<String>("RECORD WITH " + id + " NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return resp;

	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOnePurchaseOrder(@PathVariable Integer id) {
		Optional<Grn> opt = service.getOneGrn(id);
		GrnDTO grnDto = new GrnDTO();
		grnDto.setGrnCode(opt.get().getGrnCode());
		grnDto.setGrnType(opt.get().getGrnType());
		grnDto.setOrderCode(opt.get().getOrderCode());
		grnDto.setDescription(opt.get().getDescription());

		ResponseEntity<?> resp = null;
		if (opt.isPresent()) {
			resp = new ResponseEntity<GrnDTO>(grnDto, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Record Not Found", HttpStatus.NOT_FOUND);
		}
		return resp;

	}

	@GetMapping("/grnDtls/{id}")
	public ResponseEntity<List<GrnDtlDTO>> showGrnDtls(@PathVariable Integer id) {
		List<GrnDtl> grndtls = service.getAllDtlsByGrnId(id);

		List<GrnDtlDTO> list = new ArrayList<GrnDtlDTO>();
		GrnDtlDTO grnDtlDTO = null;

		for (GrnDtl gdtls : grndtls) {
			grnDtlDTO = new GrnDtlDTO();
			grnDtlDTO.setId(gdtls.getId());
			grnDtlDTO.setBaseCost(gdtls.getBaseCost());
			grnDtlDTO.setPartCode(gdtls.getPartCode());
			grnDtlDTO.setQty(gdtls.getQty());
			grnDtlDTO.setLineValue(gdtls.getLineValue());
			grnDtlDTO.setStatus(gdtls.getStatus());

			list.add(grnDtlDTO);
		}

		return new ResponseEntity<List<GrnDtlDTO>>(list, HttpStatus.OK);

	}
	
	@GetMapping("/status/{dtlId}/{status}")
	public ResponseEntity<String> updateDtlStatus(@PathVariable String dtlId, @PathVariable String status) {
		service.updateStatusByGrnDtlId(status, Integer.parseInt(dtlId));
		return new ResponseEntity<String>("Record Updated", HttpStatus.ACCEPTED);
	}

}
