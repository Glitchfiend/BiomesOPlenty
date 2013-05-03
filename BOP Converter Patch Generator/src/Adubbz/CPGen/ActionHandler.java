package Adubbz.CPGen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ActionHandler implements ActionListener {
	
	public static File importConfigSelectedFile = null;
	public static File outputPatchSelectedFile = null;

	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == Gui.btnImportConfig)
		{
			JFileChooser importFileChooser = new JFileChooser();
			
			importFileChooser.setDialogTitle("Select Pre-0.5.2 Config File");
			importFileChooser.setFileFilter(new ConfigFileFilter());
			importFileChooser.setAcceptAllFileFilterUsed(false);
			
			int importChecker = importFileChooser.showOpenDialog(null);
			
			importConfigSelectedFile = importFileChooser.getSelectedFile();
			
			if (importChecker == JFileChooser.APPROVE_OPTION)
			{
				System.out.println("Found file at " + importConfigSelectedFile.toString());

				Gui.textFieldImportConfig.setText(importConfigSelectedFile.toString());
			}
		}
		if (event.getSource() == Gui.btnOutputPatch)
		{
			JFileChooser outputFileChooser = new JFileChooser();
		
			outputFileChooser.setDialogTitle("Select Directory to Output to");
		    outputFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			outputFileChooser.setFileFilter(new DirectoryFileFilter());
			outputFileChooser.setAcceptAllFileFilterUsed(false);
			
			int outputChecker = outputFileChooser.showOpenDialog(null);
			
			outputPatchSelectedFile = outputFileChooser.getSelectedFile();
			
			if (outputChecker == JFileChooser.APPROVE_OPTION)
			{
				System.out.println("Found folder at " + outputPatchSelectedFile);

				Gui.textFieldOutputPatch.setText(outputPatchSelectedFile.toString());
			}
		}
		
		if (event.getSource() == Gui.btnStart)
		{
			Gui.progressBar.setValue(0);
			Gui.progressBar.setVisible(true);
			
			if (importConfigSelectedFile != null)
			{
				Gui.progressBar.setValue(17);
				if (outputPatchSelectedFile != null)
				{
					try 
					{
						Gui.progressBar.setValue(34);
						ConfigFileReader.read();
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You must select an Output Folder!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You must select a Config File!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
