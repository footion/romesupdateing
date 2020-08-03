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

public class BuildingShowPanel extends ColoredPanel {

	LocationResources resource = LocationResources.getInstance();
	public BuildingShowPanel(ColoredPanel superPanel) {
		int width =800,height=700;
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		this.setPreferredSize(new Dimension(width,height));
		this.setSize(width,height);
		panel.setPreferredSize(new Dimension(width,height));
		panel.setSize(width,height);
		add(panel);
		panel.setLayout(null);
		
		int building_aWitdh=300,building_aHeight=200;
		JLabel building_a = resource.getBuilding_a(building_aWitdh,building_aHeight);
		building_a.setBounds(373, 80, building_aWitdh, building_aHeight);
		building_a.addMouseListener(new BuildingClickListener(superPanel));
		panel.add(building_a);
		
		int building_bWitdh=200,building_bHeight=130;
		JLabel building_b = resource.getBuilding_b(building_bWitdh,building_bHeight);
		building_b.setBounds(50, 500, building_bWitdh, building_bHeight);
		building_b.addMouseListener(new BuildingClickListener(superPanel));
		panel.add(building_b);
		
		int building_cWitdh=200,building_cHeight=130;
		JLabel building_c = resource.getBuilding_c(building_cWitdh,building_cHeight);
		building_c.setBounds(300, 500, building_cWitdh, building_cHeight);
		building_c.addMouseListener(new BuildingClickListener(superPanel));
		panel.add(building_c);
		
		int building_dWitdh=200,building_dHeight=130;
		JLabel building_d = resource.getBuilding_d(building_dWitdh,building_dHeight);
		building_d.setBounds(550, 500, building_dWitdh, building_dHeight);
		building_d.addMouseListener(new BuildingClickListener(superPanel));
		panel.add(building_d);
		
		
		superPanel.add(this,BorderLayout.CENTER);
	}
}
