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
import in.jit.model.WhUserType;
import in.jit.model.WhUserTypeVO;
import in.jit.service.WhUserTypeService;

@RestController
@RequestMapping("/rest/whuser")
public class WhUserTypeRestController {

	@Autowired
	WhUserTypeService whUserTypeService;

	@GetMapping("/whusertypecode")
	public ResponseEntity<List<WhUserTypeVO>> getShipmentCode() {

		List<WhUserTypeVO> whUserTypeCode = whUserTypeService.getWhUserTypeIdAndCode();

		return new ResponseEntity<List<WhUserTypeVO>>(whUserTypeCode, HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<?> saveShipmentType(@Valid @RequestBody WhUserType wh, BindingResult errors) {
		ResponseEntity<?> resp = null;
		try {
			if (errors.hasErrors()) {
				HashMap<String, String> errorsMap = new HashMap<String, String>();
				for (FieldError err : errors.getFieldErrors()) {
					errorsMap.put(err.getField(), err.getDefaultMessage());
				}
				resp = new ResponseEntity<HashMap<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
			} else {
				Integer id = whUserTypeService.saveWhUserType(wh);
				resp = new ResponseEntity<String>("RECORD HAS BEEN SAVED WITH " + id, HttpStatus.OK);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("RECORD CAN'T BE SAVED WITH " + wh.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/all")
	public ResponseEntity<List<WhUserType>> getAllShipmentType() {
		List<WhUserType> list = whUserTypeService.getAllWhUserTypes();
		return new ResponseEntity<List<WhUserType>>(list, HttpStatus.OK);

	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneShipmentType(@PathVariable Integer id) {
		Optional<WhUserType> opt = whUserTypeService.getOneWhUserType(id);
		ResponseEntity<?> resp = null;
		if (opt.isPresent()) {
			resp = new ResponseEntity<WhUserType>(opt.get(), HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Record Not Found", HttpStatus.NOT_FOUND);
		}
		return resp;

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<WhUserType> updateUom(@PathVariable Integer id, 
			@Valid @RequestBody WhUserType whuser) 
			throws ResourceNotFoundException {
		
		WhUserType whUserType = whUserTypeService.findById(id).
									orElseThrow(() -> new ResourceNotFoundException("Shipmenttype not found for this id :: " + id));
		
		whUserType.setUserCode(whuser.getUserCode());
		whUserType.setUserContact(whuser.getUserContact());
		whUserType.setUserMail(whuser.getUserMail());
		whUserType.setIfOther(whuser.getIfOther());
		whUserType.setUserIdType(whuser.getUserIdType());
		whUserType.setUserType(whuser.getUserType());
		whUserType.setUserFor(whuser.getUserFor());
		whUserType.setIdNumber(whuser.getIdNumber());
		
		WhUserType whUserTypeUpdate = whUserTypeService.update(whUserType);
		
		return ResponseEntity.ok(whUserTypeUpdate);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneShipmentType(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		if (whUserTypeService.isWhUserTypeExist(id)) {
			try {
				whUserTypeService.deleteWhUserType(id);
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
