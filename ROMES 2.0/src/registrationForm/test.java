package registrationForm;
//package registrationPanel;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//
//
//import evevtListener.selectFrameEvent;
//import hibernate.hibernate;
//import selectFrame.selectFrame;
//
//public class test{
//	static ArrayList<String[]> locationList = new ArrayList<String[]>();
//	public static void main(String[] args) {
//		loadFile("C:/Users/user/Desktop/우편번호 정보.csv");
//		String[] dataTest = new String [locationList.size()];
//		System.out.println(locationList.size());
//		for(int i=1;i<locationList.size();i++) {
//			String [] a=locationList.get(i);
//			String b =a[1]+" "+a[2]+" "+a[3]+" "+a[4]+" "+a[5];
//			b=b.replaceAll("\"", "");
//			dataTest[i]=b;
//			//System.out.println(b);
//		}
//		String title="Input Representative";
//		String type ="user";
//		String[] listData=dataTest;
//		selectFrame selectFrame=new selectFrame(title, type, listData);
//		//addActionListener
//		selectFrame.enumTypePanel.button.addActionListener(new selectFrameEvent(selectFrame, null,null,null));
//	}
//	@SuppressWarnings("resource")
//	public static void loadFile(String locationFilePath) {
//		String line=null;
//		File locationFile = new File(locationFilePath);
//		try {
//			BufferedReader in = new BufferedReader(new FileReader(locationFile));
//			while ((line=in.readLine())!=null) {
//				String[]arr = line.split(",");
//				locationList.add(arr);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
//
