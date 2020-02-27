package spark;

import helper.GetFromProperty;
import org.apache.spark.sql.SparkSession;
import scala.reflect.ScalaSignature;

import java.io.IOException;

public class SparkPhoenixConnection
{
  public SparkPhoenixConnection() throws IOException {
  }

  private GetFromProperty gfp()
  {
    return this.gfp;
  }
  
  private final GetFromProperty gfp = new GetFromProperty();
  
  public SparkSession spark()
  {
    this.spark.sparkContext().setLogLevel("ERROR");
    return this.spark;
  }

  private final SparkSession spark = SparkSession.builder().master(gfp().get_spark_master()).getOrCreate();
}
