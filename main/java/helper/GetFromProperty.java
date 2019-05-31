package helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetFromProperty
 {
 Properties prop;
 String rootPath;
 String appConfigPath;
InputStream ins;

public GetFromProperty() throws IOException
 {
this.prop = new Properties();

 InputStream ins = getClass().getClassLoader().getResourceAsStream("api.properties");
this.prop.load(ins);
 }

 protected String get_log_url() { return this.prop.getProperty("log_url"); }
 public String get_spark_master() { return this.prop.getProperty("spark_master"); }

protected String get_zkQuoram() { return this.prop.getProperty("zk1") + "," + this.prop.getProperty("zk2") + "," + this.prop.getProperty("zk3") + "," + this.prop.getProperty("zk4") + ":" + this.prop.getProperty("zkport"); }
}


