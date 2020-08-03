package mainFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import image.icon;
import image.url;
import layoutSetting.basicIconBtn;
import layoutSetting.basicPanel;

@SuppressWarnings("serial")
public class logo extends basicPanel{
	public logo() {
		
		JLabel Logo = new JLabel(new icon(url.ROMES, 125, 25));
		
		basicIconBtn infobutton = new basicIconBtn(url.INFO,18,18);
		infobutton.setClean(false, true, false);
		infobutton.setToolTipText("System Info");
		
		basicPanel panel = new basicPanel();
		basicPanel panel2= new basicPanel();
		panel2.setLayout(new BorderLayout(0,5));
		panel.setLayout(new BorderLayout());
		panel.add(Logo,BorderLayout.WEST);
		panel2.add(infobutton,BorderLayout.NORTH);
		panel2.add(new JLabel(" "),BorderLayout.PAGE_END);
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.WEST);
		this.add(panel2,BorderLayout.CENTER);
		
		infobutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Info();
			}
		});
	}
}
