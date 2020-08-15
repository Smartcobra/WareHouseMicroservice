package in.jit.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
