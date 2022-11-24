
package asset_admin.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import asset_admin.dao.IUserDAO;
import asset_admin.entities.User;
import asset_admin.utils.HibernateUtils;

@Service
@SuppressWarnings("unchecked")

public class UserDAOImpl implements IUserDAO {
	private SessionFactory sf;
	
	private PasswordEncoder passwordEncoder;

	@Autowired

	public UserDAOImpl() {
		super();
		sf = HibernateUtils.getSessionFactory();
		passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public List<User> listAllUser() {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<User> userList = null;
		try {
			tx = ss.beginTransaction();
			userList = ss.createQuery("FROM User").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return userList;
	}

	@Override
	public boolean deleteUserById(int userId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = ss.beginTransaction();
			int result = ss.createQuery("DELETE FROM User WHERE id = :userId").setParameter("userId", userId)
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
	public User addNewUser(User user) {
		Session ss = sf.openSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			int result = (Integer) ss.save(user);
			tx.commit();
			System.out.println(result + " Row added");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			System.out.println("Failed to add new User !");
		}
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = ss.beginTransaction();
			ss.update(user);
			tx.commit();
			isUpdated = true;
			System.out.println("ID = " + user.getId() + "Affected");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isUpdated;
	}

	@Override
	public User selectUserById(int userId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = ss.beginTransaction();
			user = (User) ss.createQuery("FROM User WHERE id = :userId").setParameter("userId", userId)
					.getSingleResult();
			tx.commit();
			System.out.println("1 Data Found: ");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return user;
	}

	@Override
	public List<User> selectUserByUsername(String username) {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<User> userList = null;
		try {
			tx = ss.beginTransaction();
			userList = ss.createQuery("FROM User WHERE username LIKE :username")
					.setParameter("username", username + "%").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User findUserByUsername(String username) {
		Session ss = sf.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = ss.beginTransaction();
			user = (User) ss.createQuery("FROM User WHERE username = :username").setParameter("username", username)
					.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findUserByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
	}

	@Override
	public User matchUsernameAndPassword(String username, String password) {
		Session ss = sf.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = ss.beginTransaction();
			user = (User) ss.
					createQuery("FROM User WHERE username = :username AND password = :password")
					.setParameter("username", username)
					.setParameter("password", passwordEncoder.encode(password))
					.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return user;
	}
}
