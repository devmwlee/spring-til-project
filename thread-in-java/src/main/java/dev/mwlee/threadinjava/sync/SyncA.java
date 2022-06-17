package dev.mwlee.threadinjava.sync;

public class SyncA {

	// 동기화를 구현하는 2가지 방법
	public void methodA(String thread) {
		System.out.println("methodA 시작");	// sync method 밖에 있으므로 순서와 상관없이 출력
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println("thread : " + thread + ", methodA : " + i);
			}
		}
		System.out.println("methodA 끝");
	}

	public synchronized void methodB(String thread) {
		System.out.println("methodB 시작");
		for (int i = 0; i < 5; i++) {
			System.out.println("thread : " + thread + ", methodB : " + i);
		}
		System.out.println("methodB 끝");
	}
}
