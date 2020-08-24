package in.jit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.jit.model.Grn;


public interface GrnRepo extends JpaRepository<Grn, Integer> {
	
	
	@Query("SELECT GN.id,GN.orderCode FROM Grn GN WHERE GN.purchaseStatus=:status")
	public List<Object[]> getPurchaseOrderStatus(Boolean  status);
	
	

}
