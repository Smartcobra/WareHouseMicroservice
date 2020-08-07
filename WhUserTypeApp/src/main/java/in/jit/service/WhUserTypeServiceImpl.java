package in.jit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jit.model.WhUserType;
import in.jit.repo.WhUserTypeRepository;

@Service
public class WhUserTypeServiceImpl implements WhUserTypeService {

	@Autowired
	private WhUserTypeRepository repo;
	
	@Override
	public Integer saveWhUserType(WhUserType user) {
		return repo.save(user).getId();
	}

	@Override
	public void updateWhUserType(WhUserType user) {
		repo.save(user);

	}

	@Override
	public void deleteWhUserType(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public Optional<WhUserType> getOneWhUserType(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<WhUserType> getAllWhUserTypes() {
		return repo.findAll();
	}

	@Override
	public boolean isWhUserTypeExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public boolean isWhUserTypeEmailExist(String mail) {
		return repo.getWhUserTypeCount(mail)>0?true:false;
	}

	@Override
	public Map<Integer, String> getWhUserTypeIdAndCode(String userType) {
		return repo.getWhUseTypeIdCode(userType)
				.stream()
				.collect(Collectors.toMap(
						ob->Integer.valueOf(ob[0].toString()), 
						ob->ob[1].toString()));
	}

}
