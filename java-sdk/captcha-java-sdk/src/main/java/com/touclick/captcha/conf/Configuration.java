package com.touclick.captcha.conf;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Configuration {
	private Configuration(){}
	private static Properties p = new Properties();  
	 
	private static final Logger LOGGER = Logger.getLogger(Configuration.class);
    /** 
     * 读取properties配置文件信息 
     */  
    static{  
        try {  
            p.load(Configuration.class.getClassLoader().getResourceAsStream("config.properties"));  
        } catch (IOException e) {  
            LOGGER.error("load config.properties error",e);
        }  
    }  
    /** 
     * 根据key得到value的值 
     * @throws IllegalAccessException 
     */  
    public static String getString(String key) throws IllegalArgumentException  
    {  
    	String value = p.getProperty(key); 
        return value == null ? "" : value;
    }  
    
    public static Integer getInteger(String key)  
    {  
    	try {
    		return Integer.parseInt(p.getProperty(key));  
		} catch (Exception e) {
			LOGGER.error("transfer String to Integer error",e);
		}
    	return 0;
    } 
    
    public static Boolean getBoolean(String key)  
    {  
    	try {
    		String value = p.getProperty(key);
    		if(value != null && !"".equals(value) && "true".equals(value)){
    			return Boolean.TRUE;
    		} 
		} catch (Exception e) {
			LOGGER.error("transfer String to Integer error",e);
		}
    	return Boolean.FALSE;
    } 
}
	