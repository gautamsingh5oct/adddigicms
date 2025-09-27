package net.mycomp.repositoy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import net.jpa.repository.JPAService;
import net.mycomp.dao.CommonDaoImpl;
import net.mycomp.persist.CmsContent;
import net.mycomp.persist.Portal;
import net.mycomp.service.IDaoService;


@Service("cmsDataRepository")
public class CMSDataRepository {

	private static final Logger logger = Logger.getLogger(CMSDataRepository.class);

	
	@Autowired
	private IDaoService daoService;
	
	@Autowired
	private JPAService jpaService;
	
	public  ConcurrentMap<Integer,Portal> mapPortalIdToPortal = 
			new ConcurrentHashMap<Integer, Portal>();
	
	public  ConcurrentMap<Integer, List<CmsContent>> mapPortalIdToCmsContent = 
			new ConcurrentHashMap<Integer, List<CmsContent>>();
	
	public Map<Integer,net.mycomp.persist.Service> mapServiceIdToService=new HashMap<Integer,net.mycomp.persist.Service>();
	

	@PostConstruct
	public void init(){
		List<CmsContent> listCmsContent=daoService.findAllEnabledCmsContent();
		//logger.info("init:: "+listCmsContent);
		if(listCmsContent!=null&&listCmsContent.size()>0){
			Map<Integer, List<CmsContent>> map = 
					listCmsContent.stream().collect(Collectors.groupingBy(d->d.getPortalId()));		
			mapPortalIdToCmsContent.putAll(map);
		}
		List<Portal> listPortal=daoService.findAllEnabledPortal();
		if(listPortal!=null){
			Map<Integer, Portal> mapPortal 
			=listPortal.stream().collect(Collectors.toMap(Portal::getId, a->a));
			mapPortalIdToPortal.putAll(mapPortal);
		}
		logger.info("mapPortalIdToPortal:: "+mapPortalIdToPortal);
		
		List<net.mycomp.persist.Service> listSrvice=jpaService.findAll();
		mapServiceIdToService.putAll(
				listSrvice.stream().collect(
						Collectors.toMap(p -> p.getServiceId(), p -> p)));
	
		logger.info("init:: "+mapServiceIdToService);
	}
	
}
