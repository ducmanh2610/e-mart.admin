package asset_admin.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PreAuthorize(value = "hasRole('ADMIN') and hasRole('USER') and hasRole('MANAGER')")
public class HomeController {
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
