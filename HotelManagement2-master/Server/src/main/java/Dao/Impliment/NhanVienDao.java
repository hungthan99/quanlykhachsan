package Dao.Impliment;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.mongodb.MongoBulkWriteException;

import Constant.Page;
import Dao.Interface.INhanVien;
import Entity.NhanVien;
import Entity.PhieuDichVu;
import Entity.PhieuThue;
import Model.PageList;
import Rmi.DTO.NhanVienDTO;
import Utilities.HibernateUtil;
import Utilities.MappingDtoFacade;
import Utilities.NhanVienUtil;

public class NhanVienDao implements INhanVien {
	private OgmSessionFactory sessionFactory;

	public NhanVienDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public NhanVien get(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id < 0 is not allow");

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		try {
			NhanVien temp = session.find(NhanVien.class, id);

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
	public List<NhanVien> getAll() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.nhanviens.find({})";
		try {
			List<NhanVien> list = session.createNativeQuery(query, NhanVien.class).getResultList();
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
	public boolean add(NhanVien addObject) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(addObject);

			tr.commit();

			return true;
		} catch (Exception e) {
			tr.rollback();
			if (e instanceof javax.persistence.PersistenceException) {
				System.out.println("Fail because Same Account Name");
			} else {
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public boolean update(NhanVien updateObject) {
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

		try {
			session.remove(session.find(NhanVien.class, deleteObjectId));

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
	public PageList<NhanVienDTO> getListNhanVienByPage(int pageNumb, int maxRow, String employeeName) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String mongoAggregate;
		if (employeeName.length() > 0) {
			mongoAggregate = "db.nhanviens.aggregate([{ '$match': { '$text': { '$search': '" + employeeName + "' }}}])";
		} else {
			mongoAggregate = "db.nhanviens.find({})";
		}

		try {
			NativeQuery<NhanVien> javaQuery = session.createNativeQuery(mongoAggregate, NhanVien.class);
			int totalRow = javaQuery.getResultList().size();

			List<NhanVien> NhanViens_Paged = javaQuery.setFirstResult(maxRow * (pageNumb - 1)).setMaxResults(maxRow)
					.getResultList();

			PageList<NhanVienDTO> pageList = new PageList<>();

			int maxPage = totalRow / maxRow + (totalRow % maxRow > 0 ? 1 : 0);

			pageList.setListData(MappingDtoFacade.convertToListNhanVienDTO(NhanViens_Paged));
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
	public PageList<NhanVienDTO> searchListNhanVien(String tenNv, int pageNumb) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();

		String mongoAggregate;
		if (tenNv.length() > 0) {
			mongoAggregate = "db.nhanviens.aggregate([{ '$match': { '$text': { '$search': '" + tenNv + "' }}}])";
		} else {
			mongoAggregate = "db.nhanviens.find({})";
		}

		try {
			NativeQuery<NhanVien> javaQuery = session.createNativeQuery(mongoAggregate, NhanVien.class);
			int totalRow = javaQuery.getResultList().size();

			List<NhanVien> nhanViens_Paged = javaQuery.setFirstResult(Page.LIMITROW_ONPAGE * (pageNumb - 1))
					.setMaxResults(Page.LIMITROW_ONPAGE).getResultList();

			PageList<NhanVienDTO> pageList = new PageList<>();

			int maxPage = totalRow / Page.LIMITROW_ONPAGE + (totalRow % Page.LIMITROW_ONPAGE > 0 ? 1 : 0);

			pageList.setListData(NhanVienUtil.convertListDTO(nhanViens_Paged));
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
	public boolean isDeleteAble(int maNV) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try {
			String query = "db.phieuthues.find({maNV: " + maNV + "})";

			int row = session.createNativeQuery(query, PhieuThue.class).getResultList().size();

			tr.commit();

			return row > 0 ? false : true;
		} catch (Exception e) {
			tr.rollback();

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public NhanVien getNhanVienByTenTK(String tenTK) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String query = "db.nhanviens.aggregate([{'$match': { 'taiKhoan.tenTK':'" + tenTK + "'}}])";
		try {
			NhanVien nv = session.createNativeQuery(query, NhanVien.class).getSingleResult();

			tr.commit();

			return nv;
		} catch (Exception e) {
			tr.rollback();
			if (e instanceof NoResultException) {
				System.out.println("Không tìm thấy tài khoản với tên: " + tenTK);
			} else {
				e.printStackTrace();
			}
		}
		return null;
	}
}
