package in.jit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="uom_tab")
public class Uom {

	
	@Id
	@GeneratedValue(generator = "uom_gen")
	@SequenceGenerator(name="uom_gen",sequenceName = "uom_gen_sqcence")
	@Column(name="uom_id_col")
	private Integer id;
	
	@Column(name="uom_type_col",length=15,nullable=false)
	private String uomType;
	
	@Column(name="uom_model_col",length=25,nullable=false)
	private String uomModel;
	
	@Column(name="description_col",length=150,nullable=false)
	private String description;

	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
