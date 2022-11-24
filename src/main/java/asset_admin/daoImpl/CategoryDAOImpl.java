package asset_admin.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import asset_admin.dao.ICategoryDAO;
import asset_admin.entities.Category;
import asset_admin.utils.HibernateUtils;

@SuppressWarnings("unchecked")
public class CategoryDAOImpl implements ICategoryDAO {
	private SessionFactory sf;

	public CategoryDAOImpl() {
		sf = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<Category> getAllCategory() {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Category> categoryList = null;
		try {
			tx = ss.beginTransaction();
			categoryList = ss.createQuery("FROM Category").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return categoryList;
	}

	@Override
	public Category addNewCategory(Category category) {
		Session ss = sf.openSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			int result = (Integer) ss.save(category);
			tx.commit();
			System.out.println(result + "Row(s) added");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return category;
	}

	@Override
	public boolean deleteCategoryById(int categoryId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDone = false;
		try {
			tx = ss.beginTransaction();
			String hql = "DELETE FROM Category " + "WHERE id = :categoryId";
			int result = ss.createQuery(hql).setParameter("categoryId", categoryId).executeUpdate();
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
	public Category getCategoryById(int categoryId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		Category category = null;
		try {
			tx = ss.beginTransaction();
			String hql = "FROM Category WHERE id = :categoryId";
			category = (Category) ss.createQuery(hql).setParameter("categoryId", categoryId).getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return category;
	}

	@Override
	public boolean updateCategoryById(Category category) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDone = false;
		try {
			tx = ss.beginTransaction();
			ss.update(category);
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
	public List<Category> selectCategoryByName(String categoryName) {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Category> categoryList = null;
		try {
			tx = ss.beginTransaction();
			categoryList = ss.createQuery("FROM Category WHERE name LIKE :categoryName")
					.setParameter("categoryName", categoryName + "%").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return categoryList;
	}

}
