package Dao.Impliment;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import Dao.Interface.ITinhTrangPhongDao;
import Entity.TinhTrangPhong;
import Utilities.HibernateUtil;

public class TinhTrangPhongDao implements ITinhTrangPhongDao {

	private OgmSessionFactory sessionFactory;

	public TinhTrangPhongDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public TinhTrangPhong get(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id < 0 is not allow");

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			TinhTrangPhong temp = session.find(TinhTrangPhong.class, id);

			tr.commit();
			session.close();

			return temp;
		} catch (Exception e) {
			tr.rollback();
			session.close();

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TinhTrangPhong> getAll() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.tinhtrangphongs.find({})";
		try {
			List<TinhTrangPhong> list = session.createNativeQuery(query, TinhTrangPhong.class).getResultList();
			tr.commit();

			return list;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean add(TinhTrangPhong addObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(addObject);

			tr.commit();
			session.close();

			return true;
		} catch (Exception e) {
			tr.rollback();
			session.close();

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(TinhTrangPhong updateObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			session.saveOrUpdate(updateObject);

			tr.commit();
			session.close();

			return true;
		} catch (Exception e) {
			tr.rollback();
			session.close();

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(int deleteObjectId) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		TinhTrangPhong deleteObj = new TinhTrangPhong(deleteObjectId);
		try {
			session.delete(deleteObj);

			tr.commit();
			session.close();

			return true;
		} catch (Exception e) {
			tr.rollback();
			session.close();

			e.printStackTrace();
		}
		return false;
	}

}
