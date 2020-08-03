package layoutSetting;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;

@SuppressWarnings({ "serial", "rawtypes" })
public class WhiteCombo extends JComboBox{
	@SuppressWarnings("unchecked")
	public WhiteCombo(String [] list) {
		super(list);
		this.setBackground(Color.WHITE);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
	}
}
