package in.jit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.jit.model.ShipmentType;


@Repository
public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {

	@Query("SELECT ST.id,ST.shipmentCode FROM ShipmentType ST WHERE ST.enableShipment='Yes'")
	public List<Object[]> getAllShipmentCodeEnable();
}
