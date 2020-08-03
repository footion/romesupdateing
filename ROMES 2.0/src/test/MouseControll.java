package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.sun.jna.Native;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.W32APIOptions;

public class MouseControll {	
	public interface User32 extends W32APIOptions{
		User32 instance = (User32) Native.loadLibrary("user32",User32.class,DEFAULT_OPTIONS);
		boolean ShowWindow(HWND hwnd,int nCmdShow);
		boolean SetForegroundWindow(HWND hwnd);
		HWND FindWindow(String winClass,String title);
		int SW_SHOW=1;
	}
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		new MouseControll();
	}
	private Robot robot;
	public MouseControll() throws AWTException, InterruptedException {
		WindowUtils.getAllWindows(true).forEach(window->{
			if(window.getTitle().contains("Label")) {
				System.out.println("Ã£À½");
				System.out.println(window.getHWND());
				System.out.println(window.getHWND().toString());
				User32 user32 = User32. instance;
				user32.ShowWindow(window.getHWND(), User32.SW_SHOW) ;
				user32.SetForegroundWindow(window.getHWND());
			}
		});
		this.robot = new Robot();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ALT);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_F);
		Thread.sleep(500);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_ALT);
		Thread.sleep(100);
		robot.keyPress(KeyEvent.VK_P);
		Thread.sleep(100);
		robot.keyRelease(KeyEvent.VK_P);
		Thread.sleep(100);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(100);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}