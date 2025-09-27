<%@ include file="./header.jsp" %>  
	<style>
			
	.linear-bg1{
		background: linear-gradient(to bottom, #1EFF00 0%,#404B3F 24%,#131513 50%,#404B3F 79%,#1EFF00 100%);
		color: #fff;
		font-weight: bold;
	}
	tr, th {
   
    text-align: center;
}
		</style>
<script type="text/javascript">
function unsub(id,unsubUrl){ 
    //alert("unsub "+unsubUrl);
	//alert(id);
    event.preventDefault(); 
    $.ajax({
        url: unsubUrl,
        async: false,
        //dataType: "jsonp",
        //jsonpCallback: 'callback',
        type: 'GET',
        //accepts: 'application/json',
        //crossDomain:true,
        //jsonp:true,
        data: {
            format: 'jsonp'
            
        },
        success: function(response) {
        	
        	var json = $.parseJSON(response);
        	//alert(json);
        	alert(json.messgae);
        	$("#"+id).text(); //Response
        	if(json.status==true){
        	window.location.href="http://www.gamzfun.com:8080/addigicharging/cnt/cmp?adid=1&cmpid=18&token=123";
        	return false;
        }
        	 //alert(response);
            //alert(response.messgae);
        },
    error: function(response) {
        alert("Some technical issue to unsubscribe.please try after some time ");
    }
    });
    return false; // for good measure
};

</script>
<br><br><br><br>
      <div style=" width:95%;margin:0 auto;">
		<table border="1"  style="width:100%;text-align:center;font-size:15px;">
		
	  <c:if test="${subscriberRegList==null or subscriberRegList.size()<=0}">
		<tr>
	 		<td colspan="2">
	 		this mobile number is not subscribed any service for this portal
	 	  </td>
	 	</tr> 
			
		</c:if>
		
		<c:if test="${subscriberRegList!=null and subscriberRegList.size()>0}">
	  <c:forEach items="${subscriberRegList}" var="subscriberReg" >	 		
	 		<tr>
	 		<td >Msisdn</td>
	 		<td>${subscriberReg.msisdn}</td>
	 		</tr>
	 		<tr>
	 		<td >Service Name</td>
	 		<td>${mapServiceIdToService[subscriberReg.serviceId].serviceDesc}</td>
	 		</tr>
	 		<tr>
	 		<td >Valid From</td>
	 		<td> <fmt:formatDate pattern = "yyyy/MM/dd H:m " 
         value = "${subscriberReg.validityFrom}"/></td>
         </tr>
	 		<tr>
	 		<td >Valid To</td>
	 		<td><fmt:formatDate pattern = "yyyy/MM/dd H:m " 
         value = "${subscriberReg.validityTo}"/></td>	
         </tr>
         <tr> 	
         <td>Status Desc</td>
	 		<td>${subscriberReg.statusDescp}</td>	 		
	 		</tr>
	 		
	 		<tr>
	 		<td >Action</td>
	 		<td id="${subscriberReg.subscriberId}">
	 		<c:if test="${subscriberReg.status==1}">
	 		<a href="#" onclick="unsub('${subscriberReg.subscriberId}','${unsubUrl}?subid=${subscriberReg.subscriberId}&serviceid=${subscriberReg.serviceId}')">
	 		Unsubscribe
	 		</a>
	 		</c:if>	 	
	 	    </td>
	 	</tr> 
	 	
			</c:forEach>	
		</c:if>
			
	</table>
	</div>
	<br><br><br><br>
<%@ include file="./footer.jsp" %>				