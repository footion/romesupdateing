package message;

import javax.swing.JOptionPane;

public class errorMessage {
	public errorMessage(String text) {
		JOptionPane.showMessageDialog(null, "Failed to "+text+" an error !","Error",JOptionPane.ERROR_MESSAGE);
	}
}
