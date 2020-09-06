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
import in.jit.model.PurchaseDtlDTO;
import in.jit.model.PurchaseOrder;
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

	@GetMapping("/all")
	public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrder() {
		List<PurchaseOrder> list = purchaseOrderService.getAllPurchaseOrders();
		return new ResponseEntity<List<PurchaseOrder>>(list, HttpStatus.OK);

	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOnePurchaseOrder(@PathVariable Integer id) {
		Optional<PurchaseOrder> opt = purchaseOrderService.getOnePurchaseOrder(id);
		ResponseEntity<?> resp = null;
		if (opt.isPresent()) {
			resp = new ResponseEntity<PurchaseOrder>(opt.get(), HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Record Not Found", HttpStatus.NOT_FOUND);
		}
		return resp;

	}

	// 3.Delete one record from DB
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOnePurchaseOrder(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		if (purchaseOrderService.isPurchaseOrderExist(id)) {
			try {
				purchaseOrderService.deletePurchaseOrder(id);
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
	public ResponseEntity<?> saveShipmentType(@Valid @RequestBody PurchaseOrder po, BindingResult errors) {
		ResponseEntity<?> resp = null;
		try {
			if (errors.hasErrors()) {
				HashMap<String, String> errorsMap = new HashMap<String, String>();
				for (FieldError err : errors.getFieldErrors()) {
					errorsMap.put(err.getField(), err.getDefaultMessage());
				}
				resp = new ResponseEntity<HashMap<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
			} else {
				Integer id = purchaseOrderService.savePurchaseOrder(po);
				resp = new ResponseEntity<String>("RECORD HAS BEEN SAVED WITH " + id, HttpStatus.OK);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("RECORD CAN'T BE SAVED WITH " + po.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<PurchaseOrder> updateUom(	@PathVariable Integer id, 
													@Valid @RequestBody PurchaseOrder purchaseOrderDetails) 
															throws ResourceNotFoundException {

		// ResponseEntity<String> resp = null;
		PurchaseOrder po = purchaseOrderService.findById(id).
												orElseThrow(() ->
												new ResourceNotFoundException("Purchase order  not found for this id :: " + id));

		po.setOrderCode(purchaseOrderDetails.getOrderCode());
		po.setQualityCheck(purchaseOrderDetails.getQualityCheck());
		po.setReferenceNumber(purchaseOrderDetails.getReferenceNumber());
		po.setDescription(purchaseOrderDetails.getDescription());
		po.setShipmentType(purchaseOrderDetails.getShipmentType());
		po.setStatus(purchaseOrderDetails.getStatus());
		po.setVendor(purchaseOrderDetails.getVendor());

		PurchaseOrder updatePurchaseOrder = purchaseOrderService.update(po);

		return ResponseEntity.ok(updatePurchaseOrder);
	}

}
