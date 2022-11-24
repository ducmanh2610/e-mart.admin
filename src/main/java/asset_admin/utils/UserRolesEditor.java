package asset_admin.utils;

import java.beans.PropertyEditorSupport;

import asset_admin.daoImpl.UserRolesDAOImpl;
import asset_admin.entities.UserRoles;

public class UserRolesEditor extends PropertyEditorSupport{
	private final UserRolesDAOImpl userRolesDAOImpl;
	
	public UserRolesEditor(UserRolesDAOImpl userRolesDAOImpl) {
		this.userRolesDAOImpl = userRolesDAOImpl;
	}
	
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      UserRoles userRoles = userRolesDAOImpl.getRolesById(Integer.parseInt(text));
      setValue(userRoles);
    }
}
