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

import asset_admin.daoImpl.UserRolesDAOImpl;
import asset_admin.entities.UserRoles;
 
@Controller
@RequestMapping(value = "/roles")

public class UserRolesController {

	private UserRolesDAOImpl userRolesDAO;
	private List<UserRoles> rolesList;

	public UserRolesController() {
		this.userRolesDAO = new UserRolesDAOImpl();
		this.rolesList = userRolesDAO.getRolesList();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listOfRoles(Model m) {
		m.addAttribute("rolesList", rolesList);
		return "roles/roles-list";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String rolesView(@PathVariable(name = "id") int id, Model m) {
		UserRoles matchRoles = rolesList.stream().filter(r -> r.getId() == id).findAny().orElse(null);
		int currentIndex = rolesList.indexOf(matchRoles);
		int previousIndex = currentIndex == 0 ? 0 : currentIndex - 1;
		int nextIndex = currentIndex == rolesList.size() - 1 ? 0 : currentIndex + 1;
		
		m.addAttribute("prev", rolesList.get(previousIndex).getId());
		m.addAttribute("next", rolesList.get(nextIndex).getId());
		m.addAttribute("role", matchRoles);
		return "roles/roles-view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String rolesEditor(@PathVariable(name = "id") int id, Model m) {
		UserRoles role = userRolesDAO.getRolesById(id);
		m.addAttribute("role", role);
		return "roles/roles-editor";
	}

	@RequestMapping(value = "/new-role", method = RequestMethod.GET)
	public String addNewRoles(Model m) {
		UserRoles role = new UserRoles();

		m.addAttribute("role", role);
		m.addAttribute("msgErr", "");
		
		return "roles/roles-editor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("roles") UserRoles role,
			Errors errors, Model m, BindingResult result, HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		m.addAttribute("role", role);

		if (!result.hasErrors()) {
			Date createdDate = new Date();
			role.setCreatedDate(createdDate);
			UserRoles matchRoles = rolesList.stream().filter(u -> u.getName().equals(role.getName())).findAny().orElse(null);
			
			if (matchRoles != null) {
				m.addAttribute("msgErr", "Roles was exited, Data save Failed !");
				response.sendRedirect(request.getContextPath() + "/new-user");
				return "roles/roles-editor";
			}

			userRolesDAO.createNewRoles(role);
			response.sendRedirect(request.getContextPath() + "/roles");
			m.addAttribute("msg", "Data Saved !");
			m.addAttribute("rolesList", rolesList);
			
			return "roles/roles-list";
		}

		response.sendRedirect("/roles/new-roles");
		m.addAttribute("msgErr", "Data Save Failed !");
		return "roles/roles-editor";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("role") UserRoles role, Errors errors, Model m, BindingResult result,HttpServletRequest request, HttpServletResponse response) throws IOException {
		m.addAttribute("role", role);
		
		if(!result.hasErrors()) {
			Date modifiedDate = new Date();
			role.setModifiedDate(modifiedDate);
			
			userRolesDAO.updateUserRole(role);
			
			m.addAttribute("rolesList", rolesList);
			m.addAttribute("msg", "Data Updated !");
			response.sendRedirect(request.getContextPath() + "/roles");
			
			return "roles/roles-list";
		}
		
		m.addAttribute("msgErr", "Data Update Failed !");
		return "roles/roles-editor";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(required=false) String keywords, Model m) {
		List<UserRoles> rolesListKeywords = userRolesDAO.getRolesByName(keywords);
		m.addAttribute("rolesListKeywords", rolesListKeywords);
		
		m.addAttribute("found", "Found " + (rolesListKeywords.size()) + " result");
		return "roles/roles-search";
	}
}
