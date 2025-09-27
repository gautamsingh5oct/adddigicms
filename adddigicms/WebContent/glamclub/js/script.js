<script type="text/javascript">
	    $(function(){
    $(".nav .dropdown").hover(            
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeIn("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            },
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeOut("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            });
    });

$(document).ready(function() {
              var owl = $('.owl-theme');
              owl.owlCarousel({
                margin: 10,
                nav: false,
                autoplay:true,
                loop: true,
                responsive: {
                  0: {
                    items: 1
                  },
                  600: {
                    items: 3
                  },
                  1000: {
                    items: 3
                  }
                }
              })
            })

$(document).ready(function() {
              var owl = $('.clients');
              owl.owlCarousel({
                margin: 25,
                nav: false,
                dots:false,
                autoplay:true,
                loop: true,
                responsive: {
                  0: {
                    items: 1
                  },
                  600: {
                    items: 1
                  },
                  1000: {
                    items: 5
                  }
                }
              })
            })

$(document).ready(function() {
              var owl = $('.test');
              owl.owlCarousel({
                margin: 25,
                nav: false,
                dots:false,
                autoplay:true,
                loop: true,
                responsive: {
                  0: {
                    items: 1
                  },
                  600: {
                    items: 1
                  },
                  1000: {
                    items: 1
                  }
                }
              })
            })

$(document).ready(function() {
              var owl = $('.video');
              owl.owlCarousel({
                margin: 25,
                nav: false,
                dots:false,
                autoplay:true,
                loop: true,
                responsive: {
                  0: {
                    items: 1
                  },
                  600: {
                    items: 1
                  },
                  1000: {
                    items: 1
                  }
                }
              })
            })

$(document).ready(function() {
              var owl = $('.award');
              owl.owlCarousel({
                margin: 25,
                nav: false,
                dots:false,
                autoplay:true,
                loop: true,
                responsive: {
                  0: {
                    items: 1
                  },
                  600: {
                    items: 1
                  },
                  1000: {
                    items: 4
                  }
                }
              })
            })

$(document).ready(function() {
              var owl = $('.certificates');
              owl.owlCarousel({
                margin: 25,
                nav: false,
                dots:false,
                autoplay:true,
                loop: true,
                responsive: {
                  0: {
                    items: 3
                  },
                  600: {
                    items: 1
                  },
                  1000: {
                    items: 1
                  }
                }
              })
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

$(document).ready(function(e) {
        $(".side_btn").click(function(){
      $(this).toggleClass("side_btn_hide");
      $(".side_frm").toggleClass("side_frm_hide");
      //$(".side_btn, .side_frm").toggleClass("pos_abs");
    })
    });


</script>