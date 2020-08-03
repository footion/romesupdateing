package layoutSetting;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;


@SuppressWarnings("serial")
public class scrollFrame extends basicFrame{
	public JScrollPane pane;
	public basicPanel container;
	public scrollFrame(String title) {
		super(title);
		container=new basicPanel();
		pane = new JScrollPane(container);
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
		//pane.setBorder(bevelBorder);
		this.add(pane);
	}
	public void addComponent(Component component) {
		container.add(component);
	}
}
