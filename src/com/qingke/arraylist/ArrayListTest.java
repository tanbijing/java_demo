package com.qingke.arraylist;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {

	public static void main(String[] args){
		//������ֻ�ܴ��������ã����ܴ�����������ͣ�����intҪ��Integer
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		//ctrl+shift+l�����޸Ŀ�ݷ�ʽ��alt+/���Բ�ȫ����,��������sysout����ݼ���ȫSystem.out.println����
		System.out.println(list.get(1));
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		list.remove(0);
		Iterator<Integer> it = list.iterator();
		System.out.println("===================");
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		
	}
}
