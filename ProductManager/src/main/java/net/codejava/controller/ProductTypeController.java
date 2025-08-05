package net.codejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import net.codejava.entity.ProductType;
import net.codejava.exception.AccessLevelException;
import net.codejava.exception.DatabaseException;
import net.codejava.service.ProductTypeService;

@Controller
public class ProductTypeController {

	@Autowired
	private ProductTypeService service; 
	
	
	@RequestMapping("/newproducttype")
	public String showNewProductTypePage(Model model) {
		ProductType productType = new ProductType();
		model.addAttribute("producttype", productType);
		
		return "new_producttype";
	}
	
	
	@RequestMapping(value = "/saveproducttype", method = RequestMethod.POST)
	public String saveProductType(@ModelAttribute("product") ProductType productType) throws DatabaseException {
		try {
			service.save(productType);
		}
		catch(Exception ex)
		{
			System.out.print("An Error saving productType:"+ex.getMessage());
		}
		return "redirect:/";
	}
	
	@RequestMapping("/editproducttype/{id}")
	public ModelAndView showEditProductTypePage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_productType");
		ProductType productType = service.get(id);
		mav.addObject("productType", productType);
		
		return mav;
	}
	
	@RequestMapping("/deleteproducttype/{id}")
	public String deleteProductType(@PathVariable(name = "id") int id) throws AccessLevelException{
		try {
			service.delete(id);
		}
		catch(Exception ex) {
			throw ex;
		}
		
		return "redirect:/";		
	}
}
