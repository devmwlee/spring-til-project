package dev.mwlee.threadinjava;

import dev.mwlee.threadinjava.sync.SyncA;
import dev.mwlee.threadinjava.thread.RunA;
import dev.mwlee.threadinjava.thread.RunB;

public class ThreadInJava {
	public static void main(String[] args) {
		System.out.println("ThreadInJava");

		// Runnable interface를 implements 하여 run메소드를 구현
		Thread threadA = new Thread(new RunA());
		threadA.start();

		// Thread를 상속 받았기 때문에 바로 start() 메소드로 실행 가능
		RunB runB = new RunB();
		runB.start();

		// Sync & Async
		SyncA syncA = new SyncA();

//		Thread threadC = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				syncA.methodA("methodA");
//			}
//		});

		// 람다로 변경
		Thread threadC = new Thread(() -> syncA.methodA("methodA"));
		Thread threadD = new Thread(() -> syncA.methodB("methodB"));
		
		threadC.start();
		threadD.start();
	}
}
