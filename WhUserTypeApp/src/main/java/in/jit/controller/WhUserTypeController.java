package in.jit.controller;

import java.util.Arrays;
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

import in.jit.model.WhUserType;
import in.jit.service.WhUserTypeService;
import in.jit.util.EmailUtil;
import in.jit.view.WhUserTypeExcelView;
import in.jit.view.WhUserTypePDFView;

@Controller
@RequestMapping("/whusertype")
public class WhUserTypeController {

	@Autowired
	private WhUserTypeService service;

	@Autowired
	private EmailUtil emailUtil;

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("whUserType", new WhUserType());
		return "WhUserTypeRegister";
	}

	@PostMapping("/save")
	public String saveWhUserType(@ModelAttribute WhUserType whUserType, Model model) {
		Integer id = service.saveWhUserType(whUserType);
		String message = "WhUserType '" + id + "' saved successfully";
		/*
		 * if (id != 0) { new Thread(new Runnable() { public void run() { boolean flag =
		 * emailUtil.send(whUserType.getUserMail(), "Welcome", "Hello User:" +
		 * whUserType.getUserCode() + "You are Type" + whUserType.getUserIdType());
		 * System.out.println(flag);// update to db } }).start(); }
		 */
		model.addAttribute("message", message);
		model.addAttribute("whUserType", new WhUserType());
		return "WhUserTypeRegister";

	}

	// dispaly all data

	@GetMapping("/all")
	public String showAll(Model model) {
		List<WhUserType> allWhUserTypes = service.getAllWhUserTypes();
		model.addAttribute("list", allWhUserTypes);
		return "WhUserTypeData";

	}

	// edit
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id, Model model) {

		String page = null;
		Optional<WhUserType> oneWhUserType = service.getOneWhUserType(id);

		if (oneWhUserType.isPresent()) {
			WhUserType whUserType = oneWhUserType.get();
			model.addAttribute("whUserType", whUserType);
			page = "WhUserTypeEdit";
		} else {
			page = "redirect../all";
		}
		return page;

	}

	@PostMapping("/update")
	public String updateWhUserType(@ModelAttribute WhUserType whUserType, Model model) {
		service.updateWhUserType(whUserType);
		String msg = "WhUserType '" + whUserType.getId() + "' Updated!";
		model.addAttribute("message", msg);

		List<WhUserType> list = service.getAllWhUserTypes();
		model.addAttribute("list", list);
		return "WhUserTypeData";

	}

	@GetMapping("/delete/{id}")
	public String removedById(@PathVariable Integer id, Model model) {
		String message = null;
		if (service.isWhUserTypeExist(id)) {
			service.deleteWhUserType(id);
			message = "Wh User with '" + id + "' Deleted!";
		} else {
			message = "Wh User with '" + id + "' Not Exists!";
		}
		model.addAttribute("message", message);
		List<WhUserType> allWhUserTypes = service.getAllWhUserTypes();
		model.addAttribute("list", allWhUserTypes);

		return "WhUserTypeData";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView mv = new ModelAndView();
		mv.setView(new WhUserTypeExcelView());

		List<WhUserType> list = service.getAllWhUserTypes();
		mv.addObject("obs", list);

		return mv;

	}

	@GetMapping("/excel/{id}")
	public ModelAndView exportToExcelWithID(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new WhUserTypeExcelView());

		Optional<WhUserType> oneWhUserType = service.getOneWhUserType(id);
		if (oneWhUserType.isPresent()) {
			mv.addObject("obs", Arrays.asList(oneWhUserType.get()));
		}

		return mv;

	}
	
	
	@GetMapping("/pdf")
	public ModelAndView exportToPDF() {
		ModelAndView mv = new ModelAndView();
		mv.setView(new WhUserTypePDFView());

		List<WhUserType> list = service.getAllWhUserTypes();
		mv.addObject("list", list);

		return mv;

	}

	@GetMapping("/pdf/{id}")
	public ModelAndView exportToPDFWithID(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new WhUserTypePDFView());

		Optional<WhUserType> oneWhUserType = service.getOneWhUserType(id);
		if (oneWhUserType.isPresent()) {
			mv.addObject("list", Arrays.asList(oneWhUserType.get()));
		}

		return mv;

	}

}
