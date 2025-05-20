package automation_common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SwabLabs_SupportingCapabilities {


	public String getPropertyVal(String propertyName, String fileName)
	{
		Properties prop = new Properties();
		try {
			//load a properties file from class path, inside static method
			prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/"+ fileName));
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty(propertyName);
	}

}
