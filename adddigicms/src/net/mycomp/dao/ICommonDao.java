package net.mycomp.dao;

import java.util.List;

import net.mycomp.persist.CmsContent;
import net.mycomp.persist.Portal;

public interface ICommonDao {

	public boolean saveObject(Object object);
	public List<CmsContent> findAllEnabledCmsContent();
	public List<Portal> findAllEnabledPortal();
	
}
