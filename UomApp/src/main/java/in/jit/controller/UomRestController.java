package in.jit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jit.model.Uom;
import in.jit.model.UomVO;
import in.jit.service.UomService;

@RestController
@RequestMapping("/rest/uom")
public class UomRestController {

	@Autowired
	private UomService service;

	@GetMapping("/all")
	public ResponseEntity<List<UomVO>> getUomToPart() {
		UomVO uomVO =null;
		List<UomVO> listUomVO = new ArrayList<UomVO>();;
		int index = 0;

		List<Uom> allUoms = service.getAllUoms();
		for (Uom uom : allUoms) {
			uomVO= new UomVO();
			uomVO.setId(uom.getId());
			uomVO.setUomModel(uom.getUomModel());
			uomVO.setUomType(uom.getUomType());
			listUomVO.add(index, uomVO);
			index++;

		}
		/*
		 * for (int i = 0; i < listUomVO.size(); i++) {
		 * System.out.println(listUomVO.get(i).getId()); }
		 */
		// listUomVO.forEach(System.out::println);
		return new ResponseEntity<List<UomVO>>(listUomVO, HttpStatus.OK);

	}

}
