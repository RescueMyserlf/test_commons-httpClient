package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * @author Administrator
 *
 */
public class CfgHelper {
public static Properties props = new Properties();
	private static void ReadCfgFile(){
		InputStream is=CfgHelper.class.getResourceAsStream("config.cfg");
		BufferedReader br= new BufferedReader(new InputStreamReader(is));
		try {
			props.load(br);
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
		public static String getString(String key){
			if (props.size() == 0){
				ReadCfgFile();
			}
			return props.getProperty(key);
		}
	
}
