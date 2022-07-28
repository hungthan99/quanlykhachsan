package Dao.Impliment;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import Dao.Interface.ILoaiPhongDao;
import Entity.KhachHang;
import Entity.LoaiPhong;
import Entity.PhieuThue;
import Model.PageList;
import Rmi.DTO.KhachHangDTO;
import Rmi.DTO.LoaiPhongDTO;
import Utilities.HibernateUtil;
import Utilities.MappingDtoFacade;

public class LoaiPhongDao implements ILoaiPhongDao {
	private OgmSessionFactory sessionFactory;

	public LoaiPhongDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public LoaiPhong get(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id < 0 is not allow");

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			LoaiPhong temp = session.find(LoaiPhong.class, id);

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
	public List<LoaiPhong> getAll() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.loaiphongs.find({})";
		try {
			List<LoaiPhong> list = session.createNativeQuery(query, LoaiPhong.class).getResultList();
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
	public boolean add(LoaiPhong addObject) {
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
	public boolean update(LoaiPhong updateObject) {
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
		LoaiPhong deleteObj = new LoaiPhong(deleteObjectId);
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
	public PageList<LoaiPhongDTO> getListLoaiPhongByPage(int pageNumb, int maxRow, String key) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String mongoAggregate;
		if (key.length() > 0) {
			mongoAggregate = "db.loaiphongs.aggregate([{ '$match': { '$text': { '$search': '" + key + "' }}}])";
		} else {
			mongoAggregate = "db.loaiphongs.find({})";
		}

		try {
			NativeQuery<LoaiPhong> javaQuery = session.createNativeQuery(mongoAggregate, LoaiPhong.class);
			int totalRow = javaQuery.getResultList().size();

			List<LoaiPhong> loaiPhongs_Paged = javaQuery.setFirstResult(maxRow * (pageNumb - 1)).setMaxResults(maxRow)
					.getResultList();

			PageList<LoaiPhongDTO> pageList = new PageList<>();

			int maxPage = totalRow / maxRow + (totalRow % maxRow > 0 ? 1 : 0);

			pageList.setListData(MappingDtoFacade.mapToListLoaiPhongDTO(loaiPhongs_Paged));
			pageList.setMaxPage(maxPage == 0 ? 1 : maxPage);
			pageList.setCurrentPage(pageNumb);

			tr.commit();

			return pageList;
		} catch (Exception e) {
			tr.rollback();
			if (e instanceof com.mongodb.MongoCommandException) {
				System.out.println("Add query: db.loaiphongs.createIndex({'tenLP':'text'})");
			}
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isDeleteAble(int maLP) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			String query = "db.phongs.find({maLP: " + maLP + "})";

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
