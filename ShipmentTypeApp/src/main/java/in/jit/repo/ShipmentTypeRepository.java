package in.jit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jit.model.ShipmentType;

@Repository
public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {

}
