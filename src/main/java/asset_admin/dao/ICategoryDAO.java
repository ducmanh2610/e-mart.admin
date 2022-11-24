package asset_admin.dao;

import java.util.List;

import asset_admin.entities.Category;

public interface ICategoryDAO {
	public List<Category> getAllCategory();

	public Category addNewCategory(Category category);

	public boolean deleteCategoryById(int categoryId);

	public Category getCategoryById(int categoryId);

	public boolean updateCategoryById(Category category);

	public List<Category> selectCategoryByName(String categoryName);
}
