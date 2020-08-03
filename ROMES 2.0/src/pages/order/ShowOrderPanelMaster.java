package pages.order;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.ColoredPanel;

public class ShowOrderPanelMaster extends ColoredPanel {

	/**
	 * Create the panel.
	 */
	JPanel topPanel = null, centerPanel= null;
	public int keynumber;
	public ShowOrderPanelMaster() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(5, 5));
		this.initTopPanel();
		this.initCenterPanel();
	}

	private void initCenterPanel() {
		centerPanel = new ShowOderspanel(this);
		
	}

	private void initTopPanel() {
		topPanel= new TopShowPanel(this);
		
	}

}
