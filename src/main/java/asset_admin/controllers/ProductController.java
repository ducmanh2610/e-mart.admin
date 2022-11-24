package asset_admin.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import asset_admin.daoImpl.BrandDAOImpl;
import asset_admin.daoImpl.CategoryDAOImpl;
import asset_admin.daoImpl.DepartmentDAOImpl;
import asset_admin.daoImpl.ProductDaoImpl;
import asset_admin.entities.Brand;
import asset_admin.entities.Category;
import asset_admin.entities.Department;
import asset_admin.entities.Product;

import asset_admin.utils.BrandEditor;
import asset_admin.utils.DepartmentEditor;

@Controller
@RequestMapping(value = "/products")

public class ProductController {

	private ProductDaoImpl productDAO;
	
	private List<Brand> brandList;
	private List<Department> departmentList;
	private List<Category> categoryList;

	public ProductController() {
		productDAO = new ProductDaoImpl();
		brandList = new BrandDAOImpl().getAllBrand();
		departmentList = new DepartmentDAOImpl().listAllDepartment();
		categoryList = new CategoryDAOImpl().getAllCategory();
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder, DepartmentDAOImpl departmentDAO, CategoryDAOImpl categoryDAO, BrandDAOImpl brandDAO) {
		binder.registerCustomEditor(Category.class, new DepartmentEditor(departmentDAO));
		binder.registerCustomEditor(Department.class, new DepartmentEditor(departmentDAO));
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandDAO));
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listOfAsset(Model m) {
		List<Product> products = productDAO.getAllProduct();
		m.addAttribute("listProduct", products);
		return "products/asset-list";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String assetView(@PathVariable(name = "id") int id, Model m) {
		List<Product> productsList = productDAO.getAllProduct();
		Product matchProduct = productsList.stream().filter(p -> p.getId() == id).findAny().orElse(null);
		
		int currentIndex = productsList.indexOf(matchProduct);
		int previousIndex = currentIndex == 0 ? 0 : currentIndex - 1;
		int nextIndex = currentIndex == productsList.size() - 1 ? 0 : currentIndex + 1;
		
		m.addAttribute("product", matchProduct);
		m.addAttribute("prev", productsList.get(previousIndex).getId());
		m.addAttribute("next", productsList.get(nextIndex).getId());
		
		return "products/asset-view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String assetEdit(@PathVariable(name = "id") int id, Model m) {
		Product product = productDAO.getProductById(id);
		BrandDAOImpl brandDAO = new BrandDAOImpl();
		List<Brand> brands = brandDAO.getAllBrand();
		m.addAttribute("brandList", brands);
		m.addAttribute("product", product);
		return "products/asset-editor";
	}

	@RequestMapping(value = "/new-product", method = RequestMethod.GET)
	public String addAnAsset(Model m) {
		Product product = new Product();
		product.setStatus(1);

		m.addAttribute("product", product);
		m.addAttribute("brandList", brandList);
		m.addAttribute("categoryList", categoryList);
		m.addAttribute("departmentList", departmentList);

		m.addAttribute("msgErr", "");

		return "products/asset-editor";
	}

	@RequestMapping(value = "/check-in", method = RequestMethod.GET)
	public String checkIn(Model m) {
		List<Product> productsList = productDAO.getAllProduct();
		List<Product> checkInList = new LinkedList<Product>();
		
		for (Product product : productsList) {
			if(product.getStatus() == 0) {
				checkInList.add(product);
			}
		}
		return "products/check-in";
	}

	@RequestMapping(value = "/check-out", method = RequestMethod.GET)
	public String checkOut() {
		return "products/check-out";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid 
			@ModelAttribute("product") Product product, 
			Errors errors,
			Model m,
			BindingResult result) {
		m.addAttribute("product", product);
		m.addAttribute("brandList", brandList);
		m.addAttribute("categoryList", categoryList);
		m.addAttribute("departmentList", departmentList);
		
		product.toString();
		
		if (!result.hasErrors()) {
			productDAO.addNewProduct(product);
			return "products/asset-list";
		}

		m.addAttribute("msgErr", "Data save failed !");
		return "products/asset-editor";
	}

	@RequestMapping(value = "/search?keywords={keywords}", method = RequestMethod.GET)
	public String search(@RequestParam("keywords") String keywords, Model m) {
		List<Product> productList = productDAO.selectProductByName(keywords);
		m.addAttribute(productList);
		return "products/search";
	}
	
	/*
	 * public boolean containsName(final List<Object> list, final String name){
	 * return list.stream().map(o ->
	 * o.getName().equals(name)).findFirst().isPresent(); }
	 */
}
