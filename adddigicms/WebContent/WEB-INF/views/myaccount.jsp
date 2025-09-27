<%@ include file="header.jsp" %>  
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
		<table border="1" align="center" style="width:95%;text-align:center;font-size:15px;">
		<tr class="linear-bg1">
		<th >Msisdn</th>
		<th>Service Name</th>
		<th>Valid From</th>
		<th>Valid To</th>
		<th>Status</th>
		<th>Unsubscribe</th>
		</tr>
	  <c:if test="${subscriberRegList==null or subscriberRegList.size()<=0}">
		<tr>
	 		<td colspan="6">
	 		this mobile number is not subscribed any service for this portal
	 	  </td>
	 	</tr> 
			
		</c:if>
		
		<c:if test="${subscriberRegList!=null and subscriberRegList.size()>0}">
	  <c:forEach items="${subscriberRegList}" var="subscriberReg" >	 		
	 		<tr>
	 		<td>${subscriberReg.msisdn}</td>
	 		<td>${mapServiceIdToService[subscriberReg.serviceId].serviceDesc}</td>
	 		<td>${subscriberReg.validityFrom}</td>
	 		<td>${subscriberReg.validityTo}</td>	 	
	 		<td>${subscriberReg.statusDescp}</td>	 		
	 		<td id="${subscriberReg.subscriberId}">
	 		<c:if test="${subscriberReg.status==1}">
	 		<a href="#" onclick="unsub('${subscriberReg.subscriberId}','${unsubUrl}?subid=${subscriberReg.subscriberId}')">
	 		Unsubscribe
	 		</a>
	 		</c:if>	 	
	 	  </td>
	 	</tr> 
			</c:forEach>	
		</c:if>
			
	</table>
	<br><br><br><br>
<%@ include file="footer.jsp" %>				