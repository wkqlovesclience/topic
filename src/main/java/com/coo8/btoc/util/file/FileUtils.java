package com.coo8.btoc.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

/**
 * 操作文件的一些相关方法，该类不需要实例化，不允许继承。
 * 
 */
public final class FileUtils {
	public static final String ENCODE = "GBK";
	 private  static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * 该类不需要实例化
	 */
	private FileUtils() {
	}

	/**
	 * 读入文件到安符串中，本方法是以流的方式来读取。以系统默认的encoding到String<br>
	 * --------------------------------------------------------------------------<br>
	 * 创建日期：2004-1-5<br>
	 * <br>
	 * 修改者：<br>
	 * 修改日期：<br>
	 * 修改原因：<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            文件名，包括路径
	 * @return 文件内容
	 * @throws IOException
	 *             读取例外。
	 */
	public static String fileRead(String fileFullName) throws IOException {
		// ---------------------------------
		// 定义返回结果变量
		// ---------------------------------
		String result = null;
		try {
			File file = new File(fileFullName);
			long len = file.length();
			if (len > 0) {
				// ---------------------------------
				// 如果文件的字节数大于0，打开流
				// ---------------------------------
				InputStream in 	= new FileInputStream(file);
				byte[] bytes = new byte[(int) len];
				// ---------------------------------
				// 读入全部内容到byte数组中
				// ---------------------------------
				 
				// ---------------------------------
				// 把byte数组中的内容转换成String
				// --------------------------------
				in.close();
				result = new String(bytes);
				bytes = null;
			}
			return result;
		} catch(IOException  e){
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 将String写入到文件，该方法是以文本形式写得到文件中<br>
	 * --------------------------------------------------------------------------<br>
	 * 创建日期：2004-1-5<br>
	 * <br>
	 * 修改者：<br>
	 * 修改日期：<br>
	 * 修改原因：<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            文件全名
	 * @param fileContent
	 *            内容
	 * @param append
	 *            是否追加
	 * @throws IOException
	 *             例外
	 */
	public static void fileWrite(String fileFullName, String fileContent,
			boolean append) throws IOException {
		fileWrite(new File(fileFullName), fileContent, append);
	}

	/**
	 * 将String写入到文件，该方法是以文本形式写得到文件中<br>
	 * --------------------------------------------------------------------------<br>
	 * 创建日期：2004-1-5<br>
	 * <br>
	 * 修改者：<br>
	 * 修改日期：<br>
	 * 修改原因：<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            文件全名
	 * @param fileContent
	 *            内容
	 * @param append
	 *            是否追加
	 * @throws IOException
	 *             例外
	 */
	public static void fileWrite(File fileFullName, String fileContent,
			boolean append) throws IOException {
		File parent = fileFullName.getParentFile();
		if (!parent.exists())
			parent.mkdirs();
		
		try {
			// ---------------------------------
			// 获得一个文件写入的句柄
			// ---------------------------------
			OutputStreamWriter	writer = new OutputStreamWriter(new FileOutputStream(fileFullName,
					append), "GBK");
			// ---------------------------------
			// 写入内容
			// ---------------------------------
			writer.write(fileContent);
			// ---------------------------------
			// 将内容写到碰盘上
			// ---------------------------------
			writer.flush();
			writer.close();
		} catch(IOException e){
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 将byte数组写入到文件，本方法是以二进制的形式写到碰盘上<br>
	 * --------------------------------------------------------------------------<br>
	 * 创建日期：2004-1-5<br>
	 * <br>
	 * 修改者：<br>
	 * 修改日期：<br>
	 * 修改原因：<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            文件全名
	 * @param fileContent
	 *            内容
	 * @param append
	 *            是否追加
	 * @throws IOException
	 *             例外
	 */
	public static void fileWrite(String fileFullName, byte[] fileContent,
			boolean append) throws IOException {
		fileWrite(new File(fileFullName), fileContent, append);
	}

	/**
	 * 将byte数组写入到文件，本方法是以二进制的形式写到碰盘上<br>
	 * --------------------------------------------------------------------------<br>
	 * 创建日期：2004-1-5<br>
	 * <br>
	 * 修改者：<br>
	 * 修改日期：<br>
	 * 修改原因：<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            文件全名
	 * @param fileContent
	 *            内容
	 * @param append
	 *            是否追加
	 * @throws IOException
	 *             例外
	 */
	public static void fileWrite(File fileFullName, byte[] fileContent,
			boolean append) throws IOException {
		File parent = fileFullName.getParentFile();
		if (!parent.exists())
			parent.mkdirs();
		
		try {
			// ---------------------------------
			// 获得一个二进制写入流的句柄
			// ---------------------------------
			FileOutputStream outputStream = new FileOutputStream(fileFullName, append);
			// ---------------------------------
			// 写入内容
			// ---------------------------------
			outputStream.write(fileContent);
			// ---------------------------------
			// 将内容写到碰盘上
			// ---------------------------------
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @return
	 */
	public static boolean copyFile(File fromFile, File toFile) {

		FileInputStream fis = null;
		FileOutputStream fos = null;

		String toFilePath = toFile.getParent();
		File path = new File(toFilePath);
		if (!path.exists()) {
			path.mkdirs();
		}
		try {
			toFile.createNewFile();
			fis = new FileInputStream(fromFile);
			fos = new FileOutputStream(toFile);
			int bytesRead;
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			while ((bytesRead = fis.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.flush();
			fos.close();
			fis.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	public static void writeFile(String pathname, String content)
			throws IOException {
		File path = new File(pathname);
		File parent = path.getParentFile();
		if (!parent.exists())
			parent.mkdirs();
		FileOutputStream fos = new FileOutputStream(path);
		try {
			byte[] buff = content.getBytes();
			fos.write(buff, 0, buff.length);
		} finally {
			fos.close();
		}
	}

	// 生成目录地址
	public static boolean genDirectory(String path) {
		File file = new File(path);
		if (!file.isDirectory() && !file.exists()) {
			return file.mkdirs();
		}
		return true;
	}
 

	/**
	 * 
	 * @param path
	 *            文件路径
	 * @param suffix
	 *            后缀名
	 * @param isdepth
	 *            是否遍历子目录
	 * @return
	 */
	 
	public static List<java.lang.String> listFile(File f, String suffix, boolean isdepth) {
		// 是目录，同时需要遍历子目录
		List<String>  fileList = new ArrayList<String>(); 
		if (f.isDirectory() && isdepth == true) {
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++) {
				listFile(t[i], suffix, isdepth);
			}
		} else {
			String filePath = f.getAbsolutePath();
			if (suffix == "" || suffix == null) {
				// 后缀名为null则为所有文件 
				fileList.add(filePath);
			} else {
				int begIndex = filePath.lastIndexOf(".");// 最后一个.(即后缀名前面的.)的索引
				String tempsuffix = "";

				if (begIndex != -1)// 防止是文件但却没有后缀名结束的文件
				{
					tempsuffix = filePath.substring(begIndex + 1, filePath
							.length());
				}

				if (tempsuffix.equals(suffix)) {
					fileList.add(filePath);
				}
			 
			}

		}

		return fileList;
	}
	 public static void replaceFile(String filePath,String oldStr,String newStr) {
		  try {  
			  
		   String templateContent = "";
		   FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
		   
		   //下面四行：获得输入流的长度，然后建一个该长度的数组，然后把输入流中的数据以字节的形式读入到数组中，然后关闭流
		   int lenght = fileinputstream.available();
		   byte bytes[] = new byte[lenght];
		   fileinputstream.close();
		   
		   //通过使用平台的默认字符集解码指定的 byte 数组，构造一个新的 String。然后利用字符串的replaceAll()方法进行指定字符的替换
		   //此处除了这种方法之外，应该还可以使用表达式语言${}的方法来进行。
		   templateContent = new String(bytes);
		   templateContent = templateContent.replaceAll(oldStr, newStr); 
		   
		  //使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
		   byte tag_bytes[] = templateContent.getBytes();
		  
		   FileOutputStream fileoutputstream = new FileOutputStream(filePath);// 建立文件输出流
		   fileoutputstream.write(tag_bytes);
		   fileoutputstream.close();
		  } catch (Exception e) {
			  logger.error(e.getMessage(), e);  
		  }
		 }
}
