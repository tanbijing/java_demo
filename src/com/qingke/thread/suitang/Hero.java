package com.qingke.thread.suitang;

public class Hero implements Runnable{
	private Team lock;
	
	public Hero(Team lock){
		this.lock = lock;
	}

	@Override
	public void run() {
		System.out.println("----------------------------");
		System.out.println("�����ձ�");
		lock.flag = false;
		for(int i=0;i<3;i++){
			System.out.println(Thread.currentThread().getName()+"����["+i+"]");
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName()+"����ս��");
	}
	
}
