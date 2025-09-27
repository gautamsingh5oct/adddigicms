package net.mycomp.service;

import java.util.List;

import net.mycomp.persist.CmsContent;
import net.mycomp.persist.Portal;

public interface IDaoService {
	
	public boolean saveObject(Object object);

	public List<CmsContent> findAllEnabledCmsContent();
	public List<Portal> findAllEnabledPortal();
}
