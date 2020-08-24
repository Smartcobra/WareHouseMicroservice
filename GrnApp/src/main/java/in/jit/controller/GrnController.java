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

import in.jit.model.Grn;
import in.jit.service.GrnService;

@Controller
@RequestMapping("/grn")
public class GrnController {

	@Autowired
	private GrnService service;

	private void addDorpDownUi(Model model) {
		model.addAttribute("pos", service.getOrderIdAndCode());
	}

	@GetMapping("/register")
	public String showReg(Model model) {
		model.addAttribute("grn", new Grn());
		addDorpDownUi(model);
		return "GrnReg";
	}

	@PostMapping("/save")
	public String saveGrn(@ModelAttribute Grn grn, Model model) {
		addDorpDownUi(model);
		grn.setPurchaseStatus(false);
		Integer id = service.saveGrn(grn);
		model.addAttribute("message", "GRN SAVED WITH ID:" + id);
		model.addAttribute("grn", new Grn());
		return "GrnReg";
	}

	@GetMapping("/all")
	public String showData(Model model) {
		model.addAttribute("list", service.getAllGrns());
		return "GrnData";
	}

	@GetMapping("/delete/{id}")
	public String deleteGrn(@PathVariable Integer id, Model model) {
		String msg = null;
		// invoke service
		if (service.isGrnExist(id)) {
			service.deleteGrn(id);
			;
			msg = "Grn with '" + id + "'  deleted !";
		} else {

			msg = "Grn With'" + id + "' Not Existed !";
		}
		// display other records
		List<Grn> allGrns = service.getAllGrns();
		model.addAttribute("list", allGrns);

		// send confirmation to UI
		model.addAttribute("message", msg);
		return "GrnData";
	}
	
	@GetMapping("/view/{id}")
	public String showView(@PathVariable Integer id, Model model) {
		String page = null;
	Optional<Grn> oneGrn = service.getOneGrn(id);
		if (oneGrn.isPresent()) {
			Grn order = oneGrn.get();
			model.addAttribute("ob", order);
			page = "GrnDataView";
		} else {
			page = "redirect:../all";
		}
		return page;
	}
	
	// 5.Edit form
		@GetMapping("/edit/{id}")
		public String showEdit(@PathVariable Integer id, Model model) {
			String page = null;
			Optional<Grn> oneGrn = service.getOneGrn(id);
			if (oneGrn.isPresent()) {
				Grn grn = oneGrn.get();			
				model.addAttribute("grn", grn);
				addDorpDownUi(model);
				page = "GrnEdit";
			} else {
				page = "redirect:../all";
			}
			return page;
		}

		// 6.update method
		@PostMapping("/update")
		public String update(@ModelAttribute Grn grn, Model model) {
			String msg = null;
			service.updateGrn(grn);
			msg = "GRn Order '" + grn.getId() + "' updated successfully..";
			model.addAttribute("message", msg);
			// display other records
			List<Grn> list = service.getAllGrns();
			model.addAttribute("list", list);
			return "GrnData";
		}
}
