package in.jit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.jit.model.PurchaseDtl;


public interface PurchaseDtlRepository extends JpaRepository<PurchaseDtl, Integer> {

	@Query("SELECT PDTL FROM PurchaseDtl PDTL INNER JOIN PDTL.po as PO WHERE PO.id=:purchaseId")
	public List<PurchaseDtl> getPurchaseDtlWithPoId(Integer purchaseId);
}
