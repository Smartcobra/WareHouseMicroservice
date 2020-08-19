package in.jit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.jit.model.PurchaseDtl;
import in.jit.model.PurchaseDtlVO;
import in.jit.model.PurchaseOrder;
import in.jit.service.PurchaseOrderService;
import in.jit.view.PurchaseOrderExcelView;
import in.jit.view.PurchaseOrderPdfView;

@Controller
@RequestMapping("/purchaseorder")
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService service;

	private void addDorpDownUi(Model model) {
		model.addAttribute("shipmentcode", service.getShipmentIdAndCode());
		model.addAttribute("whUsercode", service.getWhUserTypeIdAndCode());
	}

	/*
	 * for dtls page
	 */

	private void addDorpDownUiDtls(Model model) {
		model.addAttribute("partcode", service.getPartIdAndCode());
		//// model.addAttribute("partBaseCost", service.getPartIdAndBaseCost());
		//// //////////////////Added
	}

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("purchaseOrder", new PurchaseOrder());
		addDorpDownUi(model);
		return "PurchaseOrderRegister";

	}

	@PostMapping("/save")
	public String purchaseOrderSave(@ModelAttribute PurchaseOrder purchaseOrder, Model model) {

		Integer id = null;
		String message = null;

		id = service.savePurchaseOrder(purchaseOrder);
		message = "Purcahse Order with id '" + id + "' saved succesfully ";
		model.addAttribute("message", message);
		model.addAttribute("purchaseOrder", new PurchaseOrder());
		addDorpDownUi(model);
		return "PurchaseOrderRegister";

	}

	@GetMapping("/all")
	public String getAllPurchaseOrder(Model model) {
		List<PurchaseOrder> list = service.getAllPurchaseOrders();
		model.addAttribute("list", list);
		return "PurchaseOrderData";
	}

	@GetMapping("/delete/{id}")
	public String removePurchaseOrder(@PathVariable Integer id, Model model) {

		String message = null;

		if (service.isPurchaseOrderExist(id)) {
			service.deletePurchaseOrder(id);
			message = "Purchase order with '" + id + "' Deleted";
		} else {
			message = "Purchase order with '" + id + "' Does not Exists";
		}
		service.getAllPurchaseOrders();
		model.addAttribute("message", message);

		return "PurchaseOrderData";
	}

	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<PurchaseOrder> opt = service.getOnePurchaseOrder(id);
		if (opt.isPresent()) {
			PurchaseOrder order = opt.get();
			model.addAttribute("purchaseOrder", order);
			addDorpDownUi(model);
			page = "PurchaseOrderEdit";
		} else {
			page = "redirect:../all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updatePurchaseOrder(@ModelAttribute PurchaseOrder purchaseOrder, Model model) {

		String message = null;
		service.updatePurchaseOrder(purchaseOrder);
		message = "Purchase Order '" + purchaseOrder.getId() + "' updated successfully..";
		model.addAttribute("message", message);
		List<PurchaseOrder> list = service.getAllPurchaseOrders();
		model.addAttribute("list", list);
		return "PurchaseOrderData";

	}

	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();
		m.setView(new PurchaseOrderExcelView());
		m.addObject("obs", service.getAllPurchaseOrders());
		return m;
	}

	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView m = new ModelAndView();
		m.setView(new PurchaseOrderPdfView());
		m.addObject("obs", service.getAllPurchaseOrders());
		return m;
	}

	@GetMapping("/dtls/{id}")
	public String showPurchaseOrderDtls(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<PurchaseOrder> po = service.getOnePurchaseOrder(id);
		if (po.isPresent()) {
			model.addAttribute("po", po.get());
			addDorpDownUiDtls(model);
			// form backing object +linked with PO

			PurchaseDtl purchaseDtl = new PurchaseDtl();
			purchaseDtl.setPo(po.get()); /// linked with po
			model.addAttribute("purchaseOrderdtl", purchaseDtl);

			List<PurchaseDtl> purchaseDtlWithPoId = service.getPurchaseDtlWithPoId(po.get().getId());

			// convert list of PurchaseDtl to PurchaseDtlVO

			PurchaseDtlVO purchaseDtlVO = null;
			int index = 0;

			List<PurchaseDtlVO> listPurchaseDtlVO = new ArrayList<PurchaseDtlVO>();
			for (PurchaseDtl pdtl : purchaseDtlWithPoId) {
				purchaseDtlVO = new PurchaseDtlVO();
				purchaseDtlVO.setId(pdtl.getId());
				purchaseDtlVO.setPartCode(service.getPartIdAndCode().get(Integer.valueOf(pdtl.getPart())));
				purchaseDtlVO.setBaseCost(service.getPartIdAndBaseCost().get(Integer.valueOf(pdtl.getPart())));
				purchaseDtlVO.setQty(pdtl.getQty());
				listPurchaseDtlVO.add(index, purchaseDtlVO);
				index++;

			}

			System.out.println(" from controller***************************************");
			for (PurchaseDtlVO dtl : listPurchaseDtlVO) {
				System.out.println(dtl.getPartCode());
				System.out.println(dtl.getQty());
				System.out.println(dtl.getId());

			}
			System.out.println(" from controller***************************************");

			/// dispalying all added parts
			// model.addAttribute("purchasedtlList",service.getPurchaseDtlWithPoId(po.get().getId()));

			model.addAttribute("purchasedtlList", listPurchaseDtlVO);
			page = "PurchaseDtls";

		} else {
			page = "redirect:../all";
		}

		return page;

	}

	@PostMapping("/addpart")
	public String addPartToPo(@ModelAttribute PurchaseDtl purchaseDtl) {
		service.addPartToPo(purchaseDtl);
		Integer poId = purchaseDtl.getPo().getId();
		service.updatePurchaseOrderStatus("PICKING", poId);
		return "redirect:dtls/" + poId; // POID
	}

	@GetMapping("/removePart")
	public String removePart(@RequestParam Integer dtlId,@RequestParam Integer poId) {
		service.deletePurchaseDtl(dtlId);
		Integer dtlCount = service.getPurchaseDtlWithPoIdCount(poId);

		if (dtlCount == 0) {
			service.updatePurchaseOrderStatus("OPEN", poId);
		}

		return "redirect:dtls/" + poId; // POID
	}
	
	@GetMapping("/confirmOrder/{id}")
	public String placeOrder(@PathVariable Integer id) {
		System.out.println(">>>>>>>>>>>>>>placeOrder>>>>>>>>>>");
		Integer dtlCount = service.getPurchaseDtlWithPoIdCount(id);
		System.out.println(">>>>>>>>>>>>>>placeOrder>>>>>>>>>> dtlCount::"+dtlCount);
		if (dtlCount > 0) {
			service.updatePurchaseOrderStatus("ORDERD", id);
		}
		return "redirect:../dtls/" + id;
	}
}
