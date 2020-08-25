package in.jit.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import in.jit.model.PartDTO;
import in.jit.model.PurchaseDtlDTO;
import in.jit.model.PurchaseOrderVO;


@Component
public class ClientCalls {

	
	@Autowired
	RestTemplate restTemplate;
	
	public static final String PURCHASEORDERURL = "http://localhost:8600/purchaseorder/rest/status";
	public static final String PARTCODEBASECOSTURL = "http://localhost:8500/part/rest/partcodebasecost";
	public static final String PURCHASEORDERDTLSURL = "http://localhost:8600/purchaseorder/rest/purchasedtls";
	
	
	public List<PurchaseOrderVO> purchaseOrderCode() {
		List<PurchaseOrderVO> purchaseOrderVOList = new ArrayList<PurchaseOrderVO>();
		ResponseEntity<List<PurchaseOrderVO>> response = restTemplate.exchange(PURCHASEORDERURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PurchaseOrderVO>>() {
				});
		purchaseOrderVOList = response.getBody();
		System.out.println(purchaseOrderVOList);
		return purchaseOrderVOList;

	}
	
	public List<PartDTO> partCodeBaseCost() {
		List<PartDTO> partDTOList = new ArrayList<PartDTO>();
		ResponseEntity<List<PartDTO>> response = restTemplate.exchange(PARTCODEBASECOSTURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PartDTO>>() {
				});
		partDTOList = response.getBody();
		System.out.println(partDTOList);
		return partDTOList;

	}
	
	public List<PurchaseDtlDTO> allPurchaseOrderDtls() {
		List<PurchaseDtlDTO> purchaseOrderDTOList = new ArrayList<PurchaseDtlDTO>();
		ResponseEntity<List<PurchaseDtlDTO>> response = restTemplate.exchange(PURCHASEORDERDTLSURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PurchaseDtlDTO>>() {
				});
		purchaseOrderDTOList = response.getBody();
		System.out.println(purchaseOrderDTOList);
		return purchaseOrderDTOList;

	}
}
