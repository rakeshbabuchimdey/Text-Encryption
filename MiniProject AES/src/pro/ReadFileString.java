package pro;

import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 
public class ReadFileString 
{ 
	public String rfs (String path) throws Exception 
	{ 
		try
		{ 
			String entireFileText = new Scanner(new File(path)) .useDelimiter("A").next(); 
			return entireFileText; 
		} 
		catch(Exception e)
		{ 	
			System.err.println(e); 
			return null; 
		} 
	} 
}
