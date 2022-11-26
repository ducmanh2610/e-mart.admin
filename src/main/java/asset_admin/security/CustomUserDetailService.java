package asset_admin.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import asset_admin.daoImpl.UserDAOImpl;
import asset_admin.entities.User;

public class CustomUserDetailService implements UserDetailsService{
	private UserDAOImpl userDAOImpl;
	
	public CustomUserDetailService(UserDAOImpl userDAOImpl) {
		this.userDAOImpl = userDAOImpl;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 final User customer = userDAOImpl.findUserByUsername(username);
	        if (customer == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        UserDetails user = org.springframework.security.core.userdetails.User.withUsername(customer.getEmail())
	                            .password(customer.getPassword())
	                            .authorities("ROLE_USER").build();
	        return user;
	}
	
}
