
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>GlamClub - All Fun Here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Oswald|PT+Sans" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Karla" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/glamclub/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/glamclub/css/owl.carousel.min.css">	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/glamclub/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	</style>
<script type="text/javascript">
$(document).ready(function() {
	
jQuery("a[href$='mp4']").click(function(){
	if('${sub}'!=1){
		window.location.href='${portal.campaignUrl}';
		return false;
	}
	return true;
})
});

function unsub(id,unsubUrl){ 
   // alert("unsub "+unsubUrl);
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
</head>
<body>	

<!-- Header Start -->
<header>
<div class="top_header">
<div class="container">

  <nav class="navbar navbar-default">
    <div class="navbar-header">

<a class="logo" href="index.html"><img src="${contentWebPrefixPath}/VideoContent/logo.png"></a>

      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>


    <div class="collapse navbar-collapse js-navbar-collapse">
      <ul class="nav navbar-nav">

        <li><a href="${pageContext.request.contextPath}/cnt/gccat/portal/p?pid=${portal.id}">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/cnt/gccat/myaccount?pid=${portal.id}&page=my-account">My Account</a></li>
        <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=faq">FAQ</a></li>
        <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=term-condition">Term & Conditions</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">CATEGORIES <span class="glyphicon glyphicon-menu-down pull-right"></span></a>

          <ul class="dropdown-menu">
        
                <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=comedy_videos">Comedy Videos</a></li>
                <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=arabic-videos">Arabic Videos</a></li>
                <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=hollywood-videos">Hollywood Videos</a></li>
                <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=kids-videos">Kids Videos</a></li>
                <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=pilates-videos">Pilates Videos</a></li>
                <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=sport-videos">Sports Videos</a></li>
                <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=yoga-videos">Yoga Videos</a></li>
          </ul>

        </li>
      

        
      </ul>

    </div>
    <!-- /.nav-collapse -->
  </nav>


</div>
</div>


</header>
