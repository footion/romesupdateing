package pages.location.events;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pages.location.LocationResources;
import pages.location.LocationResources.ResizeImageLabel;
import pages.location.ShowProductsByAreaPanel;
import pages.utils.ColoredPanel;

public class AreaClickListener implements MouseListener {

	ColoredPanel superPanel;
	private String buildingName;
	

	public AreaClickListener(ColoredPanel superPanel, String buildingName) {
		this.superPanel = superPanel;
		this.buildingName = buildingName;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// remove sueperPanel's center component(panel)
		BorderLayout layout = (BorderLayout) superPanel.getLayout();
		superPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));

		ColoredPanel coloredPanel = new ColoredPanel();

		LocationResources.ResizeImageLabel label = (ResizeImageLabel) e.getComponent();

		
		System.out.println("this area number is  "+buildingName+label.getActionCommand());
		JPanel panel = new ShowProductsByAreaPanel(superPanel,buildingName+"-"+label.getActionCommand());
		coloredPanel.add(panel);
		superPanel.add(coloredPanel, BorderLayout.CENTER);
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
