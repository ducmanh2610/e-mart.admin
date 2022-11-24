package asset_admin.entities;

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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Product")
@Table(name = "tblProduct")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "name")
	@NotEmpty(message = "Name is required")
	@Length(min = 5, max = 50, message = "Name length is between 5 and 50 characters")
	private String name;

	@Column(name = "asset_tag_id")
	@Length(max = 6, message = "Tag ID is less than 6 chars")
	@NotEmpty(message = "Tag is required")
	private String assetTagId;

	@Column(name = "cost")
	@Min(value = 0, message = "Cost is greater than 0")
	private float cost;

	@Column(name = "site")
	@Pattern(regexp = "[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)", message = "Invalid site param")
	private String site;

	@Column(name = "location")
	private String location;

	@Column(name = "status")
	private int status;

	@Column(name = "description")
	private String description;

	@Column(name = "created_date", updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;

	@Column(name = "modified_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modifiedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<Image> imagesList;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name ="created_by")
	private String createdBy;

	public Product() {
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

	public String getAssetTagId() {
		return assetTagId;
	}

	public void setAssetTagId(String assetTagId) {
		this.assetTagId = assetTagId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<Image> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<Image> imagesList) {
		this.imagesList = imagesList;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [Id=" + Id + ", name=" + name + ", assetTagId=" + assetTagId + ", cost=" + cost + ", site="
				+ site + ", location=" + location + ", status=" + status + ", description=" + description
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", category=" + category
				+ ", brand=" + brand + ", department=" + ", imagesList=" + imagesList + ", thumbnail="
				+ thumbnail + ", createdBy=" + createdBy + "]";
	}
}
