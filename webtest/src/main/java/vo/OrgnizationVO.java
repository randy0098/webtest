package vo;

import framework.BaseVO;

public class OrgnizationVO extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String email;
	
	private long id;
	
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
}
