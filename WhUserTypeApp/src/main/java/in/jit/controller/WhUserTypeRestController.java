package in.jit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jit.model.WhUserTypeVO;
import in.jit.service.WhUserTypeService;

@RestController
@RequestMapping("/rest")
public class WhUserTypeRestController {

	@Autowired
	WhUserTypeService whUserTypeService;

	@GetMapping("/whusertypecode")
	public ResponseEntity<List<WhUserTypeVO>> getShipmentCode() {

		List<WhUserTypeVO> whUserTypeCode = whUserTypeService.getWhUserTypeIdAndCode();

		return new ResponseEntity<List<WhUserTypeVO>>(whUserTypeCode, HttpStatus.OK);
	}

}
