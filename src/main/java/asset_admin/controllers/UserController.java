package asset_admin.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
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

import asset_admin.daoImpl.DepartmentDAOImpl;
import asset_admin.daoImpl.UserDAOImpl;
import asset_admin.daoImpl.UserRolesDAOImpl;
import asset_admin.entities.Department;
import asset_admin.entities.User;
import asset_admin.entities.UserRoles;
import asset_admin.utils.DepartmentEditor;
import asset_admin.utils.UserRolesEditor;

@PreAuthorize(value = "hasRole('ADMIN')")
@Controller
@RequestMapping(value = "/users")

public class UserController {

	private UserDAOImpl userDAO;
	
	private List<UserRoles> rolesList;
	private List<Department> departmentList;

	public UserController() {
		userDAO = new UserDAOImpl();

		rolesList = new UserRolesDAOImpl().getRolesList();

		rolesList.remove(rolesList.stream().filter(r -> r.getName().equals("admin")).findFirst().orElse(null));

		departmentList = new DepartmentDAOImpl().listAllDepartment();
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UserRoles.class, new UserRolesEditor(new UserRolesDAOImpl()));
		binder.registerCustomEditor(Department.class, new DepartmentEditor(new DepartmentDAOImpl()));
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listOfUsers(Model m) {
		List<User> userList = userDAO.listAllUser();
		m.addAttribute("mainSlug", "user");
		m.addAttribute("userList", userList);
		return "users/user-list";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String UserView(@PathVariable(name = "id") int id, Model m) {
		m.addAttribute("mainSlug", "user");
		List<User> userList = userDAO.listAllUser();

		User matchedUser = userList.stream().filter(b -> b.getId() == id).findAny().orElse(null);
		int currentIndex = userList.indexOf(matchedUser);
		int previousIndex = currentIndex == 0 ? 0 : currentIndex - 1;
		int nextIndex = currentIndex == userList.size() - 1 ? 0 : currentIndex + 1;

		m.addAttribute("prev", userList.get(previousIndex).getId());
		m.addAttribute("next", userList.get(nextIndex).getId());
		m.addAttribute("user", matchedUser);
		return "users/user-view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String UserEditor(@PathVariable(name = "id") int id, Model m) {
		m.addAttribute("mainSlug", "user");
		User matchedUser = userDAO.listAllUser().stream().filter(b -> b.getId() == id).findAny().orElse(null);

		UserRoles role = rolesList.stream().filter(r -> r.getId() == matchedUser.getRoles().getId()).findAny()
				.orElse(null);

		matchedUser.setRoles(role);

		m.addAttribute("user", matchedUser);
		m.addAttribute("rolesList", rolesList);
		m.addAttribute("departmentList", departmentList);

		return "users/user-editor";
	}

	@RequestMapping(value = "/new-user", method = RequestMethod.GET)
	public String addAnUser(Model m) {
		m.addAttribute("mainSlug", "user");
		User user = new User();

		user.setDepartment(departmentList.get(0));
		user.setStatus(1);
		user.setAccountNonLocked(1);

		m.addAttribute("user", user);
		m.addAttribute("departmentList", departmentList);
		m.addAttribute("rolesList", rolesList);
		m.addAttribute("msgErr", "");

		return "users/user-editor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("user") User user, Errors errors, Model m, BindingResult result,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		m.addAttribute("user", user);
		m.addAttribute("mainSlug", "user");
		
		if (!result.hasErrors()) {
			Date createdDate = new Date();

			user.setCreatedDate(createdDate);
			user.setAccountNonLocked(1);

			List<User> list = userDAO.listAllUser();
			User duplicateUsername = list.stream().filter(u -> u.getUsername().equals(user.getUsername())).findAny()
					.orElse(null);

			User duplicateEmail = list.stream().filter(u -> u.getEmail().equals(user.getEmail())).findAny()
					.orElse(null);

			if (duplicateUsername != null) {
				m.addAttribute("msgErr", "Username was exited, Data save Failed !");
				m.addAttribute("departmentList", departmentList);
				m.addAttribute("rolesList", rolesList);

				return "users/user-editor";
			}
			
			if (duplicateEmail != null) {
				m.addAttribute("msgErr", "Email was exited, Data save Failed !");
				m.addAttribute("departmentList", departmentList);
				m.addAttribute("rolesList", rolesList);

				return "users/user-editor";
			}

			userDAO.addNewUser(user);

			m.addAttribute("userList", userDAO.listAllUser());

			response.sendRedirect(request.getContextPath() + "/users");
			m.addAttribute("msg", "Data Saved !");
			return "users/user-list";
		}

		m.addAttribute("msgErr", "Data Save Failed !");
		m.addAttribute("departmentList", departmentList);
		m.addAttribute("rolesList", rolesList);
		return "users/user-editor";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user, Errors errors, Model m, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		m.addAttribute("user", user);
		m.addAttribute("mainSlug", "user");
		user.setAccountNonLocked(1);

		if (!result.hasErrors()) {
			Date modifiedDate = new Date();
			user.setModifiedDate(modifiedDate);

			userDAO.updateUser(user);

			List<User> userList = userDAO.listAllUser();
			m.addAttribute("userList", userList);

			response.sendRedirect(request.getContextPath() + "/users");
			m.addAttribute("msg", "Data Updated !");
			return "users/user-list";
		}

		m.addAttribute("msgErr", "Data Update Failed !");
		m.addAttribute("departmentList", departmentList);
		m.addAttribute("rolesList", rolesList);
		return "users/user-editor";
	}

	@RequestMapping(value = "/unlocked/{id}", method = RequestMethod.GET)
	public String unlock(@PathVariable(value = "id", required = true) int id, Model m, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		m.addAttribute("mainSlug", "user");
		User unlockUser = userDAO.selectUserById(id);
		unlockUser.setAccountNonLocked(1);
		userDAO.updateUser(unlockUser);

		response.sendRedirect(request.getContextPath() + "/users");
		m.addAttribute("msg", "Account Unlocked");
		return "users/user-list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, Model m, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		m.addAttribute("mainSlug", "user");
		userDAO.deleteUserById(id);
		m.addAttribute("msg", "User Deleted !");

		List<User> userList = userDAO.listAllUser();
		m.addAttribute("userLists", userList);

		response.sendRedirect(request.getContextPath() + "/users");
		return "users/user-list";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(required = false) String keywords, Model m) {
		m.addAttribute("mainSlug", "user");
		List<User> userListKeywords = userDAO.selectUserByUsername(keywords);
		m.addAttribute("userListKeywords", userListKeywords);
		m.addAttribute("found", "Found " + (userListKeywords.size()) + " result");
		return "users/user-search";
	}
}
