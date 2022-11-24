package asset_admin.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import asset_admin.dao.IimageDAO;
import asset_admin.entities.Image;
import asset_admin.utils.HibernateUtils;

@SuppressWarnings("unchecked")
public class ImageDAOImpl implements IimageDAO {
	private SessionFactory sf;

	public ImageDAOImpl() {
		sf = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<Image> listAllImage() {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Image> imageList = null;
		try {
			tx = ss.beginTransaction();
			imageList = ss.createQuery("FROM Image").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return imageList;
	}

	@Override
	public boolean deleteImageById(int imageId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = ss.beginTransaction();
			int result = ss.createQuery("DELETE FROM Image WHERE id = :imageId").setParameter("imageId", imageId)
					.executeUpdate();
			System.out.println("Rows affected: " + result);
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isDeleted;
	}

	@Override
	public Image addNewImage(Image image) {
		Session ss = sf.openSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			int result = (Integer) ss.save(image);
			tx.commit();
			System.out.println(result + " Row Added");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return image;
	}

	@Override
	public boolean updateImageById(Image image) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = ss.beginTransaction();
			ss.update(image);
			tx.commit();
			isUpdated = true;
			System.out.println("ID = " + image.getId() + "Affected");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isUpdated;
	}

	@Override
	public Image selectImageById(int imageId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		Image image = null;
		try {
			tx = ss.beginTransaction();
			image = (Image) ss.createQuery("FROM Image WHERE id = :imageId").setParameter("imageId", imageId)
					.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return image;
	}

}
