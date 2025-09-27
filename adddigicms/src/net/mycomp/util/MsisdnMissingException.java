package net.mycomp.util;

public class MsisdnMissingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String info;
	private Integer pid;
	
	public MsisdnMissingException(String info,Integer pid){
	this.info=info;	
	this.pid=pid;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
