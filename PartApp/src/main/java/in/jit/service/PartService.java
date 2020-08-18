package in.jit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import in.jit.model.Part;
import in.jit.model.PartVO;


public interface PartService {

	public Integer savePart(Part p);
	public void updatePart(Part p);
	public void deletePart(Integer id);
	
	public Optional<Part> getOnePart(Integer id);
	
	public List<Part> getAllParts();
	
	public boolean isPartExist(Integer id);
	
	/*
	 * this method AJAX call.
	 */
	public boolean isPartCodeExist(String partCode);
	
	public boolean isPartCodeExistForEdit(String partCode,Integer id);
	
	//public List<UomVO> partUom();
	
	public Map<Integer, String> getUomIdAndModel();
	
	public List<PartVO> getPartCodeAndBaseCost();
	
	
	
	
}
