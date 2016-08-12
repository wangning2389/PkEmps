package module.common.vo;

import java.util.Date;



public class C_OP_LOG {
	private int ID;
	private String MODULE;
	private String TYPE;
	private String CONTENT;
	private String OPERATOR;
	private Date OP_DATE;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMODULE() {
		return MODULE;
	}
	public void setMODULE(String mODULE) {
		MODULE = mODULE;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public String getOPERATOR() {
		return OPERATOR;
	}
	public void setOPERATOR(String oPERATOR) {
		OPERATOR = oPERATOR;
	}
	public Date getOP_DATE() {
		return OP_DATE;
	}
	public void setOP_DATE(Date oPDATE) {
		OP_DATE = oPDATE;
	}
	
}
