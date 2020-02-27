package es.conf;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConfigureES
{
  public org.elasticsearch.client.transport.TransportClient client;
  private Settings settings;


  public ConfigureES()
  {
    System.setProperty("es.set.netty.runtime.available.processors", "false");
   this.settings = Settings.builder().put("cluster.name", "dev-cluster").build();
    try
    {
     this.client = new PreBuiltTransportClient(this.settings)
             .addTransportAddress(new TransportAddress(InetAddress.getByName("10.10.5.30"), 9300));
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
 }
}

