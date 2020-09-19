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
import in.jit.model.OrderMethod;
import in.jit.service.OrderMethodService;


@RestController
@RequestMapping("/rest/ordermethod")
public class OrderMethodRestController {

	@Autowired
	private OrderMethodService service;

	
	@PostMapping("/insert")
	public ResponseEntity<?> saveOrderMethod(@Valid @RequestBody OrderMethod orderMethod, BindingResult errors) {
		ResponseEntity<?> resp = null;
		try {
			if (errors.hasErrors()) {
				HashMap<String, String> errorsMap = new HashMap<String, String>();
				for (FieldError err : errors.getFieldErrors()) {
					errorsMap.put(err.getField(), err.getDefaultMessage());
				}
				resp = new ResponseEntity<HashMap<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
			} else {
				Integer id = service.saveOrderMethod(orderMethod);
				resp = new ResponseEntity<String>("RECORD HAS BEEN SAVED WITH " + id, HttpStatus.OK);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("RECORD CAN'T BE SAVED WITH " + orderMethod.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<OrderMethod>> getAllOrders() {
		List<OrderMethod> allOrders = service.getAllOrderMethod();
		return new ResponseEntity<List<OrderMethod>>(allOrders, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneShipmentType(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		if (service.isOrderMethodExist(id)) {
			try {
				service.deleteOrderMethod(id);
				resp = new ResponseEntity<String>("RECORD HAS BEEN DELETED WITH " + id, HttpStatus.OK);
			} catch (Exception e) {
				resp = new ResponseEntity<String>("RECORD CAN'T BE DELETED, IT USED ANOTHER OPERATIONS " + id, HttpStatus.BAD_REQUEST);
			}
		} else {
			resp = new ResponseEntity<String>("RECORD WITH " + id + " NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<OrderMethod> updateOrderMethod(@PathVariable Integer id, @Valid @RequestBody OrderMethod om) 
			throws ResourceNotFoundException {
		OrderMethod ordermethod = service.findById(id).
										orElseThrow(() -> new ResourceNotFoundException("OrderMethod not found for this id :: " + id));
		ordermethod.setOrderCode(om.getOrderCode());
		ordermethod.setOrderMode(om.getOrderMode());
		ordermethod.setOrderType(om.getOrderType());
		ordermethod.setDescription(om.getDescription());
		ordermethod.setOrderAcpt(om.getOrderAcpt());
		OrderMethod omu= service.updateUom(ordermethod);
		return ResponseEntity.ok(omu);
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneOrderMethod(@PathVariable Integer id) {
		Optional<OrderMethod> opt = service.getOneOrderMethod(id);
		ResponseEntity<?> resp = null;
		if (opt.isPresent()) {
			resp = new ResponseEntity<OrderMethod>(opt.get(), HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Record Not Found", HttpStatus.NOT_FOUND);
		}
		return resp;

	}

}
