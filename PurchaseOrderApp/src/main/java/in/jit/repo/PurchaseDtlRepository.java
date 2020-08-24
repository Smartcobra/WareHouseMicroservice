package in.jit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.jit.model.PurchaseDtl;


public interface PurchaseDtlRepository extends JpaRepository<PurchaseDtl, Integer> {

	@Query("SELECT PDTL FROM PurchaseDtl PDTL INNER JOIN PDTL.po as PO WHERE PO.id=:purchaseId")
	public List<PurchaseDtl> getPurchaseDtlWithPoId(Integer purchaseId);
	
	@Query("SELECT COUNT(PDTL) FROM PurchaseDtl PDTL INNER JOIN PDTL.po as PO WHERE PO.id=:purchaseId")
	public Integer getPurchaseDtlWithPoIdCount (Integer purchaseId);
	
	//@Query("SELECT PDTL.part,PDTL.qty FROM PurchaseDtl PDTL INNER JOIN PDTL.po as PO WHERE PO.id=:poid")
	@Query(value = "SELECT  b.pur_dtl_part_col,b.pur_dtl_qty_col FROM purchaseorder.purchase_order_tab a\r\n" + 
			"INNER JOIN purchaseorder.purchase_dtl_tab b ON a.porder_id_col=:poid;",nativeQuery = true)
	public List<Object[]> getPartCodeInvoced(Integer poid);
	
	
	
	
	
	
}
