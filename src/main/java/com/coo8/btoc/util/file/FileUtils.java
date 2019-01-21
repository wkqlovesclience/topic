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
 * �����ļ���һЩ��ط��������಻��Ҫʵ������������̳С�
 * 
 */
public final class FileUtils {
	public static final String ENCODE = "GBK";
	 private  static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * ���಻��Ҫʵ����
	 */
	private FileUtils() {
	}

	/**
	 * �����ļ����������У��������������ķ�ʽ����ȡ����ϵͳĬ�ϵ�encoding��String<br>
	 * --------------------------------------------------------------------------<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ���������·��
	 * @return �ļ�����
	 * @throws IOException
	 *             ��ȡ���⡣
	 */
	public static String fileRead(String fileFullName) throws IOException {
		// ---------------------------------
		// ���巵�ؽ������
		// ---------------------------------
		String result = null;
		try {
			File file = new File(fileFullName);
			long len = file.length();
			if (len > 0) {
				// ---------------------------------
				// ����ļ����ֽ�������0������
				// ---------------------------------
				InputStream in 	= new FileInputStream(file);
				byte[] bytes = new byte[(int) len];
				// ---------------------------------
				// ����ȫ�����ݵ�byte������
				// ---------------------------------
				 
				// ---------------------------------
				// ��byte�����е�����ת����String
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
	 * ��Stringд�뵽�ļ����÷��������ı���ʽд�õ��ļ���<br>
	 * --------------------------------------------------------------------------<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ�ȫ��
	 * @param fileContent
	 *            ����
	 * @param append
	 *            �Ƿ�׷��
	 * @throws IOException
	 *             ����
	 */
	public static void fileWrite(String fileFullName, String fileContent,
			boolean append) throws IOException {
		fileWrite(new File(fileFullName), fileContent, append);
	}

	/**
	 * ��Stringд�뵽�ļ����÷��������ı���ʽд�õ��ļ���<br>
	 * --------------------------------------------------------------------------<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ�ȫ��
	 * @param fileContent
	 *            ����
	 * @param append
	 *            �Ƿ�׷��
	 * @throws IOException
	 *             ����
	 */
	public static void fileWrite(File fileFullName, String fileContent,
			boolean append) throws IOException {
		File parent = fileFullName.getParentFile();
		if (!parent.exists())
			parent.mkdirs();
		
		try {
			// ---------------------------------
			// ���һ���ļ�д��ľ��
			// ---------------------------------
			OutputStreamWriter	writer = new OutputStreamWriter(new FileOutputStream(fileFullName,
					append), "GBK");
			// ---------------------------------
			// д������
			// ---------------------------------
			writer.write(fileContent);
			// ---------------------------------
			// ������д��������
			// ---------------------------------
			writer.flush();
			writer.close();
		} catch(IOException e){
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * ��byte����д�뵽�ļ������������Զ����Ƶ���ʽд��������<br>
	 * --------------------------------------------------------------------------<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ�ȫ��
	 * @param fileContent
	 *            ����
	 * @param append
	 *            �Ƿ�׷��
	 * @throws IOException
	 *             ����
	 */
	public static void fileWrite(String fileFullName, byte[] fileContent,
			boolean append) throws IOException {
		fileWrite(new File(fileFullName), fileContent, append);
	}

	/**
	 * ��byte����д�뵽�ļ������������Զ����Ƶ���ʽд��������<br>
	 * --------------------------------------------------------------------------<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ�ȫ��
	 * @param fileContent
	 *            ����
	 * @param append
	 *            �Ƿ�׷��
	 * @throws IOException
	 *             ����
	 */
	public static void fileWrite(File fileFullName, byte[] fileContent,
			boolean append) throws IOException {
		File parent = fileFullName.getParentFile();
		if (!parent.exists())
			parent.mkdirs();
		
		try {
			// ---------------------------------
			// ���һ��������д�����ľ��
			// ---------------------------------
			FileOutputStream outputStream = new FileOutputStream(fileFullName, append);
			// ---------------------------------
			// д������
			// ---------------------------------
			outputStream.write(fileContent);
			// ---------------------------------
			// ������д��������
			// ---------------------------------
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * �ļ�����
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

	// ����Ŀ¼��ַ
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
	 *            �ļ�·��
	 * @param suffix
	 *            ��׺��
	 * @param isdepth
	 *            �Ƿ������Ŀ¼
	 * @return
	 */
	 
	public static List<java.lang.String> listFile(File f, String suffix, boolean isdepth) {
		// ��Ŀ¼��ͬʱ��Ҫ������Ŀ¼
		List<String>  fileList = new ArrayList<String>(); 
		if (f.isDirectory() && isdepth == true) {
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++) {
				listFile(t[i], suffix, isdepth);
			}
		} else {
			String filePath = f.getAbsolutePath();
			if (suffix == "" || suffix == null) {
				// ��׺��Ϊnull��Ϊ�����ļ� 
				fileList.add(filePath);
			} else {
				int begIndex = filePath.lastIndexOf(".");// ���һ��.(����׺��ǰ���.)������
				String tempsuffix = "";

				if (begIndex != -1)// ��ֹ���ļ���ȴû�к�׺���������ļ�
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
		   FileInputStream fileinputstream = new FileInputStream(filePath);// ��ȡģ���ļ�
		   
		   //�������У�����������ĳ��ȣ�Ȼ��һ���ó��ȵ����飬Ȼ����������е��������ֽڵ���ʽ���뵽�����У�Ȼ��ر���
		   int lenght = fileinputstream.available();
		   byte bytes[] = new byte[lenght];
		   fileinputstream.close();
		   
		   //ͨ��ʹ��ƽ̨��Ĭ���ַ�������ָ���� byte ���飬����һ���µ� String��Ȼ�������ַ�����replaceAll()��������ָ���ַ����滻
		   //�˴��������ַ���֮�⣬Ӧ�û�����ʹ�ñ��ʽ����${}�ķ��������С�
		   templateContent = new String(bytes);
		   templateContent = templateContent.replaceAll(oldStr, newStr); 
		   
		  //ʹ��ƽ̨��Ĭ���ַ������� String ����Ϊ byte ���У���������洢��һ���µ� byte �����С�
		   byte tag_bytes[] = templateContent.getBytes();
		  
		   FileOutputStream fileoutputstream = new FileOutputStream(filePath);// �����ļ������
		   fileoutputstream.write(tag_bytes);
		   fileoutputstream.close();
		  } catch (Exception e) {
			  logger.error(e.getMessage(), e);  
		  }
		 }
}
