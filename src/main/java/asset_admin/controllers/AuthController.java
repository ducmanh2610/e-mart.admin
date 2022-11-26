package asset_admin.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asset_admin.entities.User;
import asset_admin.security.UserLoginDTO;
import asset_admin.daoImpl.UserDAOImpl;

@Controller
@RequestMapping(value = "/")
public class AuthController {

	private UserDAOImpl userDAO;

	public AuthController() {
		userDAO = new UserDAOImpl();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model m, HttpServletRequest request, HttpServletResponse response) {
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		m.addAttribute("user", userLoginDTO);
		return "credentials/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, Model m) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//		}
		UserLoginDTO dto = new UserLoginDTO();
		m.addAttribute("user", dto);
		return "credentials/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("user") UserLoginDTO user, Errors errors, Model m,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
		m.addAttribute("user", user);

		if (!bindingResult.hasErrors()) {
			UserDetails userDetails = userDAO.loadUserByUsername(user.getUsername());
			
			m.addAttribute("username", userDetails.getUsername());
			m.addAttribute("roles", userDetails.getAuthorities());
			
			return "index";
		} else {
			m.addAttribute("msgError", "Login Failed");
			return "credentials/login";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(Model m) {
		User userRegistrationDTO = new User();
		m.addAttribute("user", userRegistrationDTO);
		return "credentials/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			UserDAOImpl userDAOImpl) {
		boolean status = false;
		this.userDAO = userDAOImpl;
		if (!bindingResult.hasErrors()) {
			userDAO.addNewUser(user);
			status = true;
		}

		return "redirect:/register?success=" + status;
	}
}
