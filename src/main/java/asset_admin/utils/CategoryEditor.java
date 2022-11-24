package asset_admin.utils;

import java.beans.PropertyEditorSupport;

import asset_admin.daoImpl.CategoryDAOImpl;
import asset_admin.entities.Category;

public class CategoryEditor extends PropertyEditorSupport{
	private final CategoryDAOImpl categoryDAO;
	
	public CategoryEditor(CategoryDAOImpl categoryDAOImpl) {
		this.categoryDAO = categoryDAOImpl;
	}
	
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      Category category = categoryDAO.getCategoryById(Integer.parseInt(text));
      setValue(category);
    }
}
