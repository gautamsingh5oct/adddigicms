package net.mycomp.util;

public class SubscriberNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String redirectToUrl;
	private Integer portalId;
	
	public SubscriberNotFoundException(String redirectToUrl){
		this.redirectToUrl=redirectToUrl;
	}
	
	public SubscriberNotFoundException(Integer portalId){
		this.portalId=portalId;
	}
	public String getRedirectToUrl() {
		return redirectToUrl;
	}
	public void setRedirectToUrl(String redirectToUrl) {
		this.redirectToUrl = redirectToUrl;
	}

	public Integer getPortalId() {
		return portalId;
	}

	public void setPortalId(Integer portalId) {
		this.portalId = portalId;
	}
}
