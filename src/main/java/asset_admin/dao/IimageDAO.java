package asset_admin.dao;

import java.util.List;

import asset_admin.entities.Image;

public interface IimageDAO {
	public List<Image> listAllImage();

	public boolean deleteImageById(int imageId);

	public Image addNewImage(Image image);

	public boolean updateImageById(Image image);

	public Image selectImageById(int imageId);
}
