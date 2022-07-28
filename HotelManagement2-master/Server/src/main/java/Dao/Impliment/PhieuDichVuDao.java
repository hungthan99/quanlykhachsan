package Dao.Impliment;

import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import Dao.Interface.IPhieuDichVu;
import Entity.DichVu;
import Entity.PhieuDichVu;
import Entity.PhieuThue;
import Entity.Phong;
import Entity.TinhTrangPhong;
import Rmi.DTO.PhieuDichVuInfoDTO;
import Utilities.HibernateUtil;
import Utilities.MappingDtoFacade;

public class PhieuDichVuDao implements IPhieuDichVu {
	private OgmSessionFactory sessionFactory;

	public PhieuDichVuDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public PhieuDichVu get(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id < 0 is not allow");

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			PhieuDichVu temp = session.find(PhieuDichVu.class, id);

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
	public List<PhieuDichVu> getAll() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.phieudichvus.find({})";
		try {
			List<PhieuDichVu> list = session.createNativeQuery(query, PhieuDichVu.class).getResultList();
			tr.commit();
			session.close();

			return list;
		} catch (Exception e) {
			tr.rollback();
			session.close();

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean add(PhieuDichVu addObject) {
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
	public boolean update(PhieuDichVu updateObject) {
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
		PhieuDichVu deleteObj = new PhieuDichVu();
		deleteObj.setMaPDV(deleteObjectId);
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

	@Override
	public boolean addPhieuDichVuByMaPhong(int maPhong, int maDichVu, int soluong) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			String queryPhieuThue = "db.phieuthues.find({'$and':[{'maP':" + maPhong
					+ " ,'trangThai':'CHECKED'}]},{'_id':1})";
			int mapt = (int) session.createNativeQuery(queryPhieuThue).getSingleResult();

			String query = "db.phieudichvus.aggregate([{'$match':{'$and':[{'maPT':" + mapt + "},{'maDV':" + maDichVu
					+ "}]}}])";
			PhieuDichVu pdv = session.createNativeQuery(query, PhieuDichVu.class).uniqueResult();
			if (pdv == null) {
				pdv = new PhieuDichVu();
				pdv.setDichVu(new DichVu(maDichVu));
				pdv.setPhieuThue(new PhieuThue(mapt));
				pdv.setNgayLap(new Date());
				pdv.setDaThanhToan(false);
				pdv.setSoLuong(soluong);

				session.save(pdv);
				tr.commit();
				return true;
			}

			int soLuongCu = pdv.getSoLuong();
			pdv.setSoLuong(soLuongCu + soluong);

			session.update(pdv);

			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			if (e instanceof NullPointerException) {
				System.out.println("Có thể không tồn tại phòng với mã : " + maPhong + "");
			} else {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public List<PhieuDichVuInfoDTO> getListPhieuDichVuByMaPT(int maPT) {
		String sql = "db.phieudichvus.aggregate([{'$match':{'$and': [{'maPT': " + maPT + "},{'daThanhToan':false}]}}])";
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {

			List<PhieuDichVu> lst = session.createNativeQuery(sql, PhieuDichVu.class).getResultList();

			List<PhieuDichVuInfoDTO> lstDTO = MappingDtoFacade.mapToListPdvInfoDTO(lst);

			tr.commit();
			return lstDTO;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateThanhToanDvByMaPT(int maPT) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.phieudichvus.updateMany({'maPT': " + maPT + "},{'$set':{'daThanhToan' : true}})";
		try {

			String queryPhieuThue = "db.phieuthues.updateOne({'$and':[{'_id':" + maPT
					+ " ,'trangThai':'OUT'}]},{'$set':{'trangThai':'DONE'}})";
			session.createNativeQuery(queryPhieuThue).executeUpdate();
			session.createNativeQuery(query).executeUpdate();

			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}

}
