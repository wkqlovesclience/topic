<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <title>热搜索引修改</title>
    <script type="text/javascript">
    function validateIndexUpdate(){
	    var hotkeyIndex = $('#hotkeyIndex').val();
	    var priority = $('#priority').val();
	    if(hotkeyIndex == ''){
	        alert('热搜索引为空，请重新填写');
	        $('#hotkeyIndex').focus();
	        return false;
	    }
	    if(priority == ''){
	        alert('热搜优先级为空，请重新填写');
	        $('#priority').focus();
	        return false;
	    }
	}
    </script>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>热搜索引修改</h3>
        </div>
        <div class="bd clearfix">
        <div class="container-1">
        <h3>基本信息</h3>
        <div class="line-1"></div>
         <form name="hotkeyIndexUpdateform" action="${ctx}/HotkeyIndex/update.action" method="post" onsubmit="return validateIndexUpdate();">
            <table class="tb-1" style="margin-left: 30px;" width="400">
                <tbody>
                <tr>
                    <td width="100">热搜编号：</td>
                    <td>
                        ${hotkeyIndexEntity.hotkeyId }
                        <input type="hidden" name="hotkeyIndexEntity.hotkeyId" value="${hotkeyIndexEntity.hotkeyId}" />
                        <input type="hidden" name="hotkeyIndexEntity.id" value="${hotkeyIndexEntity.id}" />
                    </td>
                </tr>
                <tr>
                    <td>热搜标题：</td>
                    <td>${hotkeyIndexEntity.hotkeyTitle }</td>
                </tr>
                <tr>
                    <td>热搜索引：</td>
                    <td><s:select id="hotkeyIndex" name="hotkeyIndexEntity.hotkeyIndex" cssStyle="width:80px;" theme="simple" value="hotkeyIndexEntity.hotkeyIndex" list="#{'':'','A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N',
                            'O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z','0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9'}">
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;优先级&nbsp;：</td>
                    <td><s:select id="priority" name="hotkeyIndexEntity.priority" theme="simple" cssStyle="width:80px;" value="hotkeyIndexEntity.priority" list="#{'':'','0':'低','1':'中','2':'高'}"></s:select></td>
                </tr>
                <tr>
                    <td>&nbsp;操作者&nbsp;：</td>
                    <td>${hotkeyIndexEntity.operator }<input type="hidden" name="hotkeyIndexEntity.operator" value="${hotkeyIndexEntity.operator }" /></td>
                </tr> 
                <tr>
                    <td>热搜状态：</td>
                    <td>
                        <s:select id="status" name="hotkeyIndexEntity.status" value="hotkeyIndexEntity.status" cssStyle="width:80px;" theme="simple" list="#{'0':'失效','1':'有效' }">
                        </s:select>
                    </td>
                </tr> 
                <tr>
                    <td>热搜站点：</td>
                    <td>
                        <c:choose>
                            <c:when test="${hotkeyIndexEntity.site eq 'gome'}">国美</c:when>
                            <c:otherwise>${hotkeyIndexEntity.site}</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="button" value="返回" onclick="location.href='${ctx}/HotkeyIndex/list.action'"/> 
                        <input type="submit" value="修改" />
                    </td>
                </tr>
                </tbody>
                </table>
            </form>
            </div>
        </div>
    </div>
  </body>
</html>
