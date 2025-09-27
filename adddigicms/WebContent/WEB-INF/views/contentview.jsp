<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>Portal</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	</head>
	<body background="${pageContext.request.contextPath}/images/background/BEAUTY_WORLD.png" onload="onLoad('${contentWebPrefixPath}${portal.portalBanner2}')">
		<script>
			function showdata(){
				var x = document.getElementsByClassName("hidedata");
				for(var i=0;i<=x.length;i++){
					x[i].style.display = "block";
				}
			}
			function onLoad( bgImg){
				
			$('#header').css("background-image", "url("+bgImg+")");  
		 }
		</script>
		<div class="container-fluid">
			<header id="header">
				<div class="container">
					<div class="row">
						<div class="col-sm-8">
							<h2 class="shadow">${portal.portalBannerText}</h2>
						</div>
					</div>
					<div class="col-sm-4"></div>
				</div>
			</header>
		</div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>                        
					</button>
<%-- 					<a class="navbar-brand active" href="${pageContext.request.contextPath}/cnt/portal?pid=${portal.id}&tc=0&sub=${sub}">Home</a> --%>
	<a class="navbar-brand active" href="${prevUrl}">Home</a> --%>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li>
							 <a class="navbar-brand active" href="../../myaccount?pid=${portal.id}">User Account</a>
						</li>	
					</ul>
					<ul class="nav navbar-nav navbar-right">
					</ul>
				</div>
			</div>
		</nav>
		<script>
			$(document).ready(function(){
				$("#user").click(function(){
					alert("User not subscribed");
				});
			});
		</script>
		<div>
			<video class="videoplayer" type="video/mp4" src="${contentWebPrefixPath}${cmsContent.filePath}" 
			controls="controls"></video>
			<a href="${pageContext.request.contextPath}/cnt/download/${portal.id}/${cmsContent.id}" class="btndownload">Download</a>
		</div>
		<footer class="container-fluid text-center footer">
			<p></p>
			<p>Copyright © 2018</p>
		</footer>
	</body>
</html>