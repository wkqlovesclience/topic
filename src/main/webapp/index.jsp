<%@ page language="java" import="com.coo8.btoc.config.ReloadableConfig" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>后台登录</title>
<link href="http://css.gomein.net.cn/topics/css/OPTBG.css" rel="stylesheet" type="text/css" />
<style>
.Login {
    background: url("http://app.gomein.net.cn/topics/images/LoginspBg1.gif") no-repeat scroll left top transparent;
    height: 320px;
    margin: 113px auto 0;
    padding-top: 118px;
    width: 498px;
}
</style>
</head>
<body style="background:#545e68 top left repeat-x; margin:0 auto;">
<div class="Login"> 
    <form name="myform" method="post" id="f1">
		<div class="Enter">
			<div class="Username">
				<input type="text" name="username" id="name" />
			</div>
			<div class="Username">
				<input type="password" name="password" id="password" />
			</div>
		</div>
		<div class="LogIcon">
			<a href="javascript:;" onclick="checklogin()"><img
					src="http://app.gomein.net.cn/topics/images/LogIcon.gif" />
			</a>
		</div> 	 
    </form>
<div class="Tel">开通账号、意见建议，请联系张晓婷。联系电话：010-57303665</div>
</div>
<script type="text/javascript" src="http://js.gomein.net.cn/topics/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	function checklogin(){
		if(validate()){
        	submit();
        }
	}	
	
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
        if(e.keyCode==13){
        	if(validate()){
        		submit();
        	}
        }
    }
    
    function validate(){
    	var name=$('#name').val();
    	var password=$('#password').val();
    		if(name==""){
    			alert("登录名不能为空");
    			$("#name").focus();
    			return false;
    		}
    		
    		if(password==""){
    			alert("密码不能为空");
    			$("#password").focus();
    			return false;
    		}
    		
    		return true	;
    }
    
    function submit(){
    	var f1 = document.getElementById("f1");
    	f1.action="${ctx}/admin/role/loginCheck.action";
    	f1.submit();
    }

</script>
</body>
</html>
