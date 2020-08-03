package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import layoutSetting.miniTable;
import registrationForm.R_ProductFrame;

public class MiniTableEvent_byProduct implements MouseListener{
	miniTable miniTable;
	int EVENTCOL=1;
	public MiniTableEvent_byProduct(miniTable miniTable) {
		this.miniTable=miniTable;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(miniTable.table.getSelectedColumn()==EVENTCOL) {
			R_ProductFrame frame =new R_ProductFrame();
			frame.editBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String data =(String) frame.getDataList().getSelectedValue();
					miniTable.table.setValueAt(data, miniTable.table.getSelectedRow(), EVENTCOL);
					miniTable.table.setValueAt(frame.returnDataKey(), miniTable.table.getSelectedRow(), EVENTCOL+1);
					frame.dispose();
				}
			});
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
