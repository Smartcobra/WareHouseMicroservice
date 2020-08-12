package in.jit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="part_tab")
public class Part {

	@Id
	@GeneratedValue(generator="part_gen")
	@SequenceGenerator(name="part_gen", sequenceName = "part_gen_seq")
	@Column(name="part_id_col")
	private Integer id;
	
	@Column(name="part_code_col")
	private String partCode;
	
	@Column(name="part_width_col")
	private String partWidth;
	
	@Column(name="part_len_col")
	private String partLen;
	
	@Column(name="part_hgh_col")
	private String partHgh;
	
	@Column(name="part_cost_col")
	private Double baseCost;
	
	@Column(name="part_curr_col")
	private String baseCurr;
	
	@Column(name="part_desc_col")
	private String description;
	
	@Column(name="part_uom_col")
	private String uom; //HAS-A 
	

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
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

	public String getPartWidth() {
		return partWidth;
	}

	public void setPartWidth(String partWidth) {
		this.partWidth = partWidth;
	}

	public String getPartLen() {
		return partLen;
	}

	public void setPartLen(String partLen) {
		this.partLen = partLen;
	}

	public String getPartHgh() {
		return partHgh;
	}

	public void setPartHgh(String partHgh) {
		this.partHgh = partHgh;
	}

	public Double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	public String getBaseCurr() {
		return baseCurr;
	}

	public void setBaseCurr(String baseCurr) {
		this.baseCurr = baseCurr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	
}
