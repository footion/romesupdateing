package page.storing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.imagelabel.ImageLabel;
import components.imagelabel.ImgPanel;
import evevtListener.textFieldEvent;
import pages.location.LocationResources.ResizeImageLabel;
import pages.utils.ColoredPanel;

public class CreateStoring extends ColoredPanel {
	private JTextField countText;
	private JTextField lotText;
	private JLabel productName;
	private JLabel companyName;
	private JLabel countOrderField;
	private JLabel preDateField;
	private JLabel workerField;

	public CreateStoring() {
		this.setLayout(new BorderLayout());
		ImageLabel button = new ImageLabel(new ImageIcon("./resources/saveButton.png"),new ImageIcon("./resources/clickedButton.png"), "저장");
		button=(ImageLabel) button.resizeLabel(button,150,50);
		button.setAlignmentX( Component.RIGHT_ALIGNMENT );
	
		ColoredPanel botPanel = new ColoredPanel();
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.LINE_AXIS));
		botPanel.add(Box.createHorizontalGlue());
		botPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		botPanel.add(button);
		botPanel.setPreferredSize(new Dimension(0, 200));
		this.add(botPanel,BorderLayout.SOUTH);
		
		
		
		//center panel
		JPanel centerPanel = new ColoredPanel();
		centerPanel.setLayout(new BorderLayout());
		JPanel countPanel = new ColoredPanel();
		countPanel.setPreferredSize(new Dimension(0, 50));
		JLabel count = new JLabel("갯수 (개) : ");
		count.setFont(new Font("나눔고딕", Font.BOLD, 16));
		count.setAlignmentX( Component.LEFT_ALIGNMENT );
		this.countText= new JTextField();
		countText.setAlignmentX(Component.LEFT_ALIGNMENT);
		countText.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		countPanel.setLayout(new BoxLayout(countPanel, BoxLayout.LINE_AXIS));
		countPanel.add(Box.createHorizontalGlue());
		countPanel.add(Box.createRigidArea(new Dimension(100, 0)));
		countPanel.add(count);
		countPanel.add(Box.createRigidArea(new Dimension(100, 0)));
		countPanel.add(countText);
		countPanel.add(Box.createRigidArea(new Dimension(500, 1)));
		JPanel grayPaenl = new ImgPanel(new ImageIcon("./resources/backGroundGray.png"));
		grayPaenl.setPreferredSize(new Dimension(0, 300));
		centerPanel.add(countPanel,BorderLayout.SOUTH);
		
		grayPaenl.setLayout(new GridLayout(0, 4, 30, 30));
		grayPaenl.setBorder(new EmptyBorder(60,70,30,70));
		JLabel product = new JLabel("제품 이름 : ");
		product.setFont(new Font("나눔고딕", Font.BOLD, 16));
		this.productName = new JLabel("노즐캡");
		productName.setFont(new Font("나눔고딕", Font.BOLD, 16));
		JLabel company = new JLabel("판매사 : ");
		company.setFont(new Font("나눔고딕", Font.BOLD, 16));
		this. companyName = new JLabel("노즐회사");
		companyName.setFont(new Font("나눔고딕", Font.BOLD, 16));
		JLabel countOrder = new JLabel("주문 갯수");
		countOrder.setFont(new Font("나눔고딕", Font.BOLD, 16));
		this. countOrderField = new JLabel("10000");
		countOrderField.setFont(new Font("나눔고딕", Font.BOLD, 16));
		JLabel preDate = new JLabel("입고예정일");
		preDate.setFont(new Font("나눔고딕", Font.BOLD, 16));
		this. preDateField = new JLabel("2020-07-06");
		preDateField.setFont(new Font("나눔고딕", Font.BOLD, 16));
		JLabel worker = new JLabel("담당자");
		worker.setFont(new Font("나눔고딕", Font.BOLD, 16));
		this. workerField = new JLabel("정지원");
		workerField.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		grayPaenl.add(product);
		grayPaenl.add(productName);
		grayPaenl.add(company);
		grayPaenl.add(companyName);
		grayPaenl.add(countOrder);
		grayPaenl.add(countOrderField);
		grayPaenl.add(preDate);
		grayPaenl.add(preDateField);
		grayPaenl.add(worker);
		grayPaenl.add(workerField);
		
		centerPanel.add(grayPaenl,BorderLayout.CENTER);
		
		JPanel selectOrderLotPanel = new ColoredPanel();
		selectOrderLotPanel.setPreferredSize(new Dimension(0, 100));
		selectOrderLotPanel.setLayout(new BoxLayout(selectOrderLotPanel,BoxLayout.LINE_AXIS));
		JLabel lot = new JLabel("발주 lot : ");
		lot.setFont(new Font("나눔고딕", Font.BOLD, 16));
		lot.setAlignmentX( Component.LEFT_ALIGNMENT );
		this.lotText= new JTextField();
		lotText.setName("inputLot");
		lotText.addMouseListener(new textFieldEvent());
		lotText.setAlignmentX(Component.LEFT_ALIGNMENT);
		lotText.setFont(new Font("나눔고딕", Font.BOLD, 16));
		
		selectOrderLotPanel.add(Box.createHorizontalGlue());
		selectOrderLotPanel.add(Box.createRigidArea(new Dimension(100, 0)));
		selectOrderLotPanel.add(lot);
		selectOrderLotPanel.add(Box.createRigidArea(new Dimension(100, 0)));
		selectOrderLotPanel.add(lotText);
		selectOrderLotPanel.add(Box.createRigidArea(new Dimension(500, 1)));
		selectOrderLotPanel.setBorder(new EmptyBorder(25, 0, 25, 0));
		centerPanel.add(selectOrderLotPanel,BorderLayout.NORTH );
		this.add(centerPanel,BorderLayout.CENTER);
	}
}
