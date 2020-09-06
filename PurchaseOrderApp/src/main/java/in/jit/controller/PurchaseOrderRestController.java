package in.jit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jit.model.PurchaseDtlDTO;
import in.jit.model.PurchaseOrderVO;
import in.jit.service.PurchaseOrderService;

@RestController
@RequestMapping("/purchaseorder/rest")
public class PurchaseOrderRestController {

	@Autowired
	PurchaseOrderService purchaseOrderService;

	@GetMapping("/status")
	public ResponseEntity<List<PurchaseOrderVO>> getPurchaseOrder() {
		List<PurchaseOrderVO> purchaseOrderByStatus = purchaseOrderService.getPurchaseOrderByStatus("INVOICED");

		return new ResponseEntity<List<PurchaseOrderVO>>(purchaseOrderByStatus, HttpStatus.OK);

	}
	
	
	@GetMapping("/purchasedtls")
	public ResponseEntity<List<PurchaseDtlDTO>> fetchAllPurchaseOrderDtls() {
		List<PurchaseDtlDTO> allPurchaseOrderDtls = purchaseOrderService.getAllPurchaseOrderDtls();

		return new ResponseEntity<List<PurchaseDtlDTO>>(allPurchaseOrderDtls, HttpStatus.OK);

	}

}
