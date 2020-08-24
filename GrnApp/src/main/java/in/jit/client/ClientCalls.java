package in.jit.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import in.jit.model.PurchaseOrderVO;


@Component
public class ClientCalls {

	
	@Autowired
	RestTemplate restTemplate;
	
	public static final String PURCHASEORDERURL = "http://localhost:8600/purchaseorder/rest/status";
	
	public List<PurchaseOrderVO> purchaseOrderCode() {
		List<PurchaseOrderVO> purchaseOrderVOList = new ArrayList<PurchaseOrderVO>();
		ResponseEntity<List<PurchaseOrderVO>> response = restTemplate.exchange(PURCHASEORDERURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PurchaseOrderVO>>() {
				});
		purchaseOrderVOList = response.getBody();
		System.out.println(purchaseOrderVOList);
		return purchaseOrderVOList;

	}
	
}
