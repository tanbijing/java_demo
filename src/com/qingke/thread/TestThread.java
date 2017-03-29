package com.qingke.thread;


public class TestThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		coding();
		BankAccount account = new BankAccount();
		Thread th = new BankThread(account,"hu");
		th.start();
		Thread th2 = new BankThread(account,"wi");
		th2.start();
		
	}
	//ѧϰ�̴߳���
	public static void coding(){
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
//			Thread th = new MyThread();
//			th.start();
			SecondThread sth = new SecondThread();
			Thread th = new Thread(sth);
			th.start();
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	//ѧϰ�߳�ͬ��
	public static void learnThreadSync(){
		
	}
}
//��һ�ַ����̳�Thread����дrun����
class MyThread extends Thread{
	public void run(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
//�ڶ��ַ���ʵ��Runnable�ӿڣ���ʵ��run����
class SecondThread implements Runnable{
	public void run(){
		try {
			System.out.println("=====");
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class BankAccount{
	private int balance = 0;
	
	public boolean deposit(int balance,String user){
		if(balance==0)
			return false;
		else{
			synchronized (this) {
				System.out.println("��ǰ��"+this.balance);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.balance += balance;
				System.out.println(user+"��"+this.balance);
			}
			return true;
		}
	}
}
class BankThread extends Thread{
	private BankAccount account;
	private String user;
	public BankThread(BankAccount account,String user){
		this.account = account;
		this.user = user;
	}
	public void run(){
		System.out.println(Thread.currentThread().getName());
		account.deposit(100,user);
	}
}
