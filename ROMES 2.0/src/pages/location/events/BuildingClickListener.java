package pages.location.events;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pages.location.LocationResources;
import pages.location.LocationResources.ResizeImageLabel;
import pages.location.show.BuildingInsideShowPanel;
import pages.utils.ColoredPanel;

public class BuildingClickListener implements MouseListener{
	
	ColoredPanel superPanel;
	
	public BuildingClickListener(ColoredPanel superPanel) {
		this.superPanel = superPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//remove sueperPanel's center component(panel)
		BorderLayout layout = (BorderLayout)superPanel.getLayout();
		superPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		ColoredPanel coloredPanel = new ColoredPanel();
		
		LocationResources.ResizeImageLabel label=(ResizeImageLabel) e.getComponent();
		JPanel panel = new BuildingInsideShowPanel(superPanel,label.getActionCommand());
		coloredPanel.add(panel);
		superPanel.add(coloredPanel,BorderLayout.CENTER);
		superPanel.revalidate();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}
