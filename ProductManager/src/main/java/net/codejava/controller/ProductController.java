package net.codejava.controller;


import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import net.codejava.entity.Product;
import net.codejava.entity.ProductType;
import net.codejava.service.ProductService;
import net.codejava.service.ProductTypeService;

@Controller
public class ProductController {
	
	//private static Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductTypeService pTypeService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		List<ProductType> pTypes = pTypeService.listAll();
		Long productTypeName = 1L;
		for(Product prod : listProducts) {
			Long madetId = prod.getMadein();
			for(ProductType type : pTypes) {
				Long typeId = type.getId();
				if(typeId==madetId) {
					productTypeName = type.getId();
				}
			}
			prod.setMadein(productTypeName);
		}
		model.addAttribute("listProducts", listProducts);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		List<ProductType> productType = pTypeService.listAll();
		model.addAttribute("productType", productType);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product, @RequestParam(value="action", required=true) String action) throws SQLDataException {
		try {
			
			switch(action) {
	        case "save":
	        	service.save(product);
	            break;
	        case "cancel":
	            return "redirect:/";
	        default:
	            // do stuff
	            break;
			}
			//service.save(product);
		}
		catch(Exception ex) {
			System.out.print("Invalid Input: "+ex.getMessage());
			new Throwable("Waxbaa dhacay: ");
			//Logger.error("Invalid Input:",ex.getMessage());
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id){
		ModelAndView mav = new ModelAndView("edit_product");
		try {
			Product product = service.get(id);
			mav.addObject("product", product);
		}
		catch(Exception ex) {
			System.out.println("Exception : "+ex.getMessage());
		}
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
