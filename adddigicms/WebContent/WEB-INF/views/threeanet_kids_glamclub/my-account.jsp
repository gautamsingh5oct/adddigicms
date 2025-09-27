<%@ include file="header.jsp" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- Header End -->

<!-- Main Slider Start -->
<section class="slider">
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner" role="listbox">
    <!-- Slide 1 -->
    <div class="item active">
      <img src="${contentWebPrefixPath}/VideoContent/slide2.jpg" alt="">
      <div class="carousel-caption">
      <h1>My Account</h1>
      </div>
    </div>

    

  <!-- Controls -->
  <!-- <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a> -->
</div>
</section>
<!-- Main Slider End -->
<br><br><br><br><br>
 <div style="text-align:center;">
<table border="1" align="center" style="width:50%;text-align:center;font-size:20px;color:white;">
		
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
	 		<td>Msisdn</td>
	 		<td>${subscriberReg.msisdn}</td>
	 		</tr>
	 		<tr>
	 		<td>Service</td>
	 		<td>
	 		${mapServiceIdToService[subscriberReg.serviceId].serviceDesc}</td>
	 		</tr>
	 		<tr>
	 		<td>Valid From</td>
	 		<td> <fmt:formatDate pattern = "yyyy/MM/dd H:m " 
         value = "${subscriberReg.validityFrom}"/></td>
         </tr>
         <tr>
        <td>Valid To</td>
	 		<td><fmt:formatDate pattern = "yyyy/MM/dd H:m " 
         value = "${subscriberReg.validityTo}"/></td>	
         </tr> 	
          <tr>
        <td>Status</td>
	 		<td>${subscriberReg.statusDescp}</td>
	 		</tr>
	 		 <tr>
        <td>Unsubscribe</td>	 		
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
<!-- Mid Part Start -->
<section class="main_content">
	<div class="container">
	<div class="row">
      
    </div>

	</div>
</section>
<!-- Mid Part End -->
<%@ include file="footer.jsp" %>
