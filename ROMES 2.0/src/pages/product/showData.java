package pages.product;

import javax.swing.ImageIcon;

public class showData {
	public int key;
	public ImageIcon icon;
	public String productName;
	public String productPrice;

	public showData(int i, ImageIcon icon, String productName, String productPrice) {
		super();
		this.key = i;
		this.icon = icon;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + key;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		showData other = (showData) obj;
		if (key != other.key)
			return false;
		return true;
	}

}
