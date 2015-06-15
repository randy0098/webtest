package resteasy;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrgnizationDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
	public void addOrg(Orgnization org){
		jdbcTemplate.update("insert into orgnization (name,email) values (?,?)", new Object[] {org.getName(),org.getEmail()});
	}
	
	public void updateOrg(Orgnization org){
		jdbcTemplate.update("update orgnization set email=?,name=? where id=?", new Object[] {org.getEmail(),org.getName(),org.getId()});
	}
	
	public Orgnization getOneOrg(long id){
		Orgnization org = (Orgnization)jdbcTemplate.queryForObject("select * from orgnization where id=?", new Object[] {id}, new Orgnization());
		return org;  
	}
	
	public ArrayList<Orgnization> getOrgList(){
		ArrayList<Orgnization> orgList = (ArrayList<Orgnization>)jdbcTemplate.query("select * from orgnization",new Orgnization());
		return orgList;  
	}
	
	//注意这里ids是String，是带有双引号的。
	public void deleteOrgs(String ids){
		jdbcTemplate.update("delete from orgnization where id in ("+ids+")");
	}
	
	public void deleteOrg(Orgnization org){
		jdbcTemplate.update("delete from orgnization where name=?", new Object[] {org.getName()});
	}
}
