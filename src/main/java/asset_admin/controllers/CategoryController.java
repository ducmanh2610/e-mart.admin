package asset_admin.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asset_admin.daoImpl.BrandDAOImpl;
import asset_admin.daoImpl.CategoryDAOImpl;
import asset_admin.entities.Brand;
import asset_admin.entities.Category;
import asset_admin.entities.Product;

@Controller
@RequestMapping(value = "/categories")

public class CategoryController {

	private CategoryDAOImpl categoryDAO;

	public CategoryController() {
		this.categoryDAO = new CategoryDAOImpl();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listOfCategory(Model m) {
		m.addAttribute("categoriesList", categoryDAO.getAllCategory());

		return "categories/category-list";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String categoryView(@PathVariable(name = "id") int id, Model m) {
		List<Category> categoriesList = categoryDAO.getAllCategory();
		Category matchedCategory = categoriesList.stream().filter(c -> c.getId() == id).findAny().orElse(null);

		int currentIndex = categoriesList.indexOf(matchedCategory);
		int previousIndex = currentIndex == 0 ? 0 : currentIndex - 1;
		int nextIndex = currentIndex == categoriesList.size() - 1 ? 0 : currentIndex + 1;

		m.addAttribute("category", matchedCategory);
		m.addAttribute("prev", categoriesList.get(previousIndex).getId());
		m.addAttribute("next", categoriesList.get(nextIndex).getId());

		return "categories/category-view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String categoryEditor(@PathVariable(name = "id") int id, Model m) {
		Category category = categoryDAO.getCategoryById(id);

		m.addAttribute("category", category);

		return "categories/category-editor";
	}

	@RequestMapping(value = "/new-category", method = RequestMethod.GET)
	public String addAnCategory(Model m, CategoryDAOImpl categoryDAOImpl) {
		Category category = new Category();
		category.setStatus(1);

		m.addAttribute("category", category);

		return "categories/category-editor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("category") Category category,
			Errors errors, Model m, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws IOException {

		m.addAttribute("category", category);

		if (!result.hasErrors()) {
			category.setCreatedDate(new Date());
			categoryDAO.addNewCategory(category);
			
			m.addAttribute("categoriesList", categoryDAO.getAllCategory());
			response.sendRedirect(request.getContextPath() + "/categories");
			return "categories/category-list";
		}

		m.addAttribute("msgErr", "Data save failed !");
		response.sendRedirect(request.getContextPath() + "/categories/new-category");
		return "categories/category-editor";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("category") Category category, Errors errors, Model m, BindingResult result,HttpServletRequest request, HttpServletResponse response) throws IOException {
		m.addAttribute("category", category);
		
		if(!result.hasErrors()) {
			category.setModifiedDate(new Date());
			categoryDAO.updateCategoryById(category);
			
			m.addAttribute("categoriesList", categoryDAO.getAllCategory());
			m.addAttribute("msg", "Data Updated !");
			
			response.sendRedirect(request.getContextPath() + "/categories");
			return "categories/category-list";
		}
		
		m.addAttribute("msgErr", "Data Update Failed !");
		response.sendRedirect(request.getContextPath() + "/categories/edit/" + category.getId());
		return "categories/category-editor";
	}

	@RequestMapping(value = "/search?keywords={keywords}", method = RequestMethod.GET)
	public String search(@PathVariable("keywords") String keywords, Model m) {
		/*
		 * List<Product> productList = categoryDAO.selectProductByName(keywords);
		 * m.addAttribute(productList);
		 */
		return "categories/search";
	}

	/*
	 * public boolean containsName(final List<Object> list, final String name){
	 * return list.stream().map(o ->
	 * o.getName().equals(name)).findFirst().isPresent(); }
	 */
}
