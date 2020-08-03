package layoutSetting;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class basicSrollPane extends JScrollPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8037988437942251191L;

	public basicSrollPane(Component component) {
		super(component);
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
		this.setBorder(bevelBorder);
	}
}
