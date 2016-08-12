package core.vo.system;

import java.util.Date;
   /**
    * oa_tymenu 实体类
    * Wed Dec 16 16:30:55 CST 2015 gjx
    */ 


public class Oa_tymenu{
	private int PID;
	private int ID;
	private String NAME;
	private String URL;
	private String TARGET;
	private String FLAG;
	private String LOGFLAG;
	private int SORT;
	private String HIDEURL;
	private Date CZSJ;
	public void setPID(int PID){
	this.PID=PID;
	}
	public int getPID(){
		return PID;
	}
	public void setID(int ID){
	this.ID=ID;
	}
	public int getID(){
		return ID;
	}
	public void setNAME(String NAME){
	this.NAME=NAME;
	}
	public String getNAME(){
		return NAME;
	}
	public void setURL(String URL){
	this.URL=URL;
	}
	public String getURL(){
		return URL;
	}
	public void setTARGET(String TARGET){
	this.TARGET=TARGET;
	}
	public String getTARGET(){
		return TARGET;
	}
	public void setFLAG(String FLAG){
	this.FLAG=FLAG;
	}
	public String getFLAG(){
		return FLAG;
	}
	public void setLOGFLAG(String LOGFLAG){
	this.LOGFLAG=LOGFLAG;
	}
	public String getLOGFLAG(){
		return LOGFLAG;
	}
	public void setSORT(int SORT){
	this.SORT=SORT;
	}
	public int getSORT(){
		return SORT;
	}
	public void setHIDEURL(String HIDEURL){
	this.HIDEURL=HIDEURL;
	}
	public String getHIDEURL(){
		return HIDEURL;
	}
	public void setCZSJ(Date CZSJ){
	this.CZSJ=CZSJ;
	}
	public Date getCZSJ(){
		return CZSJ;
	}
}

