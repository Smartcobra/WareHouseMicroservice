package in.jit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.jit.model.WhUserType;
import in.jit.model.WhUserTypeVO;

public interface WhUserTypeService {
	
	
	public Integer saveWhUserType(WhUserType user);
	public void updateWhUserType(WhUserType user);
	
	public void deleteWhUserType(Integer id);
	public Optional<WhUserType> getOneWhUserType(Integer id);
	
	public List<WhUserType> getAllWhUserTypes();
	public boolean isWhUserTypeExist(Integer id);

	public boolean isWhUserTypeEmailExist(String mail);
	
	public Map<Integer,String> getWhUserTypeIdAndCode(String userType);
	
	public List<WhUserTypeVO> getWhUserTypeIdAndCode();
	

}
