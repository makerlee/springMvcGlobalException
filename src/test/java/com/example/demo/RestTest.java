package com.example.demo;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijiyang on 2018/4/11.
 */
public class RestTest {
    public static void main(String[] args) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        final String APPLICATION_PDF = "image/jpeg";
        HttpHeaders headers = new HttpHeaders();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            List list = new ArrayList<>();
            list.add(MediaType.valueOf(APPLICATION_PDF));
            headers.setAccept(list);

            ResponseEntity<byte[]> response = restTemplate.exchange(
                    "http://api.map.baidu.com/panorama/v2?ak=LLEmsGCFCWcFIWCeNMPqls9lnlrAZhzF&width=512&height=256&location=116.313393,40.04778&fov=180",
                    HttpMethod.GET,
                    new HttpEntity<byte[]>(headers),
                    byte[].class);
            byte[] result = response.getBody();
            System.out.println(new String(result,"utf-8"));

            inputStream = new ByteArrayInputStream(result);

            File file = new File("d://test3.jpg");
            if (!file.exists())
            {
                file.createNewFile();
            }

            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();

        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
        }

    }
}
