package asset_admin.utils;

import java.beans.PropertyEditorSupport;

import asset_admin.daoImpl.BrandDAOImpl;
import asset_admin.entities.Brand;

public class BrandEditor extends PropertyEditorSupport{
	private final BrandDAOImpl brandDAO;
	
	public BrandEditor(BrandDAOImpl brandDAOImpl) {
		this.brandDAO = brandDAOImpl;
	}
	
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      Brand brand = brandDAO.getBrandById(Integer.parseInt(text));
      setValue(brand);
    }
}
