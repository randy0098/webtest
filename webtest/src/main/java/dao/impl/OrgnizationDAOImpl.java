package dao.impl;

import java.util.List;

import vo.OrgnizationVO;
import dao.OrgnizationDAO;
import framework.BaseDAOImpl;

public class OrgnizationDAOImpl extends BaseDAOImpl implements OrgnizationDAO {

	@Override
	public OrgnizationVO get(Long id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(OrgnizationVO.class, id);
	}

	@Override
	public Integer save(OrgnizationVO org) {
		// TODO Auto-generated method stub
		return (Integer)getHibernateTemplate().save(org);
	}

	@Override
	public void update(OrgnizationVO org) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(org);
	}

	@Override
	public void delete(OrgnizationVO org) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(org);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public List<OrgnizationVO> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from OrgnizationVO");
	}
}
