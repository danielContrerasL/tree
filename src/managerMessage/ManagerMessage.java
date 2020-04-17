package managerMessage;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class ManagerMessage {
	public void showMesagge(String mesagge, Frame frame) {
		JOptionPane.showMessageDialog(frame, mesagge, "Te sugerimos ver primero" , JOptionPane.INFORMATION_MESSAGE);
	}

}
