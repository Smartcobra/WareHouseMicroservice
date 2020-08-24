package in.jit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jit.client.ClientCalls;
import in.jit.model.Grn;
import in.jit.model.GrnPurchaseOrderDTO;
import in.jit.model.PurchaseOrderVO;
import in.jit.repo.GrnRepo;

@Service
public class GrnServiceImpl implements GrnService {

	@Autowired
	private GrnRepo grnRepo;

	@Autowired
	private ClientCalls clientCalls;

	@Override
	public Integer saveGrn(Grn grn) {
		return grnRepo.save(grn).getId();
	}

	@Override
	public void updateGrn(Grn grn) {
		grnRepo.save(grn);

	}

	@Override
	public void deleteGrn(Integer id) {
		grnRepo.deleteById(id);

	}

	@Override
	public Optional<Grn> getOneGrn(Integer id) {
		return grnRepo.findById(id);
	}

	@Override
	public List<Grn> getAllGrns() {
		return grnRepo.findAll();
	}

	@Override
	public boolean isGrnExist(Integer id) {
		return grnRepo.existsById(id);
	}

	@Override
	public Map<Integer, String> getOrderIdAndCode() {
		List<PurchaseOrderVO> OrderCodeId = clientCalls.purchaseOrderCode();
		Map<Integer, String> partIdMap = OrderCodeId.stream().collect(Collectors.toMap(PurchaseOrderVO::getId, PurchaseOrderVO::getOrderCode));
		return partIdMap;

	}

	@Override
	public List<GrnPurchaseOrderDTO> getPurchaseOrderStatus(Boolean status) {
		List<Object[]> purchaseOrderStatus = grnRepo.getPurchaseOrderStatus(status);

		GrnPurchaseOrderDTO grnPurchaseOrderDTO = null;
		List<GrnPurchaseOrderDTO> grnPurchaseOrderDTOList = new ArrayList<GrnPurchaseOrderDTO>();
		int index = 0;

		for (Object[] l : purchaseOrderStatus) {
			grnPurchaseOrderDTO = new GrnPurchaseOrderDTO();
			grnPurchaseOrderDTO.setId(Integer.valueOf(l[0].toString()));
			grnPurchaseOrderDTO.setOrderCode(Integer.valueOf(l[1].toString()));
			grnPurchaseOrderDTOList.add(index, grnPurchaseOrderDTO);
			index++;
			// System.out.println(">>>>>l1>>>"+l[1]);
		}

		for (int i = 0; i < grnPurchaseOrderDTOList.size(); i++) {
			System.out.println(grnPurchaseOrderDTOList.get(i).getId());
			System.out.println(grnPurchaseOrderDTOList.get(i).getOrderCode());
		}

		return grnPurchaseOrderDTOList;
	}

}
