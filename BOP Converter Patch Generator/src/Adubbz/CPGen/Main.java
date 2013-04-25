package Adubbz.CPGen;

import java.awt.EventQueue;

public class Main {

	public static void main(String args [])
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
