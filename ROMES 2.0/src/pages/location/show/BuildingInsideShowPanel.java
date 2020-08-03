package pages.location.show;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pages.location.LocationResources;
import pages.location.LocationResources.Strings;
import pages.location.events.AreaClickListener;
import pages.utils.ColoredPanel;

public class BuildingInsideShowPanel extends ColoredPanel {

	LocationResources resources = LocationResources.getInstance();
	private String buildingName=null;
	private ColoredPanel superPanel=null;
	
	public BuildingInsideShowPanel(ColoredPanel superPanel, String buildingName) {
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

	public JPanel initBuildingA(JPanel panel) {
		final int x=0,y=1,witdh=2,height=3;
		int area_aLocaion[]= {100,100,200,100};
		int area_bLocaion[]= {350,100,200,100};
		int area_cLocaion[]= {100,400,200,100};
		int area_dLocaion[]= {350,400,200,100};
		
		
		
		JLabel area_a = resources.getArea_a(area_aLocaion[witdh], area_aLocaion[height]);
		area_a.setBounds(area_aLocaion[x], area_aLocaion[y], area_aLocaion[witdh], area_aLocaion[height]);
		area_a.addMouseListener(new AreaClickListener(superPanel,buildingName));
		panel.add(area_a);
		
		JLabel area_b = resources.getArea_b(area_bLocaion[witdh], area_bLocaion[height]);
		area_b.setBounds(area_bLocaion[x], area_bLocaion[y], area_bLocaion[witdh], area_bLocaion[height]);
		area_b.addMouseListener(null);
		panel.add(area_b);
		
		JLabel area_c = resources.getArea_c(area_cLocaion[witdh], area_cLocaion[height]);
		area_c.setBounds(area_cLocaion[x], area_cLocaion[y], area_cLocaion[witdh], area_bLocaion[height]);
		area_c.addMouseListener(null);
		panel.add(area_c);
		
		JLabel area_d = resources.getArea_d(area_dLocaion[witdh], area_dLocaion[height]);
		area_d.setBounds(area_dLocaion[x], area_dLocaion[y], area_dLocaion[witdh], area_dLocaion[height]);
		area_d.addMouseListener(null);
		panel.add(area_d);
		
		return panel;	
	}
	
	public JPanel initBuildingB(JPanel panel) {
		final int x=0,y=1,witdh=2,height=3;
		int area_aLocaion[]= {100,100,200,100};
		int area_bLocaion[]= {350,100,200,100};
		int area_cLocaion[]= {100,400,200,100};
		int area_dLocaion[]= {350,400,200,100};
		
		
		
		JLabel area_a = resources.getArea_a(area_aLocaion[witdh], area_aLocaion[height]);
		area_a.setBounds(area_aLocaion[x], area_aLocaion[y], area_aLocaion[witdh], area_aLocaion[height]);
		area_a.addMouseListener(null);
		panel.add(area_a);
		
		JLabel area_b = resources.getArea_b(area_bLocaion[witdh], area_bLocaion[height]);
		area_b.setBounds(area_bLocaion[x], area_bLocaion[y], area_bLocaion[witdh], area_bLocaion[height]);
		area_b.addMouseListener(null);
		panel.add(area_b);
		
		JLabel area_c = resources.getArea_c(area_cLocaion[witdh], area_cLocaion[height]);
		area_c.setBounds(area_cLocaion[x], area_cLocaion[y], area_cLocaion[witdh], area_bLocaion[height]);
		area_c.addMouseListener(null);
		panel.add(area_c);
		
		JLabel area_d = resources.getArea_d(area_dLocaion[witdh], area_dLocaion[height]);
		area_d.setBounds(area_dLocaion[x], area_dLocaion[y], area_dLocaion[witdh], area_dLocaion[height]);
		area_d.addMouseListener(null);
		panel.add(area_d);
		return panel;	
	}

	public JPanel initBuildingC(JPanel panel) {
		final int x=0,y=1,witdh=2,height=3;
		int area_aLocaion[]= {100,100,200,100};
		int area_bLocaion[]= {350,100,200,100};
		int area_cLocaion[]= {100,400,200,100};
		int area_dLocaion[]= {350,400,200,100};
		
		
		
		JLabel area_a = resources.getArea_a(area_aLocaion[witdh], area_aLocaion[height]);
		area_a.setBounds(area_aLocaion[x], area_aLocaion[y], area_aLocaion[witdh], area_aLocaion[height]);
		area_a.addMouseListener(null);
		panel.add(area_a);
		
		JLabel area_b = resources.getArea_b(area_bLocaion[witdh], area_bLocaion[height]);
		area_b.setBounds(area_bLocaion[x], area_bLocaion[y], area_bLocaion[witdh], area_bLocaion[height]);
		area_b.addMouseListener(null);
		panel.add(area_b);
		
		JLabel area_c = resources.getArea_c(area_cLocaion[witdh], area_cLocaion[height]);
		area_c.setBounds(area_cLocaion[x], area_cLocaion[y], area_cLocaion[witdh], area_bLocaion[height]);
		area_c.addMouseListener(null);
		panel.add(area_c);
		
		JLabel area_d = resources.getArea_d(area_dLocaion[witdh], area_dLocaion[height]);
		area_d.setBounds(area_dLocaion[x], area_dLocaion[y], area_dLocaion[witdh], area_dLocaion[height]);
		area_d.addMouseListener(null);
		panel.add(area_d);
		return panel;	
	}

	public JPanel initBuildingD(JPanel panel) {
		final int x=0,y=1,witdh=2,height=3;
		int area_aLocaion[]= {100,100,200,100};
		int area_bLocaion[]= {350,100,200,100};
		int area_cLocaion[]= {100,400,200,100};
		int area_dLocaion[]= {350,400,200,100};
		
		
		
		JLabel area_a = resources.getArea_a(area_aLocaion[witdh], area_aLocaion[height]);
		area_a.setBounds(area_aLocaion[x], area_aLocaion[y], area_aLocaion[witdh], area_aLocaion[height]);
		area_a.addMouseListener(null);
		panel.add(area_a);
		
		JLabel area_b = resources.getArea_b(area_bLocaion[witdh], area_bLocaion[height]);
		area_b.setBounds(area_bLocaion[x], area_bLocaion[y], area_bLocaion[witdh], area_bLocaion[height]);
		area_b.addMouseListener(null);
		panel.add(area_b);
		
		JLabel area_c = resources.getArea_c(area_cLocaion[witdh], area_cLocaion[height]);
		area_c.setBounds(area_cLocaion[x], area_cLocaion[y], area_cLocaion[witdh], area_bLocaion[height]);
		area_c.addMouseListener(null);
		panel.add(area_c);
		
		JLabel area_d = resources.getArea_d(area_dLocaion[witdh], area_dLocaion[height]);
		area_d.setBounds(area_dLocaion[x], area_dLocaion[y], area_dLocaion[witdh], area_dLocaion[height]);
		area_d.addMouseListener(null);
		panel.add(area_d);
		return panel;	
	}

}
