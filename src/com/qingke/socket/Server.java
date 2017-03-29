package com.qingke.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.*;

public class Server implements MainStop {
	private int port;
	private ServerSocket server;
	public boolean shouldRun;
	public InputHander input;
	public Scanner sc;
	public HashMap<BufferedWriter,String> writers;
	public Logger logger;
	
	public Server(int port){
		this.port = port;
		shouldRun = true;
		sc = new Scanner(System.in);
		writers = new HashMap<BufferedWriter,String>();
		logger = LogManager.getLogger();
		
		logger.error("========================");
	}
	
	public static void main(String[] args){
		Server server = new Server(3000);
		server.createSocket();
		while(true){
			Socket socket = server.accept();
			BufferedWriter writer = server.createOutput(socket);
			server.writers.put(writer, "dd");
			server.input = new InputHander(server.createInput(socket),server);
			server.input.writer = writer;
			server.input.start();
		}
//		server.close(socket);
	}
	
	public void createSocket(){
		try {
			logger.error("�����С�����");
			server = new ServerSocket(port);
			logger.info("�����ɹ����ȴ����ӡ�����");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Socket accept(){
		Socket socket = null;
		try {
			socket = server.accept();
			logger.info("���������ӣ���ʼͨ��");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return socket;
	}
	
	public BufferedReader createInput(Socket socket){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}
	
	public BufferedWriter createOutput(Socket socket){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer;
	}
	
	public void write(BufferedWriter writer){
		try {
			while(shouldRun){
				System.out.println("�����룺");
				String text = sc.nextLine();
				writer.write(text+"\r");
				writer.flush();
				if("bye".equals(text)){
					this.shouldRun = false;
					this.input.shouldRun = false;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendToAll(String msg,BufferedWriter from){
		for(BufferedWriter writer : writers.keySet()){
			if(writer.equals(from)) continue;
			try {
				logger.info(msg);
				writer.write(msg);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				writers.remove(writer);
			}
		}
	}
	
	public void close(Socket socket){
		try {
			socket.close();
			this.server.close();
			logger.info("�������ر�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}

	@Override
	public void setShouldRun(boolean canRun) {
		this.shouldRun = canRun;
	}

}
