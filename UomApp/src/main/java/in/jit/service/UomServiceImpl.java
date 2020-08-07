package in.jit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.jit.model.Uom;
import in.jit.repo.UomRepository;

@Service
public class UomServiceImpl implements UomService {

	@Autowired
	private UomRepository uomrepo;

	
	@Override
	@Transactional
	public Integer saveUom(Uom uom) {
		Integer id = uomrepo.save(uom).getId();
		return id;
	}

    
	@Transactional
	@Override
	public void updateUom(Uom uom) {
		uomrepo.save(uom);

	}

	@Transactional
	@Override
	public void deleteUom(Integer id) {
		uomrepo.deleteById(id);

	}
	
	
	@Override
	public Optional<Uom> getOneUom(Integer id) {
		Optional<Uom> opt = uomrepo.findById(id);
		return opt;
	}

	@Override
	public boolean isUomExist(Integer id) {
		boolean exist = uomrepo.existsById(id);
		return exist;
	}

	@Override
	public List<Uom> getAllUoms() {
	        List<Uom> list = uomrepo.findAll();
		return list;
	}
	

	@Override
	public Map<Integer, String> getUomIdAndModel() {
		return null;
	}

	@Override
	public Page<Uom> getAllUoms(Pageable pageable) {
		return uomrepo.findAll(pageable);
	}

}
