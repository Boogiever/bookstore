/**
 * 
 */
package test;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年12月9日 下午9:06:44
 */
public class ThreadLocalTest implements Runnable {
	String name=null;
	ThreadLocal<String> threadLocal=new ThreadLocal<>();
	int i=0;
	@Override
	public void run() {
		for(;i<10;i++) {
//			 name = Thread.currentThread().getName();
		//	threadLocal.set(Thread.currentThread().getName());
			 try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			threadLocal.set(Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
		}
		
	}
	public static void main(String[] args) {
		ThreadLocalTest t=new ThreadLocalTest();
		new Thread(t,"AAA").start();
		new Thread(t,"BBB").start();
	}

}
