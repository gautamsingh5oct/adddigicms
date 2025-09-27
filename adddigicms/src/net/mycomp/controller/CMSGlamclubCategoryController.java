package net.mycomp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jpa.repository.JPAOtp;
import net.jpa.repository.JPAService;
import net.jpa.repository.JPASubscriberregConfig;
import net.mycomp.persist.CmsContent;
import net.mycomp.persist.Portal;
import net.mycomp.persist.Service;
import net.mycomp.persist.SubscriberReg;
import net.mycomp.portal.service.CommonService;
import net.mycomp.repositoy.CMSDataRepository;
import net.mycomp.util.MsisdnMissingException;
import net.mycomp.util.Mutility;
import net.mycomp.util.SubscriberNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("gccat")
public class CMSGlamclubCategoryController {

	private static final Logger logger = Logger.getLogger(CMSGlamclubCategoryController.class);

	@Autowired
	private CMSDataRepository cmsDataRepository;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private JPASubscriberregConfig jpaSubscriberregConfig;
   
	@Autowired
	private JPAService jpaService;

	//private int numberOfContentPerPage = 6;
	private int contentPerCategory = 6;
	//@Value("${cms.content.web.path}")
	private String contentWebPrefixPath="http://13.232.99.62:8080/content/";
	@Value("${cms.content.physical.path}")
	private String contentPhysicalPrefixPath;
	
	@Value("${cms.portal.url}")
	private String cmsPortalUrl;
	@Value("${cms.portal.slid.url.template}")
	private String cmsPortalTemplate;
	
	@Value("${unsub.url}")
	private String unsubUrl;

	@PostConstruct
	public void init(){
	}
	

	@RequestMapping("/portal/{portalName}")
	public ModelAndView portal(
			HttpServletRequest request,HttpServletResponse response,
			ModelAndView modelAndView,
			@RequestParam(name = "pid", defaultValue = "0") int portalId,
			@RequestParam(name = "tc", defaultValue = "0", required = false) int totalContnet,
			@RequestParam(name = "sub", defaultValue = "0", required = false) int sub,
			@RequestParam(name = "msisdn", required = false) String msisdn,
			@RequestParam(name = "portalName", required = false) String portalName) throws Exception {
			
		Portal portal=cmsDataRepository.mapPortalIdToPortal.get(portalId);
			sub=commonService.userSession(  request, portal);	
		    
			modelAndView.addObject("portal",portal);
			modelAndView.addObject("contentWebPrefixPath",contentWebPrefixPath);
			
			modelAndView.addObject("sub", sub);			
			if(portal!=null&&portal.getPortalView()!=null){
			modelAndView.setViewName(portal.getPortalView());
			}else{
			 modelAndView.setViewName("glamclub/index");
			}
		    return modelAndView;
	}
	

