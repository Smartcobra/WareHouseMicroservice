package in.jit.service;

import java.util.List;
import java.util.Optional;

import in.jit.model.ShipmentType;
import in.jit.model.ShipmentVO;

public interface ShipmentTypeService {

	public Integer saveShipmentType(ShipmentType st);
	
	public void updateShipmentType(ShipmentType st);
	
	public void deleteShipmentType (Integer id);
	
	public List<ShipmentType> getAllShipmentType();
	
	public boolean isShipmentTypeExist(Integer id);
	
	public Optional<ShipmentType> getOneShipmentType(Integer id);
	
	public List<ShipmentVO> getShipmentIdAndCode();

}
