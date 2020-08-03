package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import org.hibernate.Session;

import Dao.ProductionPlanDAO;
import Dao.ReceivedOrderDAO;
import entity.BomEntity;
import entity.ProductionPlan;
import entity.ReceivedOrderProduct;
import entity.Received_order_history;
import hibernate.hibernate;
import message.errorMessage;
import message.successMessage;
import registrationForm.R_ProductionPlan;

public class R_ProductionPlanSubmitEvent implements ActionListener{
	R_ProductionPlan frame;
	public R_ProductionPlanSubmitEvent(R_ProductionPlan frame) {
		this.frame=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			JTable table = frame.getMiniTable().table;
			int rows[] = table.getSelectedRows();
			for(int row : rows) {
				int RO_id = (int) table.getValueAt(row, 0);
				Received_order_history RO = session.get(Received_order_history.class, RO_id);
				RO.setProductPlan(true);
				for(ReceivedOrderProduct p : RO.getProducts()) {
					if(p.getProduct().getCommonInfo().getProductType().equals("¿ÏÁ¦Ç°")
							&&p.getProduct().getBoms().size()!=0) {
						for(BomEntity bom : p.getProduct().getBoms()) {
							ProductionPlan plan = new ProductionPlan();
							plan.setReceivedOrder(RO);
							plan.setROproduct(p);
							plan.setMaterial(bom.getMeterial());
							session.save(plan);
						}
					}else {
						ProductionPlan plan = new ProductionPlan();
						plan.setReceivedOrder(RO);
						plan.setROproduct(p);
						session.save(plan);
					}
				}
				session.update(RO);
			}
			hibernate.transaction.commit();
			frame.getTabbedPane().refresh();
			frame.dispose();
			new successMessage();
		} catch (Exception e2) {
			e2.printStackTrace();
			new errorMessage("submit");
		}
	}

}
