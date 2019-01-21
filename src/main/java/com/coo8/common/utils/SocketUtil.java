package com.coo8.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SocketUtil {
	private static Socket socket;
	private static final String COMMAND_CREATE = "create";
	private  static Logger logger = LoggerFactory.getLogger(SocketUtil.class);
	
	private SocketUtil(){}
	
	public static boolean request(String serverIp,int serverPort) {
		boolean flag = false;
		if(null != serverIp && !"".equals(serverIp) && serverPort > 1024){
			try {
				socket = new Socket(serverIp, serverPort);			
				OutputStreamWriter oxr = new OutputStreamWriter(
						socket.getOutputStream());
				PrintWriter pxr  = new PrintWriter(oxr, true);
				pxr.println(COMMAND_CREATE);
				BufferedReader bdq = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String str = bdq.readLine();
				if (null != str && "yes".equals(str)) {			
					flag = true;
				}
				bdq.close();
				pxr.close();
				oxr.close();
			}  catch (IOException e) {
				logger.error(e.getMessage(), e);
			} 
		}
		return flag;
	}
}
