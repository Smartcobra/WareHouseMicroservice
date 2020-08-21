package in.jit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.jit.model.PurchaseDtl;
import in.jit.model.PurchaseOrder;

public interface PurchaseOrderService {
	
	public Integer savePurchaseOrder(PurchaseOrder order);
	
	public void updatePurchaseOrder(PurchaseOrder order);
	
	public void deletePurchaseOrder(Integer id);
	
	public Optional<PurchaseOrder> getOnePurchaseOrder(Integer id);
	
	public List<PurchaseOrder> getAllPurchaseOrders();
	
	public boolean isPurchaseOrderExist(Integer id);
	public boolean isPurchaseOrderCodeExists(String orderCode);
	
	public Map<Integer, String> getShipmentIdAndCode();
	
	public Map<Integer, String> getWhUserTypeIdAndCode();
	
	public Map<Integer, String> getPartIdAndCode();
	
	public Map<Integer, String> getPartIdAndBaseCost();

	public int addPartToPo(PurchaseDtl purchaseDtl);
	
	public List<PurchaseDtl> getPurchaseDtlWithPoId(Integer purchaseId);

	public void deletePurchaseDtl(Integer dtlId);
	
	public void updatePurchaseOrderStatus(String status,Integer id);
	
	public Integer getPurchaseDtlWithPoIdCount (Integer purchaseId);
	
	public List<Integer> getPartCodeInvoced(Integer poid);

	
	
	
	

}
