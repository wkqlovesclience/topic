/*
 * 文件名： ListPage.java
 * 
 * 创建日期： May 10, 2010
 *
 * Copyright(C) 2010, by caowei.
 *
 * 原始作者: <a href="mailto:caowei@staff.chinabyte.com">caowei</a>
 *
 */
package com.coo8.btoc.util.taglib;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.components.Component;

import com.coo8.btoc.util.pages.PaginatedList;
import com.opensymphony.xwork2.util.ValueStack;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 分页标签
 * 
 * @author <a href="mailto:caowei@staff.chinabyte.com">caowei</a>
 * 
 * @version $Revision$
 * 
 * @since May 10, 2010
 */
public class ListPage extends Component {
	private static final Log LOG = LogFactory.getLog(ListPage.class);

	public ListPage(ValueStack stack) {
		super(stack);
	}

	private String name;
	private String style;
	private String target;
	private HttpServletRequest request;
	private Integer systemId;

	public void setName(String name) {
		this.name = name;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	private String getJsString(PaginatedList cl, int SystemId) {
		StringBuilder buffer = new StringBuilder();
		String styles = synthesizeStyle();
		if (cl != null && cl.getTotalRec() > 0) {
			// ---------------------------------
			// 加入总记录数、当前页、总页数标签
			// ---------------------------------
			if (SystemId == 1) {
				buffer.append("<h6>共" + cl.getTotalRec() + "条记录，"
						+ cl.getPageIndex() + "/" + cl.getTotalPage()
						+ "页</h6>");

			}

			if (cl.getPageIndex() > 1) {
				// ---------------------------------
				// 当前页不是第一页，
				// 还要加入上一页标签
				// ---------------------------------
				buffer.append("<a " + styles + " href=\"JavaScript:tunePage("
						+ (cl.getPageIndex() - 1) + ");\">上一页</a>");
			}
			for (int j = cl.getBegin(); j <= cl.getEnd(); j++) {
				if (j == cl.getPageIndex()) {
					buffer.append("<span >" + j + "</span>");
				} else {
					buffer
							.append("<a target=\"_self\" href=\"javascript:tunePage("
									+ j + ");\">" + j + "</a>");
				}

			}

			if (cl.getPageIndex() < cl.getTotalPage()) {
				buffer.append("<a " + styles + " href=\"JavaScript:tunePage("
						+ (cl.getPageIndex() + 1) + ");\">下一页</a>");
			}
			if (SystemId == 1) {
				if (cl.getTotalPage() > 1) {
					buffer
							.append("<label>到第 <input  name=\"ppgg\" type=\"text\" /> 页</label><tt><a href=\"javascript:goPage();\" style=\"border:none;\"><img src=\"http://app.gomein.net.cn/topics/images/qd_czrz.gif\" /></a></tt>");
					buffer.append("<script type=\"text/javascript\">");
					buffer.append("function goPage(){");
					buffer.append("var tmpPage;");

					buffer
							.append("var ppgg = document.getElementsByName(\"ppgg\");");
					buffer.append("for(i=0;i<ppgg.length;i++){");
					buffer.append("if(ppgg[i].value!=''){");
					buffer.append("tmpPage= ppgg[i].value;");
					buffer.append("break;");
					buffer.append("}");
					buffer.append("}");
					buffer.append("var reg=/[^\\d]/;");
					buffer.append("if(reg.exec(tmpPage)) return;");
					buffer.append("if(tmpPage!=null){");
					buffer.append("tunePage(tmpPage);");
					buffer.append("}");
					buffer.append("}");
					buffer.append("</script>");
				}
			}
			if (SystemId == 2) {
				buffer
						.append("<select id=\"pageSize\" name=\"pageSize\" class=\"fr\" onchange=\"changePageSize(this.value);\">");
				int[] p = { 15, 20, 25 };
				for (int w : p) {
					if (cl.getPageSize() == w) {
						buffer.append("<option selected value=\"").append(w)
								.append("\">").append(w).append("</option>");
					} else {
						buffer.append("<option  value=\"").append(w).append(
								"\">").append(w).append("</option>");
					}
				}
				buffer.append("</select>").append(
						"<font class=\"fr\">每页显示：</font> ");
			}
			
			if (SystemId == 3) {
				StringBuilder builder = new StringBuilder();
				builder
						.append("<select id=\"pageSize\" name=\"pageSize\" class=\"fr\" onchange=\"changePageSize(this.value);\">");
				int[] p = {10, 15, 20 };
				for (int w : p) {
					if (cl.getPageSize() == w) {
						builder.append("<option selected value=\"").append(w)
								.append("\">").append(w).append("</option>");
					} else {
						builder.append("<option  value=\"").append(w).append(
								"\">").append(w).append("</option>");
					}
				}
				builder.append("</select>").append(
						"<font class=\"fr\">每页显示：</font> ");
				String pagestr = builder.toString();
				// ---------------------------------
				// 显式释放资源
				// ---------------------------------
				builder.setLength(0);
				builder = null;
				return pagestr;
			}

		}
		String pagestr = buffer.toString();
		// ---------------------------------
		// 显式释放资源
		// ---------------------------------
		buffer.setLength(0);
		buffer = null;
		return pagestr;
	}

	public boolean end(Writer writer, String body) {
		String msg = null;
		PaginatedList paginatedList = null;
		try {
			paginatedList = (PaginatedList) findValue(name);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		if (paginatedList != null) {
			if ("js".equals(style)) {
				msg = getJsString(paginatedList, systemId);
			}
		}

		if (msg != null) {
			try {

				writer.write(msg);

			} catch (IOException e) {
				LOG.error("Could not write out PaginatedList tag", e);
			}
		}

		return super.end(writer, "");
	}

	private String synthesizeStyle() {
		StringBuilder str = new StringBuilder();
		if (target != null && !"".equals(target)) {
			str.append(" target=\"").append(target).append("\" ");
		}
		return str.toString();
	}

	/**
	 * @return 返回 systemId。
	 */
	public Integer getSystemId() {
		return systemId;
	}

	/**
	 * @param systemId
	 *            要设置的 systemId。
	 */
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

}
