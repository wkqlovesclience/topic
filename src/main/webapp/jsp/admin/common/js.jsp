<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/model-alert1.js"></script>
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/public.js"></script>
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/util.js"></script>
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/ajaxfileupload.js"></script>


<script type="text/javascript">
$(document).ready(function () {	
	$('#nav dl').css({"display":"none"})
	$('#nav li').hover(
		function () {
			$(this).addClass("now");
			$('dl', this).show();
		}, 
		function () {
			$(this).removeClass("now");
			$('dl', this).hide();			
		}
	);
});
</script>

<script type="text/javascript">	
	$(document).ready(function(){		
    //hide
    closeLeft();
    $("#showitems").attr("hide","h");
	$("#showpin").attr("hide","h");
	$("#showmuban").attr("hide","h");
	$("#showorder").attr("hide","h");
	$("#showkui").attr("hide","h");
	$("#showquan").attr("hide","h");
	$("#showyan").attr("hide","h");
	$("#showgao").attr("hide","h");
	$("#showuser").attr("hide","h");
	$("#showapply").attr("hide","h");
	$("#showcampay").attr("hide","h");
	$("#showTitle").attr("hide","h");
	$("#showModel").attr("hide","h");
	$("#showBack").attr("hide","h");
	$("#showHotkey").attr("hide","h");
	$("#showAladdin").attr("hide","h");
	$("#showSiteMap").attr("hide","h");
	$("#\\/showRank").attr("hide","h");
	$("#showGomeStores").attr("hide","h");
	$("#showAllHotKey").attr("hide","h");
	$("#showBrand").attr("hide","h");
	
	function openMenu(classid,domid){
		closeLeft();
		closeimg();
		if($("#"+domid).attr("hide")=='h'){
			$("#showitems").attr("hide","h");
			$("#showpin").attr("hide","h");
			$("#showmuban").attr("hide","h");
			$("#showorder").attr("hide","h");
			$("#showkui").attr("hide","h");
			$("#showquan").attr("hide","h");
			$("#showyan").attr("hide","h");
			$("#showgao").attr("hide","h");
			$("#showuser").attr("hide","h");
			$("#showapply").attr("hide","h");
			$("#showcampay").attr("hide","h");
			$("#showTitle").attr("hide","h");
			$("#showModel").attr("hide","h");
			$("#showBack").attr("hide","h");
			$("#showHotkey").attr("hide","h");
			$("#showAladdin").attr("hide","h");
			$("#showSiteMap").attr("hide","h");
			$("#\\/showRank").attr("hide","h");
			$("#showGomeStores").attr("hide","h");
			$("#showAllHotKey").attr("hide","h");
			$("#showBrand").attr("hide","h");
			$("."+classid).show();	
			var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			$("#"+domid).parents("td").prev().children("img").attr("src",simg);
			var param=$("#"+domid).attr("id");	
			$("#"+domid).attr("hide","s");
		}
		else if($("#"+domid).attr("hide")=='s'){
			$("."+classid).hide();	
			var simg="http://app.gomein.net.cn/topics/images/nav_1.gif";
			$("#"+domid).parents("td").prev().children("img").attr("src",simg);
			var param=$("#"+domid).attr("id");	
			setCookie("daohang",param);
			$("#"+domid).attr("hide","h");
		}
	}

	$("#showTitle").bind("click", function(){
		openMenu("slideTitle","showTitle");
	});
	$("#showModel").bind("click", function(){
		openMenu("slideModel","showModel");
	});
	$("#showHotkey").bind("click", function(){
        openMenu("slideHotkey","showHotkey");
    });
    $("#showAladdin").bind("click", function(){
        openMenu("slideAladdin","showAladdin");
    });
    $("#showSiteMap").bind("click", function(){
        openMenu("slideSiteMap","showSiteMap");
    });
    $("#\\/showRank").bind("click", function(){
        openMenu("\\/slideRank","\\/showRank");
    });
     $("#showGomeStores").bind("click", function(){
        openMenu("slideGomeStores","showGomeStores");
    });
	$("#showBack").bind("click", function(){
		openMenu("slideBack","showBack");
	});
	$("#showorder").bind("click", function(){
		openMenu("slideorder","showorder");
	});
	$("#showitems").bind("click", function(){
		openMenu("slideitems","showitems");
	});
	$("#showpin").bind("click", function(){
		openMenu("slidepin","showpin");
	});
	$("#showmuban").bind("click", function(){
		openMenu("slidemuban","showmuban");
	});
	$("#showorder").bind("click", function(){
		openMenu("slideorder","showorder");
	});
	$("#showkui").bind("click", function(){
		openMenu("slidekui","showkui");
	});
	$("#showquan").bind("click", function(){
		openMenu("slidequan","showquan");
	});
	$("#showyan").bind("click", function(){
		openMenu("slideyan","showyan");
	});
	$("#showgao").bind("click", function(){
		openMenu("slidegao","showgao");
	});
	$("#showuser").bind("click", function(){
		openMenu("slideuser","showuser");
	});
	$("#showapply").bind("click", function(){
		openMenu("slideapply","showapply");
	});
	$("#showcampay").bind("click", function(){
		openMenu("slidecampay","showcampay");
	});
	$("#showAllHotKey").bind("click", function(){
		openMenu("slideAllHotKey","showAllHotKey");
	});
	$("#showBrand").bind("click", function(){
		openMenu("slideBrand","showBrand");
	});
	
	function closeLeft(){
		$(".slideitems").hide();
		$(".slidepin").hide();
		$(".slidemuban").hide();
		$(".slideorder").hide();
		$(".slidekui").hide();
		$(".slidequan").hide();
		$(".slideyan").hide();
		$(".slidegao").hide();
		$(".slideuser").hide();
		$(".slideapply").hide();
		$(".slidecampay").hide();
		$(".slideTitle").hide();
		$(".slideModel").hide();
		$(".slideHotkey").hide();
		$(".slideAladdin").hide();
		$(".slideSiteMap").hide();
		$(".\\/slideRank").hide();
		$(".slideGomeStores").hide();
		$(".slideBack").hide();
		$(".slideAllHotKey").hide();
		$(".slideBrand").hide();
		
	}
	function closeimg(){
		var simg="http://app.gomein.net.cn/topics/images/nav_1.gif";
		
		$("#showitems").parents("td").prev().children("img").attr("src",simg);
		$("#showpin").parents("td").prev().children("img").attr("src",simg);
		$("#showmuban").parents("td").prev().children("img").attr("src",simg);
		$("#showorder").parents("td").prev().children("img").attr("src",simg);
		$("#showkui").parents("td").prev().children("img").attr("src",simg);
		$("#showquan").parents("td").prev().children("img").attr("src",simg);
		$("#showyan").parents("td").prev().children("img").attr("src",simg);
		$("#showgao").parents("td").prev().children("img").attr("src",simg);
		$("#showuser").parents("td").prev().children("img").attr("src",simg);
		$("#showapply").parents("td").prev().children("img").attr("src",simg);
		$("#showcampay").parents("td").prev().children("img").attr("src",simg);
		$("#showTitle").parents("td").prev().children("img").attr("src",simg);
		$("#showModel").parents("td").prev().children("img").attr("src",simg);
		$("#showHotkey").parents("td").prev().children("img").attr("src",simg);
		$("#showAladdin").parents("td").prev().children("img").attr("src",simg);
		$("#showSiteMap").parents("td").prev().children("img").attr("src",simg);
		$("#\\/showRank").parents("td").prev().children("img").attr("src",simg);
		$("#showGomeStores").parents("td").prev().children("img").attr("src",simg);
		$("#showBack").parents("td").prev().children("img").attr("src",simg);
		$("#showAllHotKey").parents("td").prev().children("img").attr("src",simg);
		$("#showBrand").parents("td").prev().children("img").attr("src",simg);
		
	}
	});
