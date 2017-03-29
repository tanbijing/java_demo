package com.qingke.thread;

public class Tickets {
	public static void main(String[] args) {
		pay p = new pay();
		Thread th1 = new Thread(p,"����1");
		Thread th2 = new Thread(p,"����2");
		Thread th3 = new Thread(p,"����3");
		th1.start();
		th2.start();
		th3.start();
		try {
			th1.join();
			th2.join();
			th3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("����");
	}

}

class pay implements Runnable{
	public int count = 100;
	@Override
	public void run() {
		while(true){
			synchronized (this) {
				if(count>0){
					count--;
					System.out.println(Thread.currentThread().getName()+"��Ʊ��"+count);
				}else{
					break;
				}
			}
		}
	}
	
}
