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
import org.springframework.web.bind.annotation.RestController;

import in.jit.exception.ResourceNotFoundException;
import in.jit.model.PurchaseDtl;
import in.jit.model.PurchaseDtlDTO;
import in.jit.model.PurchaseDtlVO;
import in.jit.model.PurchaseOrder;
import in.jit.model.PurchaseOrderDTO;
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
	public ResponseEntity<List<PurchaseOrderDTO>> getAllPurchaseOrder() {
		List<PurchaseOrderDTO> list = new ArrayList<PurchaseOrderDTO>();
		PurchaseOrderDTO pdto=null;
		
		
		for (PurchaseOrder po: purchaseOrderService.getAllPurchaseOrders()) {
			pdto= new PurchaseOrderDTO();
			pdto.setId(po.getId());
			pdto.setOrderCode(po.getOrderCode());
			pdto.setQualityCheck(po.getQualityCheck());
			pdto.setReferenceNumber(po.getReferenceNumber());
			pdto.setDescription(po.getDescription());
			pdto.setStatus(po.getStatus());
			pdto.setVendor(po.getVendor());
			pdto.setShipmentType(po.getShipmentType());
			list.add(pdto);
			
		}
		return new ResponseEntity<List<PurchaseOrderDTO>>(list, HttpStatus.OK);

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
	public ResponseEntity<?> savePurchaseorder(@Valid @RequestBody PurchaseOrder po, BindingResult errors) {
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
	
	
	@GetMapping("/dtls/{id}")
	public ResponseEntity<List<PurchaseDtlVO>> showPurchaseOrderDtls(@PathVariable Integer id) {
		Optional<PurchaseOrder> po = purchaseOrderService.getOnePurchaseOrder(id);
		

			PurchaseDtl purchaseDtl = new PurchaseDtl();
			purchaseDtl.setPo(po.get()); /// linked with po
			

			List<PurchaseDtl> purchaseDtlWithPoId = purchaseOrderService.getPurchaseDtlWithPoId(po.get().getId());

			// convert list of PurchaseDtl to PurchaseDtlVO

			PurchaseDtlVO purchaseDtlVO = null;
			int index = 0;

			List<PurchaseDtlVO> listPurchaseDtlVO = new ArrayList<PurchaseDtlVO>();
			for (PurchaseDtl pdtl : purchaseDtlWithPoId) {
				purchaseDtlVO = new PurchaseDtlVO();
				purchaseDtlVO.setId(pdtl.getId());
				purchaseDtlVO.setPartCode(purchaseOrderService.getPartIdAndCode().get(Integer.valueOf(pdtl.getPart())));
				purchaseDtlVO.setBaseCost(purchaseOrderService.getPartIdAndBaseCost().get(Integer.valueOf(pdtl.getPart())));
				purchaseDtlVO.setQty(pdtl.getQty());
				listPurchaseDtlVO.add(index, purchaseDtlVO);
				index++;

			}

			return ResponseEntity.ok(listPurchaseDtlVO);

	}
	
	@GetMapping("/po/{id}")
	public ResponseEntity<PurchaseOrderDTO>  getPurchaseOrder(@PathVariable Integer id) {
		Optional<PurchaseOrder> po = purchaseOrderService.getOnePurchaseOrder(id);
		PurchaseOrderDTO pdto= new PurchaseOrderDTO();
		pdto.setId(po.get().getId());
		pdto.setDescription(po.get().getDescription());
		pdto.setOrderCode(po.get().getOrderCode());
		pdto.setQualityCheck(po.get().getQualityCheck());
		pdto.setReferenceNumber(po.get().getReferenceNumber());
		pdto.setShipmentType(po.get().getShipmentType());
		pdto.setStatus(po.get().getStatus());
		pdto.setVendor(po.get().getVendor());
		return ResponseEntity.ok(pdto);

	}
	
	@PostMapping("/addpart")
	public ResponseEntity<String> addPartToPo(@RequestBody PurchaseDtl purchaseDtl) {
		purchaseOrderService.addPartToPo(purchaseDtl);
		Integer poId = purchaseDtl.getPo().getId();
		purchaseOrderService.updatePurchaseOrderStatus("PICKING", poId);
		return ResponseEntity.ok("Added");
	}
	
	@DeleteMapping("pdtl/delete/{id}")
	public ResponseEntity<String> deleteOneUom(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		if (purchaseOrderService.isPurchaseOrderDtlsExist(id)) {
			try {
				purchaseOrderService.deletePurchaseDtl(id);
				resp = new ResponseEntity<String>("RECORD HAS BEEN DELETED WITH " + id, HttpStatus.OK);
			} catch (Exception e) {
				resp = new ResponseEntity<String>("RECORD CAN'T BE DELETED, IT USED ANOTHER OPERATIONS " + id, HttpStatus.BAD_REQUEST);
			}
		} else {
			resp = new ResponseEntity<String>("RECORD WITH " + id + " NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return resp;
	}

	

	@GetMapping("/confirmOrder/{id}")
	public ResponseEntity<String> placeOrder(@PathVariable Integer id) {
		System.out.println(">>>>>>>>>>>>>>placeOrder>>>>>>>>>>");
		Integer dtlCount = purchaseOrderService.getPurchaseDtlWithPoIdCount(id);
		System.out.println(">>>>>>>>>>>>>>placeOrder>>>>>>>>>> dtlCount::"+dtlCount);
		if (dtlCount > 0) {
			purchaseOrderService.updatePurchaseOrderStatus("ORDERED", id);
		}
		return ResponseEntity.ok("confrimed");
	}
	
	@GetMapping("/invoiceOrder/{id}")
	public ResponseEntity<String> invoiceOrder(@PathVariable Integer id)
	{
		System.out.println(">>>>>>>PurchaseOrderRestController.invoiceOrder()");
		purchaseOrderService.updatePurchaseOrderStatus("INVOICED",id);
		return ResponseEntity.ok("invoiceOrder");
	}

}
