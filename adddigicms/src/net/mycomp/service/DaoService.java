package net.mycomp.service;

import java.util.List;

import javax.annotation.Resource;

import net.mycomp.dao.ICommonDao;
import net.mycomp.persist.CmsContent;
import net.mycomp.persist.Portal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("daoService")
public class DaoService implements IDaoService{

	private static final Logger logger = Logger.getLogger(DaoService.class);
	
	
	@Resource
	private ICommonDao commonDao;
	
	@Override
	public List<CmsContent> findAllEnabledCmsContent() {
		try{
			return commonDao.findAllEnabledCmsContent();
		}catch(Exception ex){
			logger.error("findAllEnabledCmsContent:: exception :: "+ex);			
		}
		return null;
	}

	@Override
	public List<Portal> findAllEnabledPortal() {
		try{
			return commonDao.findAllEnabledPortal();
		}catch(Exception ex){
			logger.error("findAllEnabledPortal:: exception :: ",ex);			
		}
		return null;
	}

	@Override
	public boolean saveObject(Object object) {
		try{
			return commonDao.saveObject(object);
		}catch(Exception ex){
			logger.error("saveObject:: exception :: ",ex);			
		}
		return false;
	}

}
