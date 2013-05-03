package Adubbz.CPGen;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DirectoryFileFilter extends FileFilter {

	public boolean accept(File file)
	{
		if (file.isDirectory())
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
		return "Directories Only";
	}
}
