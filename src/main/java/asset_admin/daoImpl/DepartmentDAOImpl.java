package asset_admin.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import asset_admin.dao.IDepartmentDAO;
import asset_admin.entities.Department;
import asset_admin.utils.HibernateUtils;

@SuppressWarnings("unchecked")
public class DepartmentDAOImpl implements IDepartmentDAO {
	private SessionFactory sf;

	public DepartmentDAOImpl() {
		sf = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<Department> listAllDepartment() {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Department> deptList = null;
		try {
			tx = ss.beginTransaction();
			deptList = ss.createQuery("FROM Department").list();
			tx.commit();
			System.out.println("All data Selected");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return deptList;
	}

	@Override
	public boolean deleteDepartmentById(int deptId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = ss.beginTransaction();
			int result = ss.createQuery("DELETE FROM Department WHERE id = :deptId").setParameter("deptId", deptId)
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
	public Department addNewDepartment(Department department) {
		Session ss = sf.openSession();
		Transaction tx = null;
		Department dept = department;
		try {
			tx = ss.beginTransaction();
			ss.save(dept);
			tx.commit();
			System.out.println("Data Saved !");
			System.out.println("\nID:" + dept.getId() + "\nName:" + dept.getName() + "\nStatus:" + dept.getStatus()
					+ "\nDesc:" + dept.getDescription() + "\nImages:" + dept.getImageURL());
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return dept;
	}

	@Override
	public boolean updateDepartmentById(Department department) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = ss.beginTransaction();
			ss.update(department);
			tx.commit();
			isUpdated = true;
			System.out.println("ID = " + department.getId() + "Affected");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isUpdated;
	}

	@Override
	public Department selectDepartmentById(int deptId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		Department dept = null;
		try {
			tx = ss.beginTransaction();
			dept = (Department) ss.createQuery("FROM Department WHERE id = :deptId").setParameter("deptId", deptId)
					.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return dept;
	}

	@Override
	public List<Department> selectDepartmentByName(String deptName) {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Department> deptList = null;
		try {
			tx = ss.beginTransaction();
			deptList = ss.createQuery("FROM Department WHERE name LIKE :deptName")
					.setParameter("deptName", deptName + "%").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return deptList;
	}

	@Override
	public Department listPersonInDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

}