	@RequestMapping("/catcontent/{portalName}")
	public ModelAndView catContent(
			HttpServletRequest request,HttpServletResponse response,
			ModelAndView modelAndView,
			@RequestParam(name = "pid", defaultValue = "0") int portalId,
			@RequestParam(name = "tc", defaultValue = "0", required = false) int totalContnet,
			@RequestParam(name = "sub", defaultValue = "0", required = false) int sub,
			@RequestParam(name = "msisdn", required = false) String msisdn,
			@RequestParam(name = "page", required = false) String page) throws Exception {
			
		    Portal portal=cmsDataRepository.mapPortalIdToPortal.get(portalId);
			sub=commonService.userSession(   request, portal);			    
			modelAndView.addObject("portal",portal);	
			modelAndView.addObject("contentWebPrefixPath",contentWebPrefixPath);
			modelAndView.addObject("sub", sub);			
			if(page!=null&&portal.getContentView()!=null){
			//modelAndView.setViewName(portal.getPortalView());
			modelAndView.setViewName(portal.getContentView().replaceAll("<page>", Objects.toString(page)));
			}else{
			 modelAndView.setViewName("glamclub/"+page);
			}
		    return modelAndView;
	}
	
	
	@RequestMapping("/contentview/{portalId}")
	public ModelAndView contentView(HttpServletRequest request,HttpServletResponse response,
			ModelAndView modelAndView,
			@PathVariable(value = "portalId") int portalId,
			@RequestParam("path") String contentPath
		
	) {

		try{
		logger.info("contentView::: "+contentPath);
			Portal portal=cmsDataRepository.mapPortalIdToPortal.get(portalId);
			int sub=commonService.userSession(   request, portal);
			logger.info("contentView :: sub:: "+sub);
			modelAndView.addObject("contentWebPrefixPath",contentWebPrefixPath);
			if(sub!=1){
				modelAndView.setView(new RedirectView(portal.getCampaignUrl()));
				return modelAndView;
			}
		  //  Portal portal=cmsDataRepository.mapPortalIdToPortal.get(portalId);
		    String prevUrl = request.getHeader("referer");
			
//		    if(prevUrl==null){
//				String url=cmsPortalTemplate.replaceAll("<pid>", String.valueOf(portalId));
//				modelAndView.setView(new RedirectView(url));
//				return modelAndView;
//			}		
		    
			modelAndView.addObject("prevUrl", prevUrl);		
			
			if(contentPath.contains(".apk")){
				download( request, response,
						portalId,contentPath);
				    return new ModelAndView("#");
			}else if(contentPath.contains(".html")){					
					return new ModelAndView(new RedirectView(contentWebPrefixPath+contentPath));
				}
			
		
		}catch(Exception ex){
			logger.error("contentView:: ",ex);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/download/{portalId}/{contentPath}", method = RequestMethod.GET)
	public void download(HttpServletRequest request,HttpServletResponse response,
			@PathVariable int portalId, @PathVariable String contentPath)
			throws Exception {

		
	
		File file = new File(contentPhysicalPrefixPath
				+ contentPath);
		InputStream is = new FileInputStream(file);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ file.getName() + "\"");
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}
	

	@RequestMapping("myaccount")
	public ModelAndView myaccount(HttpServletRequest request,
			ModelAndView modelAndView,
			@RequestParam(name = "pid", defaultValue = "0") int portalId,
			@RequestParam(name = "msisdn", required = false) String msisdn) throws Exception {
	
	
		  Portal portal=cmsDataRepository.mapPortalIdToPortal.get(portalId);
		  modelAndView.addObject("contentWebPrefixPath",contentWebPrefixPath);
		   modelAndView.addObject("portal",portal);
		   modelAndView
			.addObject("contentWebPrefixPath", contentWebPrefixPath);
			String prevUrl = request.getHeader("referer");
			if (prevUrl == null){
				prevUrl = "#";
			}	
			modelAndView.addObject("prevUrl", prevUrl);
			List<SubscriberReg> subscriberRegList=commonService.myAccountDetail(request, portal);
			
			logger.info("subscriberRegList::::::::: "+subscriberRegList
					+", cmsDataRepository.mapServiceIdToService::"
					+ ""+cmsDataRepository.mapServiceIdToService);
			modelAndView.addObject("subscriberRegList", subscriberRegList);
			modelAndView.addObject("unsubUrl", unsubUrl);
			modelAndView.addObject("mapServiceIdToService", cmsDataRepository.mapServiceIdToService);
			if(portal.getMyaccountView()!=null){
				modelAndView.setViewName(portal.getMyaccountView());
			}else{
		        modelAndView.setViewName("glamclub/myaccount");
			}
		return modelAndView;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ModelAndView exception(HttpServletRequest request,Exception ex){
		logger.error("exception:: error:: query  string: "+request.getQueryString()+", Exception:: ", ex);
		return new ModelAndView("error");
	}
	

@ExceptionHandler(SubscriberNotFoundException.class)
@ResponseBody
public ModelAndView subscriberNotFoundException(HttpServletRequest request,SubscriberNotFoundException ex){
	logger.error("SubscriberNotFoundException:: error:: query  string: "
              +request.getQueryString()+", Exception:: ", ex);
	Portal portal=cmsDataRepository.mapPortalIdToPortal.get(ex.getPortalId());
	return new ModelAndView("redirect:" +portal.getCampaignUrl());
}


}