</script>
	
<script type="text/javascript">
	function setCookie(name,value)
	{	    
	    var exp  = new Date();    
	    exp.setTime(exp.getTime() + 5*24*60*60*1000);
	    document.cookie=name+"="+ escape(value)+";expires="+exp.toGMTString()+";path=/admin";
	    
	}
	function clickchild(obj){		
		setCookie("daochild",obj.id);
	}
	
</script>
   
<script type="text/javascript">
    $(document).ready(function(){
        // dao hang cookie
        getCookie();
    });

	function showCookie(name){
	
	    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	     if(arr != null) 
		     return unescape(arr[2]); 
	     return null;
	}
    
    function getCookie(){		
		
		var param=showCookie("daohang");
		var child=showCookie("daochild");
		if(param!=null){			
			
			if(param=="showitems"){					
			   $(".slideitems").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showitems").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showpin"){
			   $(".slidepin").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showpin").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showmuban"){
			   $(".slidemuban").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showmuban").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showorder"){
			   $(".slideorder").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showorder").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showkui"){
			   $(".slidekui").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showkui").parents("td").prev().children("img").attr("src",simg);
			}
			   
			else if(param=="showquan"){
			   $(".slidequan").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showquan").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showyan"){
			   $(".slideyan").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showyan").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showgao"){
			   $(".slidegao").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showgao").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showuser"){
			   $(".slideuser").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showuser").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showapply"){
			   $(".slideapply").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showapply").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showTitle"){
			   $(".slideTitle").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showTitle").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showModel"){
			   $(".slideModel").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showModel").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showHotkey"){
               $(".slideHotkey").show();
               $(document.getElementById(child)).css("font-weight","bold");
               var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
               $("#showHotkey").parents("td").prev().children("img").attr("src",simg);
            }
            else if(param=="showAladdin"){
               $(".slideAladdin").show();
               $(document.getElementById(child)).css("font-weight","bold");
               var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
               $("#showAladdin").parents("td").prev().children("img").attr("src",simg);
            }
            else if(param=="showSiteMap"){
               $(".slideSiteMap").show();
               $(document.getElementById(child)).css("font-weight","bold");
               var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
               $("#showSiteMap").parents("td").prev().children("img").attr("src",simg);
            }
            else if(param=="/showRank"){
               $(".\\/slideRank").show();
               $(document.getElementById(child)).css("font-weight","bold");
               var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
               $("#\\/showRank").parents("td").prev().children("img").attr("src",simg);
            }
            else if(param=="showGomeStores"){
               $(".slideGomeStores").show();
               $(document.getElementById(child)).css("font-weight","bold");
               var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
               $("#showGomeStores").parents("td").prev().children("img").attr("src",simg);
            }
			else if(param=="showBack"){
			   $(".slideBack").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showBack").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showAllHotKey"){
			   $(".slideAllHotKey").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showAllHotKey").parents("td").prev().children("img").attr("src",simg);
			}
			else if(param=="showBrand"){
			   $(".slideBrand").show();
			   $(document.getElementById(child)).css("font-weight","bold");
			   var simg="http://app.gomein.net.cn/topics/images/nav_0.gif";
			   $("#showBrand").parents("td").prev().children("img").attr("src",simg);
			}
		}
	}
</script>