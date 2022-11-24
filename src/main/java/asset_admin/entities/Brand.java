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
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Brand")
@Table(name = "tblBrand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "name")
	@NotEmpty(message = "Name is Required")
	private String name;
	
	@Column(name = "image_url")
	@NotEmpty(message = "Image is Required")
	private String imageURL;
	
	@Column(name = "status")
	private int status;
	
	@DateTimeFormat(pattern = "DD-MM-YYYY")
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	
	@DateTimeFormat(pattern = "DD-MM-YYYY")
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@NotEmpty(message = "Description is Required")
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@NotEmpty(message = "Website is Required")
	@Pattern(regexp = "[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)", message = "Invalid site param")
	@Column(name = "website")
	private String website;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Product> productListByBrandId;

	public Brand() {
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public List<Product> getProductListByBrandId() {
		return productListByBrandId;
	}

	public void setProductListByBrandId(List<Product> productListByBrandId) {
		this.productListByBrandId = productListByBrandId;
	}

	@Override
	public String toString() {
		return "Brand [Id=" + Id + ", name=" + name + ", imageURL=" + imageURL + ", status=" + status + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", description=" + description + ", createdBy="
				+ createdBy + ", website=" + website + "]";
	}
}
