package Dao.Impliment;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import Constant.Page;
import Dao.Interface.IKhachHangDao;
import Entity.KhachHang;
import Entity.PhieuThue;
import Model.PageList;
import Rmi.DTO.KhachHangDTO;
import Utilities.HibernateUtil;
import Utilities.KhachHangUtil;
import Utilities.MappingDtoFacade;

public class KhachHangDao implements IKhachHangDao {
	private OgmSessionFactory sessionFactory;

	public KhachHangDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public KhachHang get(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id < 0 is not allow");

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			KhachHang temp = session.find(KhachHang.class, id);

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
	public List<KhachHang> getAll() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			String query = "db.khachhangs.find({})";
			List<KhachHang> list = session.createNativeQuery(query, KhachHang.class).getResultList();

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
	public boolean add(KhachHang addObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
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
	public boolean update(KhachHang updateObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			session.update(updateObject);
			tr.commit();
			session.close();

			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(int deleteObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		KhachHang deletObj = new KhachHang(deleteObject);
		try {
			session.delete(deletObj);

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
	public PageList<KhachHangDTO> searchListKhachhang(String tenKh, int pageNumb) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String mongoAggregate;
		if (tenKh.length() > 0) {
			mongoAggregate = "db.khachhangs.aggregate([{ '$match': { '$text': { '$search': '" + tenKh + "' }}}])";
		} else {
			mongoAggregate = "db.khachhangs.find({})";
		}

		try {
			NativeQuery<KhachHang> javaQuery = session.createNativeQuery(mongoAggregate, KhachHang.class);
			int totalRow = javaQuery.getResultList().size();

			List<KhachHang> khachHangs_Paged = javaQuery.setFirstResult(Page.LIMITROW_ONPAGE * (pageNumb - 1))
					.setMaxResults(Page.LIMITROW_ONPAGE).getResultList();

			PageList<KhachHangDTO> pageList = new PageList<>();

			int maxPage = totalRow / Page.LIMITROW_ONPAGE + (totalRow % Page.LIMITROW_ONPAGE > 0 ? 1 : 0);

			pageList.setListData(KhachHangUtil.convertListDTO(khachHangs_Paged));
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

	@Override
	public PageList<KhachHangDTO> getListKhachHangByPage(int pageNumb, int maxRow, String customerName) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String mongoAggregate;
		if (customerName.length() > 0) {
			mongoAggregate = "db.khachhangs.aggregate([{ '$match': { '$text': { '$search': '" + customerName
					+ "' }}}])";
		} else {
			mongoAggregate = "db.khachhangs.find({})";
		}

		try {
			NativeQuery<KhachHang> javaQuery = session.createNativeQuery(mongoAggregate, KhachHang.class);
			int totalRow = javaQuery.getResultList().size();

			List<KhachHang> KhachHangs_Paged = javaQuery.setFirstResult(maxRow * (pageNumb - 1)).setMaxResults(maxRow)
					.getResultList();

			PageList<KhachHangDTO> pageList = new PageList<>();

			int maxPage = totalRow / maxRow + (totalRow % maxRow > 0 ? 1 : 0);

			pageList.setListData(MappingDtoFacade.convertToListKhachHangDTO(KhachHangs_Paged));
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

	@Override
	public boolean isDeleteAble(int maKH) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			String query = "db.phieuthues.find({maKH: " + maKH + "})";

			int row = session.createNativeQuery(query, PhieuThue.class).getResultList().size();

			tr.commit();

			return row > 0 ? false : true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}

}
