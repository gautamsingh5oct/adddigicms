


<div class="clearfix"></div>
<footer>
  <div class="container">
    <div class="row">
      <div class="col-md-12 ftr_clm quick">

      <ul>
<%--                  <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=comedy_videos">Comedy Videos</a></li> --%>
<%--                 <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=arabic-videos">Arabic Videos</a></li> --%>
<%--                 <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=hollywood-videos">Hollywood Videos</a></li> --%>
<%--                 <li><a href="${pageContext.request.contextPath}/cnt/gccat/portal/p?pid=${portal.id}">Home</a></li> --%>
<%--                 <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=pilates-videos">Pilates Videos</a></li> --%>
<%--                 <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=sport-videos">Sports Videos</a></li> --%>
<%--                 <li><a href="${pageContext.request.contextPath}/cnt/gccat/catcontent/p?pid=${portal.id}&page=yoga-videos">Yoga Videos</a></li> --%>
          
          </ul>
     
      </div>

    

    

    </div>
  </div>
</footer>
<section class="copyright">
  <div class="container">
    COPYRIGHT © 2022 GLAMCLUB
  </div>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/glamclub/js/owl.carousel.min.js"></script>

<a id="back-to-top" href="#" class="btn back-to-top" role="button"
 title="Click to return on the top page" data-toggle="tooltip" 
 data-placement="left"><span class="glyphicon glyphicon-chevron-up"></span></a>

<script type="text/javascript">
 $('.first').owlCarousel({
    loop:true,
    autoplay:true,
    margin:10,
    nav:true,
    responsive:{
        0:{
            items:3,
            nav:false
        },
        600:{
            items:3,
            nav:false
        },
        1000:{
            items:5
        }
    }
})


 $('.second').owlCarousel({
    loop:true,
    autoplay:true,
    margin:10,
    nav:true,
    responsive:{
        0:{
            items:2,
            nav:false
        },
        600:{
            items:2,
            nav:false
        },
        1000:{
            items:4
        }
    }
})





            $(document).ready(function(){
     $(window).scroll(function () {
            if ($(this).scrollTop() > 200) {
                $('#back-to-top').fadeIn();
            } else {
                $('#back-to-top').fadeOut();
            }
        });
        // scroll body to 0px on click
        $('#back-to-top').click(function () {
            $('#back-to-top').tooltip('hide');
            $('body,html').animate({
                scrollTop: 0
            }, 800);
            return false;
        });
        
        $('#back-to-top').tooltip('show');

});


</script>
</body>
</html>