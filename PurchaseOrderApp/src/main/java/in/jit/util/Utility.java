package in.jit.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.jit.client.ClientCalls;
import in.jit.model.PartVO;

@Component
public class Utility {
	
	@Autowired
	private ClientCalls clientCalls;
	
	public Map<String, String> getPartIdAndCode() {
		List<PartVO> partCodeBaseCost = clientCalls.partCodeBaseCost();

		Map<String, String> partIdMap = partCodeBaseCost.stream().collect(Collectors.toMap( PartVO::getPartCode,PartVO::getBaseCost));
		/*
		 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"); partIdMap.forEach((k, v) ->
		 * System.out.println(k + "::" + v));
		 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		 */
		return partIdMap;

	}

}
