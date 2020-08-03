package gui;

import java.util.HashMap;

import javax.swing.JPanel;

import gui.pages.CustomerInfoPage;
import gui.pages.MainPageDefaultPanel;
import gui.pages.MyDefaultTableModel;

public class MainPagePanel extends JPanel {

	private HashMap<String, JPanel> hashMap;

	public static MainPagePanel getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final MainPagePanel INSTANCE = new MainPagePanel();
	}

	protected MainPagePanel() {
		super();
		inputPagesAsunVisible();
		//system.out.println("MainPagePanel 생성자 실행");

	}

	private void inputPagesAsunVisible() {
		hashMap = new HashMap<String, JPanel>();
		
		hashMap.put("CustomerInfoPage", new CustomerInfoPage());
		
		hashMap.forEach((key, value) -> {
			value.setVisible(true);

			this.add(value);
		});
	}

	public void visiblePanel(String className) {

		hashMap.forEach((key, value) -> {
			if (key.equals(className)) {
				//system.out.println(key + " is setting visible");
				//system.out.println(value.getClass());
				value.setVisible(true);

			} else {
				//system.out.println(key + " is setting unvisible");
				value.setVisible(false);
			}
		});
		this.repaint();
	}
}
