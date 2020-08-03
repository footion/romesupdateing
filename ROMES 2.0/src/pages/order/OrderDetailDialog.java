package pages.order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import before.DbChecker;
import components.imagelabel.ImageLabel;
import entity.OrderHistory;
import entity.dao.OrderHistoryDAO;
import pages.utils.ColoredPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderDetailDialog extends JDialog {

	private final ColoredPanel contentPanel = new ColoredPanel();
	private OrderHistoryDAO dao;
	private OrderHistory orderhistoryEntity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DbChecker.initCfg();
			OrderDetailDialog dialog = new OrderDetailDialog(131);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param pkey
	 */
	public OrderDetailDialog(int pkey) {
		System.out.println(pkey);
		dao = new OrderHistoryDAO();
		dao.getOrderHistory().forEach(e->System.out.println(e));
		orderhistoryEntity = dao.findByPkey(pkey);
		System.out.println(orderhistoryEntity);
		setBounds(100, 100, 499, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(
				new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
						new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
								FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));
		{
			ColoredPanel panel = new ColoredPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel, "2, 2, fill, fill");
			{
				JLabel label = new JLabel("발주 상세보기");
				label.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
				panel.add(label);
			}
		}
		{
			ColoredPanel panel = new ColoredPanel();
			panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 10, 0, 10)));
			contentPanel.add(panel, "2, 4, fill, fill");
			panel.setLayout(new FormLayout(
					new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(45dlu;default)"),
							FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
							ColumnSpec.decode("max(81dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
							ColumnSpec.decode("max(40dlu;default):grow"), },
					new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(24dlu;default)"),
							FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(24dlu;default)"),
							FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(24dlu;default)"),
							FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(24dlu;default)"),
							FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(24dlu;default)"),
							FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));
			{
				JLabel lbllot = new JLabel("발주lot");
				panel.add(lbllot, "2, 4");
			}
			{
				JLabel lblNewLabel = new JLabel(orderhistoryEntity.getLotNo());
				panel.add(lblNewLabel, "4, 4");
			}
			{
				ColoredPanel panel_1 = new ColoredPanel();
				panel.add(panel_1, "6, 2, 1, 3, fill, fill");
			}
			{
				JLabel lblNewLabel_2 = new ImageLabel(new ImageIcon(orderhistoryEntity.getProduct().getImage()),
						"image");
				panel.add(lblNewLabel_2, "8, 2, 1, 3");
			}
			{
				JLabel label = new JLabel("제품명");
				label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				panel.add(label, "2, 2");
			}
			{
				JLabel lblNewLabel_1 = new JLabel(orderhistoryEntity.getProduct().getName());
				lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				panel.add(lblNewLabel_1, "4, 2");
			}
			{
				JLabel label = new JLabel("발주회사");
				panel.add(label, "2, 6");
			}
			{
				JLabel label = new JLabel(orderhistoryEntity.getCompany().getCompanyName());
				panel.add(label, "4, 6");
			}
			{
				ColoredPanel panel_1 = new ColoredPanel();
				panel.add(panel_1, "6, 6, 3, 5, fill, fill");
				panel_1.setLayout(new FormLayout(
						new ColumnSpec[] { ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
								ColumnSpec.decode("default:grow"), },
						new RowSpec[] { RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC,
								RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC,
								RowSpec.decode("default:grow"), }));
				{
					JLabel label = new JLabel("작업자");
					panel_1.add(label, "1, 1");
				}
				{
					JLabel label = new JLabel(orderhistoryEntity.getWorker().getName());
					panel_1.add(label, "3, 1");
				}
				{
					JLabel lblNewLabel_3 = new JLabel("현재입고갯수");
					panel_1.add(lblNewLabel_3, "1, 3");
				}
				{
					JLabel label = new JLabel("500");
					panel_1.add(label, "3, 3");
				}
				{
					JLabel label = new JLabel("도착예정날짜");
					panel_1.add(label, "1, 5");
				}
				{
					JLabel label = new JLabel(orderhistoryEntity.getPlannedArrivalDate());
					panel_1.add(label, "3, 5");
				}
			}
			{
				JLabel label = new JLabel("주문갯수");
				panel.add(label, "2, 8");
			}
			{
				JLabel label = new JLabel(orderhistoryEntity.getQuantity() + "");
				panel.add(label, "4, 8");
			}
			{
				JLabel label = new JLabel("주문날짜");
				panel.add(label, "2, 10");
			}
			{
				JLabel label = new JLabel(orderhistoryEntity.getOrderDate());
				panel.add(label, "4, 10");
			}
			{
				JLabel lblNewLabel_4 = new JLabel("미완료");
				lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
				lblNewLabel_4.setForeground(Color.RED);
				lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel_4, "2, 12, 7, 1");
			}
		}
		{
			ColoredPanel buttonPane = new ColoredPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("확인");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
