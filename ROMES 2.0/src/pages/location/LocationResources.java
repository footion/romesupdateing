package pages.location;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LocationResources {
	public static class Strings {
		public static final String buildingA = "A";
		public static final String buildingB = "B";
		public static final String buildingC = "C";
		public static final String buildingD = "D";
		public static final String areaA = "01", areaB = "02", areaC = "03", areaD = "04";
	}

	private JLabel building_a = null, building_b = null, building_c = null, building_d = null;
	private JLabel area_a= null, area_b= null,area_c= null,area_d= null;
	public static LocationResources getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final LocationResources INSTANCE = new LocationResources();
	
	}

	public LocationResources() {
		building_a = new ResizeImageLabel(new ImageIcon("./resources/Building_A.png"),Strings.buildingA);
		building_b = new ResizeImageLabel(new ImageIcon("./resources/Building_B.png"),Strings.buildingB);
		building_c = new ResizeImageLabel(new ImageIcon("./resources/Building_C.png"),Strings.buildingC);
		building_d = new ResizeImageLabel(new ImageIcon("./resources/Building_D.png"),Strings.buildingD);
		
		area_a = new ResizeImageLabel(new ImageIcon("./resources/area_A.png"), Strings.areaA);
		area_b = new ResizeImageLabel(new ImageIcon("./resources/area_B.png"), Strings.areaB);
		area_c = new ResizeImageLabel(new ImageIcon("./resources/area_C.png"), Strings.areaC);
		area_d = new ResizeImageLabel(new ImageIcon("./resources/area_D.png"), Strings.areaD);
		
	}

	public JLabel getBuilding_a(int witdh, int height) {
		return ResizeImageLabel.resizeLabel(building_a, witdh, height);
	}

	public JLabel getBuilding_b(int witdh, int height) {
		return ResizeImageLabel.resizeLabel(building_b, witdh, height);
	}

	public JLabel getBuilding_c(int witdh, int height) {
		return ResizeImageLabel.resizeLabel(building_c, witdh, height);
	}

	public JLabel getBuilding_d(int witdh, int height) {
		return ResizeImageLabel.resizeLabel(building_d, witdh, height);
	}
	
	public JLabel getArea_a(int witdh, int height) {
		return ResizeImageLabel.resizeLabel(area_a, witdh, height);
	}

	public JLabel getArea_b(int witdh, int height) {
		return ResizeImageLabel.resizeLabel(area_b, witdh, height);
	}

	public JLabel getArea_c(int witdh, int height) {
		return ResizeImageLabel.resizeLabel(area_c, witdh, height);
	}

	public JLabel getArea_d(int witdh, int height) {
		return ResizeImageLabel.resizeLabel(area_d, witdh, height);
	}
	
	
	
	

	public static class ResizeImageLabel extends JLabel {
		String actionCommand =null;
		public ResizeImageLabel(ImageIcon icon, String actionCommand) {
			this.setIcon(icon);
			this.actionCommand = actionCommand;
		}

		static JLabel resizeLabel(JLabel label, int widht, int height) {
			
			ImageIcon icon = (ImageIcon) label.getIcon();
			Image originImg = icon.getImage();

			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성

			Image changedImg = originImg.getScaledInstance(widht, height, Image.SCALE_SMOOTH);

			// 새로운 Image로 ImageIcon객체를 생성

			ImageIcon Icon = new ImageIcon(changedImg);
			label.setIcon(Icon);
			return label;

		}

		public String getActionCommand() {
			return actionCommand;
		}

		public void setActionCommand(String actionCommand) {
			this.actionCommand = actionCommand;
		}
	}

}
