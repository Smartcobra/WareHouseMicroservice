package in.jit.controller;

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
import org.springframework.web.bind.annotation.RestController;

import in.jit.exception.ResourceNotFoundException;
import in.jit.model.Part;
import in.jit.model.PartVO;
import in.jit.service.PartService;

@RestController
@RequestMapping("/part/rest")
public class PartRestController {

	@Autowired
	private PartService service;

	@GetMapping("/partcodebasecost")
	public ResponseEntity<List<PartVO>> getPartCodeId() {
		List<PartVO> partCodeAndBaseCost = service.getPartCodeAndBaseCost();
		return new ResponseEntity<List<PartVO>>(partCodeAndBaseCost, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> saveShipmentType(@Valid @RequestBody Part part, BindingResult errors) {
		ResponseEntity<?> resp = null;
		try {
			if (errors.hasErrors()) {
				HashMap<String, String> errorsMap = new HashMap<String, String>();
				for (FieldError err : errors.getFieldErrors()) {
					errorsMap.put(err.getField(), err.getDefaultMessage());
				}
				resp = new ResponseEntity<HashMap<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
			} else {
				Integer id = service.savePart(part);
				resp = new ResponseEntity<String>("RECORD HAS BEEN SAVED WITH " + id, HttpStatus.OK);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("RECORD CAN'T BE SAVED WITH " + part.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Part>> getAllShipmentType() {
		List<Part> list = service.getAllParts();
		return new ResponseEntity<List<Part>>(list, HttpStatus.OK);

	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneShipmentType(@PathVariable Integer id) {
		Optional<Part> opt = service.getOnePart(id);
		ResponseEntity<?> resp = null;
		if (opt.isPresent()) {
			resp = new ResponseEntity<Part>(opt.get(), HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Record Not Found", HttpStatus.NOT_FOUND);
		}
		return resp;

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Part> updateUom(@PathVariable Integer id, 
			@Valid @RequestBody Part part) 
			throws ResourceNotFoundException {
		
		Part newPart = service.findById(id).
									orElseThrow(() -> new ResourceNotFoundException("Shipmenttype not found for this id :: " + id));
		
		newPart.setPartCode(part.getPartCode());
		newPart.setBaseCost(part.getBaseCost());
		newPart.setPartHgh(part.getPartHgh());
		newPart.setPartLen(part.getPartLen());
		newPart.setPartWidth(part.getPartWidth());
		newPart.setDescription(part.getDescription());
		newPart.setBaseCurr(part.getBaseCurr());
		newPart.setUom(part.getUom());
		
		Part uPart = service.update(newPart);
		
		return ResponseEntity.ok(uPart);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneShipmentType(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		if (service.isPartExist(id)) {
			try {
				service.deletePart(id);
				resp = new ResponseEntity<String>("RECORD HAS BEEN DELETED WITH " + id, HttpStatus.OK);
			} catch (Exception e) {
				resp = new ResponseEntity<String>("RECORD CAN'T BE DELETED, IT USED ANOTHER OPERATIONS " + id, HttpStatus.BAD_REQUEST);
			}
		} else {
			resp = new ResponseEntity<String>("RECORD WITH " + id + " NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return resp;
	}

}
