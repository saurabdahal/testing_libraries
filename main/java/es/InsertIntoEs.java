package es;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class InsertIntoEs {
    private BulkRequestBuilder bulkRequest = null;
    TransportClient client = new ConfigureES().client;
    DateFormat dfl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public InsertIntoEs(Dataset<Row> ds) throws java.io.IOException {
        this.bulkRequest = this.client.prepareBulk();
        Row[] row = (Row[]) ds.collect();
        for (Row data : row) {
            Object bv_thou = data.get(7);
            Object sku = data.get(6);
            if (data.get(7) == null) {
                bv_thou = 0.0D;
            }
            if (data.get(6) == null) {
                sku = 0.0D;
            }
            try {
                this.bulkRequest.add(this.client.prepareIndex("bm_top", "top", encryptID(data.getString(0) + "/" + data.getString(5)))
                        .setSource(XContentFactory.jsonBuilder()
                                .startObject()
                                .field("item", data.get(0))
                                .field("qty_contrib", data.get(1))
                                .field("bp", data.get(2))
                                .field("rev_contrib", data.get(3))
                                .field("division", data.get(4))
                                .field("bill_date", data.get(5))
                                .field("sku", sku)
                                .field("bv_thou", bv_thou)
                                .endObject()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        closeESClient(this.client, this.bulkRequest);
    }

    private void closeESClient(TransportClient client, BulkRequestBuilder bulkRequest) {
        try {
            System.out.println("number of actions : " + bulkRequest.numberOfActions());
            BulkResponse r = (BulkResponse) bulkRequest.get();
            System.out.println(r.buildFailureMessage());
            for (BulkItemResponse a : r.getItems()) {
                System.out.println("failure message : " + a.getFailureMessage());
                System.out.println("response : " + a.getResponse());
                System.out.println("failure : " + a.getFailure());
            }
            System.out.println("closing client");
            client.close();
        } catch (Exception we) {
            we.printStackTrace();
        }
    }

    private String encryptID(String id) {
        String enc_id = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(id.getBytes());
            byte[] digest = md.digest();
            enc_id = DatatypeConverter.printHexBinary(digest).toUpperCase().substring(0, 20);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return enc_id;
    }
}