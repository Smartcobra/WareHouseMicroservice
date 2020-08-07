package in.jit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jit.model.OrderMethod;
import in.jit.repo.OrderMethodRepository;

@Service
public class OrderMethodServiceImpl implements OrderMethodService {

	@Autowired
	private OrderMethodRepository repo;
	
	@Override
	public Integer saveOrderMethod(OrderMethod om) {
		return repo.save(om).getId();
	}

	@Override
	public void updateOrderMethod(OrderMethod om) {
		repo.save(om);

	}

	@Override
	public void deleteOrderMethod(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public Optional<OrderMethod> getOneOrderMethod(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<OrderMethod> getAllOrderMethod() {
		return repo.findAll();
	}

	@Override
	public boolean isOrderMethodExist(Integer id) {
		return repo.existsById(id);
	}

}
