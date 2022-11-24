package asset_admin.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Roles")
@Table(name = "tblRoles")
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@NotEmpty(message = "Role name is required")
	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private int status;

	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "modified_date")
	private Date modifiedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private List<User> listUserHasRoles;

	public UserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<User> getListUserHasRoles() {
		return listUserHasRoles;
	}

	public void setListUserHasRoles(List<User> listUserHasRoles) {
		this.listUserHasRoles = listUserHasRoles;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
