<%@ include file="./header.jsp" %>  
<%@ page contentType="text/html; charset=UTF-8" %>
	<style>
			
	.linear-bg1{
		background: linear-gradient(to bottom, #1EFF00 0%,#404B3F 24%,#131513 50%,#404B3F 79%,#1EFF00 100%);
		color: #fff;
		font-weight: bold;
	}
	tr, th {
   
    text-align: center;
}

.button {
	width:99%;
	max-width:280px;
	background:#DF73A2;
	font-size:30px;
	color:#fff;
	padding:15px 20px;
	border:none;
	border-radius:7px;
	margin:10px auto;
	display:table;
	 text-decoration: none;
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
        	if(json.status==true){
        		window.location.href='${portal.campaignUrl}'
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
		<table border="1" align="center" style="width:95%;text-align:center;font-size:15px;">
		
	 <c:if test="${subscriberRegList!=null and subscriberRegList.size()>0}">
	  <c:forEach items="${subscriberRegList}" var="subscriberReg" >	 		
	 		<tr>
	 		<td>Service Name</td>
	 		<td>${mapServiceIdToService[subscriberReg.serviceId].serviceDesc}</td>
	 		</tr>
	 		
	 		
<!-- 	 		<tr> -->
<!-- 	 		<td>Valid From</td> -->
<%-- 	 		<td> <fmt:formatDate pattern = "yyyy/MM/dd H:m "  --%>
<%--          value = "${subscriberReg.validityFrom}"/></td> --%>
<!--          </tr> -->
<!--          <tr> -->
<!--          <td>Valid To</td> -->
<%-- 	 		<td><fmt:formatDate pattern = "yyyy/MM/dd H:m "  --%>
<%--          value = "${subscriberReg.validityTo}"/></td>	 --%>
<!--          </tr> -->
          	<tr>
          	<td>Status</td>
	 		<td>${subscriberReg.statusDescp}</td>	
	 		</tr>
	 		
	 	
		 	<tr>	
	 		<td id="${subscriberReg.subscriberId}" dir="rtl" colspan="2">
	 		
	 		<c:if test="${subscriberReg.status==1}">
	 		<a class="button" href="#" onclick="unsub('${subscriberReg.subscriberId}','${unsubUrl}?subid=${subscriberReg.subscriberId}&serviceid=${subscriberReg.serviceId}')">
	 		Unsubscribe
	 		</a>
	 		</c:if>
	 	
	 	  </td>
	 	</tr> 
			</c:forEach>	
		</c:if>		
			
	</table>
	<br><br><br><br>
<%@ include file="./footer.jsp" %>				