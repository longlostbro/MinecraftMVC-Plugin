package com.simplifiedgamingsolutions.minecraftmvc;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by longl on 2/16/2016.
 */
public class Client
{

    String serveraddress;
    int port;

    public Client(String serveraddress, int port)
    {
        this.serveraddress = serveraddress;
        this.port = port;
    }

    public String PlayerJoined(String player) throws Exception,
            IOException
    {
        return StringUtils.newStringUtf8(doPost("/PlayerJoined",
                URLEncoder.encode(player, "UTF-8")));
    }

    public String PlayerLeft(String player) throws Exception,
            IOException
    {
        return StringUtils.newStringUtf8(doPost("/PlayerLeft",
                URLEncoder.encode(player, "UTF-8")));
    }

    private byte[] doPost(String urlPath, String postData)
            throws Exception
    {
        try
        {
            URL url = new URL("http", serveraddress, port, urlPath);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.println(postData);
            writer.flush();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStream responseBody = connection.getInputStream();
                // Read response body from InputStream ...
                byte[] bytes = IOUtils.toByteArray(responseBody);
                responseBody.close();
                connection.disconnect();
                return bytes;
            } else
            {
                connection.disconnect();
                throw new Exception();
            }
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    public static void main(String[] args)
    {
        Thread thread = new Thread(new Runnable()
        {

            public void run()
            {
                try
                {
                    new Client("localhost", 39640).PlayerJoined("testplayer");
                    Thread.sleep(10000);
                    new Client("localhost", 39640).PlayerLeft("testplayer");
                    // System.out.println(result);
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}