package asset_admin.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import asset_admin.dao.IProductDAO;
import asset_admin.entities.Product;
import asset_admin.utils.HibernateUtils;

@SuppressWarnings("unchecked")
public class ProductDaoImpl implements IProductDAO {

	private SessionFactory sf;

	public ProductDaoImpl() {
		sf = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<Product> getAllProduct() {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Product> products = null;
		try {
			tx = ss.beginTransaction();
			String hql = "FROM Product";
			products = ss.createQuery(hql).list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return products;
	}

	@Override
	public int addNewProduct(Product product) {
		Session ss = sf.openSession();
		Transaction tx = null;
		int result = 0;
		try {
			tx = ss.beginTransaction();
			result = (Integer) ss.save(product);
			tx.commit();
			System.out.println(result + "Row(s) added");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return result;
	}

	@Override
	public boolean deleteProductById(int id) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDone = false;
		try {
			tx = ss.beginTransaction();
			String hql = "DELETE FROM Product " + "WHERE id = :id";
			int result = ss.createQuery(hql).setParameter("id", id).executeUpdate();
			tx.commit();
			isDone = true;
			System.out.println(result + "Rows affected: ");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isDone;
	}

	@Override
	public Product getProductById(int id) {
		Session ss = sf.openSession();
		Transaction tx = null;
		Product product = null;
		try {
			tx = ss.beginTransaction();
			String hql = "FROM Product WHERE id = :id";
			product = (Product) ss.createQuery(hql).setParameter("id", id).getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return product;
	}

	@Override
	public boolean updateProductById(Product product) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDone = false;
		try {
			tx = ss.beginTransaction();
			ss.update(product);
			tx.commit();
			isDone = true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isDone;
	}

	@Override
	public List<Product> selectProductByName(String productName) {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Product> productList = null;
		try {
			tx = ss.beginTransaction();
			productList = ss.createQuery("FROM Product WHERE productName LIKE :productName")
					.setParameter("productName", productName + "%").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return productList;
	}
}
