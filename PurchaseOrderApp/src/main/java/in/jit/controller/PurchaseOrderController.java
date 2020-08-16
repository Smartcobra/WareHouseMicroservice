package in.jit.controller;

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
import org.springframework.web.servlet.ModelAndView;

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
			model.addAttribute("po", po);
			page = "PurchaseDtls";
		} else {
			page ="redirect:../all";
		}

		return page;

	}

}
