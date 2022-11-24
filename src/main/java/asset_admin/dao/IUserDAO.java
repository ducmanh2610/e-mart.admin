package asset_admin.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import asset_admin.entities.User;

public interface IUserDAO extends UserDetailsService{
	public List<User> listAllUser();

	public boolean deleteUserById(int userId);

	public User addNewUser(User User);

	public boolean updateUser(User User);

	public User selectUserById(int userId);

	public List<User> selectUserByUsername(String username);

	public User findUserByUsername(String username);

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public User matchUsernameAndPassword(String username, String password);
}
