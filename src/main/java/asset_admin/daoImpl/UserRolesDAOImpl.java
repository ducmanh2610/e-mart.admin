package asset_admin.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import asset_admin.dao.IUserRolesDAO;
import asset_admin.entities.UserRoles;
import asset_admin.utils.HibernateUtils;

@SuppressWarnings("unchecked")
public class UserRolesDAOImpl implements IUserRolesDAO{
	private SessionFactory sf;
	
	public UserRolesDAOImpl() {
		sf = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<UserRoles> getRolesList() {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<UserRoles> rolesList = null;
		try {
			tx = ss.beginTransaction();
			rolesList = ss.createQuery("FROM Roles").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return rolesList;
	}

	@Override
	public UserRoles getRolesById(int rolesId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		UserRoles userRoles = null;
		try {
			tx = ss.beginTransaction();
			userRoles = (UserRoles) ss.createQuery("FROM Roles WHERE id = :rolesId").setParameter("rolesId", rolesId)
					.getSingleResult();
			tx.commit();
			System.out.println("1 Data Found: ");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return userRoles;
	}

	@Override
	public UserRoles createNewRoles(UserRoles roles) {
		Session ss = sf.openSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			int result = (Integer) ss.save(roles);
			tx.commit();
			System.out.println(result + " Row added");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			System.out.println("Failed to add new Roles !");
		}
		return roles;
	}

	@Override
	public List<UserRoles> getRolesByName(String rolesName) {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<UserRoles> rolesList = null;
		try {
			tx = ss.beginTransaction();
			rolesList = ss.createQuery("FROM Roles WHERE name LIKE :rolesName").setParameter("rolesName", rolesName + "%")
					.list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return rolesList;
	}

	@Override
	public UserRoles updateUserRole(UserRoles role) {
		Session ss = sf.openSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			ss.update(role);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return role;
	}
	
}
