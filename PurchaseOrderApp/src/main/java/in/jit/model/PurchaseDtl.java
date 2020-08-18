package in.jit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="purchase_dtl_tab")
public class PurchaseDtl {

	@Id
	@GeneratedValue
	@Column(name="pur_dtl_id_col")
	private Integer id;
	
	@Transient
	private Integer slno;
	
	@Column(name="pur_dtl_qty_col")
	private Integer qty;
	
	@Column(name="pur_dtl_part_col")
	private String part;
	
	@ManyToOne
	@JoinColumn(name="po_id_fk")
	private PurchaseOrder po;

	public PurchaseOrder getPo() {
		return po;
	}

	public void setPo(PurchaseOrder po) {
		this.po = po;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}
	
	
	
}
