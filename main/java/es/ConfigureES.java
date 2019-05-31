package es;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConfigureES
{
  org.elasticsearch.client.transport.TransportClient client;
  Settings settings;


  public ConfigureES()
  {
    System.setProperty("es.set.netty.runtime.available.processors", "false");
   this.settings = Settings.builder().put("cluster.name", "ek-ES").build();
    try
    {
     this.client = new PreBuiltTransportClient(this.settings, new Class[0])
             .addTransportAddress(new TransportAddress(InetAddress.getByName("10.10.5.72"), 9300));
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
 }
}

