package in.jit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.jit.model.Uom;

public interface UomService {

	public Integer saveUom(Uom uom);
	
	public void updateUom(Uom uom);
	
	public void deleteUom(Integer id);
	
	public Optional<Uom> getOneUom(Integer id);
	
	public boolean isUomExist(Integer id);
	
	public List<Uom> getAllUoms();
	
	public  Map<Integer,String> getUomIdAndModel();
	
	public Page<Uom> getAllUoms(Pageable pageable);
	
	
	
}
