package asset_admin.utils;

import java.beans.PropertyEditorSupport;

import asset_admin.daoImpl.DepartmentDAOImpl;
import asset_admin.entities.Department;

public class DepartmentEditor extends PropertyEditorSupport{
	private final DepartmentDAOImpl departmentDAOImpl;
	
	public DepartmentEditor(DepartmentDAOImpl departmentDAOImpl) {
		this.departmentDAOImpl = departmentDAOImpl;
	}
	
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      Department department = departmentDAOImpl.selectDepartmentById(Integer.parseInt(text));
      setValue(department);
    }
}
