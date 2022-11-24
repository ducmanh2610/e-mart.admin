package asset_admin.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import asset_admin.daoImpl.UserRolesDAOImpl;


@Entity(name = "User")
@Table(name = "tblUser", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@NotEmpty(message = "Username is required")
	@Column(name = "username", updatable = false)
	private String username;

	@NotEmpty(message = "Password is required")
	@Column(name = "password")
	private String password;
	
	@NotEmpty(message = "Name is required")
	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty(message = "Name is required")
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty(message = "Email is required")
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid Email")
	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private int status;

	@Column(name = "account_non_locked")
	private int accountNonLocked;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roles_id", nullable = false)
	private UserRoles roles;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "image_url")
	@NotBlank(message = "Avatar is Required")
	private String imageURL;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name =" department_id", nullable = false)
	private Department department;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Product> productList;
	
	public User() {
		super();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(int accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public UserRoles getRoles() {
		return roles;
	}

	public void setRoles(UserRoles roles) {
		this.roles = roles;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked == 1 ? true : false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return status == 1 ? true : false;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", status=" + status + ", accountNonLocked=" + accountNonLocked
				+  "createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", imageURL="
				+ imageURL + "]";
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Collection<UserRoles> roles = new UserRolesDAOImpl().getRolesList();
		
		for (UserRoles role : roles) {	
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorities;
	}

}
