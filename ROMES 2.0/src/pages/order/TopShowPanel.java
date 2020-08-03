package pages.order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import pages.product.BomButtonEvnet;
import pages.product.ProductResource;
import pages.product.ReturnButtonEvent;
import pages.product.SaveButtonEvent;
import pages.product.modifyButtonEvent;
import pages.utils.ColoredPanel;

public class TopShowPanel extends ColoredPanel {

	/**
	 * Create the panel.
	 */

	ProductResource resource = ProductResource.getInstance();

	public TopShowPanel(ShowOrderPanelMaster superPanel) {
		JButton modifyButton = new JButton(ProductResource.modifyButtonString), gotoBackButton = new JButton(ProductResource.gotoBackButtonString),
				deleteButton =  new JButton("삭제");
		ColoredPanel buttonPanel = new ColoredPanel();
		if(gotoBackButton.getActionListeners().length==0)
			gotoBackButton.addActionListener(new ReturnButtonEvent());
		if(modifyButton.getActionListeners().length==0)
			modifyButton.addActionListener(new modifyButtonEvent(superPanel.keynumber));
		if(deleteButton.getActionListeners().length==0)
			deleteButton.addActionListener(new BomButtonEvnet(superPanel.keynumber));
		else {
			for(ActionListener action: deleteButton.getActionListeners()) {
				deleteButton.removeActionListener(action);
			}
			System.out.println(superPanel.keynumber+ "번호로 리스너 재 배치");
			deleteButton.addActionListener(new BomButtonEvnet(superPanel.keynumber));
		}
		//1. setting panel
		this.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		this.setPreferredSize(new Dimension(0, 45));
		this.setLayout(new BorderLayout());

		//2. attach components on panel
		buttonPanel.add(gotoBackButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(deleteButton);
		//3. attach the panel
		this.add(buttonPanel, BorderLayout.WEST);
		superPanel.add(this, BorderLayout.NORTH);
	}

}
