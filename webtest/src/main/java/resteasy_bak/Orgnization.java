package resteasy_bak;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Orgnization implements RowMapper<Orgnization>{
	private String name;
	
	private String email;
	
	private long id;
	
	public Orgnization() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Orgnization mapRow(ResultSet rs, int value) throws SQLException {
		Orgnization org = new Orgnization();
		org.setId(rs.getLong("id"));
		org.setName(rs.getString("name"));
		org.setEmail(rs.getString("email"));
		return org;
	}
}
