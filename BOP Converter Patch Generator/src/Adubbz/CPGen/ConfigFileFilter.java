package Adubbz.CPGen;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ConfigFileFilter extends FileFilter {

	private String ImageFormat = "CFG";
	private char DotIndex = '.';
	
	public boolean accept(File file)
	{
		if (file.isDirectory())
		{
			return true;
		}
		else if (extension(file).equalsIgnoreCase(ImageFormat))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public String getDescription() 
	{
		return "Config Files Only";
	}
	
	public String extension(File file)
	{
		String FileName = file.getName();
		int IndexFile = FileName.lastIndexOf(DotIndex);
		
		if (IndexFile > 0 && IndexFile < FileName.length() - 1)
		{
			return FileName.substring(IndexFile + 1);
		}
		else
		{
			return "";
		}
	}
}
