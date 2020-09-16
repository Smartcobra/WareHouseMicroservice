package in.jit.model;



import java.util.List;


public class GrnDTO {

	private Integer id;
	private String grnCode;
	private String grnType;
	private String description;
	private String orderCode;
	private boolean purchaseStatus;
	private List<GrnDtl> dtls;
	
	
	
	public List<GrnDtl> getDtls() {
		return dtls;
	}

	public void setDtls(List<GrnDtl> dtls) {
		this.dtls = dtls;
	}

	public boolean isPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(boolean purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGrnCode() {
		return grnCode;
	}

	public void setGrnCode(String grnCode) {
		this.grnCode = grnCode;
	}

	public String getGrnType() {
		return grnType;
	}

	public void setGrnType(String grnType) {
		this.grnType = grnType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
