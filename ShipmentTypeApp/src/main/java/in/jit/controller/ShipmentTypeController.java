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

import in.jit.model.ShipmentType;
import in.jit.service.ShipmentTypeService;
import in.jit.view.ShipmentTypeExcelView;
import in.jit.view.ShipmentTypePDFView;

@Controller
@RequestMapping("/shipmenttype")
public class ShipmentTypeController {

	@Autowired
	ShipmentTypeService shipmentTypeService;

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute ShipmentType shipmentType, Model model) {

		Integer id = shipmentTypeService.saveShipmentType(shipmentType);
		String message = "Shipment Type with '" + id + "'saved succesfully";
		model.addAttribute("message", message);
		return "ShipmentTypeRegister";

	}

	@GetMapping("/all")
	public String fetchAll(Model model) {
		List<ShipmentType> allShipmentType = shipmentTypeService.getAllShipmentType();
		model.addAttribute("list", allShipmentType);
		return "ShipmentTypeData";
	}

	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable Integer id, Model model) {
		String message = null;
		if (shipmentTypeService.isShipmentTypeExist(id)) {
			shipmentTypeService.deleteShipmentType(id);
			message = "Shipment Type with '" + id + "'deleted";
		} else {
			message = "Shipment Type with '" + id + "'Does Not Exists";
		}
		model.addAttribute("message", message);
		List<ShipmentType> allShipmentType = shipmentTypeService.getAllShipmentType();
		model.addAttribute("list", allShipmentType);
		return "ShipmentTypeData";
	}

	@GetMapping("edit/{id}")
	public String showEdit(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<ShipmentType> opt = shipmentTypeService.getOneShipmentType(id);

		if (opt.isPresent()) {
			ShipmentType st = opt.get();
			model.addAttribute("shipmentType", st);
			page = "ShipmentTypeEdit";
		} else {
			page = "redirect:../all";
		}

		return page;
	}

	@PostMapping("/update")
	public String update(@ModelAttribute ShipmentType shipmentType, Model model) {

		shipmentTypeService.updateShipmentType(shipmentType);
		String mesg = "Shipment with '" + shipmentType.getId() + "' update successful";
		model.addAttribute("message", mesg);

		List<ShipmentType> list = shipmentTypeService.getAllShipmentType();
		model.addAttribute("list", list);
		return "ShipmentTypeData";
	}

	// export data to excel

	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView mv = new ModelAndView();
		mv.setView(new ShipmentTypeExcelView());

		List<ShipmentType> list = shipmentTypeService.getAllShipmentType();
		mv.addObject("obs", list);

		return mv;

	}

	@GetMapping("/excel/{id}")
	public ModelAndView exportToExcelWithID(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new ShipmentTypeExcelView());

		Optional<ShipmentType> oneShipmentType = shipmentTypeService.getOneShipmentType(id);
		if (oneShipmentType.isPresent()) {
			mv.addObject("obs", Arrays.asList(oneShipmentType.get()));
		}

		return mv;

	}
	
	
	@GetMapping("/pdf")
	public ModelAndView exportToPDF() {
		ModelAndView mv = new ModelAndView();
		mv.setView(new ShipmentTypePDFView());

		List<ShipmentType> list = shipmentTypeService.getAllShipmentType();
		mv.addObject("list", list);

		return mv;

	}

	@GetMapping("/pdf/{id}")
	public ModelAndView exportToPDFWithID(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new ShipmentTypePDFView());

		Optional<ShipmentType> oneShipmentType = shipmentTypeService.getOneShipmentType(id);
		if (oneShipmentType.isPresent()) {
			mv.addObject("list", Arrays.asList(oneShipmentType.get()));
		}

		return mv;

	}
}
