package functions;

import java.awt.Font;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTable;

import factory.fontFactory;
import factory.instanceofFactory;
import layoutSetting.basicLabel;
import layoutSetting.basicPanel;

@SuppressWarnings("serial")
public class totalPrice extends basicPanel{
	basicLabel totalPriceLabel;
	long totalprice=0;
	JTable Table;
	int PriceColNum;
	int QuantityColNum;
	NumberFormat numberFormat;
	public totalPrice(JTable table, int priceColNum, int quantityColNum) {
		Table=table;
		PriceColNum=priceColNum;
		QuantityColNum=quantityColNum;
		numberFormat = NumberFormat.getCurrencyInstance( Locale.KOREA );
		totalPriceLabel =new basicLabel("Total price : "+numberFormat.format(totalprice)+" won  ");
		totalPriceLabel.setFont(new Font(fontFactory.ENGLISH_FONT, Font.BOLD, 16));
		totalPriceLabel.setHorizontalAlignment(JLabel.RIGHT);
		this.add(totalPriceLabel);
	}
	public void refreshPrice() {
		totalprice=0;
		for(int i=0;i<Table.getRowCount();i++) {
			if(instanceofFactory.isStringInteger((String) Table.getValueAt(i, PriceColNum))
					&& instanceofFactory.isStringInteger((String) Table.getValueAt(i, QuantityColNum))) {
				long price =Long.parseLong((String) Table.getValueAt(i, PriceColNum));
				long quantity =Long.parseLong((String) Table.getValueAt(i, QuantityColNum));
				totalprice += price*quantity;
			}
		}
		totalPriceLabel.setText("Total price : "+numberFormat.format(totalprice)+" won  ");
	}
}
