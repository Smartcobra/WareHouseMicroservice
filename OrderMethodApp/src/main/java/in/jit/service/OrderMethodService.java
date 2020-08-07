package in.jit.service;

import java.util.List;
import java.util.Optional;

import in.jit.model.OrderMethod;

public interface OrderMethodService {

	public Integer saveOrderMethod(OrderMethod om);
	public void updateOrderMethod(OrderMethod om);
	
	public void deleteOrderMethod(Integer id);
	public Optional<OrderMethod> getOneOrderMethod(Integer id);
	
	public List<OrderMethod> getAllOrderMethod();
	public boolean isOrderMethodExist(Integer id);
}
