package asset_admin.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import asset_admin.dao.IBrandDAO;
import asset_admin.entities.Brand;
import asset_admin.utils.HibernateUtils;

@SuppressWarnings("unchecked")
public class BrandDAOImpl implements IBrandDAO {

	private SessionFactory sf;

	public BrandDAOImpl() {
		sf = HibernateUtils.getSessionFactory();
	}

	@Override
//	worked
	public List<Brand> getAllBrand() {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Brand> brands = null;
		try {
			tx = ss.beginTransaction();
			String hql = "FROM Brand";
			brands = ss.createQuery(hql).list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return brands;
	}

	@Override
	public Brand getBrandById(int id) {
		Session ss = sf.openSession();
		Transaction tx = null;
		Brand brand = null;
		try {
			tx = ss.beginTransaction();
			String hql = "FROM Brand WHERE id = :id";
			brand = (Brand) ss.createQuery(hql).setParameter("id", id).getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return brand;
	}

	@Override
//	worked
	public boolean createNewBrand(Brand brand) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDone = false;
		try {
			tx = ss.beginTransaction();
			ss.save(brand);
			tx.commit();
			isDone = true;
			System.out.println("1 Row(s) Added");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isDone;
	}

	@Override
	public boolean updateBrandById(Brand brand) {
		boolean isDone = false;
		Session ss = sf.openSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			ss.update(brand);
			tx.commit();
			isDone = true;
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isDone;
	}

	@Override
	public boolean deleteBrandById(int brandId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDone = false;
		try {
			tx = ss.beginTransaction();
			String hql = "DELETE FROM Brand WHERE id = :brandId";
			int result = ss.createQuery(hql).setParameter("brandId", brandId).executeUpdate();
			tx.commit();
			isDone = true;
			System.out.println(result + " Rows affected: ");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isDone;
	}

	@Override
	public List<Brand> getBrandByName(String brandName) {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Brand> brandListWithKeywords = null;
		try {
			tx = ss.beginTransaction();
			String hql = "FROM Brand WHERE name LIKE :brandName";
			brandListWithKeywords = ss.createQuery(hql).setParameter("brandName", brandName + "%").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return brandListWithKeywords;
	}
}
