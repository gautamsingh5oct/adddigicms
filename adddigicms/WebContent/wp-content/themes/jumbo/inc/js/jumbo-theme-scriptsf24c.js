jQuery(document).ready(function($) {

// Responsive Menu
(function (a) {
    var d = a(".main-nav li.current-menu-item a").html(),
    d = a(".main-nav li.current_page_item a").html();
    if (a("span").hasClass("custom-mobile-menu-title")) d = a("span.custom-mobile-menu-title").html();
    else if ("undefined" == typeof d || null === d) d = a("body").hasClass("home") ? a("#logo span").hasClass("site-name") ? a("#logo .site-name a").html() : a("#logo img").attr("alt") : a("body").hasClass("woocommerce") && a("h1").hasClass("page-title") ? a("h1.page-title").html() : a("body").hasClass("woocommerce") && a("h1").hasClass("entry-title") ?
        a("h1.entry-title").html() : a("body").hasClass("archive") && a("h6").hasClass("title-archive") ? a("h6.title-archive").html() : a("body").hasClass("search-results") && a("h6").hasClass("title-search-results") ? a("h6.title-search-results").html() : a("body").hasClass("page-template-blog-excerpt-php") && a("li").hasClass("current_page_item") ? a("li.current_page_item").text() : a("body").hasClass("page-template-blog-php") && a("li").hasClass("current_page_item") ? a("li.current_page_item").text() : a("h1").hasClass("post-title") ?
    a("h1.post-title").html() : "&nbsp;";
    a(".main-nav").append('<a id="mobile_menu_button"></a>');
    a(".main-nav").prepend('<div id="responsive_current_menu_item">' + d + "</div>");
    a("a#mobile_menu_button, #responsive_current_menu_item").click(function () {
        a(".js .main-nav .menu").slideToggle(function () {
            a(this).is(":visible") ? a("a#mobile_menu_button").addClass("responsive-toggle-open") : (a("a#mobile_menu_button").removeClass("responsive-toggle-open"), a(".js .main-nav .menu").removeAttr("style"))
        })
    })
})(jQuery);

(function (a) {
    a("html").click(function () {
        a("a#mobile_menu_button").hasClass("responsive-toggle-open") && a(".js .main-nav .menu").slideToggle(function () {
            a("a#mobile_menu_button").removeClass("responsive-toggle-open");
            a(".js .main-nav .menu").removeAttr("style")
        })
    })
})(jQuery);

// Stop propagation on click on menu.
jQuery('.main-nav').click(function (event) {
    var pathname = window.location.pathname;
    if (pathname != '/wp-admin/customize.php') {
        event.stopPropagation();
    }
});

//Responsive iframe - HTML Games
$('.td-embed-container').fitFrame();

// qtip2 tooltip
$( 'div[title!=""]' ).qtip({

  position: {
    effect: false,
              my: 'center left',  // Position my top left...
              at: 'center right', // at the bottom right of...
             target: 'mouse', // Track the mouse as the positioning target
             adjust: { x: 60, y: 40 } // Offset it slightly from under the mouse
           }

         });

// making fitvids to work only on max-width: 767px
$(function() {
  "use strict";
    $(window).resize(function(){
          if(window.matchMedia('(max-width: 767px)')){
              $(".showfitvids").fitVids();
          }
    });
});

// HTML5 full screen with target content div
var target = $('#full-screen-wrapp')[0]; // Get DOM element from jQuery collection
$('.td-fullscreen').click(function () {
    if (screenfull.enabled) {
        screenfull.toggle(target);
         $('#td-game-wrap').removeClass('showfitvids');
    }
});

// FitVids - Target content div.
$("#homepage-wrap, #widgets, #content").fitVids();

// Facebook
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.5&appId=218976484825837";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


// Sticky menu
$(".td-sticky").sticky({topSpacing:0});

// Sub menu of mega menu navigation
$( '.td-mega-wrap .main-nav .menu-item' ).hover(
  function() {
    $( '> .sub-menu' , this )
    .stop( true, true )
    .fadeIn( { duration: 0 } );

  },

  function() {
    $( '> .sub-menu' , this )
    .stop( true, true )
    .fadeOut( { duration: 0 } );
  } );

// search toggle
  $(".click-search").click(function(){
    $(".td-expand").slideToggle(0);
  });

// Add a class to all linked images to initialize magnific popup
$('img').parent('.td-popup-hook a').addClass('td-popup-image');
// disable magnific popup for linked images that have td-no-lightbox class
$('img').parent('.td-no-lightbox, .td-no-lightbox a, .quads-location, .quads-location a').removeClass('td-popup-image');


// Initializing Magnific Popup
$('.post-entry').magnificPopup({
   delegate: '.td-popup-image', // child items selector, by clicking on it popup will open
   type: 'image',
   gallery:{
    enabled:true
  },
  zoom: {
    enabled: true,
    duration: 300,
    opener: function(element) {
      return element.find("img");
    }
  },
});

// skip-link-focus-fix.js https://github.com/Automattic/_s/blob/master/js/skip-link-focus-fix.js
( function() {
    var is_webkit = navigator.userAgent.toLowerCase().indexOf( 'webkit' ) > -1,
        is_opera  = navigator.userAgent.toLowerCase().indexOf( 'opera' )  > -1,
        is_ie     = navigator.userAgent.toLowerCase().indexOf( 'msie' )   > -1;

    if ( ( is_webkit || is_opera || is_ie ) && 'undefined' !== typeof( document.getElementById ) ) {
        var eventMethod = ( window.addEventListener ) ? 'addEventListener' : 'attachEvent';
        window[ eventMethod ]( 'hashchange', function() {
            var element = document.getElementById( location.hash.substring( 1 ) );

            if ( element ) {
                if ( ! /^(?:a|select|input|button|textarea)$/i.test( element.tagName ) )
                    element.tabIndex = -1;

                element.focus();
            }
        }, false );
    }
})();


// Scrolling to the Top and Bottom: http://tympanus.net/codrops/2010/01/03/scrolling-to-the-top-and-bottom-with-jquery/
$(window).load(function() {

                $('#scroll_up').fadeIn('slow');
                $('#scroll_down').fadeIn('slow');

                $(window).bind('scrollstart', function(){
                    $('#scroll_up,#scroll_down').stop().animate({'opacity':'1'});
                });
                $(window).bind('scrollstop', function(){
                    $('#scroll_up,#scroll_down').stop().animate({'opacity':'0'});
                });

                $('#scroll_down').click(
                    function (e) {
                        $('html, body').animate({scrollTop: $(document).height()}, 800);
                    }
                );
                $('#scroll_up').click(
                    function (e) {
                        $('html, body').animate({scrollTop: '0px'}, 800);
                    }
                );
            });

(function($) {
"use strict";
  /**
   * Copyright 2012, Digital Fusion
   * Licensed under the MIT license.
   * http://teamdf.com/jquery-plugins/license/
   *
   * @author Sam Sehnert
   * @desc A small plugin that checks whether elements are within
   *     the user visible viewport of a web browser.
   *     only accounts for vertical position, not horizontal.
   */

  $.fn.visible = function(partial) {

      var $t            = $(this),
          $w            = $(window),
          viewTop       = $w.scrollTop(),
          viewBottom    = viewTop + $w.height(),
          _top          = $t.offset().top,
          _bottom       = _top + $t.height(),
          compareTop    = partial === true ? _bottom : _top,
          compareBottom = partial === true ? _top : _bottom;

    return ((compareBottom <= viewBottom) && (compareTop >= viewTop));

  };

})(jQuery);

// Smooth Page Scrolling: http://css-tricks.com/snippets/jquery/smooth-scrolling/
$(function() {
  $('a[href*="#"]:not([href="#"])').click(function() {
    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: target.offset().top
        }, 1000);
        return false;
      }
    }
  });
});

// Vertical Ticker
$('#vertical-ticker').totemticker({
  row_height  :   '132px',
  speed       :   800,
  interval    :   4000,
  mousestop   :   true,
  next        :   '#ticker-next',
  previous    :   '#ticker-previous'
});


// show / hide game screenshots div
 $('#td-show-hide').on('click', function () {
        if ($('.td-screens-wrap').is(':visible')) {
            $(this).text('+');
            $('.td-screens-wrap').hide();
        } else {
            $(this).text("-");
            $('.td-screens-wrap').show();
        }
    });


// Sticky Sidebar
 $(window).load(function(){
"use strict";
if ($(".td-sticky")[0]){ //different topspacing if menu is stiky
$('.td-sidebar-sticky').stickybar({
  topSpacing: 98, // Space between element and top of the viewport
  zIndex: 1, // z-index
  stopper: "#td-sticky-stopper" // Id, class, or number value
});
} else {
  $('.td-sidebar-sticky').stickybar({
  topSpacing: 48, // Space between element and top of the viewport
  zIndex: 100, // z-index
  stopper: "#td-sticky-stopper" // Id, class, or number value
});
}
});

}); //end document ready