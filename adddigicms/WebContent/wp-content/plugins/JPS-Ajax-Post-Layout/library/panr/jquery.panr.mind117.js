/* 	panr - v0.0.1
 	jQuery plugin for zoom & pan elements on mousemove
	by Robert Bue (@robert_bue)
	Powered by the Greensock Tweening Platform
	http://www.greensock.com
	Greensock License info at http://www.greensock.com/licensing/ 	
 	Dual licensed under MIT and GPL.
*/
!function(e){function t(t,o){this.element=t,this.settings=e.extend({},a,o),this._defaults=a,this._name=n,this.init()}var n="panr",a={sensitivity:30,scale:!0,scaleOnHover:!1,scaleTo:1.1,scaleDuration:.25,panY:!0,panX:!0,panDuration:1.25,resetPanOnMouseLeave:!1,onEnter:function(){},onLeave:function(){}};t.prototype={init:function(){var t,n,a,o,s,i,r=this.settings,c=e(this.element),l=c.width(),u=(c.height(),c.width()-r.sensitivity),v=(l-u)/u;(r.scale||!r.scaleOnHover&&r.scale)&&TweenMax.set(c,{scale:r.scaleTo}),"string"===jQuery.type(r.moveTarget)&&(r.moveTarget=e(this.element).parent(r.moveTarget)),r.moveTarget||(r.moveTarget=e(this.element)),r.moveTarget.on("mousemove",function(i){t=i.pageX-c.offset().left,n=i.pageY-c.offset().top,r.panX&&(o={x:-v*t}),r.panY&&(s={y:-v*n}),a=e.extend({},o,s),TweenMax.to(c,r.panDuration,a)}),r.moveTarget.on("mouseenter",function(){r.scaleOnHover&&TweenMax.to(c,r.scaleDuration,{scale:r.scaleTo}),r.onEnter(c)}),r.scale&&(r.scaleOnHover||r.scale)?r.resetPanOnMouseLeave&&(i={x:0,y:0}):i={scale:1,x:0,y:0},r.moveTarget.on("mouseleave",function(){TweenMax.to(c,r.scaleDuration,i),r.onLeave(c)})}},e.fn[n]=function(a){return this.each(function(){e.data(this,"plugin_"+n)||e.data(this,"plugin_"+n,new t(this,a))})}}(jQuery,window,document);