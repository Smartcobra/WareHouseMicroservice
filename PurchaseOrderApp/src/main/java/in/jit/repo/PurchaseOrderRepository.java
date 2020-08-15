package in.jit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.jit.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	
	@Query("SELECT COUNT(PO.orderCode) FROM PurchaseOrder PO WHERE PO.orderCode=:code")
	public Integer getPurchaseOrderCodeCount(String code);

}