package Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.query.Query;

import entity.BomEntity;
import entity.CommonCode;
import hibernate.hibernate;

public class CommonCodeDAO {

	public void saveCommonCode(CommonCode entity) {
		Transaction transaction = null;
		try (Session session = hibernate.factory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(entity);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void getColums() {
		Transaction transaction = null;
		try (Session session = hibernate.factory.openSession()) {
			// start a transaction

			ClassMetadata classMetadata = hibernate.factory.getClassMetadata(CommonCode.class);
			String[] propertyNames = classMetadata.getPropertyNames();
			for (String s : propertyNames) {
				// system.out.println(s);
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public void updateCommonCode(CommonCode entity) {
		Transaction transaction = null;
		try (Session session = hibernate.factory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(entity);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<CommonCode> getCommonCodes() {
		try (Session session = hibernate.factory.openSession()) {
			return session.createCriteria(CommonCode.class).addOrder(Order.asc("id")).list();
			// return session.createQuery("from Product", Product.class).list();
		}
	}

	public List<CommonCode> getCommonCodeWithPagenation(int firstResult, int maxResults) {
		try (Session session = hibernate.factory.openSession()) {
			int paginatedCount = 0;
			Criteria criteria = session.createCriteria(BomEntity.class);
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResults);
			List<CommonCode> datas = (List<CommonCode>) criteria.addOrder(Order.asc("id")).list();

			if (datas != null) {
				// system.out.println("Total Results: " + paginatedCount);
				for (CommonCode entity : datas) {
					// system.out.println("Retrieved Product using Criteria. id: " +
					// Product.getId());
				}
			}
			return datas;
		}

	}

	public int getCommonCodesSize() {
		try (Session session = hibernate.factory.openSession()) {
			return session.createCriteria(CommonCode.class).list().size();
		}
	}

//	public static void main(String[] args) {
//		DbChecker.initCfg();
//		CommonCodeDAO dao = new CommonCodeDAO();
//		CommonCode code = new CommonCode();
//
//		List<String> list1 =dao.getColumnValuesByColumName("?ž?ž¬ë¶„ë¥˜");
//		list1.forEach(e->System.out.println(e));
//
//	}

	@SuppressWarnings("finally")
	public CommonCode findByPkey(int pkey) {
		Transaction transaction = null;
		CommonCode code = null;
		try (Session session = hibernate.factory.openSession()) {

			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			code = session.find(CommonCode.class, pkey);
			// commit transaction
			if (code != null) {
				// system.out.println(code.toString());

			} else {
				// system.out.println("nothing");
			}
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			return code;
		}

	}

	public void delCommonCode(CommonCode entity) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = hibernate.factory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.delete(entity);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<String> getColumnNames() {
		Transaction transaction = null;
		List<String> list= null;
		try (Session session = hibernate.factory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			String hql = "Select distinct this_.columnName From CommonCode this_";
			Query q = session.createQuery(hql);
			session.createQuery(hql);
			
			list = q.list();
			
			transaction.commit();
		}
		return list;
	}

	public List<String> getColumnValuesByColumName(String string) {
		Transaction transaction = null;
		List<String> list= null;
		try (Session session = hibernate.factory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			String hql = "Select distinct this_.value From CommonCode this_ Where this_.columnName =: columnName ";
			Query q = session.createQuery(hql);
			q.setParameter("columnName", string);
			session.createQuery(hql);
			
			list = q.list();
			
			transaction.commit();
		}
		return list;
	}
}
