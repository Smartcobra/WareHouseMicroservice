package in.jit.model;



public class UomVO {

	private Integer id;
	private String uomType;
	private String uomModel;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUomType() {
		return uomType;
	}

	public void setUomType(String uomType) {
		this.uomType = uomType;
	}

	public String getUomModel() {
		return uomModel;
	}

	public void setUomModel(String uomModel) {
		this.uomModel = uomModel;
	}

	@Override
	public String toString() {
		return "UomVO [id=" + id + ", uomType=" + uomType + ", uomModel=" + uomModel + "]";
	}
	
	
}
