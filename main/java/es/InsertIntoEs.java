package es;

import es.conf.ConfigureES;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class InsertIntoEs {
    private BulkRequestBuilder bulkRequest = null;
    TransportClient client = new ConfigureES().client;
    DateFormat dfl = new SimpleDateFormat("yyyy-MM-dd");

    public InsertIntoEs() {
    }

    public InsertIntoEs(Dataset<Row> ds) throws java.io.IOException {
        this.bulkRequest = this.client.prepareBulk();
        ds.show(10,0);
//        System.exit(0);
        Row[] row = (Row[]) ds.collect();
        for (Row data : row) {

            try {
                this.bulkRequest.add(this.client.prepareIndex("hgi_19_20", "policy")
                        .setSource(XContentFactory.jsonBuilder()
                                .startObject()
                                .field("portfolio", data.get(0))
                                .field("issue_date", data.get(1))
                                .field("expiry_date", data.get(2))
                                .field("effective_date", data.get(3))
                                .field("branch", data.get(4))
                                .field("class", data.get(5))
                                .field("document_no", data.get(6))
                                .field("document_type", data.get(7))
                                .field("endorsement_type", data.get(8))
                                .field("sum_insured_amount", data.get(9))
                                .field("premium_amount", data.get(10))
                                .field("stamp_duty", data.get(11))
                                .field("vat_amount", data.get(12))
                                .field("vat_bill_no", data.get(13))
                                .field("commission_amount", data.get(14))
                                .field("policy_no", data.get(15))
                                .field("insured_name", data.get(16))
                                .field("quater", data.get(17))
                                .field("month_name", data.get(18))
                                .field("fiscal_month", data.get(19))
                                .field("agent", data.get(20))
                                .field("data", data.get(21))
                                .endObject()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        closeESClient(this.client, this.bulkRequest);
    }

    public void readFile() {
        List<Map<String, String>> csvInputList = new CopyOnWriteArrayList<>();
        List<Map<String, Integer>> headerList = new CopyOnWriteArrayList<>();

        CSVFormat format = CSVFormat.newFormat(',').withHeader();

        try (BufferedReader inputReader = new BufferedReader(new FileReader(new File("/home/saurab/Documents/hgi/all_policy_issuance_actual.csv")));
             CSVParser dataCSVParser = new CSVParser(inputReader, format);) {

            List<CSVRecord> csvRecords = dataCSVParser.getRecords();

            Map<String, Integer> headerMap = dataCSVParser.getHeaderMap();
            headerList.add(headerMap);
            headerList.forEach(System.out::println);

            for (CSVRecord record : csvRecords) {
                Map<String, String> inputMap = new LinkedHashMap<>();
//                System.out.println(record.get("AgentDetail"));
//                System.out.println(new JSONObject(record.get("AgentDetail")).get("AgentName"));
                for (Map.Entry<String, Integer> header : headerMap.entrySet()) {
                    inputMap.put(header.getKey(), record.get(header.getValue()));
                }

                if (!inputMap.isEmpty()) {
                    csvInputList.add(inputMap);
                }
            }

            csvInputList.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e);
        }
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