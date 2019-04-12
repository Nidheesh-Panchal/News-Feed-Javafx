/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;


/**
 *
 * @author nilu1
 */
public class News
{
    
    final static String APP_ID="379ffae9a00341d8b3360ad895d59c1f";
    
    public static void search(String keyword) throws Exception
    {
        Map<String, String> parameters = new HashMap<>();
        String newseverything="https://newsapi.org/v2/everything?";
        String newstopheadlines="https://newsapi.org/v2/top-headlines?";
        parameters.put("q", "bitcoin");
        parameters.put("apiKey",APP_ID);
        newseverything+=ParameterStringBuilder.getParamsString(parameters);
        System.out.println(newseverything);
        
        URL url = new URL(newseverything);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        
        int status = con.getResponseCode();
        
        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        int count=0;
        JSONObject response=null;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
        {
            content.append(inputLine);
            count++;
        }
        in.close();
        con.disconnect();
        String result=content.toString();
        JSONObject res=new JSONObject(result);
        String stat=res.getString("status");
        //System.out.println(stat);
        System.out.println(result);
//        JSONObject response=null;

//        BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line;
//        while((line=rd.readLine())!=null)
//        {
//            result.append(line);
//        }
//        System.out.println(result);
//        
//        Map<String,Object> resMap=jsonToMap(result.toString());
//        Map<String,Object> mainMap=jsonToMap(resMap.get("status").toString());
//        Map<String,Object> windMap=jsonToMap(resMap.get("totalResults").toString());
//        
//        System.out.println("Status : "+mainMap.get("status"))
    }
}