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
import org.springframework.web.bind.annotation.RequestParam;

import asset_admin.daoImpl.BrandDAOImpl;
import asset_admin.entities.Brand;
 
@Controller
@RequestMapping(value = "/brands")

public class BrandController {

	private BrandDAOImpl BrandDAO;
	private List<Brand> brandList;

	public BrandController() {
		BrandDAO = new BrandDAOImpl();
		brandList = BrandDAO.getAllBrand();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listOfBrands(Model m) {
		List<Brand> brands = BrandDAO.getAllBrand();
		m.addAttribute("brandList", brands);
		return "brands/brand-list";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String brandView(@PathVariable(name = "id") int id, Model m) {
		List<Brand> brands = BrandDAO.getAllBrand();
		Brand matchBrand = brands.stream().filter(b -> b.getId() == id).findAny().orElse(null);
		int currentIndex = brands.indexOf(matchBrand);
		int previousIndex = currentIndex == 0 ? 0 : currentIndex - 1;
		int nextIndex = currentIndex == brands.size() - 1 ? 0 : currentIndex + 1;
		
		m.addAttribute("prev", brands.get(previousIndex).getId());
		m.addAttribute("next", brands.get(nextIndex).getId());
		m.addAttribute("brand", matchBrand);
		
		return "brands/brand-view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String brandEditor(@PathVariable(name = "id") int id, Model m) {
		Brand brand = BrandDAO.getBrandById(id);
		m.addAttribute("brand", brand);
		return "brands/brand-editor";
	}

	@RequestMapping(value = "/new-brand", method = RequestMethod.GET)
	public String addAnAsset(Model m, BrandDAOImpl BrandDAOImpl) {
		BrandDAO = BrandDAOImpl;
		Brand brand = new Brand();

		m.addAttribute("brand", brand);
		m.addAttribute("msgErr", "");
		
		return "brands/brand-editor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("brand") Brand brand,
			BrandDAOImpl BrandDAOImpl, Errors errors, Model m, BindingResult result, HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		m.addAttribute("brand", brand);

		if (!result.hasErrors()) {
			Date createdDate = new Date();
			brand.setCreatedDate(createdDate);
			List<Brand> list = BrandDAO.getAllBrand();

			for (Brand b : list) {
				if (b.getName().equals(brand.getName())) {
					m.addAttribute("msgErr", "Brand was exited, Data save Failed !");
					response.sendRedirect("/brands/new-brand");
					return "brands/new-brand";
				}
			}

			BrandDAO.createNewBrand(brand);
			response.sendRedirect(request.getContextPath() + "/brands");
			m.addAttribute("msg", "Data Saved !");
			m.addAttribute("brandList", list);
			
			return "brands/brand-list";
		}

		response.sendRedirect("/brands/new-brand");
		m.addAttribute("msgErr", "Data Save Failed !");
		return "brands/brand-editor";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("brand") Brand brand, Errors errors, Model m, BindingResult result,HttpServletRequest request, HttpServletResponse response) throws IOException {
		m.addAttribute("brand", brand);
		
		if(!result.hasErrors()) {
			Date modifiedDate = new Date();
			brand.setModifiedDate(modifiedDate);
			BrandDAO.updateBrandById(brand);
			
			List<Brand> list = BrandDAO.getAllBrand();
			m.addAttribute("brandList", list);
			m.addAttribute("msg", "Data Updated !");
			response.sendRedirect(request.getContextPath() + "/brands");
			
			return "brands/brand-list";
		}
		m.addAttribute("msgErr", "Data Update Failed !");
		return "brands/brand-editor";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, Model m, HttpServletRequest request, HttpServletResponse response) throws IOException {
		BrandDAO.deleteBrandById(id);
		
		m.addAttribute("brandLists", brandList);
		m.addAttribute("msg", "Brand Deleted !");
		response.sendRedirect(request.getContextPath() + "/brands");
		return "brands/brand-list";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(required=false) String keywords, Model m) {
		List<Brand> brandListKeywords = BrandDAO.getBrandByName(keywords);
		m.addAttribute("brandListKeywords", brandListKeywords);
		m.addAttribute("found", "Found" + (brandListKeywords.size()) + "result");
		return "brands/brand-search";
	}
}
