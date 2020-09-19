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
import in.jit.model.ShipmentType;
import in.jit.model.ShipmentVO;
import in.jit.service.ShipmentTypeService;

@RestController
@RequestMapping("/rest")
public class ShipmentTypeRestController {

	@Autowired
	ShipmentTypeService service;

	@GetMapping("/shipmentcode")
	public ResponseEntity<List<ShipmentVO>> getShipmentCode() {
		
		List<ShipmentVO> shipmentIdAndCode = service.getShipmentIdAndCode();
		/*
		 * for (ShipmentType shipment : shipmentIdAndCode) { shipmentVO = new
		 * ShipmentVO(); shipmentVO.setId(shipment.getId());
		 * shipmentVO.setShipmentCode(shipment.getShipmentCode());
		 * listShipmentTypeVO.add(index, shipmentVO); index++; }
		 */

		/*
		 * for (int i = 0; i < listShipmentTypeVO.size(); i++) {
		 * System.out.println(listShipmentTypeVO.get(i).getId()); }
		 */
		// listUomVO.forEach(System.out::println);
		return new ResponseEntity<List<ShipmentVO>>(shipmentIdAndCode, HttpStatus.OK);
	}
	
	@GetMapping("/allshipmenttype")
	public ResponseEntity<List<ShipmentType>> getAllShipmentType() {
		List<ShipmentType> list = service.getAllShipmentType();
		return new ResponseEntity<List<ShipmentType>>(list, HttpStatus.OK);

	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneShipmentType(@PathVariable Integer id) {
		Optional<ShipmentType> opt = service.getOneShipmentType(id);
		ResponseEntity<?> resp = null;
		if (opt.isPresent()) {
			resp = new ResponseEntity<ShipmentType>(opt.get(), HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Record Not Found", HttpStatus.NOT_FOUND);
		}
		return resp;

	}

	// 3.Delete one record from DB
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneShipmentType(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		if (service.isShipmentTypeExist(id)) {
			try {
				service.deleteShipmentType(id);;
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
	public ResponseEntity<?> saveShipmentType(@Valid @RequestBody ShipmentType shipment, BindingResult errors) {
		ResponseEntity<?> resp = null;
		try {
			if (errors.hasErrors()) {
				HashMap<String, String> errorsMap = new HashMap<String, String>();
				for (FieldError err : errors.getFieldErrors()) {
					errorsMap.put(err.getField(), err.getDefaultMessage());
				}
				resp = new ResponseEntity<HashMap<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
			} else {
				Integer id = service.saveShipmentType(shipment);
				resp = new ResponseEntity<String>("RECORD HAS BEEN SAVED WITH " + id, HttpStatus.OK);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("RECORD CAN'T BE SAVED WITH " + shipment.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
 /*
	@PutMapping("/update")
	public ResponseEntity<String> updateShipmentType(@RequestBody ShipmentType shipment) {
		ResponseEntity<String> resp = null;
		if (shipment.getId() == null || !service.isShipmentTypeExist(shipment.getId())) {
			resp = new ResponseEntity<String>("RECORD NOT EXIST IN DB", HttpStatus.BAD_REQUEST);
		} else {
			service.updateShipmentType(shipment);;
			resp = new ResponseEntity<String>("UOM WITH '" + shipment.getId() + "' UPDATED", HttpStatus.OK);
		}
		return resp;
	}
	
	*/
	@PutMapping("/update/{id}")
	public ResponseEntity<ShipmentType> updateUom(@PathVariable Integer id, 
			@Valid @RequestBody ShipmentType shipmentTypeDetails) 
			throws ResourceNotFoundException {
		
		ShipmentType shipmentType = service.findById(id).
									orElseThrow(() -> new ResourceNotFoundException("Shipmenttype not found for this id :: " + id));
		
		shipmentType.setShipmentCode(shipmentTypeDetails.getShipmentCode());
		shipmentType.setShipmentGrade(shipmentTypeDetails.getShipmentGrade());
		shipmentType.setEnableShipment(shipmentTypeDetails.getEnableShipment());
		shipmentType.setShipmentMode(shipmentTypeDetails.getShipmentMode());
		shipmentType.setDescription(shipmentTypeDetails.getDescription());
		ShipmentType updateshipmentType = service.update(shipmentType);
		
		
		return ResponseEntity.ok(updateshipmentType);
	}

}
