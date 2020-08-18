package in.jit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
