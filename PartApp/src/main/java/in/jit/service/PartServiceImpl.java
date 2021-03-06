package in.jit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.jit.model.Part;
import in.jit.model.PartVO;
import in.jit.model.UomVO;
import in.jit.repo.PartRepository;

@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private PartRepository repo;

	@Autowired
	RestTemplate restTemplate;

	//String partURL = "http://localhost:8300/rest/uom/all";
	
	//public static final String UOMPARTURL = "http://localhost:8300/rest/uom/all";
	public static final String UOMPARTURL = "http://UOM-SERVICE/rest/uom/all";
	
	

	@Override
	public Integer savePart(Part p) {
		return repo.save(p).getId();
	}

	@Override
	public void updatePart(Part p) {
		repo.save(p);

	}

	@Override
	public void deletePart(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public Optional<Part> getOnePart(Integer id) {

		return repo.findById(id);
	}

	@Override
	public List<Part> getAllParts() {
		return repo.findAll();
	}

	@Override
	public boolean isPartExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public boolean isPartCodeExist(String partCode) {
		Integer count = repo.getPartCodeCount(partCode);
		boolean flag = count >= 1 ? true : false;
		return flag;
	}

	@Override
	public boolean isPartCodeExistForEdit(String partCode, Integer id) {
		return false;
	}

	private List<UomVO> partUom() {
		List<UomVO> listuomVO = new ArrayList<UomVO>();
		ResponseEntity<List<UomVO>> response = restTemplate.exchange(UOMPARTURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<UomVO>>() {
				});
		listuomVO = response.getBody();
		System.out.println(listuomVO);
		return listuomVO;

	}

	@Override
	public Map<Integer, String> getUomIdAndModel() {
		// Map<Integer, String> map = new LinkedHashMap<>();
		List<UomVO> list = partUom();
		/*
		 * for (UomVO ob : list) { map.put(ob.getId(), ob.getUomModel()); }
		 */
		///
		Map<Integer, String> map = list.stream().collect(Collectors.toMap(UomVO::getId, UomVO::getUomModel));
		return map;

	}

	@Override
	public List<PartVO> getPartCodeAndBaseCost() {
		PartVO partVO = null;
		List<PartVO> partVoList = new ArrayList<PartVO>();
		int index = 0;

		List<Object[]> listPart = repo.getPartCodeAndBaseCost();
		for (Object[] l : listPart) {
			partVO = new PartVO();
			partVO.setId(Integer.valueOf(l[0].toString()));
			// System.out.println(">>>>>l0>>>>"+l[0]);
			partVO.setPartCode(l[1].toString());
			partVO.setBaseCost(String.valueOf(l[2]));
			partVoList.add(index, partVO);
			index++;
			// System.out.println(">>>>>l1>>>"+l[1]);
		}

		System.out.println("getShipmentIdAndCode--shipmentTypleist");
		for (int i = 0; i < partVoList.size(); i++) {
			System.out.println(partVoList.get(i).getId());
			System.out.println(partVoList.get(i).getPartCode());
			System.out.println(partVoList.get(i).getBaseCost());
		}

		return partVoList;
	}

	@Override
	public Optional<Part> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public Part update(Part newPart) {
		return repo.save(newPart);
	}
	
	
}
