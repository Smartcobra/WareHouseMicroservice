package in.jit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.jit.model.Grn;
import in.jit.model.GrnPurchaseOrderDTO;

public interface GrnService {
	public Integer saveGrn(Grn g);

	public void updateGrn(Grn g);

	public void deleteGrn(Integer id);

	public Optional<Grn> getOneGrn(Integer id);

	public List<Grn> getAllGrns();

	public boolean isGrnExist(Integer id);
	
	public Map<Integer, String> getOrderIdAndCode();
	
	public List<GrnPurchaseOrderDTO> getPurchaseOrderStatus(Boolean  status);

}
