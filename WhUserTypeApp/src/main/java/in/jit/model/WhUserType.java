package in.jit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="wh_user_type_tab")
public class WhUserType {
	
	@Id
	@GeneratedValue(generator = "whusertype_seq")
	@SequenceGenerator(name="whusertype_seq",sequenceName = "whusertype_seq")
	@Column(name="wh_usr_id_col")
	private Integer id;
	
	@Column(name="wh_usr_type_col")
	private String userType;
	
	@Column(name="wh_usr_code_col")
	private String userCode;
	
	@Column(name="wh_usr_for_col")
	private String userFor;
	
	@Column(name="wh_usr_mail_col")
	private String userMail;
	
	@Column(name="wh_usr_contact_col")
	private String userContact;
	
	@Column(name="wh_usr_Id_type_col")
	private String userIdType;
	
	@Column(name="wh_usr_if_other_col")
	private String ifOther;
	
	@Column(name="wh_usr_id_number_col")
	private String idNumber;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserFor() {
		return userFor;
	}

	public void setUserFor(String userFor) {
		this.userFor = userFor;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getUserIdType() {
		return userIdType;
	}

	public void setUserIdType(String userIdType) {
		this.userIdType = userIdType;
	}

	public String getIfOther() {
		return ifOther;
	}

	public void setIfOther(String ifOther) {
		this.ifOther = ifOther;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	

}
