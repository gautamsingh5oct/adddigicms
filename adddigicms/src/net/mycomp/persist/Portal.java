
package net.mycomp.persist;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.ws.rs.core.Context;

import net.mycomp.util.JpaConverterJson;

@Entity
@Table(name = "tb_portal")
@NamedQueries({ @NamedQuery(name = "Portal.findAllEnablePortal",
query = "SELECT b FROM Portal b where b.status=:status")	
		})

public class Portal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "portal_desc")
	private String portalDesc;
	
	@Column(name = "portal_name")
	private String portalName;
	@Column(name = "portal_banner_text")
	private String portalBannerText;
	
	@Column(name="portal_banner_text2")
	private String portalBannerText2;
	@Column(name = "portal_banner")
	private String portalBanner;
	@Column(name = "portal_name2")
	private String portalName2;
	
	@Column(name = "portal_banner2")
	private String portalBanner2;
	@Column(name = "campaign_url")
	private String campaignUrl;
	
	@Column(name = "op_id")
	private Integer opId;
	
	@Column(name = "service_ids")
	@Convert(converter=JpaConverterJson.class)
	private List<Integer> serviceIds;
	
	
	@Column(name = "portal_view")
	private String portalView;
	
	@Column(name="content_view")
	private String contentView;
	
	@Column(name = "myaccount_view")
	private String myaccountView;
	
	@Column(name="msisdn_missing_view")
	private String msisdnMissingView;
	
	@Column(name="term_condition_url")
	private String termConditionUrl;
	
	
	@Column(name = "create_date")
	private Timestamp createDate;	
	
	@Column(name = "is_demo_portal")
	private Boolean isDemoPortal=false;	
	
	@Column(name = "check_sub_status")
	private Boolean checkSubStatus=false;	
	
	
	@Column(name = "status")
	private Boolean status;
	
	
	
public String toString() {
		
        Field[] fields = this.getClass().getDeclaredFields();
        String str = this.getClass().getName();
        try {
            for (Field field : fields) {
                str += field.getName() + "=" + field.get(this) + ",";
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        } catch (IllegalAccessException ex) {
            System.out.println(ex);
        }
        return str;
    }


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPortalName() {
		return portalName;
	}
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getPortalBanner() {
		return portalBanner;
	}
	public void setPortalBanner(String portalBanner) {
		this.portalBanner = portalBanner;
	}
	public String getPortalBannerText() {
		return portalBannerText;
	}
	public void setPortalBannerText(String portalBannerText) {
		this.portalBannerText = portalBannerText;
	}
	public String getPortalBanner2() {
		return portalBanner2;
	}
	public void setPortalBanner2(String portalBanner2) {
		this.portalBanner2 = portalBanner2;
	}
	public String getCampaignUrl() {
		return campaignUrl;
	}
	public void setCampaignUrl(String campaignUrl) {
		this.campaignUrl = campaignUrl;
	}


	public List<Integer> getServiceIds() {
		return serviceIds;
	}


	public void setServiceIds(List<Integer> serviceIds) {
		this.serviceIds = serviceIds;
	}


	public String getPortalView() {
		return portalView;
	}


	public void setPortalView(String portalView) {
		this.portalView = portalView;
	}


	public String getPortalDesc() {
		return portalDesc;
	}


	public void setPortalDesc(String portalDesc) {
		this.portalDesc = portalDesc;
	}


	public String getMyaccountView() {
		return myaccountView;
	}


	public void setMyaccountView(String myaccountView) {
		this.myaccountView = myaccountView;
	}


	public String getMsisdnMissingView() {
		return msisdnMissingView;
	}


	public void setMsisdnMissingView(String msisdnMissingView) {
		this.msisdnMissingView = msisdnMissingView;
	}


	public Integer getOpId() {
		return opId;
	}


	public void setOpId(Integer opId) {
		this.opId = opId;
	}


	public Boolean getIsDemoPortal() {
		return isDemoPortal;
	}


	public void setIsDemoPortal(Boolean isDemoPortal) {
		this.isDemoPortal = isDemoPortal;
	}


	public String getContentView() {
		return contentView;
	}


	public void setContentView(String contentView) {
		this.contentView = contentView;
	}


	public String getPortalName2() {
		return portalName2;
	}


	public void setPortalName2(String portalName2) {
		this.portalName2 = portalName2;
	}


	public String getPortalBannerText2() {
		return portalBannerText2;
	}


	public void setPortalBannerText2(String portalBannerText2) {
		this.portalBannerText2 = portalBannerText2;
	}


	public String getTermConditionUrl() {
		return termConditionUrl;
	}


	public void setTermConditionUrl(String termConditionUrl) {
		this.termConditionUrl = termConditionUrl;
	}


	public Boolean getCheckSubStatus() {
		return checkSubStatus;
	}


	public void setCheckSubStatus(Boolean checkSubStatus) {
		this.checkSubStatus = checkSubStatus;
	}


	

}
