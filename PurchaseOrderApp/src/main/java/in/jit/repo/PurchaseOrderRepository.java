package in.jit.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.jit.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	
	@Query("SELECT COUNT(PO.orderCode) FROM PurchaseOrder PO WHERE PO.orderCode=:code")
	public Integer getPurchaseOrderCodeCount(String code);


	 
	@Modifying
	@Query("UPDATE PurchaseOrder SET status=:status WHERE id=:id")
	public void updatePurchaseOrderStatus(String status,Integer id);
	
	/*
	 * @Query("SELECT b.pur_dtl_part_col,b.pur_dtl_qty_col FROM purchase_order_tab a\r\n"
	 * + "INNER JOIN purchase_dtl_tab b ON b.po_id_fk=:poid")
	 */
	/*
	 * @Query("SELECT PDTL.part,PDTL.qty FROM PurchaseDtl PDTL INNER JOIN PDTL.po as PO WHERE PDTL.id=:poid"
	 * ) public List<Object> getPartCodeInvoced(Integer poid);
	 */
	
	@Query("SELECT PO.id,PO.orderCode FROM PurchaseOrder PO WHERE PO.status=:status")
	public List<Object[]> getPurchaseOrderByStatus(String status);
	
}
