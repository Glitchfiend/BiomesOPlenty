package Adubbz.CPGen;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Gui extends JFrame {
	
	public static JFrame frame;
	
	public static JTextField textFieldImportConfig;
	public static JTextField textFieldOutputPatch;
	
	public static JButton btnImportConfig = new JButton("Import Config");
	public static JButton btnOutputPatch = new JButton("Output Patch");
	public static JButton btnStart = new JButton("Start");
	
	public static JProgressBar progressBar = new JProgressBar();
	
	public Gui() {
		getContentPane().setLayout(new BorderLayout());
		
		frame = new JFrame("BOP Converter Patch Generator");
		frame.setBounds(100, 100, 556, 141);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		btnImportConfig.setBounds(425, 11, 115, 23);
		frame.getContentPane().add(btnImportConfig);
		
		btnOutputPatch.setBounds(425, 45, 115, 23);
		frame.getContentPane().add(btnOutputPatch);
		
		textFieldImportConfig = new JTextField();
		textFieldImportConfig.setBounds(10, 12, 405, 20);
		frame.getContentPane().add(textFieldImportConfig);
		textFieldImportConfig.setColumns(10);
		textFieldImportConfig.setEditable(false);
		
		textFieldOutputPatch = new JTextField();
		textFieldOutputPatch.setBounds(10, 46, 405, 20);
		frame.getContentPane().add(textFieldOutputPatch);
		textFieldOutputPatch.setColumns(10);
		textFieldOutputPatch.setEditable(false);
		
		btnStart.setBounds(425, 79, 115, 23);
		frame.getContentPane().add(btnStart);
		
		progressBar.setBounds(10, 77, 405, 25);
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		frame.getContentPane().add(progressBar);
		
		ActionHandler handler = new ActionHandler();
		
		btnImportConfig.addActionListener(handler);
		btnOutputPatch.addActionListener(handler);
		btnStart.addActionListener(handler);
	}
}

