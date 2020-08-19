package in.jit.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_order_tab")
public class PurchaseOrder {

	@Id
	@GeneratedValue
	@Column(name = "porder_id_col")
	private Integer id;

	@Column(name = "porder_Code_col")
	private String orderCode;

	@Column(name = "porder_ref_number_col")
	private String referenceNumber;

	@Column(name = "porder_qlty_chk_col")
	private String qualityCheck;

	@Column(name = "porder_def_status_col")
	private String status;

	@Column(name = "porder_desc_col")
	private String description;

	@Column(name = "porder_shipment_type_col")
	private String shipmentType;

	@Column(name = "porder_vendor_col")
	private String vendor;

	// Bidirectional mapping
	
	  @OneToMany(mappedBy = "po", fetch = FetchType.EAGER) private
	  List<PurchaseDtl> dtls;
	 

	public List<PurchaseDtl> getDtls() {
		return dtls;
	}

	public void setDtls(List<PurchaseDtl> dtls) {
		this.dtls = dtls;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getQualityCheck() {
		return qualityCheck;
	}

	public void setQualityCheck(String qualityCheck) {
		this.qualityCheck = qualityCheck;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
