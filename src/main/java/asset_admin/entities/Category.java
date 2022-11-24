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

/**
 * @author manhu
 *
 */
@Entity(name = "Category")
@Table(name = "tblCategory")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "name")
	@NotBlank(message = "Category name is required")
	private String name;

	@Column(name = "status")
	private int status;

	@Column(name = "description")
	@NotBlank(message = "Description is required")
	private String description;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Product> listProduct;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "created_date")
	private Date createdDate;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "modified_date")
	private Date modifiedDate;

	public Category() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
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

	@Override
	public String toString() {
		return "Category [Id=" + Id + ", name=" + name + ", status=" + status + ", description=" + description
				+ ", listProduct=" + listProduct + "]";
	}
}
