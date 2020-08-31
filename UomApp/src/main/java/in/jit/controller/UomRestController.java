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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jit.model.Uom;
import in.jit.model.UomVO;
import in.jit.service.UomService;

@RestController
@RequestMapping("/rest/uom")
@CrossOrigin
public class UomRestController {

	@Autowired
	private UomService service;

	@GetMapping("/all")
	public ResponseEntity<List<UomVO>> getUomToPart() {
		UomVO uomVO = null;
		List<UomVO> listUomVO = new ArrayList<UomVO>();
		
		int index = 0;

		List<Uom> allUoms = service.getAllUoms();
		for (Uom uom : allUoms) {
			uomVO = new UomVO();
			uomVO.setId(uom.getId());
			uomVO.setUomModel(uom.getUomModel());
			uomVO.setUomType(uom.getUomType());
			listUomVO.add(index, uomVO);
			index++;

		}
		/*
		 * for (int i = 0; i < listUomVO.size(); i++) {
		 * System.out.println(listUomVO.get(i).getId()); }
		 */
		// listUomVO.forEach(System.out::println);
		return new ResponseEntity<List<UomVO>>(listUomVO, HttpStatus.OK);

	}

	@GetMapping("/uomall")
	public ResponseEntity<List<Uom>> getAllUom() {
	System.out.println("UomRestController.getAllUom()");
		List<Uom> list = service.getAllUoms();
		return new ResponseEntity<List<Uom>>(list, HttpStatus.OK);

	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneUom(@PathVariable Integer id) {
		Optional<Uom> opt = service.getOneUom(id);
		ResponseEntity<?> resp = null;
		if (opt.isPresent()) {
			resp = new ResponseEntity<Uom>(opt.get(), HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Record Not Found", HttpStatus.NOT_FOUND);
		}
		return resp;

	}

	// 3.Delete one record from DB
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneUom(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		if (service.isUomExist(id)) {
			try {
				service.deleteUom(id);
				resp = new ResponseEntity<String>("RECORD HAS BEEN DELETED WITH " + id, HttpStatus.OK);
			} catch (Exception e) {
				resp = new ResponseEntity<String>("RECORD CAN'T BE DELETED, IT USED ANOTHER OPERATIONS " + id, HttpStatus.BAD_REQUEST);
			}
		} else {
			resp = new ResponseEntity<String>("RECORD WITH " + id + " NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return resp;
	}

	@PostMapping("/insert")
	public ResponseEntity<?> saveUom(@Valid @RequestBody Uom uom, BindingResult errors) {
		ResponseEntity<?> resp = null;
		try {
			if (errors.hasErrors()) {
				HashMap<String, String> errorsMap = new HashMap<String, String>();
				for (FieldError err : errors.getFieldErrors()) {
					errorsMap.put(err.getField(), err.getDefaultMessage());
				}
				resp = new ResponseEntity<HashMap<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
			} else {
				Integer id = service.saveUom(uom);
				resp = new ResponseEntity<String>("RECORD HAS BEEN SAVED WITH " + id, HttpStatus.OK);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("RECORD CAN'T BE SAVED WITH " + uom.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateUom(@RequestBody Uom uom) {
		ResponseEntity<String> resp = null;
		if (uom.getId() == null || !service.isUomExist(uom.getId())) {
			resp = new ResponseEntity<String>("RECORD NOT EXIST IN DB", HttpStatus.BAD_REQUEST);
		} else {
			service.updateUom(uom);
			resp = new ResponseEntity<String>("UOM WITH '" + uom.getId() + "' UPDATED", HttpStatus.OK);
		}
		return resp;
	}

}
