package in.jit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.jit.model.ShipmentType;
import in.jit.model.ShipmentVO;
import in.jit.repo.ShipmentTypeRepository;
import in.jit.service.ShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements ShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository repo;

	
	
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

	@Override
	public List<ShipmentVO> getShipmentIdAndCode() {
		/// List<Object[]> list = repo.getAllShipmentCodeEnable();
		// Map<Integer, String> map = list.stream()
		/// .collect(Collectors.toMap(ob -> Integer.valueOf(ob[0].toString()), ob ->
		/// ob[1].toString()));
		ShipmentVO shipmentVO = null;
		List<ShipmentVO> shipmentList = new ArrayList<ShipmentVO>();
		int index = 0;

		List<Object[]> listShipment = repo.getAllShipmentCodeEnable();
		for (Object[] l : listShipment) {
			shipmentVO = new ShipmentVO();
			shipmentVO.setId(Integer.valueOf(l[0].toString()));
			// System.out.println(">>>>>l0>>>>"+l[0]);
			shipmentVO.setShipmentCode(l[1].toString());
			shipmentList.add(index, shipmentVO);
			index++;
			// System.out.println(">>>>>l1>>>"+l[1]);
		}

		System.out.println("getShipmentIdAndCode--shipmentTypleist");
		for (int i = 0; i < shipmentList.size(); i++) {
			System.out.println(shipmentList.get(i).getId());
			System.out.println(shipmentList.get(i).getShipmentCode());
		}

		return shipmentList;
	}

	@Override
	public Optional<ShipmentType> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public ShipmentType update(ShipmentType sh) {
		return repo.save(sh);
	}

}
