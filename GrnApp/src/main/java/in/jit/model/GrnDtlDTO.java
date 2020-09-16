package in.jit.model;




public class GrnDtlDTO {


	private Integer id;
	private String partCode;
	private Double baseCost;
	private Integer qty;
	private Double lineValue;
	private String status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public Double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getLineValue() {
		return lineValue;
	}

	public void setLineValue(Double lineValue) {
		this.lineValue = lineValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
