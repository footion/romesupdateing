package eventListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;

import org.hibernate.Session;

import entity.ReceivedOrderProduct;
import entity.Received_order_history;
import functions.method;
import hibernate.hibernate;
import registrationForm.R_ProductionPlan;

public class R_ProductionPlanInfoEvent implements MouseListener{
	R_ProductionPlan R_ProductionPlan;
	public R_ProductionPlanInfoEvent(R_ProductionPlan R_ProductionPlan) {
		this.R_ProductionPlan=R_ProductionPlan;
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
	public void mousePressed(MouseEvent arg0) {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			int key=(int) R_ProductionPlan.getMiniTable().table.getValueAt(R_ProductionPlan.getMiniTable().table.getSelectedRow(),0);
			Received_order_history order = session.get(Received_order_history.class, key);
			List<ReceivedOrderProduct> products = order.getProducts();
			int num=1;
			R_ProductionPlan.getPageEndPanel().removeAll();
			for(ReceivedOrderProduct product : products) {
				JLabel label = new JLabel(num+". 제품명 : "+product.getProduct().getName());
				if(product.getLotNo()!=null&&!product.getLotNo().equals("")) {
					label.setText(label.getText()+" ,  lotNo : "+product.getLotNo());
				}if(product.getSize()!=null&&!product.getSize().equals("")) {
					label.setText(label.getText()+" ,  규격 : "+product.getSize());
				}if(product.getUnit()!=null&&!product.getUnit().equals("")) {
					label.setText(label.getText()+" ,  단위 : "+product.getUnit());
				}if(product.getQuantity()!=null&&!product.getQuantity().equals("")) {
					label.setText(label.getText()+" ,  수량 : "+product.getQuantity());
				}if(product.getPrice()!=null&&!product.getPrice().equals("")) {
					label.setText(label.getText()+" ,  단가 : "+product.getPrice());
				}if(product.getRemarks()!=null&&!product.getRemarks().equals("")) {
					label.setText(label.getText()+" ,  비고 : "+product.getRemarks());
				}
				R_ProductionPlan.getPageEndPanel().add(label);
				num++;
			}
			method.refreshComponent(R_ProductionPlan);
			hibernate.transaction.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
