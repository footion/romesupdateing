
package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import entity.Login;
import factory.colorFactory;
import hibernate.hibernate;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicBtn;
import layoutSetting.basicPanel;
import message.errorMessage;

@SuppressWarnings("serial")
public class login_management extends basicPanel{
	public static DefaultTableModel model;
	public static JTable table;
	public static basicBtn admin_authority;
	public login_management() {
		// TODO Auto-generated constructor stub
		String col [] = {"Id","�̸�","������ ����","��å","���̵�","�н�����","E-mail"};
		table = new JTable();
		model = new DefaultTableModel(col,0);
		table.setModel(model);
		JScrollPane pane = new JScrollPane(table);
		admin_authority = new basicBtn("������ ���� �ο�");
		basicBtn add = new basicBtn("�߰�");
		basicBtn save = new basicBtn("����");
		basicBtn delete = new basicBtn("����");
		basicPanel buttonpanel = new basicPanel();
		buttonpanel.add(admin_authority);
		buttonpanel.add(save);
		buttonpanel.add(add);
		buttonpanel.add(delete);
		admin_authority.setVisible(false);
		
		basicBorderPanel toppanel = new basicBorderPanel();
		LineBorder border = new LineBorder(Color.LIGHT_GRAY, 1);
		toppanel.setBorder(border);
		toppanel.setLayout(new BorderLayout());
		toppanel.add(buttonpanel,BorderLayout.WEST);
		
		setLayout(new BorderLayout());
		add(pane,BorderLayout.CENTER);
		add(toppanel,BorderLayout.NORTH);
	
		StartData();
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try (Session session = hibernate.factory.openSession()) {
					hibernate.transaction = session.beginTransaction();
					model = (DefaultTableModel) table.getModel();
					Login login = new Login();
					login.setAdmin_authority(false);
					session.save(login);
					hibernate.transaction.commit();
					model.addRow(new Object[] {login.getId(),"",false,"","","",""});
				} catch (Exception e) {
					e.printStackTrace();
					new errorMessage("add");
				}
			}
		});
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try (Session session = hibernate.factory.openSession()) {
					hibernate.transaction = session.beginTransaction();
					int row [] = table.getSelectedRows();
					int confirm=JOptionPane.showConfirmDialog(null,row.length+"���� �׸��� ���� �����Ͻðڽ��ϱ� ?","����",JOptionPane.YES_NO_OPTION);
					if(confirm==0) {//��ư �� = 0;
						for(int i=row.length-1; i>=0;i--) {
							int n=row[i];
							int id = (int) table.getValueAt(n, 0);
							model = (DefaultTableModel) table.getModel();
							Login login = session.get(Login.class, id);
							session.delete(login);
							model.removeRow(n);
						}
					}
					hibernate.transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
					new errorMessage("delete");
				}
			}
		});
		 save.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try (Session session = hibernate.factory.openSession()) {
						hibernate.transaction = session.beginTransaction();
						model = (DefaultTableModel) table.getModel();
						for (int i = 0; i < model.getRowCount(); i++) {
							int id = (int) model.getValueAt(i, 0);
							if(id!=1) {
								Login login = session.get(Login.class, id);
								login.setName((String) model.getValueAt(i, 1));
								login.setPosition((String) model.getValueAt(i, 3));
								login.setLoginId((String) model.getValueAt(i, 4));
								login.setLoginPw((String) model.getValueAt(i, 5));
								login.setEmail((String) model.getValueAt(i, 6));
								session.update(login);
							}else {
								JOptionPane.showMessageDialog(null, "������ ���̵�� ������ �Ұ����մϴ�.");
							}
						}
						JOptionPane.showMessageDialog(null, "���������� ó���Ǿ����ϴ�.");
						hibernate.transaction.commit();
						refresh();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showInternalMessageDialog(null, "Error","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		 admin_authority.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try (Session session = hibernate.factory.openSession()) {
					hibernate.transaction = session.beginTransaction();
					int row = table.getSelectedRow();
					int confirm=JOptionPane.showConfirmDialog(null,"������ ������ �ο� �Ͻðڽ��ϱ� ?","���� �ο�",JOptionPane.YES_NO_OPTION);
					if(confirm==0) {//��ư �� = 0;
							int id = (int) table.getValueAt(row, 0);
							model = (DefaultTableModel) table.getModel();
							Login login = session.get(Login.class, id);
							login.setAdmin_authority(true);
							session.update(login);
					}
					hibernate.transaction.commit();
					refresh();
				} catch (Exception e) {
					new errorMessage("update");
				}
			}
		});
	}
	public static void StartData() {
		try (Session session = hibernate.factory.openSession()) {
			hibernate.transaction = session.beginTransaction();
			model = (DefaultTableModel) table.getModel();
			ArrayList<Login> login = (ArrayList<Login>) session
					.createCriteria(Login.class).list();
			//Collections.sort(productionplan, new ShipmentdateUpCompare());
			for (Login log :login) {
				if(log.getId()==1) {
					model.addRow(new Object[] { log.getId(),log.getName(),"������","******","******"});
				}else {
					model.addRow(new Object[] { log.getId(),log.getName(),log.isAdmin_authority(),log.getPosition(),log.getLoginId(),log.getLoginPw(),log.getEmail()});
				}
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void refresh() {
		model.setRowCount(0);
		StartData();
	}
}
