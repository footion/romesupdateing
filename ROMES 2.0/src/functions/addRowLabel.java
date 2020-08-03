package functions;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;

import factory.colorFactory;
import factory.fontFactory;
import layoutSetting.basicLabel;

@SuppressWarnings("serial")
public class addRowLabel extends basicLabel implements MouseListener{
	DefaultTableModel tablemodel = new DefaultTableModel();
	Object object[] = {};
	public addRowLabel(String string, DefaultTableModel model, Object[] objects) {
		super(string);
		tablemodel = model;
		object=objects;
		this.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,16));
		this.setForeground(colorFactory.ADD_LINE);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,17));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,16));
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setForeground(colorFactory.ADD_LINE_PRESS);
		tablemodel.addRow(object);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setForeground(colorFactory.ADD_LINE);
	}
}
