package com.qingke.thread.suitang;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Team t = new Team();
		System.out.println("��Ļ��������");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread wg = new Thread(t,"�߸ھ�");
		System.out.println(wg.getName()+"����ս������");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread sj = new Thread(t,"���");
		System.out.println(sj.getName()+"����ս������");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wg.start();
		sj.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Hero h = new Hero(t);
		Thread cyj = new Thread(h,"��ҧ��");
		System.out.println("��·ɱ����"+cyj.getName());
		cyj.start();
		try {
			cyj.join();
			sj.join();
			wg.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("����ս��");
		System.out.println("��Ļ�����ر�");
	}

}
