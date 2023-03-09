package bus.management;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BusManagement {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		MainScreen mr = new MainScreen();
		mr.setLocationRelativeTo(null);
		mr.setVisible(true);
	}
}
