package in.jit.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import in.jit.model.PartVO;
import in.jit.model.ShipmentVO;
import in.jit.model.WhUserTypeVO;


@Component
public class ClientCalls {

	@Autowired
	RestTemplate restTemplate;

	String partURL = "http://localhost:8100/rest/shipmentcode";
	public static final String WHUSERTYPEIDCODEURL = "http://localhost:8400/rest/whusertypecode";
	public static final String PARTCODEBASECOSTURL = "http://localhost:8500/part/rest/partcodebasecost";
	
	
	public List<ShipmentVO> shipmentTypeIdCode() {
		List<ShipmentVO> listshipmentVO = new ArrayList<ShipmentVO>();
		ResponseEntity<List<ShipmentVO>> response = restTemplate.exchange(partURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ShipmentVO>>() {
				});
		listshipmentVO = response.getBody();
		System.out.println(listshipmentVO);
		return listshipmentVO;

	}
	
	
	
	
	public List<WhUserTypeVO> whUserTypeIdCode() {
		List<WhUserTypeVO> listWhUserTypeVO = new ArrayList<WhUserTypeVO>();
		ResponseEntity<List<WhUserTypeVO>> response = restTemplate.exchange(WHUSERTYPEIDCODEURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<WhUserTypeVO>>() {
				});
		listWhUserTypeVO = response.getBody();
		for (WhUserTypeVO vo : listWhUserTypeVO) {
	           System.out.println(">>>>>ID>>>"+vo.getId());
	           System.out.println(">>>>>CODE>>>"+vo.getUserCode());
			}
		return listWhUserTypeVO;

	}
	

	public List<PartVO> partCodeBaseCost() {
		List<PartVO> listPartVO = new ArrayList<PartVO>();
		ResponseEntity<List<PartVO>> response = restTemplate.exchange(PARTCODEBASECOSTURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PartVO>>() {
				});
		listPartVO = response.getBody();
		for (PartVO vo : listPartVO) {
	           System.out.println(">>>>>ID>>>"+vo.getId());
	           System.out.println(">>>>>CODE>>>"+vo.getPartCode());
	           System.out.println(">>>>>BASE COST>>>"+vo.getBaseCost());
			}
		return listPartVO;

	}
}
