package Dao.Impliment;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import Dao.Interface.IPhieuThue;
import Entity.KhachHang;
import Entity.PhieuThue;
import Entity.Phong;
import Entity.TinhTrangPhong;
import Model.PageList;
import Rmi.DTO.KhachHangDTO;
import Rmi.DTO.PhieuThueDTO;
import Rmi.DTO.PhieuThuePhongInfoDTO;
import Utilities.HibernateUtil;
import Utilities.MappingDtoFacade;

public class PhieuThueDao implements IPhieuThue {
	private OgmSessionFactory sessionFactory;

	public PhieuThueDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public PhieuThue get(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id < 0 is not allow");

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			PhieuThue temp = session.find(PhieuThue.class, id);

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
	public List<PhieuThue> getAll() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.phieuthues.find({})";
		try {
			List<PhieuThue> list = session.createNativeQuery(query, PhieuThue.class).getResultList();
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
	public boolean add(PhieuThue addObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.tinhtrangphongs.find({'tenTTP' : 'Đã Đặt'})";
		try {
			Phong phongThue = session.get(Phong.class, addObject.getPhong().getMaP());

			TinhTrangPhong ttp = session.createNativeQuery(query, TinhTrangPhong.class).getSingleResult();
			phongThue.setMaTTP(ttp);

			session.update(phongThue);

			session.save(addObject);

			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(PhieuThue updateObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String query = "db.tinhtrangphongs.find({'tenTTP' : 'Đã Thuê'})";
		try {

			Phong phongThue = session.get(Phong.class, updateObject.getPhong().getMaP());

			TinhTrangPhong ttp = session.createNativeQuery(query, TinhTrangPhong.class).getSingleResult();
			phongThue.setMaTTP(ttp);

			session.update(phongThue);

			session.update(updateObject);

			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(int deleteObjectId) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.tinhtrangphongs.find({'tenTTP' : 'Trống'})";
		try {
			PhieuThue temp = session.find(PhieuThue.class, deleteObjectId);

			Phong phongThue = session.get(Phong.class, temp.getPhong().getMaP());
			TinhTrangPhong ttp = session.createNativeQuery(query, TinhTrangPhong.class).getSingleResult();
			phongThue.setMaTTP(ttp);

			session.update(phongThue);

			session.delete(temp);

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
	public PhieuThue getPhieuThueByCMT(String cmt) {

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String queryKhachHang = "db.khachhangs.find({'soCMND' : '" + cmt + "'})";
		try {

			KhachHang khachhang = session.createNativeQuery(queryKhachHang, KhachHang.class).getSingleResult();

			String queryPhieuThue = "db.phieuthues.find({'$and':[ { 'maKH': " + khachhang.getMaKH()
					+ ", 'trangThai': 'NEW'}]})";
			PhieuThue phieuthue = session.createNativeQuery(queryPhieuThue, PhieuThue.class).getSingleResult();

			tr.commit();

			return phieuthue;
		} catch (Exception e) {
			tr.rollback();
			if (e instanceof NoResultException) {
				System.out.println("Không tìm thấy phiếu thuê có trạng thái 'NEW' của KH : " + cmt);
			} else if (e instanceof EntityNotFoundException) {
				System.out.println("Mã của một entity nào đó không đúng trong database: ");
			} else {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean traPhong(int maPhong) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.tinhtrangphongs.find({'tenTTP' : 'Trống'})";
		try {

			Phong phongThue = session.find(Phong.class, maPhong);

			TinhTrangPhong ttp = session.createNativeQuery(query, TinhTrangPhong.class).getSingleResult();
			phongThue.setMaTTP(ttp);

			session.update(phongThue);

			String queryPhieuThue = "db.phieuthues.find({'$and':[{'maP':" + maPhong + " ,'trangThai':'CHECKED'}]})";
			PhieuThue pt = session.createNativeQuery(queryPhieuThue, PhieuThue.class).getSingleResult();

			pt.setNgayTra(new Date());
			pt.setTrangThai("OUT");

			session.update(pt);

			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			if (e instanceof NullPointerException) {
				System.out.println(
						"Có thể không tồn tại phòng với mã : " + maPhong + "hoặc ko có tình trạng phòng : {'Trống'}");
			} else {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public PageList<PhieuThuePhongInfoDTO> getListPhieuThueByPage(int pageNumb, int maxRow) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String mongoAggregate = "db.phieuthues.find({trangThai: 'OUT'})";

		try {
			NativeQuery<PhieuThue> javaQuery = session.createNativeQuery(mongoAggregate, PhieuThue.class);
			int totalRow = javaQuery.getResultList().size();

			List<PhieuThue> PhieuThues_Paged = javaQuery.setFirstResult(maxRow * (pageNumb - 1)).setMaxResults(maxRow)
					.getResultList();

			PageList<PhieuThuePhongInfoDTO> pageList = new PageList<>();

			int maxPage = totalRow / maxRow + (totalRow % maxRow > 0 ? 1 : 0);

			pageList.setListData(MappingDtoFacade.convertToListPhieuThueInfoDTO(PhieuThues_Paged));
			pageList.setMaxPage(maxPage == 0 ? 1 : maxPage);
			pageList.setCurrentPage(pageNumb);

			tr.commit();

			return pageList;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return null;
	}

}
