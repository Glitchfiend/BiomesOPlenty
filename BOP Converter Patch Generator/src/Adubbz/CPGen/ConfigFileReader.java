package Adubbz.CPGen;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigFileReader {
	
	public static String text = "";

	public static void read() throws Exception
	{
		FileReader file = new FileReader(ActionHandler.importConfigSelectedFile.toString());
		BufferedReader reader = new BufferedReader(file);

		String line = reader.readLine();
		
		boolean foundBlock = false;	
		boolean endBlock = false;

		while (line != null)
		{
			String currentLineText = line;
			
			if (foundBlock && currentLineText.contains("}"))
			{
				endBlock = true;
			}
			
			if (foundBlock && !endBlock)
			{
				text += line;
			}
			
			if (currentLineText.contains("block {"))
			{
				foundBlock = true;
			}
			
			line = reader.readLine();
		}

		reader.close();
		
		Gui.progressBar.setValue(51);
		
		ConfigTextFilter.filter();
	}
}
