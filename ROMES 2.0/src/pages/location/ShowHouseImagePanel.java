package pages.location;

import javax.swing.JPanel;

import pages.location.show.BuildingShowPanel;
import pages.product.TopCreatePanel;
import pages.utils.ColoredPanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ShowHouseImagePanel extends ColoredPanel {

	/**
	 * Create the panel.
	 */
	JPanel topPanel = null, centerPanel = null;

	public ShowHouseImagePanel() {
		setLayout(new BorderLayout(5, 5));
		initTopPanel();
		initCenterPanel();
	}

	private void initCenterPanel() {
		centerPanel = new BuildingShowPanel(this);
	}
	private void initTopPanel() {
		topPanel = new TopHousePanel(this);
	}
}
