package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import Dao.ProductionPlanDAO;
import entity.ProcessMaster;
import entity.ProductionPlan;
import functions.method;
import hibernate.hibernate;
import message.errorMessage;
import pages.ProcessManagement;
import pages.ProductionPlanManagement;

public class PP_SimulationEvent implements ActionListener{
	ProductionPlanManagement productionPlanManagement;
	public PP_SimulationEvent(ProductionPlanManagement productionPlanManagement) {
		this.productionPlanManagement=productionPlanManagement;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		findLine();
	}
	private void findLine() {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			ArrayList<ProductionPlan> plans = (ArrayList<ProductionPlan>)session.createCriteria(ProductionPlan.class).list();
			ArrayList<ProcessMaster> processMasters = (ArrayList<ProcessMaster>)session.createCriteria(ProcessMaster.class).list();
			
			for(ProductionPlan plan :plans) {
				for(ProcessMaster processMaster:processMasters) {
					
					if(plan.getMaterial()!=null&&
							processMaster.getProduct().equals(plan.getMaterial())) {
						plan.setProductionLine(processMaster.getProductionLine());
					}
					else if(plan.getROproduct().getProduct().equals(processMaster.getProduct())) 
						plan.setProductionLine(processMaster.getProductionLine());
					
					//setTime(processMaster,session);
					session.update(plan);
				}
			}
			hibernate.transaction.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
			new errorMessage("simulation");
		}
	}
//	private void setTime(ProcessMaster processMaster,Session session) {
//		ArrayList<String> lineList = new ArrayList<>();
//		lineList.add("A");lineList.add("B");lineList.add("C");lineList.add("D");lineList.add("E");
//		for(String a:lineList) {
//			String startTime = "9:00:00";
//			ArrayList<ProductionPlan> plans = (ArrayList<ProductionPlan>)session.createCriteria(ProductionPlan.class)
//					.add(Restrictions.eq("productionLine", a)).list();
//			if(plans.size()!=0) {
//				for(ProductionPlan plan : plans) {
//					plan.setStartTime(startTime);
//					plan.setWorkingTime(method.parseTime(Integer.parseInt(processMaster.getCycleTime())));
//					plan.setCompleteTime(method.parseTime(method.getSec(startTime)+Integer.parseInt(processMaster.getCycleTime())));
//					startTime=plan.getCompleteTime();
//				}
//			}
//		}
//	}
}
