package Dao.Impliment;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import Dao.Interface.IPhongDao;
import Entity.Phong;
import Entity.TinhTrangPhong;
import Model.PageList;
import Rmi.DTO.PhongDTO;
import Utilities.HibernateUtil;
import Utilities.MappingDtoFacade;

public class PhongDao implements IPhongDao {
	private OgmSessionFactory sessionFactory;

	public PhongDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public Phong get(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id < 0 is not allow");

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			Phong temp = session.find(Phong.class, id);

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
	public List<Phong> getAll() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.Phongs.find({})";
		try {
			List<Phong> list = session.createNativeQuery(query, Phong.class).getResultList();
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
	public boolean add(Phong addObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String queryTTP = "db.tinhtrangphongs.find({tenTTP : 'Trống'})";

		try {
			TinhTrangPhong ttp = session.createNativeQuery(queryTTP, TinhTrangPhong.class).getSingleResult();
			addObject.setMaTTP(ttp);

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
	public boolean update(Phong updateObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			session.update(updateObject);

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

		Phong deleteObj = new Phong();
		deleteObj.setMaP(deleteObjectId);
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
	public Phong getPhongTrongByLoaiPhongId(int loaiPhongId) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String queryTTP = "db.tinhtrangphongs.find({tenTTP : 'Trống'})";
		try {
			TinhTrangPhong ttp = session.createNativeQuery(queryTTP, TinhTrangPhong.class).getSingleResult();

			String queryPhong = "db.phongs.aggregate([{ '$match': { '$and': [ {'maTTP' : " + ttp.getMaTTP()
					+ "},{'maLP' : " + loaiPhongId + "}]}},{'$limit' :1}])";
			Phong phong = session.createNativeQuery(queryPhong, Phong.class).getSingleResult();

			tr.commit();

			return phong;
		} catch (Exception e) {
			tr.rollback();
			if (e instanceof NoResultException) {
				System.out.println("Không có phòng trống với loại phòng id: " + loaiPhongId);
			} else {
				e.printStackTrace();
			}

		}

		return null;
	}

	// 11/5 = 2.2 Math round() 3
	// tong cong co 11 dong / 5 dong tren 1 trang / 3 trang / trang 1 co 5 dong
	// trang 2 5 trong trang 1
	// trang bat tau tu 1 - 5, trang 6 -10, trang3 11
	// skip(5) limit (5)
	// == trang 1: ======================================
	@Override
	public PageList<PhongDTO> getListPhongByPage(int pageNumb, int maxRow, String roomName) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String mongoAggregate;
		if (roomName.length() > 0) {
			mongoAggregate = "db.phongs.aggregate([{ '$match': { '$text': { '$search': '" + roomName + "' }}}])";
		} else {
			mongoAggregate = "db.phongs.find({})";
		}

		try {
			NativeQuery<Phong> javaQuery = session.createNativeQuery(mongoAggregate, Phong.class);
			int totalRow = javaQuery.getResultList().size();

			List<Phong> Phongs_Paged = javaQuery.setFirstResult(maxRow * (pageNumb - 1)).setMaxResults(maxRow)
					.getResultList();

			PageList<PhongDTO> pageList = new PageList<>();

			int maxPage = totalRow / maxRow + (totalRow % maxRow > 0 ? 1 : 0);

			pageList.setListData(MappingDtoFacade.convertToListPhongDTO(Phongs_Paged));
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
	public PageList<PhongDTO> getListPhongDaThue(int pageNumb, int maxRow, String roomName) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String mongoAggregate;
		try {
			String queryttp = "db.tinhtrangphongs.find({tenTTP : 'Đã Thuê'})";
			TinhTrangPhong ttp = session.createNativeQuery(queryttp, TinhTrangPhong.class).getSingleResult();

			mongoAggregate = buidQueryGetPhongByMaTTP(roomName, ttp.getMaTTP());

			NativeQuery<Phong> javaQuery = session.createNativeQuery(mongoAggregate, Phong.class);
			int totalRow = javaQuery.getResultList().size();

			List<Phong> Phongs_Paged = javaQuery.setFirstResult(maxRow * (pageNumb - 1)).setMaxResults(maxRow)
					.getResultList();

			PageList<PhongDTO> pageList = new PageList<>();

			int maxPage = totalRow / maxRow + (totalRow % maxRow > 0 ? 1 : 0);

			pageList.setListData(MappingDtoFacade.convertToListPhongDTO(Phongs_Paged));
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

	private String buidQueryGetPhongByMaTTP(String roomName, int maTTP) {
		String query = "";
		if (roomName.length() > 0) {
			query = "db.phongs.aggregate([{ '$match': { '$and': [ {'maTTP' : " + maTTP + "}, { '$text': { '$search': '"
					+ roomName + "' }}]}}])";
		} else {
			query = "db.phongs.find({'maTTP': " + maTTP + "})";
		}
		return query;

	}

//	@Override
//	public List<Phong> getListByPage(int pageNumb) {
//		OgmSession session = sessionFactory.getCurrentSession();
//		Transaction tr = session.beginTransaction();
//		
//		int skip = limit*(pageNumb-1);
//		String query = "db.Phongs.find({}).skip("+skip+").limit("+limit+")";
//		try {
//			List<Phong> list = session.createNativeQuery(query, Phong.class)
//					.getResultList();
//			tr.commit();
//			session.close();
//
//			return list;
//		} catch (Exception e) {
//			tr.rollback();
//			session.close();
//
//			e.printStackTrace();
//		}
//
//		return null;
//	}
}
