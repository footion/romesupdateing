package Menu;

import javax.swing.BoxLayout;

import Menu.MenuWriter;
import layoutSetting.basicPanel;

public class MenuPanel extends basicPanel{
	public MenuPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		MenuWriter masterDataManagement = new MenuWriter("기준 정보");
		masterDataManagement.addSubMenu("공통 코드");
		masterDataManagement.addSubMenu("거래처관리");
		masterDataManagement.addSubMenu("매입품관리");
		masterDataManagement.addSubMenu("BOM 관리");
		masterDataManagement.addSubMenu("원자재마스터");
		masterDataManagement.addSubMenu("공정마스터");
		masterDataManagement.addSubMenu("불량 코드");
		masterDataManagement.addSubMenu("생산 라인");
		masterDataManagement.addSubMenu("작업자정보");
		masterDataManagement.addSubMenu("로케이션");
		MenuWriter ProductionManagement = new MenuWriter("생산 관리");
		ProductionManagement.addSubMenu("수주 관리");
		ProductionManagement.addSubMenu("발주 관리");
		ProductionManagement.addSubMenu("생산 계획");
		ProductionManagement.addSubMenu("작업 지시");
		ProductionManagement.addSubMenu("생산 실적");
		ProductionManagement.addSubMenu("Lot추적");
		ProductionManagement.addSubMenu("작업 일보");
		ProductionManagement.addSubMenu("제품 출하");
		MenuWriter ProcessManagement = new MenuWriter("공정 관리");
		ProcessManagement.addSubMenu("입고/출고");
		ProcessManagement.addSubMenu("작업시간");
		ProcessManagement.addSubMenu("작업효율");
		ProcessManagement.addSubMenu("바코드관리");
		ProcessManagement.addSubMenu("생산이력");
		ProcessManagement.addSubMenu("공정별실적");
		MenuWriter QualityManagement = new MenuWriter("품질 정보");
		QualityManagement.addSubMenu("불량정보 등록");
		QualityManagement.addSubMenu("불량내용 조회");
		QualityManagement.addSubMenu("검사항목 관리");
		QualityManagement.addSubMenu("반품/폐기 관리");
		QualityManagement.addSubMenu("공정별 작업 현황");
		MenuWriter Monitoring = new MenuWriter("모니터링");
		Monitoring.addSubMenu("기간/라인별 실적");
		Monitoring.addSubMenu("생산 계획 진행율");
		Monitoring.addSubMenu("품질현황 관리");
		MenuWriter SystemAdmin = new MenuWriter("시스템 관리");
		SystemAdmin.addSubMenu("사용자 관리");
		SystemAdmin.addSubMenu("로그 기록");
		SystemAdmin.addSubMenu("ERP I/F");
		SystemAdmin.addSubMenu("시스템 정보 관리");
		this.add(masterDataManagement);
		this.add(ProductionManagement);
		this.add(ProcessManagement);
		this.add(QualityManagement);
		this.add(Monitoring);
		this.add(SystemAdmin);
	}
}
