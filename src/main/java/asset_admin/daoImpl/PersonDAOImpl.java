package asset_admin.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import asset_admin.dao.IPersonDAO;
import asset_admin.entities.Person;
import asset_admin.utils.HibernateUtils;

@SuppressWarnings("unchecked")
public class PersonDAOImpl implements IPersonDAO {
	private SessionFactory sf;

	public PersonDAOImpl() {
		sf = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<Person> listAllPerson() {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Person> personList = null;
		try {
			tx = ss.beginTransaction();
			personList = ss.createQuery("FROM Person").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return personList;
	}

	@Override
	public boolean deletePersonById(int personId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isDeleted = false;
		try {
			tx = ss.beginTransaction();
			int result = ss.createQuery("DELETE FROM Person WHERE id = :personId").setParameter("personId", personId)
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
	public Person addNewPerson(Person person) {
		Session ss = sf.openSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			int result = (Integer) ss.save(person);
			tx.commit();
			System.out.println(result + " Row Added");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return person;
	}

	@Override
	public boolean updatePersonById(Person person) {
		Session ss = sf.openSession();
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			tx = ss.beginTransaction();
			ss.update(person);
			tx.commit();
			isUpdated = true;
			System.out.println("ID = " + person.getId() + "Affected");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isUpdated;
	}

	@Override
	public Person selectPersonById(int personId) {
		Session ss = sf.openSession();
		Transaction tx = null;
		Person person = null;
		try {
			tx = ss.beginTransaction();
			person = (Person) ss.createQuery("FROM Person WHERE id = :personId").setParameter("personId", personId)
					.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public List<Person> selectPersonByName(String personName) {
		Session ss = sf.openSession();
		Transaction tx = null;
		List<Person> personList = null;
		try {
			tx = ss.beginTransaction();
			personList = ss.createQuery("FROM Person WHERE first_name OR last_name LIKE :deptName")
					.setParameter("personName", personName + "%").list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return personList;
	}

}
