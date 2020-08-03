package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class exitEvent implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int confirm = JOptionPane.showConfirmDialog(null, "시스템을 종료하시겠습니까 ?","Confirm Exit",JOptionPane.YES_NO_OPTION);
		if(confirm==0)
			System.exit(0);
	}

}
