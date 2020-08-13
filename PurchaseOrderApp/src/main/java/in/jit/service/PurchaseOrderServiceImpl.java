package in.jit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jit.model.PurchaseOrder;
import in.jit.repo.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	
	@Autowired
	PurchaseOrderRepository repo;
	
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
		return repo.getPurchaseOrderCodeCount(orderCode) > 0 ? true:false;
	}

	@Override
	public boolean isPurchaseOrderExist(Integer id) {
		return repo.existsById(id);
	}

}
