package com.src.server.connect;

import com.src.server.gson.Mapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect {

    /**
     * Manda un Post de un objeto a la direccion ip y el end point indicado
     * Tambien transforma el objeto a json
     * @param ipPort
     * @param endPoint
     * @param object
     * @return
     * @throws IOException
     */
    public String sendPost(String ipPort, String endPoint, Object object) throws IOException {
        URL url = new URL("http://" + ipPort + endPoint);

        HttpURLConnection connection = setConnectionPost(url);
        connection.setDoOutput(true);
        // Me transforma en Json el objeto
        String string = Mapper.toJson(object);

        sendObjectToServer(string, connection);
        String response = getResponse(connection);

        return response;
    }


    protected HttpURLConnection setConnectionPost(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        return connection;
    }

    protected void sendObjectToServer(String string, HttpURLConnection connection) {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = string.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    protected String getResponse(HttpURLConnection connection) {
        String string = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            //System.out.println(response.toString());
            string = response.toString();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return string;
    }
}
