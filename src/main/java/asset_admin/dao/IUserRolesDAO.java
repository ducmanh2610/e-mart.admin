package asset_admin.dao;

import java.util.List;

import asset_admin.entities.UserRoles;

public interface IUserRolesDAO {
	public List<UserRoles> getRolesList();

	public UserRoles getRolesById(int rolesId);

	public UserRoles createNewRoles(UserRoles roles);
	
	public List<UserRoles> getRolesByName(String rolesName);
	
	public UserRoles updateUserRole(UserRoles role);
}
