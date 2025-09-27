package net.mycomp.persist;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "tb_cms_content")
@NamedQueries({ @NamedQuery(name = "CmsContent.findAllEnableCmsContent",
query = "SELECT b FROM CmsContent b where b.status=:status order by b.sequence desc ")	
		})

public class CmsContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "content_name")
	private String contentName;
	
	@Column(name = "content_name2")
	private String contentName2;
	
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "file_path")
   private String filePath;
	@Column(name = "preview_file")
	private String previewFile;
	@Column(name = "portal_id")
	private Integer portalId;
	private Integer sequence;
	@Column(name = "create_date")
	private Timestamp createDate;
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
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getPreviewFile() {
		return previewFile;
	}
	public void setPreviewFile(String previewFile) {
		this.previewFile = previewFile;
	}
	public Integer getPortalId() {
		return portalId;
	}
	public void setPortalId(Integer portalId) {
		this.portalId = portalId;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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


	public String getContentName2() {
		return contentName2;
	}


	public void setContentName2(String contentName2) {
		this.contentName2 = contentName2;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}

