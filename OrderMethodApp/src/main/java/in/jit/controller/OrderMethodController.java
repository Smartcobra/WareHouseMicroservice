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

import in.jit.model.OrderMethod;
import in.jit.service.OrderMethodService;
import in.jit.view.OrderMethodExcelView;
import in.jit.view.OrderMethodPdfView;

@Controller
@RequestMapping("/ordermethod")
public class OrderMethodController {

	@Autowired
	private OrderMethodService service;

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("orderMethod", new OrderMethod());
		return "OrderMethodRegister";
	}

	// save data
	@PostMapping("/save")
	public String save(@ModelAttribute OrderMethod orderMethod, Model model) {
		Integer id = service.saveOrderMethod(orderMethod);
		String message = "Order Method '" + id + "' saved";
		model.addAttribute("message", message);
		// clear the form
		model.addAttribute("orderMethod", new OrderMethod());

		return "OrderMethodRegister";

	}

	@GetMapping("/all")
	public String showAll(Model model) {

		model.addAttribute("list", service.getAllOrderMethod());
		return "OrderMethodData";

	}

	// delete
	@GetMapping("/delete/{id}")
	public String deleteOrderMethodById(@PathVariable Integer id, Model model) {
		String message = null;
		if (service.isOrderMethodExist(id)) {
			service.deleteOrderMethod(id);
			message = "Order Method '" + id + "' Deleted";
		} else {
			message = "Order Method '" + id + "'Not exists";
		}
		model.addAttribute("message", message);
		model.addAttribute("list", service.getAllOrderMethod());
		return "OrderMethodData";
	}

	// update
	@PostMapping("/update")
	public String updateOrderMethod(@ModelAttribute OrderMethod orderMethod, Model model) {
		service.updateOrderMethod(orderMethod);
		String message = "OrderMethod '" + orderMethod.getId() + "' Updated !";
		model.addAttribute(message, "message");
		model.addAttribute("list", service.getAllOrderMethod());

		return "OrderMethodData";
	}

	// edit
	@GetMapping("/edit/{id}")
	public String editOrderMethodById(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<OrderMethod> opt = service.getOneOrderMethod(id);
		if (opt.isPresent()) {
			model.addAttribute("orderMethod", opt.get());
			page = "OrderMethodEdit";
		} else {
			page = "redirect.../all";
		}

		return page;
	}

//show one
	@GetMapping("/view/{id}")
	public String showViewById(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<OrderMethod> opt = service.getOneOrderMethod(id);

		if (opt.isPresent()) {
			OrderMethod orderMethod = opt.get();
			model.addAttribute("ob", orderMethod);
			page = "OrderMethodView";
		} else {
			page = "redirect.../all";
		}
		return page;

	}

	// excel
	@GetMapping("/excel")
	public ModelAndView showExcel() {
		ModelAndView excelmodel = new ModelAndView();
		excelmodel.setView(new OrderMethodExcelView());
		List<OrderMethod> list = service.getAllOrderMethod();
		excelmodel.addObject("list", list);

		return excelmodel;
	}

	// pdf
	@GetMapping("/pdf")
	public ModelAndView showPdf() {
		ModelAndView pdfModel = new ModelAndView();

		List<OrderMethod> list = service.getAllOrderMethod();
		pdfModel.addObject("list", list);

		pdfModel.setView(new OrderMethodPdfView());
		return pdfModel;

	}
}
