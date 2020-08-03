package layoutSetting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import factory.colorFactory;
import factory.componentFactory;
import factory.fontFactory;

@SuppressWarnings("serial")
public class basicTabbedPane extends JTabbedPane{
	basicTabbedPane basicTabbedPane;
	public basicTabbedPane() {
		basicTabbedPane= this;
		this.setBackground(colorFactory.PANEL_COLOR);
	}
	public void addCancelableTab(String title,Component component) {
		JLabel titlelabel = new JLabel(title);
		JLabel cancelLabel = new JLabel("¡¿");
		//titlelabel.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,13));
		//cancelLabel.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,13));
		basicPanel panel = new basicPanel();
		panel.setLayout(new BorderLayout(4,0));
		panel.add(titlelabel,BorderLayout.WEST);
		panel.add(new componentFactory().addEmptyLabel(),BorderLayout.CENTER);
		panel.add(cancelLabel,BorderLayout.EAST);
		panel.setOpaque(false);
		this.addTab(title, component);
		this.setTabComponentAt(this.indexOfComponent(component), panel);
		this.setSelectedComponent(component);
		cancelLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				basicTabbedPane.remove(component);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				cancelLabel.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				cancelLabel.setForeground(Color.RED);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	}
}
