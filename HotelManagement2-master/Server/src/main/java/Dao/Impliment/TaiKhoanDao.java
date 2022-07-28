package Dao.Impliment;

import javax.persistence.NoResultException;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import Dao.Interface.ITaiKhoan;
import Entity.NhanVien;
import Entity.TaiKhoan;
import Utilities.HibernateUtil;

public class TaiKhoanDao implements ITaiKhoan {
	private OgmSessionFactory sessionFactory;

	public TaiKhoanDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public TaiKhoan get(String tenTk) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.nhanviens.aggregate([{'$match': { 'taiKhoan.tenTK':'" + tenTk + "'}}])";
		try {
			NhanVien nv = session.createNativeQuery(query, NhanVien.class).getSingleResult();

			tr.commit();
			session.close();

			return nv.getTaiKhoan();
		} catch (Exception e) {
			tr.rollback();
			session.close();
			if (e instanceof NoResultException) {
				System.out.println("Không tìm thấy tài khoản với tên: " + tenTk);
			} else {
				e.printStackTrace();
			}

		}
		return null;
	}

	@Override
	public boolean update(int maNV, TaiKhoan updateTk) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			NhanVien temp = session.find(NhanVien.class, maNV);
			temp.setTaiKhoan(updateTk);
			session.saveOrUpdate(temp);

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
	public boolean delete(String tenTK) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.nhanviens.updateOne({'taiKhoan.tenTK':'" + tenTK + "'},{'$set': {'taiKhoan' : ''}})";
		try {
			int update = session.createNativeQuery(query).executeUpdate();
			System.out.println(update);
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
	public boolean addTkToNhanVien(int maNv, TaiKhoan tk) {

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			NhanVien temp = session.find(NhanVien.class, maNv);
			temp.setTaiKhoan(tk);
			session.update(temp);

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
