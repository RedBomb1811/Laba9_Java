package java_net;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class InternetClass {
    public static String getHtmlFromUrlString(String p_urlName) throws IOException {
        URL url = null;
        String urlName = p_urlName;
        try {
            url = new URL(urlName);
            URLConnection con = url.openConnection();
            System.out.println( "URL: " + con.getURL() + '\n' +
                                "Encoding: " + con.getContentType() + '\n' +
                                "Date: " + new Date(con.getDate()));
        } catch (MalformedURLException e) {
// некорректно заданы протокол, доменное имя или путь к файлу
            e.printStackTrace();
        }
        if (url == null) {
            throw new RuntimeException();
        }
        String lineFull = "";
        try (BufferedReader d = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line = "";
            while ((line = d.readLine()) != null) {
                lineFull += line + '\n';
//                System.out.println(line);
            }
            return lineFull;
        }
    }

    public static void main(String[] args) {
        try (BufferedWriter file = new BufferedWriter(new FileWriter("site.html"))) {
            file.write(InternetClass.getHtmlFromUrlString("https://www.tut.by"));
            System.out.println("Writing --- success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
