package pages.location.show;

import pages.location.LocationResources;
import pages.location.events.BuildingClickListener;
import pages.utils.ColoredPanel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class AreaShowPanel extends ColoredPanel {

	LocationResources resource = LocationResources.getInstance();
	private String buildingName=null;
	private ColoredPanel superPanel=null;
	public AreaShowPanel(ColoredPanel superPanel) {
		this.superPanel= superPanel;
		this.buildingName = buildingName;
		int width =800,height=700;
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		this.setPreferredSize(new Dimension(width,height));
		this.setSize(width,height);
		panel.setPreferredSize(new Dimension(width,height));
		panel.setSize(width,height);
		add(panel);
		panel.setLayout(null);
		
		switch (buildingName) {
		case LocationResources.Strings.buildingA: {
			panel=initBuildingA(panel);
		}
			break;
		case LocationResources.Strings.buildingB: {
			panel=initBuildingB(panel);
		}
			break;
		case LocationResources.Strings.buildingC: {
			panel=initBuildingC(panel);
		}
			break;
		case LocationResources.Strings.buildingD: {
			panel=initBuildingD(panel);
		}
			break;

		default:
			break;
		}
		
		panel.revalidate();
	}
	private JPanel initBuildingC(JPanel panel) {
		// TODO Auto-generated method stub
		return null;
	}
	private JPanel initBuildingD(JPanel panel) {
		// TODO Auto-generated method stub
		return null;
	}
	private JPanel initBuildingB(JPanel panel) {
		// TODO Auto-generated method stub
		return null;
	}
	private JPanel initBuildingA(JPanel panel) {
		// TODO Auto-generated method stub
		return null;
	}
}
