package Adubbz.CPGen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class TextFileWriter {
	
	static int patchNo = 0;
	static File newFile;
	
	public static void write() throws Exception
	{
		if (patchNo == 0)
		{
			newFile = new File(ActionHandler.outputPatchSelectedFile.toString() + "/Patch.txt");
		}
		else
		{
			newFile = new File(ActionHandler.outputPatchSelectedFile.toString() + "/Patch" + patchNo + ".txt");	
		}
		
		if (newFile.exists())
		{
			System.out.println("The file already exists, adding number...");
			patchNo++;
			write();
		}
		else
		{
			newFile.createNewFile();
			create();
		}	
	}
	
	private static void create() throws Exception
	{
		Gui.progressBar.setValue(85);
		
		FileWriter fileWriter = new FileWriter(newFile);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		for (int i = 1; i < 141; ++i)
		{		
			bufferedWriter.write(ConfigTextFilter.splitString[i]);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();
		
		Gui.progressBar.setValue(100);
		
		System.out.println("File Written to " + newFile);
		
		JOptionPane.showMessageDialog(null, "Done!", null, JOptionPane.INFORMATION_MESSAGE);
		if (JOptionPane.OK_OPTION == 1);
		{			
			Gui.progressBar.setVisible(false);
		}
	}
}
