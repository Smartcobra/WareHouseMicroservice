package in.jit.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="grn_tab")
public class Grn {

	@GeneratedValue
	@Id
	@Column(name="grn_id_col")
	private Integer id;

	@Column(name="grn_code_col")
	private String grnCode;

	@Column(name="grn_type_col")
	private String grnType;

	@Column(name="grn_note_col")
	private String description;
	
	
	@Column(name="grn_order_code_col")
	private String orderCode;
	

	@Column(name="grn_pur_status_col")
	private boolean purchaseStatus;
	
	@OneToMany(mappedBy = "grn")
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
