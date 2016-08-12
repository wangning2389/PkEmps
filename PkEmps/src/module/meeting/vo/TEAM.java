package module.meeting.vo;

import java.util.Date;

public class TEAM {
	
	private int ID;
	private String PROFESSION;
	private String TEAMLEADER;
	private int MEETING_ID;
	private String TEAMNAME;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPROFESSION() {
		return PROFESSION;
	}
	public void setPROFESSION(String pROFESSION) {
		PROFESSION = pROFESSION;
	}
	public String getTEAMLEADER() {
		return TEAMLEADER;
	}
	public void setTEAMLEADER(String tEAMLEADER) {
		TEAMLEADER = tEAMLEADER;
	}
	public int getMEETING_ID() {
		return MEETING_ID;
	}
	public void setMEETING_ID(int mEETINGID) {
		MEETING_ID = mEETINGID;
	}
	public String getTEAMNAME() {
		return TEAMNAME;
	}
	public void setTEAMNAME(String tEAMNAME) {
		TEAMNAME = tEAMNAME;
	}
	

}
