package Dao.Impliment;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import Dao.Interface.IDichVuDao;
import Entity.DichVu;
import Entity.KhachHang;
import Entity.PhieuDichVu;
import Model.PageList;
import Rmi.DTO.DichVuDTO;
import Rmi.DTO.KhachHangDTO;
import Utilities.HibernateUtil;
import Utilities.MappingDtoFacade;

public class DichVuDao implements IDichVuDao {
	private OgmSessionFactory sessionFactory;

	public DichVuDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public DichVu get(int id) throws Exception {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			DichVu temp = session.find(DichVu.class, id);

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
	public List<DichVu> getAll() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			String query = "db.dichvus.find({})";
			List<DichVu> list = session.createNativeQuery(query, DichVu.class).getResultList();

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
	public boolean add(DichVu addObject) {
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
	public boolean update(DichVu updateObject) {
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
	public boolean delete(int objectId) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		DichVu deleteObj = new DichVu();
		deleteObj.setMaDv(objectId);
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
	public PageList<DichVuDTO> getListDichVuByPage(int pageNumb, int maxRow, String DichVuName) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String mongoAggregate;
		if (DichVuName.length() > 0) {
			mongoAggregate = "db.dichvus.aggregate([{ '$match': { '$text': { '$search': '" + DichVuName + "' }}}])";
		} else {
			mongoAggregate = "db.dichvus.find({})";
		}

		try {
			NativeQuery<DichVu> javaQuery = session.createNativeQuery(mongoAggregate, DichVu.class);
			int totalRow = javaQuery.getResultList().size();

			List<DichVu> DichVus_Paged = javaQuery.setFirstResult(maxRow * (pageNumb - 1)).setMaxResults(maxRow)
					.getResultList();

			PageList<DichVuDTO> pageList = new PageList<>();

			int maxPage = totalRow / maxRow + (totalRow % maxRow > 0 ? 1 : 0);

			pageList.setListData(MappingDtoFacade.convertToListDichVuDTO(DichVus_Paged));
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
	public boolean isDeleteAble(int maDV) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			String query = "db.phieudichvus.find({maDV: " + maDV + "})";
			int row = session.createNativeQuery(query, PhieuDichVu.class).getResultList().size();

			tr.commit();

			return row > 0 ? false : true;
		} catch (Exception e) {
			tr.rollback();

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<DichVu> getListDichVuDaDatByMaPT(int maPT) {

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			String query = "db.phieudichvus.aggregate([" + "{'$match':{'maPT': 34}},"
					+ "{'$lookup':{'from' : 'dichvus','localField': 'maDV', 'foreignField': '_id', 'as' : 'DichVu'}}"
					+ ",{'$unwind' : '$DichVu'}," 
					+ "{'$replaceWith': '$DichVu'}])";
			List<DichVu> list = session.createNativeQuery(query, DichVu.class).getResultList();

			tr.commit();

			return list;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		return null;
	}

}
