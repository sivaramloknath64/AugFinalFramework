package Aug.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configreader {
 protected Properties prop;
 
	public configreader() {
		String path=System.getProperty("user.dir")+"\\config.properties";
		try {
			prop=new Properties();
			FileInputStream file=new FileInputStream(path);
			prop.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public String getPropertyval(String val) {
	return prop.getProperty(val);
}
	
}
