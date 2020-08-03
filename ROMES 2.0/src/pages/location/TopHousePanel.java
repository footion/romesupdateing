package pages.location;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import pages.utils.ColoredPanel;

public class TopHousePanel extends JPanel {

	public TopHousePanel (ColoredPanel superPanel) {
		JButton createButton = new JButton(), gotoBackButton =new JButton();
		ColoredPanel buttonPanel = new ColoredPanel();

		
		//1. setting panel
		this.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		this.setPreferredSize(new Dimension(0, 45));
		this.setLayout(new BorderLayout());

		//2. attach components on panel
		buttonPanel.add(gotoBackButton);
		buttonPanel.add(createButton);
		//3. attach the panel
		this.add(buttonPanel, BorderLayout.WEST);
		superPanel.add(this, BorderLayout.NORTH);
	}
}