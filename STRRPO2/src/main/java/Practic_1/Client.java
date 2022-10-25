package Practic_1;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Client {
    Vector<String> vector = null;
    XmlRpcClient client = null;
    String serverUrl = "";

    public Client() throws MalformedURLException {
        serverUrl = "http://localhost:1999/RPC2";
        client = new XmlRpcClient(serverUrl);
        vector = new Vector<String>();
    }

    void showData() {
        String ar = null;
        try {
            ar = (String) client.execute("serverRPC.getData", vector);
        }
        catch (XmlRpcException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        StringTokenizer stringTokenizer = new StringTokenizer(ar, "-");
        while(stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        Client client = new Client();
        client.showData();
    }
}
