package in.jit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grn_dtl_tab")
public class GrnDtl {

	@Id
	@Column(name = "grn_dtl_id_col")
	@GeneratedValue
	private Integer id;

	@Column(name = "grn_dtl_part_code_col")
	private String partCode;
	
	@Column(name = "grn_dtl_cost_col")
	private Double baseCost;
	
	@Column(name = "grn_dtl_qty_col")
	private Integer qty;
	
	@Column(name = "grn_dtl_line_col")
	private Double lineValue;

	@Column(name = "grn_dtl_status_col")
	private String status;
	
	
	@ManyToOne
	@JoinColumn(name="grn_id_fk")
	private Grn grn;
	
	

	public Grn getGrn() {
		return grn;
	}

	public void setGrn(Grn grn) {
		this.grn = grn;
	}

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
