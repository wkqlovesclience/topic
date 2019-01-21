<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <title>���������޸�</title>
    <script type="text/javascript">
    function validateIndexUpdate(){
	    var HotSearchIndex = $('#HotSearchIndex').val();
	    var priority = $('#priority').val();
	    if(HotSearchIndex == ''){
	        alert('��������Ϊ�գ���������д');
	        $('#HotSearchIndex').focus();
	        return false;
	    }
	    if(priority == ''){
	        alert('�������ȼ�Ϊ�գ���������д');
	        $('#priority').focus();
	        return false;
	    }
	}
    </script>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>���������޸�</h3>
        </div>
        <div class="bd clearfix">
        <div class="container-1">
        <h3>������Ϣ</h3>
        <div class="line-1"></div>
         <form name="HotSearchIndexUpdateform" action="${ctx}/HotSearchIndex/update.action" method="post" onsubmit="return validateIndexUpdate();">
            <table class="tb-1" style="margin-left: 30px;" width="400">
                <tbody>
                <tr>
                    <td width="100">���ѱ�ţ�</td>
                    <td>
                        ${hotSearchIndexEntity.hotSearchId }
                        <input type="hidden" name="hotSearchIndexEntity.hotkeyId" value="${hotSearchIndexEntity.hotSearchId}" />
                        <input type="hidden" name="hotSearchIndexEntity.id" value="${hotSearchIndexEntity.id}" />
                    </td>
                </tr>
                <tr>
                    <td>���ѱ��⣺</td>
                    <td>${HotSearchIndexEntity.hotSearchTitle }</td>
                </tr>
                <tr>
                    <td>����������</td>
                    <td><s:select id="HotSearchIndex" name="hotSearchIndexEntity.HotSearchIndex" cssStyle="width:80px;" theme="simple" value="HotSearchIndexEntity.HotSearchIndex" list="#{'':'','A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N',
                            'O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z','0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9'}">
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;���ȼ�&nbsp;��</td>
                    <td><s:select id="priority" name="hotSearchIndexEntity.priority" theme="simple" cssStyle="width:80px;" value="HotSearchIndexEntity.priority" list="#{'':'','0':'��','1':'��','2':'��'}"></s:select></td>
                </tr>
                <tr>
                    <td>&nbsp;������&nbsp;��</td>
                    <td>${hotSearchIndexEntity.operator }<input type="hidden" name="hotSearchIndexEntity.operator" value="${hotSearchIndexEntity.operator }" /></td>
                </tr> 
                <tr>
                    <td>����״̬��</td>
                    <td>
                        <s:select id="status" name="hotSearchIndexEntity.status" value="hotSearchIndexEntity.status" cssStyle="width:80px;" theme="simple" list="#{'0':'ʧЧ','1':'��Ч' }">
                        </s:select>
                    </td>
                </tr> 
                <tr>
                    <td>����վ�㣺</td>
                    <td>
                        <c:choose>
                            <c:when test="${hotSearchIndexEntity.site eq 'gome'}">����</c:when>
                            <c:otherwise>${hotSearchIndexEntity.site}</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="button" value="����" onclick="location.href='${ctx}/HotSearchIndex/list.action'"/> 
                        <input type="submit" value="�޸�" />
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
