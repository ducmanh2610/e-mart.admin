package asset_admin.daoImpl;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import asset_admin.dao.IAttemptDAO;
import asset_admin.entities.Attempts;
import asset_admin.utils.HibernateUtils;

@SuppressWarnings("unchecked")
public class AttemptDAOImpl implements IAttemptDAO {

	private SessionFactory sf;

	public AttemptDAOImpl() {
		sf = HibernateUtils.getSessionFactory();
	}

	@Override
	public Optional<Attempts> findAttemptByUsername(String username) {
		Session ss = sf.openSession();
		Transaction tx = null;
		Optional<Attempts> attempt = null;
		try {
			tx = ss.beginTransaction();
			attempt = (Optional<Attempts>) ss.createQuery("FROM Attempts WHERE username :username")
					.setParameter("username", username).getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return attempt;
	}

	@Override
	public Attempts createNewAttempt(Attempts attempts) {
		Session ss = sf.openSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			int result = (Integer) ss.save(attempts);
			tx.commit();
			System.out.println(result + " Row added");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return attempts;
	}

}
