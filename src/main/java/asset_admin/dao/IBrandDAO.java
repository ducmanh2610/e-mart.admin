package asset_admin.dao;

import java.util.List;

import asset_admin.entities.Brand;

public interface IBrandDAO {
	public List<Brand> getAllBrand();

	public Brand getBrandById(int brandId);

	public boolean createNewBrand(Brand brand);

	public boolean updateBrandById(Brand brand);

	public boolean deleteBrandById(int brandId);
	
	public List<Brand> getBrandByName(String brandName);
	
}
