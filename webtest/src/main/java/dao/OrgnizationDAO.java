package dao;

import java.util.List;

import vo.OrgnizationVO;
import framework.BaseDAO;

public interface OrgnizationDAO extends BaseDAO{
	public OrgnizationVO get(Long id);
	
	public Long save(OrgnizationVO org);
	
	public void update(OrgnizationVO org);
	
	public void delete(OrgnizationVO org);
	
	public void delete(Long id);
	
	public List<OrgnizationVO> findAll();
}
