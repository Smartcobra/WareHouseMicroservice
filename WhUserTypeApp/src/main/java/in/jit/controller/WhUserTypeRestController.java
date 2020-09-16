package in.jit.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
