<%@ include file="header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- Header End -->

<!-- Main Slider Start -->
<section class="slider" dir="rtl">
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner" role="listbox">
    <!-- Slide 1 -->
    <div class="item active">
      <img src="${contentWebPrefixPath}/VideoContent/slide2.jpg" alt="">
      <div class="carousel-caption">
      <h1>حسابي</h1>
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
 <div style="text-align:center;" dir="rtl">
<table border="1" align="center" style="width:50%;text-align:center;font-size:20px;color:white;">
		
	  <c:if test="${subscriberRegList==null or subscriberRegList.size()<=0}">
		<tr>
	 		<td colspan="2">
	 		رقم الهاتف المحمول هذا غير مشترك في أي خدمة لهذه البوابة
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
        <td>إلغاء الاشتراك</td>	 		
	 		<td id="${subscriberReg.subscriberId}">
	 		ولإلغاء الاشتراك أرسل غ 60 إلى 600700	 	
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
      <div class="col-md-12">
       
        
      </div>
    </div>

	</div>
</section>
<!-- Mid Part End -->
<%@ include file="footer.jsp" %>
