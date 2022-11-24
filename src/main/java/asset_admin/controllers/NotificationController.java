package asset_admin.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asset_admin.daoImpl.BrandDAOImpl;
import asset_admin.entities.Brand;

@RequestMapping(value = "/notifications")
public class NotificationController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listOfNotifications(Model m, BrandDAOImpl BrandDAO) {
		/*
		 * this.BrandDAO = BrandDAO; List<Brand> users = BrandDAO.getAllBrand();
		 * m.addAttribute("listBrand", users);
		 */
		return "notifications/notifications-list";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String assetView(@PathVariable(name = "id") int id, Model m) {
		/*
		 * Brand Brand = BrandDAO.getBrandById(id); m.addAttribute("Brand", Brand);
		 */
		return "notifications/notifications-view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String assetEdit(@PathVariable(name = "id") int id, Model m) {
		/*
		 * Brand Brand = BrandDAO.getBrandById(id); m.addAttribute("Brand", Brand);
		 */
		return "notifications/notifications-editor";
	}

	@RequestMapping(value = "/new-notifications", method = RequestMethod.GET)
	public String addAnAsset(Model m, BrandDAOImpl BrandDAOImpl) {
		/*
		 * this.BrandDAO = BrandDAOImpl;
		 * 
		 * List<Brand> users = BrandDAO.getAllBrand();
		 * 
		 * m.addAttribute("Brand", users);
		 * 
		 * m.addAttribute("msgErr", "");
		 */
		return "users/user-editor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("userList") List<Brand> users, BrandDAOImpl BrandDAOImpl,

			Errors errors, Model m, BindingResult result) {
		/*
		 * m.addAttribute("BrandList", users);
		 * 
		 * this.BrandDAO = BrandDAOImpl;
		 * 
		 * 
		 * if (!result.hasErrors()) {
		 */
		/*
		 * Brand brand = brandDAO.getBrandById(Integer.parseInt(productDTO.getBrand()));
		 * Department dept =
		 * departmentDAO.selectDepartmentById(Integer.parseInt(productDTO.getDepartment(
		 * ))); Brand Brand =
		 * BrandDAO.getBrandById(Integer.parseInt(productDTO.getBrand()));
		 * 
		 * brand.toString(); dept.toString(); Brand.toString();
		 */

		/* Product newProduct = new Product(); */
//			BrandDAO.addNewProduct(product);
		/*
		 * return "users/asset-list"; }
		 * 
		 * m.addAttribute("msgErr", "Data save failed !");
		 */
		return "notifications/notifications-editor";
	}

	@RequestMapping(value = "/search?keywords={keywords}", method = RequestMethod.GET)
	public String search(@PathVariable("keywords") String keywords, Model m) {
		/*
		 * List<Product> productList = BrandDAO.selectProductByName(keywords);
		 * m.addAttribute(productList);
		 */
		return "notifications/search";
	}
}
