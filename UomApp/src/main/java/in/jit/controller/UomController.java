package in.jit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.jit.model.Uom;
import in.jit.service.UomService;
import in.jit.view.UomExcelView;
import in.jit.view.UomPdfView;

@Controller
@RequestMapping("/uom")
public class UomController {

	@Autowired
	private UomService service;

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("uom", new Uom());
		return "UomRegister";
	}

	@PostMapping("/save")
	public String saveUom(@ModelAttribute Uom uom, Model model) {
		Integer id = null;
		String message = null;

		id = service.saveUom(uom);
		message = "Uom with '" + id + "' saved succesfully";
		model.addAttribute("message", message);
		model.addAttribute("uom", new Uom());

		return "UomRegister";

	}

	@GetMapping("/all")
	public String fetchAll(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {

		Page<Uom> page = service.getAllUoms(pageable);
		model.addAttribute("page", page);

		return "UomData";

	}

	@GetMapping("/delete/{id}")
	public String deleteUom(@PathVariable Integer id, Model model) {
		String message = null;
		if (service.isUomExist(id)) {
			service.deleteUom(id);
			message = "Uom With '" + id + "'Deleted";
		} else {
			message = "Uom With '" + id + "' does not Exists";
		}

		Page<Uom> page = service.getAllUoms(PageRequest.of(0, 10));
		model.addAttribute("page", page);
		model.addAttribute("message", message);
		return "UomData";
	}

	@GetMapping("/edit/{id}")
	public String uomEdit(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<Uom> oneUom = service.getOneUom(id);

		if (oneUom.isPresent()) {
			Uom uom = oneUom.get();
			page = "UomEdit";
			model.addAttribute("uom", uom);
		} else {
			page = "redirect:.../all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateUom(@ModelAttribute Uom uom, Model model) {
		String msg = null;
		service.updateUom(uom);
		msg = "Uom with '" + uom.getId() + "'updated Succesfully ";
		model.addAttribute("message", msg);
		Page<Uom> allUoms = service.getAllUoms(PageRequest.of(0, 10));
		model.addAttribute("page", allUoms);
		return "UomData";
	}

	@GetMapping("/view/{id}")
	public String showOneUom(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<Uom> oneUom = service.getOneUom(id);

		if (oneUom.isPresent()) {
			Uom uom = oneUom.get();
			model.addAttribute("ob", uom);
			page = "UomView";
		} else {
			page = "redirect:../all";
		}
		return page;
	}
	
	//export to Excel
	@GetMapping("/excel")
	public ModelAndView exportExcel() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new UomExcelView());
		
		modelAndView.addObject("list",service.getAllUoms());
		
		return modelAndView;
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportPdf() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new UomPdfView());
		
		modelAndView.addObject("list",service.getAllUoms());
		
		return modelAndView;
	}

}
