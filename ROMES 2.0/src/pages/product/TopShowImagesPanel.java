package pages.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import gui.ColoredPanel;

public class TopShowImagesPanel extends ColoredPanel {

	ProductResource resource = ProductResource.getInstance();
	private JPanel superPanel;

	public TopShowImagesPanel(JPanel superPanel) {
		this.superPanel = superPanel;
		final int height = 45;
		// 1. setting panel
		this.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		this.setPreferredSize(new Dimension(0, height));
		this.setLayout(new BorderLayout());
		// 2. attach components on panel
		initLeftPanel();
		initRightPanel();
		// 3. attach the panel
		superPanel.add(this, BorderLayout.NORTH);
		superPanel.revalidate();

	}

	// it has two kinds components group
	// 1. search components group
	// 2. button components that the way how show data
	// -1. image -2. spreadsheet
	private void initRightPanel() {
		JPanel rightPanel = new ColoredPanel();
		JTextField searchingField = resource.getSearchingField(resource.FOR_INIT);
		JButton searcingButton = resource.getSearcingButton(), gridShowButton = resource.getGridShowButton(),
				spreadShowButton = resource.getSpreadShowButton();

		rightPanel.add(searchingField);
		rightPanel.add(searcingButton);
		rightPanel.add(gridShowButton);
		rightPanel.add(spreadShowButton);
		this.add(rightPanel, BorderLayout.EAST);
	}

	// just attach createbutton on leftPanel
	private void initLeftPanel() {
		JPanel leftPanel = new ColoredPanel();
		JButton createButton = resource.getCreateNewObjectButton();
		leftPanel.add(createButton);
		createButton.addActionListener(new CreateButtonEvent(superPanel));
		this.add(leftPanel, BorderLayout.WEST);
	}

}
