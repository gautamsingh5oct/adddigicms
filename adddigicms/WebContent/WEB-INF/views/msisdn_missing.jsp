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
function sendOTP(otpUrl,serviceId){ 
	
	//alert("otpUrl:: "+otpUrl+" , msisdn: "+$("#msisdn").val()+" , serviceId: "+serviceId);
    //event.preventDefault(); 
    $.ajax({
        url: otpUrl+"?msisdn="+$("#msisdn").val()+"&serviceid="+serviceId,
        type: 'GET',       
        data: {
            format: 'jsonp'            
        },
        success: function(response) {        	
        	var json = $.parseJSON(response);   
        	//alert("json : "+json.send); 
        	if(json.send==true){
             alert("OTP has been sent to your mobile number");     
        	}else{
        	 alert("Some technical issue to send otp. please try after some time ");
        	}
        	
        },
    error: function(response) {
        alert("Some technical issue to send otp. please try after some time ");
    }
    });
    return false; // for good measure
};

</script>
<br><br><br><br>
  <form action="../cnt/validateotp" method="post">
		<table border="0" align="center" style="width:95%;text-align:center;font-size:15px;">
		
		<c:if test="${info!=null}">
		<tr >
		<td  colspan="2">${info}</td>
		
		</tr>
		</c:if>
		<tr >
		<td width="50%" align="right">Msisdn *&nbsp;</td>
		<td width="50%" align="left"><input id="msisdn" type="text" name="msisdn"> </td>
		</tr>
		<tr >
		<td width="50%" align="right">&nbsp;</td>
		<td align="left">
		<a href="#" onclick="sendOTP('${otpUrl}','${portal.serviceIds[0]}')">Send OTP</a></td>		
		</tr>
		<tr>
		<td width="50%" align="right">OTP *&nbsp;</td>
		<td width="50%" align="left"><input id="otp" type="text" name="otp">
		<input id="pid" type="hidden" name="pid" value="${pid}">
		 </td>
		</tr>
		<tr >
		<td colspan="2" align="right">&nbsp;</td>		
		</tr>
		<tr>
		<td colspan="2" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="validate" type="submit" value="Validate"></td>
		</tr>
	</table>
	</form>
	<br><br><br><br>
<%@ include file="footer.jsp" %>				