     package module.user.vo;

import java.util.Date;


public class Exp_User {
	private int ID;            // 主键                  
	private String EXPCATEGORY   ; // 专家类别              
	private String NAME  ;         // 姓名                  
	private String SEX    ;        // 性别                  
	private Date BIRTHDAY ;      // 出生日期              
	private String NATION ;        // 民族                  
	private String POLIC ;         // 政治面貌              
	private String COUNTRY ;       // 国籍                  
	private String IDTYPE ;        // 证件类型              
	private String IDCODE ;        // 证件号码              
	private String NATIVEPLACE  ;   // 籍贯                  
	private String BIRPLACE ;      // 出生地                
	private String COMPANY ;       // 工作单位              
	private String WORKTYPE ;      // 工作单位性质          
	private String POSITION  ;     // 职务                  
	private String TEL ;           // 办公电话              
	private String MOBILE ;        // 手机号码              
	private String ABROADORNOT ;   // 是否留学回国人员      
	private String LIVINGADD ;     // 常住地                
	private String PROFTITLE  ;    // 专业技术职称          
	private String PROFGRADE ;     // 专业技术岗位等级      
	private String MAJOR ;         // 从事专业              
	private String SUBJECT  ;      // 所属学科              
	private String FILED  ;        // 研究内容所属领域      
	private String CONTENTS ;      // 研究方向及内容简介    
	private String EXPTYPE  ;      // 专家类型              
	private Date SUBTIME ;       // 提交时间              
	private Date VERIFYTIME ;    // 审核时间              
	private String VERIFYSTATUS ;  // 审核状态              
	private String OPERATOR     ;  // 操作人                
	private String SOURCE ;        // 数据来源              
	private int TEAM_ID ;       // 组id                  
	private String  SAVE_TYPE ;    // 保存形式（暂存/保存） 
	private String  MARK   ;       // 备注                  
	private String  PHOTO  ;       // 照片                  
	private String  STATUS  ;      // 有效状态              
	private int  COMPANYID  ;   // 单位id  
	private String  EMAIL  ;      // 有效状态    
	private String  DELETEREASON  ;      // 注销原因  
	public String getEXPCATEGORY() {
		return EXPCATEGORY;

	}
	public void setEXPCATEGORY(String eXPCATEGORY) {
		EXPCATEGORY = eXPCATEGORY;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public Date getBIRTHDAY() {
		return BIRTHDAY;
	}
	public void setBIRTHDAY(Date bIRTHDAY) {
		BIRTHDAY = bIRTHDAY;
	}
	public String getNATION() {
		return NATION;
	}
	public void setNATION(String nATION) {
		NATION = nATION;
	}
	public String getPOLIC() {
		return POLIC;
	}
	public void setPOLIC(String pOLIC) {
		POLIC = pOLIC;
	}
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}
	public String getIDTYPE() {
		return IDTYPE;
	}
	public void setIDTYPE(String iDTYPE) {
		IDTYPE = iDTYPE;
	}
	public String getIDCODE() {
		return IDCODE;
	}
	public void setIDCODE(String iDCODE) {
		IDCODE = iDCODE;
	}
	public String getNATIVEPLACE() {
		return NATIVEPLACE;
	}
	public void setNATIVEPLACE(String nATIVEPLACE) {
		NATIVEPLACE = nATIVEPLACE;
	}
	public String getBIRPLACE() {
		return BIRPLACE;
	}
	public void setBIRPLACE(String bIRPLACE) {
		BIRPLACE = bIRPLACE;
	}
	public String getCOMPANY() {
		return COMPANY;
	}
	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}
	public String getWORKTYPE() {
		return WORKTYPE;
	}
	public void setWORKTYPE(String wORKTYPE) {
		WORKTYPE = wORKTYPE;
	}
	public String getPOSITION() {
		return POSITION;
	}
	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	public String getABROADORNOT() {
		return ABROADORNOT;
	}
	public void setABROADORNOT(String aBROADORNOT) {
		ABROADORNOT = aBROADORNOT;
	}
	public String getLIVINGADD() {
		return LIVINGADD;
	}
	public void setLIVINGADD(String lIVINGADD) {
		LIVINGADD = lIVINGADD;
	}
	public String getPROFTITLE() {
		return PROFTITLE;
	}
	public void setPROFTITLE(String pROFTITLE) {
		PROFTITLE = pROFTITLE;
	}
	public String getPROFGRADE() {
		return PROFGRADE;
	}
	public void setPROFGRADE(String pROFGRADE) {
		PROFGRADE = pROFGRADE;
	}
	public String getMAJOR() {
		return MAJOR;
	}
	public void setMAJOR(String mAJOR) {
		MAJOR = mAJOR;
	}
	public String getSUBJECT() {
		return SUBJECT;
	}
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	public String getFILED() {
		return FILED;
	}
	public void setFILED(String fILED) {
		FILED = fILED;
	}
	public String getCONTENTS() {
		return CONTENTS;
	}
	public void setCONTENTS(String cONTENTS) {
		CONTENTS = cONTENTS;
	}
	public String getEXPTYPE() {
		return EXPTYPE;
	}
	public void setEXPTYPE(String eXPTYPE) {
		EXPTYPE = eXPTYPE;
	}
	public Date getSUBTIME() {
		return SUBTIME;
	}
	public void setSUBTIME(Date sUBTIME) {
		SUBTIME = sUBTIME;
	}
	public Date getVERIFYTIME() {
		return VERIFYTIME;
	}
	public void setVERIFYTIME(Date vERIFYTIME) {
		VERIFYTIME = vERIFYTIME;
	}
	public String getVERIFYSTATUS() {
		return VERIFYSTATUS;
	}
	public void setVERIFYSTATUS(String vERIFYSTATUS) {
		VERIFYSTATUS = vERIFYSTATUS;
	}
	public String getOPERATOR() {
		return OPERATOR;
	}
	public void setOPERATOR(String oPERATOR) {
		OPERATOR = oPERATOR;
	}
	public String getSOURCE() {
		return SOURCE;
	}
	public void setSOURCE(String sOURCE) {
		SOURCE = sOURCE;
	}
	
	public String getSAVE_TYPE() {
		return SAVE_TYPE;
	}
	public void setSAVE_TYPE(String sAVETYPE) {
		SAVE_TYPE = sAVETYPE;
	}
	public String getMARK() {
		return MARK;
	}
	public void setMARK(String mARK) {
		MARK = mARK;
	}
	public String getPHOTO() {
		return PHOTO;
	}
	public void setPHOTO(String pHOTO) {
		PHOTO = pHOTO;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getTEAM_ID() {
		return TEAM_ID;
	}
	public void setTEAM_ID(int tEAMID) {
		TEAM_ID = tEAMID;
	}
	public int getCOMPANYID() {
		return COMPANYID;
	}
	public void setCOMPANYID(int cOMPANYID) {
		COMPANYID = cOMPANYID;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getDELETEREASON() {
		return DELETEREASON;
	}
	public void setDELETEREASON(String dELETEREASON) {
		DELETEREASON = dELETEREASON;
	}

}
