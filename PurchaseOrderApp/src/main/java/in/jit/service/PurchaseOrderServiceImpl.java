package in.jit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.jit.client.ClientCalls;
import in.jit.model.PartVO;
import in.jit.model.PurchaseDtl;
import in.jit.model.PurchaseOrder;
import in.jit.model.ShipmentVO;
import in.jit.model.WhUserTypeVO;
import in.jit.repo.PurchaseDtlRepository;
import in.jit.repo.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo;

	@Autowired
	private PurchaseDtlRepository purchaseDtlRepo;

	@Autowired
	private ClientCalls clientCalls;

	@Override
	public Integer savePurchaseOrder(PurchaseOrder order) {
		return repo.save(order).getId();
	}

	@Override
	public void updatePurchaseOrder(PurchaseOrder order) {
		repo.save(order);

	}

	@Override
	public void deletePurchaseOrder(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public Optional<PurchaseOrder> getOnePurchaseOrder(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return repo.findAll();
	}

	@Override
	public boolean isPurchaseOrderCodeExists(String orderCode) {
		return repo.getPurchaseOrderCodeCount(orderCode) > 0 ? true : false;
	}

	@Override
	public boolean isPurchaseOrderExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public Map<Integer, String> getShipmentIdAndCode() {
		List<ShipmentVO> shipmentTypeIdCode = clientCalls.shipmentTypeIdCode();
		Map<Integer, String> shipmentVOMap = shipmentTypeIdCode.stream().collect(Collectors.toMap(ShipmentVO::getId, ShipmentVO::getShipmentCode));
		//shipmentVOMap.forEach((k, v) -> System.out.println(k + "::" + v));
		return shipmentVOMap;
	}

	@Override
	public Map<Integer, String> getWhUserTypeIdAndCode() {
		List<WhUserTypeVO> whUserTypeIdCode = clientCalls.whUserTypeIdCode();
		/*
		 * System.out.println("from service IMPL");
		 * whUserTypeIdCode.stream().forEach(System.out::println);
		 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		 */

		/*
		 * for (WhUserTypeVO vo : whUserTypeIdCode) { System.out.println(vo.getId());
		 * System.out.println(vo.getUserCode()); }
		 */
		Map<Integer, String> WhUserTypeVOMap = whUserTypeIdCode.stream().collect(Collectors.toMap(WhUserTypeVO::getId, WhUserTypeVO::getUserCode));
		/*
		 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"); WhUserTypeVOMap.forEach((k, v)
		 * -> System.out.println(k + "::" + v));
		 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		 */
		return WhUserTypeVOMap;
	}

	@Override
	public Map<Integer, String> getPartIdAndCode() {
		List<PartVO> partIdCode = clientCalls.partCodeBaseCost();
		/*
		 * System.out.println("from service IMPL");
		 * partIdCode.stream().forEach(System.out::println);
		 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		 */

		/*
		 * for (PartVO vo : partIdCode) { System.out.println(vo.getId());
		 * System.out.println(vo.getPartCode()); }
		 */
		Map<Integer, String> partIdMap = partIdCode.stream().collect(Collectors.toMap(PartVO::getId, PartVO::getPartCode));
		/*
		 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"); partIdMap.forEach((k, v) ->
		 * System.out.println(k + "::" + v));
		 * System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		 */
		return partIdMap;

	}

	@Override
	public int addPartToPo(PurchaseDtl purchaseDtl) {
		return purchaseDtlRepo.save(purchaseDtl).getId();
	}

	@Override
	public List<PurchaseDtl> getPurchaseDtlWithPoId(Integer purchaseId) {
		return purchaseDtlRepo.getPurchaseDtlWithPoId(purchaseId) ;
	}

	@Override
	public Map<Integer, String> getPartIdAndBaseCost() {
		List<PartVO> partIdBaseCost = clientCalls.partCodeBaseCost();;
		partIdBaseCost.stream().forEach(System.out::println);

		/*
		 * for (PartVO vo : partIdBaseCost) { System.out.println(vo.getId());
		 * System.out.println(vo.getBaseCost()); }
		 */
		Map<Integer, String> partBaseCostMap = partIdBaseCost.stream().collect(Collectors.toMap(PartVO::getId, PartVO::getBaseCost));
		partBaseCostMap.forEach((k, v) -> System.out.println(k + "::" + v));
		return partBaseCostMap;

	}

	@Override
	public void deletePurchaseDtl(Integer dtlId) {
		purchaseDtlRepo.deleteById(dtlId);
		
	}
	
	@Transactional
	@Override
	public void updatePurchaseOrderStatus(String status, Integer id) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+status + ">>>>>>>>>>>>>"+id);
		repo.updatePurchaseOrderStatus(status, id);
		
	}

	@Override
	public Integer getPurchaseDtlWithPoIdCount(Integer purchaseId) {
		return purchaseDtlRepo.getPurchaseDtlWithPoIdCount(purchaseId);
	}

	@Override  
	public List<Object[]> getPartCodeInvoced(Integer poid) {
		System.out.println("getPartCodeInvoced>>**************>>"+poid);
		List<Object[]> partCodeInvoced = purchaseDtlRepo.getPartCodeInvoced(poid);
		
		return partCodeInvoced ;
	}

}
