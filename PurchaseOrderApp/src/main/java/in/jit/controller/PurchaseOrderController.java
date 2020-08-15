package in.jit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.jit.model.PurchaseOrder;
import in.jit.service.PurchaseOrderService;

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
}
