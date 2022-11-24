package asset_admin.dao;

import java.util.List;

import asset_admin.entities.Department;

public interface IDepartmentDAO {
	public List<Department> listAllDepartment();

	public boolean deleteDepartmentById(int deptId);

	public Department addNewDepartment(Department department);

	public boolean updateDepartmentById(Department department);

	public Department selectDepartmentById(int deptId);

	public List<Department> selectDepartmentByName(String deptName);

	public Department listPersonInDepartment(Department department);

}
