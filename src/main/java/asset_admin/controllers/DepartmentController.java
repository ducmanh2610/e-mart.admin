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

import asset_admin.daoImpl.DepartmentDAOImpl;
import asset_admin.entities.Department;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController {
	private DepartmentDAOImpl departmentDAO;

	public DepartmentController() {
		departmentDAO = new DepartmentDAOImpl();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listOfDepartments(Model m) {
		List<Department> departmentList = departmentDAO.listAllDepartment();
		
		m.addAttribute("departmentList", departmentList);
		
		return "departments/department-list";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String departmentView(@PathVariable(name = "id") int id, Model m) {
		List<Department> departmentList = departmentDAO.listAllDepartment();
		
		Department matchDept = departmentList.stream().filter(d -> d.getId() == id).findAny().orElse(null);
		
		int currentIndex = departmentList.indexOf(matchDept);
		int previousIndex = currentIndex == 0 ? 0 : currentIndex - 1;
		int nextIndex = currentIndex == departmentList.size() - 1 ? 0 : currentIndex + 1;

		m.addAttribute("prev", departmentList.get(previousIndex).getId());
		m.addAttribute("next", departmentList.get(nextIndex).getId());
		m.addAttribute("department", matchDept);
		
		return "departments/department-view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String departmentEditor(@PathVariable(name = "id") int id, Model m) {

		Department department = departmentDAO.selectDepartmentById(id);
		m.addAttribute("department", department);

		return "departments/department-editor";
	}

	@RequestMapping(value = "/new-department", method = RequestMethod.GET)
	public String addADepartment(Model m) {
		Department department = new Department();
		department.setStatus(1);

		m.addAttribute("department", department);
		m.addAttribute("msgErr", "");

		return "departments/department-editor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("department") Department department, Errors errors, Model m,
			BindingResult result, HttpServletResponse response, HttpServletRequest request) throws IOException {
		List<Department> departmentList = departmentDAO.listAllDepartment();
		m.addAttribute("department", department);

		if (!result.hasErrors()) {
			Date createdDate = new Date();
			department.setCreatedDate(createdDate);

			Department matchDept = departmentList.stream().filter(d -> d.getName().equals(department.getName()))
					.findAny().orElse(null);

			if (matchDept != null) {
				m.addAttribute("msgErr", "department name was exited, Data save Failed !");
				response.sendRedirect(request.getContextPath() + "/new-department");
				return "departments/department-editor";
			}

			departmentDAO.addNewDepartment(department);
			
			response.sendRedirect(request.getContextPath() + "/departments");
			
			m.addAttribute("msg", "Data Saved !");
			m.addAttribute("departmentList", departmentList);
			
			return "departments/department-list";
		}

		response.sendRedirect(request.getContextPath() + "/departments/new-department");
		m.addAttribute("msgErr", "Data Save Failed !");
		return "departments/department-editor";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, Model m, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Department> departmentList = departmentDAO.listAllDepartment();
		m.addAttribute("departmentList", departmentList);
		
		departmentDAO.deleteDepartmentById(id);
		m.addAttribute("msg", "Department Deleted !");
		response.sendRedirect(request.getContextPath() + "/departments");
		return "users/user-list";
	}

	@RequestMapping(value = "/search?keywords={keywords}", method = RequestMethod.GET)
	public String search(@RequestParam("keywords") String keywords, Model m) {
		List<Department> deptListKeywords = departmentDAO.selectDepartmentByName(keywords);
		m.addAttribute("deptListKeywords", deptListKeywords);
		m.addAttribute("found", "Found" + (deptListKeywords.size()) + "result");
		
		return "departments/search";
	}

}
