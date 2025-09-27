package net.mycomp.util;


import net.mycomp.persist.SubscriberReg;


public interface Mutility {

	  public static void isValidSubcriber(SubscriberReg subscriberReg){
	    	
		  
		  if(subscriberReg==null){
			  return;
		  }
		  
	    	if(subscriberReg.getStatus()==null||subscriberReg.getStatus()!=1){
	    		subscriberReg.setValidSubscribe(false);
	    		  return;
	    	 }
	    	
		  if(subscriberReg.getValidityTo()==null){
			  subscriberReg.setValidSubscribe(false);
			  return;
	    	 }
	    	 
			 if((subscriberReg.getValidityTo().getTime()-System.currentTimeMillis())>0){
				 subscriberReg.setValidSubscribe(true);
			 }else{
				 subscriberReg.setValidSubscribe(true);
				 subscriberReg.setMsg("Your subscription validity for  service has been expired."
				 		+ " You can not access content. Please recharge your mobile and try again.");
			 }
			}	
	  
	  
	  
	  public static Integer toInt(String str, int defaultValue) {
			try {
				return Integer.parseInt(str);
			} catch (Exception ex) {
			}
			return defaultValue;
		}
	  
	  public static String getSubId(Integer portalId) {			
				return portalId+"subid";			
		}
		
}
