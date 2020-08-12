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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.jit.model.Part;
import in.jit.service.PartService;

@Controller
@RequestMapping("/part")
public class PartController {

	@Autowired
	private PartService service;

	/*
	 * 
	 */
	private void addDorpDownUi(Model model) {
		model.addAttribute("uoms", service.getUomIdAndModel());
	}

	// show page
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("part", new Part());
		addDorpDownUi(model);
		/// System.out.println(service.partUom());
		return "PartRegister";

	}

	// save
	@PostMapping("/save")
	public String savePart(@ModelAttribute Part part, Model model) {
		Integer id = null;
		String message = null;
		id = service.savePart(part);
		message = "Part with '" + id + "' saved Successfully";
		model.addAttribute("message", message);

		model.addAttribute("part", new Part());
		addDorpDownUi(model);
		return "PartRegister";

	}

	@GetMapping("/all")
	public String fetchAllPart(Model model) {
		List<Part> allParts = service.getAllParts();
		model.addAttribute("list", allParts);
		return "PartData";

	}

	/*
	 * this will delete the part with part id
	 */
	@GetMapping("/delete/{id}")
	public String removePartById(@PathVariable Integer id, Model model) {

		String message = null;

		if (service.isPartExist(id)) {
			service.deletePart(id);
			message = "Part with '" + id + "' Removed";
		} else {
			message = "Part with '" + id + "' Not exists";
		}
		List<Part> allParts = service.getAllParts();
		model.addAttribute("list", allParts);
		model.addAttribute("message", message);

		return "PartData";
	}

	@GetMapping("/edit/{id}")
	public String showEditPartPage(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<Part> onePart = service.getOnePart(id);
		addDorpDownUi(model);
		if (onePart.isPresent()) {
			Part part = onePart.get();
			model.addAttribute("part", part);
			page = "PartEdit";
		} else {
			page = "PartEdit";
		}
		
		return page;
	}
   @PostMapping("/update")
	public String updatePart(@ModelAttribute Part part, Model model) {
		String message = null;
		service.updatePart(part);
		message = "Part with '" + part.getId() + "' saved Successfully";
		model.addAttribute("message", message);

		List<Part> allParts = service.getAllParts();
		model.addAttribute("list", allParts);

		return "PartData";

	}

	@GetMapping("/view/{id}")
	public String showOneView(@PathVariable Integer id, Model model) {

		String page = null;
		Optional<Part> onePart = service.getOnePart(id);
		if (onePart.isPresent()) {
			Part part = onePart.get();
			model.addAttribute("part", part);
			page = "PartView";
		} else {
			page = "redirect../all";
		}

		return page;

	}

	/*
	 * @ResponseBody to indicate that the method is responding a string. otherwise
	 * it will serach for a HTML page.
	 */
	@GetMapping("/validatecode")
	private @ResponseBody String validatePartCodeId(@RequestParam String code, @RequestParam Integer id) {

		String msg = "";
		if (id == 0 && service.isPartCodeExist(code)) {
			System.out.println(">>>>>>>>>validatePartCodeId>>>>>>>>>>>>>");
			msg = "Part Code<b> '" + code + "' is Already exist</b>!";
		} else if (service.isPartCodeExistForEdit(code, id)) {
			msg = "Part Code <b>'" + id + "' is Already exist</b>!";
		}

		return msg;

	}

}
