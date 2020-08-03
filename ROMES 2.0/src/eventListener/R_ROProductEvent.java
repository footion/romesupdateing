package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import registrationForm.R_ROProduct;

public class R_ROProductEvent implements ActionListener,MouseListener,KeyListener{
	R_ROProduct component;
	public R_ROProductEvent(R_ROProduct R_ROProduct) {
		component=R_ROProduct;
		component.cancelBtn.addActionListener(new cancelEvent(component));
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==component.productGroupCombo) {
			JOptionPane.showConfirmDialog(null, "d");
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//ResetBtn
		if(e.getSource()==component.resetBtn) {
			component.resetCombo();
		}
		//productGroupCombo
		else if(e.getSource()==component.productGroupCombo) {
			if(component.productGroupCombo.getSelectedIndex()!=0)
				component.productTypeCombo.setEnabled(true);
			else{
				component.productTypeCombo.setSelectedIndex(0);
				component.productTypeCombo.setEnabled(false);
			}
			confirmNozzleOption();
		}
		//productTypeCombo
		else if(e.getSource()==component.productTypeCombo) {
			if(!component.productTypeCombo.getSelectedItem().equals("肯力前")
					&&component.productTypeCombo.getSelectedIndex()!=0)
				component.materialClassCombo.setEnabled(true);
			else{
				component.materialClassCombo.setSelectedIndex(0);
				component.materialClassCombo.setEnabled(false);
			}
			confirmNozzleOption();
		}
	}
	void confirmNozzleOption() {
		if(component.productGroupCombo.getSelectedItem().equals("畴榴")
				&& component.productTypeCombo.getSelectedItem().equals("肯力前")) {
			component.addNozzleOption();
		}else
			component.cleanOptionPanel();
	}
}
