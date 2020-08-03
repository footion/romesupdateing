package Menu;

import javax.swing.BoxLayout;

import Menu.MenuWriter;
import layoutSetting.basicPanel;

public class MenuPanel extends basicPanel{
	public MenuPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		MenuWriter masterDataManagement = new MenuWriter("���� ����");
		masterDataManagement.addSubMenu("���� �ڵ�");
		masterDataManagement.addSubMenu("�ŷ�ó����");
		masterDataManagement.addSubMenu("����ǰ����");
		masterDataManagement.addSubMenu("BOM ����");
		masterDataManagement.addSubMenu("�����縶����");
		masterDataManagement.addSubMenu("����������");
		masterDataManagement.addSubMenu("�ҷ� �ڵ�");
		masterDataManagement.addSubMenu("���� ����");
		masterDataManagement.addSubMenu("�۾�������");
		masterDataManagement.addSubMenu("�����̼�");
		MenuWriter ProductionManagement = new MenuWriter("���� ����");
		ProductionManagement.addSubMenu("���� ����");
		ProductionManagement.addSubMenu("���� ����");
		ProductionManagement.addSubMenu("���� ��ȹ");
		ProductionManagement.addSubMenu("�۾� ����");
		ProductionManagement.addSubMenu("���� ����");
		ProductionManagement.addSubMenu("Lot����");
		ProductionManagement.addSubMenu("�۾� �Ϻ�");
		ProductionManagement.addSubMenu("��ǰ ����");
		MenuWriter ProcessManagement = new MenuWriter("���� ����");
		ProcessManagement.addSubMenu("�԰�/���");
		ProcessManagement.addSubMenu("�۾��ð�");
		ProcessManagement.addSubMenu("�۾�ȿ��");
		ProcessManagement.addSubMenu("���ڵ����");
		ProcessManagement.addSubMenu("�����̷�");
		ProcessManagement.addSubMenu("����������");
		MenuWriter QualityManagement = new MenuWriter("ǰ�� ����");
		QualityManagement.addSubMenu("�ҷ����� ���");
		QualityManagement.addSubMenu("�ҷ����� ��ȸ");
		QualityManagement.addSubMenu("�˻��׸� ����");
		QualityManagement.addSubMenu("��ǰ/��� ����");
		QualityManagement.addSubMenu("������ �۾� ��Ȳ");
		MenuWriter Monitoring = new MenuWriter("����͸�");
		Monitoring.addSubMenu("�Ⱓ/���κ� ����");
		Monitoring.addSubMenu("���� ��ȹ ������");
		Monitoring.addSubMenu("ǰ����Ȳ ����");
		MenuWriter SystemAdmin = new MenuWriter("�ý��� ����");
		SystemAdmin.addSubMenu("����� ����");
		SystemAdmin.addSubMenu("�α� ���");
		SystemAdmin.addSubMenu("ERP I/F");
		SystemAdmin.addSubMenu("�ý��� ���� ����");
		this.add(masterDataManagement);
		this.add(ProductionManagement);
		this.add(ProcessManagement);
		this.add(QualityManagement);
		this.add(Monitoring);
		this.add(SystemAdmin);
	}
}
