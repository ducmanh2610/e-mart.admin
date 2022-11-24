package asset_admin.dao;

import java.util.List;

import asset_admin.entities.Product;

public interface IProductDAO {
	public List<Product> getAllProduct();

	public int addNewProduct(Product product);

	public boolean deleteProductById(int id);

	public Product getProductById(int id);

	public boolean updateProductById(Product product);

	public List<Product> selectProductByName(String productName);
}
