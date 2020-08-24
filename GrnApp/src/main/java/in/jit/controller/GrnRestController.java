package in.jit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jit.model.GrnPurchaseOrderDTO;
import in.jit.model.GrnVO;
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
}
