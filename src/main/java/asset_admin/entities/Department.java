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
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Department")
@Table(name = "tblDepartment")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "name")
	@NotBlank(message = "Name is required")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private int status;

	@Column(name = "image_url")
	@NotBlank(message = "Image is required")
	private String imageURL;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "modified_date")
	private Date modifiedDate;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
	private List<User> userList;

	public Department() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Department [Id=" + Id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", imageURL=" + imageURL + ", productList=" + ", userList=" + userList + "]";
	}
}
