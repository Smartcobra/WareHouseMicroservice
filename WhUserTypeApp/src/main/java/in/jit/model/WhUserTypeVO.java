package in.jit.model;


public class WhUserTypeVO {
	
	private Integer id;
	private String userCode;
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Override
	public String toString() {
		return "WhUserTypeVO [id=" + id + ", userCode=" + userCode + "]";
	}
	
	

}
