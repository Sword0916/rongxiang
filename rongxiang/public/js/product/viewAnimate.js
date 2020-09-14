(function($){
	$.fn.viewAnimate=function(option){
		var defaults={imgScale:1.2},
		opts=$.extend({},defaults,option),
		_this=$(this),
		p_article=_this.find(opts.p_article),
		width,
		height;
		function init(){
			p_article.width((_this.width()-(opts.columnNum-1)*opts.spaceW)/opts.columnNum)
			p_article.height(function(){
				return $(this).width()/opts.percent
			})
			width=p_article.width(),
			height=p_article.height()
			_this.height(function(){
				// return opts.lineNum*(p_article.height()+opts.spaceH)-opts.spaceH
				if(opts.lineNum*opts.columnNum<=p_article.length){
					return opts.lineNum*(p_article.height()+opts.spaceH)-opts.spaceH
				}else{
					return Math.ceil(p_article.length/opts.columnNum)*(p_article.height()+opts.spaceH)-opts.spaceH
				}
			})
		}
		p_article.mouseenter(function(e){
			mouseEffect(e,$(this));
		}).mouseleave(function(e){
			mouseEffect(e,$(this));
		})
		var i=0,n=opts.columnNum,col=0;//i,n选择li的范围，起始索引和结束索引，col当前行
		function expand(){//每一行显示完毕后执行下一行展开效果，递归
			p_article.slice(i,n).css({top:height*col+opts.spaceH*col+"px"}).each(function(m){
				$(this).animate({left:m*opts.spaceW+m*width+"px",opacity:1},opts.exDuration,function(){
					if(m==opts.columnNum-1){
						i=n;
						n=i+opts.columnNum;
						col++;
						expand();
					}	
				})
			})	
		}
		init()
		expand();
		function mouseEffect(e,ele) {
			var _that=ele,
			_desc=_that.find(opts.p_container).stop(true),
			left=e.pageX-_that.offset().left,
			top=e.pageY-_that.offset().top,
			right=width-left,
			bottom=height-top,
			_min=Math.min(left,top,right,bottom),
			img=_that.find("img"),
			animateProp={left:0,top:0},
			spos=_min==left?{left:-width,top:0}:_min==top?{left:0,top:-height}:_min==right?{left:width,top:0}:{left:0,top:height};//鼠标划入之前的初始化位置
			animation();
			function animation(){
				var zoomWidth=width*opts.imgScale,
				zoomHeight=height*opts.imgScale,
				spaceX=(zoomWidth-width)/2,
				spaceY=(zoomHeight-height)/2;
				if(e.type=="mouseleave"){
					console.log(spos)
					_desc.animate(spos,opts.duration);
					img.stop().animate({width:width+"px",height:height+"px",left:0,top:0},100);
				}else if(e.type=="mouseenter"){
					_desc.css(spos).animate(animateProp,opts.duration);
					img.stop().animate({width:zoomWidth+"px",height:zoomHeight+"px",left:-spaceX+"px",top:-spaceY+"px"},300);
				}
			}
		}
	}
})(jQuery);