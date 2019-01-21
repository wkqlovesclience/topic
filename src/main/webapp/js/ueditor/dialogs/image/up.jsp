<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ page import="java.io.*"%>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.util.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.fileupload.FileItemIterator" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="java.io.BufferedInputStream" %>
<%@ page import="java.io.BufferedOutputStream" %>
<%@ page import="java.io.File"%>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.coo8.btoc.util.*" %>
<%@page import="com.coo8.common.utils.ImageUtil"%>
<%

//保存文件路径
	try{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		String filePath = sdf.format(new Date());
		String fileUploadName = "";
		
		String fileRoot = ReloadableConfig.getProperty("ImgUploadAdress", "/app/coo8_img/") + File.separatorChar;
		String realPath =  fileRoot + filePath + File.separatorChar;
		
		//判断路径是否存在，不存在则创建
		File dir = new File(realPath);
		if(!dir.isDirectory())
		    dir.mkdir();
		
		if(ServletFileUpload.isMultipartContent(request)){
		
		    DiskFileItemFactory dff = new DiskFileItemFactory();
		    dff.setRepository(dir);
		    dff.setSizeThreshold(1024000);
		    ServletFileUpload sfu = new ServletFileUpload(dff);
		    FileItemIterator fii = sfu.getItemIterator(request);
		    String title = "";   //图片标题
		    String url = "";    //图片地址
		    String fileName = "";
			String state="SUCCESS";
			int maxCount = 0; //上传图片个数限制
	 Circle:while(fii.hasNext()){
		        FileItemStream fis = fii.next();
		
		        try{
		            if(!fis.isFormField() && fis.getName().length()>0){
		                fileName = fis.getName();
		            
						Pattern reg=Pattern.compile("[.]jpg|JPG|png|PNG|jpeg|JPEG|gif|GIF$");
						Matcher matcher=reg.matcher(fileName);
						if(!matcher.find()) {
							state = "文件类型不允许！";
							break;
						}
						fileUploadName = new Date().getTime()+fileName.substring(fileName.lastIndexOf("."),fileName.length());
		                url = realPath + File.separatorChar + fileUploadName;
		                BufferedInputStream in = null;
		                FileOutputStream outp = null;
		                BufferedOutputStream output = null;
		                try{
			                in = new BufferedInputStream(fis.openStream());//获得文件输入流
			                outp = new FileOutputStream(new File(url));
			                output = new BufferedOutputStream(outp);
			                Streams.copy(in, output, true);//开始把文件写到你指定的上传文件夹
						}finally{
							if(null != output){
								try{
									output.flush();
									output.close();
								}catch(Exception e){
									e.printStackTrace();
								}
							}
							if(null != outp){
								try{
									outp.close();
								}catch(Exception e){
									e.printStackTrace();
								}
							}
			                if(null != in){
			                	try{
			                		in.close();
			                	}catch(Exception e){
									e.printStackTrace();
								}
			                }
						}
		            }else{
		                String fname = fis.getFieldName();
		                if(fname.indexOf("pictitle")!=-1){
		                    BufferedInputStream in = new BufferedInputStream(fis.openStream());
		                    try{
			                    byte c [] = new byte[100];
			                    int n = 0;
			                    while((n=in.read(c))!=-1){
			                        title = new String(c,0,n,"UTF-8");
			                        break;
			                    }
		                    }finally{
		                    	if(null != in){
		                    		try{
		                    			in.close();
		                    		}catch(Exception e){
										e.printStackTrace();
									}
		                    	}
		                    }
		                }
		            }
		            if( maxCount++ > 100){
		            	break Circle;
		            }
		        }catch(Exception e){
		            e.printStackTrace();
		        }
		    }
		    //水印文件
		    String mask = request.getRealPath("/commons/mark") + "/gome_mark.png";
		
		    ImageUtil.markImage(mask,realPath + fileUploadName);
			title = title.replace("&", "&amp;").replace("'", "&qpos;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;");
			int num = (int)Math.ceil(Math.random()*9 + 1);
			String httpUrl = "http:\\//img"+num+".gomein.net.cn/image/bbcimg/topic_img/publish/catalog/";

			response.getWriter().print("{'url':'"+httpUrl+filePath+"/"+fileUploadName+"','title':'"+title+"','state':'"+state+"'}");
		}
	}catch(Exception e){
	    e.printStackTrace();
	}
%>
