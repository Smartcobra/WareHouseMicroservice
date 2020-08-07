package in.jit.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jit.model.ShipmentType;
import in.jit.repo.ShipmentTypeRepository;
import in.jit.service.ShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements ShipmentTypeService {

	@Autowired
	ShipmentTypeRepository repo;


	@Override
	@Transactional
	public Integer saveShipmentType(ShipmentType st) {
		Integer id = repo.save(st).getId();
		return id;
	}

	@Override
	@Transactional
	public void updateShipmentType(ShipmentType st) {
		repo.save(st);

	}

	@Override
	@Transactional
	public void deleteShipmentType(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public Optional<ShipmentType> getOneShipmentType(Integer id) {
		Optional<ShipmentType> opt = repo.findById(id);
		return opt;
	}

	@Override
	public List<ShipmentType> getAllShipmentType() {
		List<ShipmentType> list = repo.findAll();
		return list;
	}

	@Override
	public boolean isShipmentTypeExist(Integer id) {
		boolean exist = repo.existsById(id);
		return exist;
	}

}
