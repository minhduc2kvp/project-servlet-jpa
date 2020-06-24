package com.minhvu.fruit.common;

import com.google.gson.Gson;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class AppConfig {
     public static final EntityManager ENTITY_MANAGER =
            Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();
     public static final Gson GSON = new Gson();

     public static String encodeUTF8(String data){
          String result = null;
          try {
               result =  new String(data.getBytes("ISO-8859-1"),"UTF-8");
          } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
          }
          return result;
     }

     public static String uploadFileImage(String base64String){
          String url_file = "";
          String nameFile = UUID.randomUUID().toString();
          byte[] data = Base64.getDecoder().decode(base64String);
          try (OutputStream stream = new FileOutputStream("D:\\Java Backend\\ServletProject\\web\\image\\" + nameFile + ".jpg")) {
               stream.write(data);
               url_file = "./image/" + nameFile + ".jpg";
          } catch (IOException e) {
               e.printStackTrace();
          }
          return url_file;
     }

     public static void logError(Exception e){
          System.out.println("----------------- ERROR HERE -----------------");
          System.out.println(e.getMessage());
          e.printStackTrace();
          System.out.println("----------------------------------------------");
     }
}
