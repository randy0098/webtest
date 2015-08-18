package framework;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO{

//	@Override
//	public List getObjects(Class clazz) {
//		return getHibernateTemplate().loadAll(clazz);
//	}
//
//	@Override
//	public Object getObject(Class clazz, Serializable id) {
//		Object obj = getHibernateTemplate().get(clazz,id);
//		if (obj == null){
//			throw new ObjectRetrievalFailureException(clazz,id);
//		}
//		return obj;
//	}
//
//	@Override
//	public void saveObject(Object obj) {
//		getHibernateTemplate().saveOrUpdate(obj);
//	}
//
//	@Override
//	public void removeObject(Class clazz, Serializable id) {
//		getHibernateTemplate().delete(getObject(clazz , id));
//	}
	
}
