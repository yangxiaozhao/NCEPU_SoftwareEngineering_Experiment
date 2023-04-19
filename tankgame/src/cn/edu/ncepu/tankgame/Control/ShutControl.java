package cn.edu.ncepu.tankgame.Control;

public class ShutControl extends Thread{
	public volatile boolean shuting = true;
	public void run() {
		while(true) {
			if(shuting==false) {
				try {
					sleep(1000);
					shuting=true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			long endtime=System.currentTimeMillis();
		}
	}
}
