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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author nilu1
 */
class MyJSONComparator implements Comparator<JSONObject> 
{

    @Override
    public int compare(JSONObject o1, JSONObject o2) 
    {
        String s=o1.get("publishedAt").toString();
        s=s.substring(0, s.length()-1);
        LocalDateTime v1=LocalDateTime.parse(s);
//        System.out.println(v1);
        String s1=o2.get("publishedAt").toString();
//        String d1=s1.substring(0, 10);
        s1=s1.substring(0, s1.length()-1);
        LocalDateTime v2=LocalDateTime.parse(s1);
        if(v1.compareTo(v2)>0)
        {
            return (-1);
        } 
        if(v1.compareTo(v2)<0)
        {
            return (1);
        } 
        else
        {
            return (0);
        }  
    }

}
public class News
{
    
    final static String APP_ID="379ffae9a00341d8b3360ad895d59c1f";
    
    public static ArrayList<NewsArray> everything(String keyword,String sort) throws Exception
    {
//        Map<String, String> parameters = new HashMap<>();
        Queue<String> q=new LinkedList<>();
        String newseverything="https://newsapi.org/v2/everything?";
        //String newstopheadlines="https://newsapi.org/v2/top-headlines?";
        q.add("q");
        q.add(keyword);
        q.add("sortBy");
        q.add(sort);
        q.add("apiKey");
        q.add(APP_ID);
        newseverything+=ParameterStringBuilder.getParamsString(q);
        System.out.println(newseverything);
        
        URL url = new URL(newseverything);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        
        int status = con.getResponseCode();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
        JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
        ArrayList<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) 
        {
            list.add((JSONObject) array.get(i));
            
        }
        
        
//        System.out.println(list);
//        Collections.sort(list, new MyJSONComparator());
//        System.out.println(list);
        String stat=res.getString("status");
        System.out.println(stat);
        System.out.println(result);
        System.out.println(list.get(0));
        return NewsArray.convert(list);
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
    
    public static ArrayList<NewsArray> topheadlines(String username) throws Exception
    {
//        Map<String, String> parameters = new HashMap<>();
        Queue<String> q=new LinkedList<>();
        //String newseverything="https://newsapi.org/v2/everything?";
        String newstopheadlines="https://newsapi.org/v2/top-headlines?";
        
        ConnectDatabase conn=new ConnectDatabase();
        String country=conn.getcountry(username);
        Queue<Integer> prefs=new LinkedList<>();
        
        prefs=conn.getpreference(username);
        if(prefs.isEmpty())
        {
            q.add("country");
            q.add(country);
            q.add("apiKey");
            q.add(APP_ID);
            newstopheadlines+=ParameterStringBuilder.getParamsString(q);
            System.out.println(newstopheadlines);

            URL url = new URL(newstopheadlines);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
            
            JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
            ArrayList<JSONObject> list = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                list.add((JSONObject) array.get(i));
            }
            
            
            String stat=res.getString("status");
            System.out.println(stat);
            System.out.println(result);
            NewsArray.convert(list);
        }
        
        ArrayList<JSONObject> list = new ArrayList<>();
        int temp=prefs.remove();
        if(temp==1)
        {
            newstopheadlines="https://newsapi.org/v2/top-headlines?";
            while(!q.isEmpty());
            q.add("country");
            q.add(country);
            q.add("category");
            q.add("business");
            q.add("apiKey");
            q.add(APP_ID);
            newstopheadlines+=ParameterStringBuilder.getParamsString(q);
            System.out.println(newstopheadlines);
            URL url = new URL(newstopheadlines);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            int count = 0;
            JSONObject response = null;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
                count++;
            }
            in.close();
            con.disconnect();
            String result = content.toString();
            JSONObject res = new JSONObject(result);
            
            JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
            
            for (int i = 0; i < array.length(); i++) {
                list.add((JSONObject) array.get(i));
            }
            
            String stat = res.getString("status");
            System.out.println(stat);
            System.out.println(result);
        }
        temp=prefs.remove();
        if(temp==1)
        {
            newstopheadlines="https://newsapi.org/v2/top-headlines?";
            while(!q.isEmpty());
            q.add("country");
            q.add(country);
            q.add("category");
            q.add("entertainment");
            q.add("apiKey");
            q.add(APP_ID);
            
            newstopheadlines+=ParameterStringBuilder.getParamsString(q);
            System.out.println(newstopheadlines);
            URL url = new URL(newstopheadlines);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            int count = 0;
            JSONObject response = null;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
                count++;
            }
            in.close();
            con.disconnect();
            String result = content.toString();
            JSONObject res = new JSONObject(result);
            
            JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
            
            for (int i = 0; i < array.length(); i++) {
                list.add((JSONObject) array.get(i));
            }
            
            String stat = res.getString("status");
            System.out.println(stat);
            System.out.println(result);
        }
        temp=prefs.remove();
        if(temp==1)
        {
            newstopheadlines="https://newsapi.org/v2/top-headlines?";
            while(!q.isEmpty());
            q.add("country");
            q.add(country);
            q.add("category");
            q.add("general");
            q.add("apiKey");
            q.add(APP_ID);
            
            newstopheadlines+=ParameterStringBuilder.getParamsString(q);
            System.out.println(newstopheadlines);
            URL url = new URL(newstopheadlines);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            int count = 0;
            JSONObject response = null;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
                count++;
            }
            in.close();
            con.disconnect();
            String result = content.toString();
            JSONObject res = new JSONObject(result);
            
            JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
            
            for (int i = 0; i < array.length(); i++) {
                list.add((JSONObject) array.get(i));
            }
            
            String stat = res.getString("status");
            System.out.println(stat);
            System.out.println(result);
        }
        temp=prefs.remove();
        if(temp==1)
        {
            newstopheadlines="https://newsapi.org/v2/top-headlines?";
            while(!q.isEmpty());
            q.add("country");
            q.add(country);
            q.add("category");
            q.add("health");
            q.add("apiKey");
            q.add(APP_ID);
            
            newstopheadlines+=ParameterStringBuilder.getParamsString(q);
            System.out.println(newstopheadlines);
            URL url = new URL(newstopheadlines);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            int count = 0;
            JSONObject response = null;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
                count++;
            }
            in.close();
            con.disconnect();
            String result = content.toString();
            JSONObject res = new JSONObject(result);
            
            JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
            
            for (int i = 0; i < array.length(); i++) {
                list.add((JSONObject) array.get(i));
            }
            
            String stat = res.getString("status");
            System.out.println(stat);
            System.out.println(result);
        }
        temp=prefs.remove();
        if(temp==1)
        {
            newstopheadlines="https://newsapi.org/v2/top-headlines?";
            while(!q.isEmpty());
            q.add("country");
            q.add(country);
            q.add("category");
            q.add("science");
            q.add("apiKey");
            q.add(APP_ID);
            
            newstopheadlines+=ParameterStringBuilder.getParamsString(q);
            System.out.println(newstopheadlines);
            URL url = new URL(newstopheadlines);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            int count = 0;
            JSONObject response = null;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
                count++;
            }
            in.close();
            con.disconnect();
            String result = content.toString();
            JSONObject res = new JSONObject(result);
            
            JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
            
            for (int i = 0; i < array.length(); i++) {
                list.add((JSONObject) array.get(i));
            }
            
            String stat = res.getString("status");
            System.out.println(stat);
            System.out.println(result);
        }
        temp=prefs.remove();
        if(temp==1)
        {
            newstopheadlines="https://newsapi.org/v2/top-headlines?";
            while(!q.isEmpty());
            q.add("country");
            q.add(country);
            q.add("category");
            q.add("sports");
            q.add("apiKey");
            q.add(APP_ID);
            
            newstopheadlines+=ParameterStringBuilder.getParamsString(q);
            System.out.println(newstopheadlines);
            URL url = new URL(newstopheadlines);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            int count = 0;
            JSONObject response = null;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
                count++;
            }
            in.close();
            con.disconnect();
            String result = content.toString();
            JSONObject res = new JSONObject(result);
            
            JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
            
            for (int i = 0; i < array.length(); i++) {
                list.add((JSONObject) array.get(i));
            }
            
            String stat = res.getString("status");
            System.out.println(stat);
            System.out.println(result);
        }
        temp=prefs.remove();
        if(temp==1)
        {
            newstopheadlines="https://newsapi.org/v2/top-headlines?";
            while(!q.isEmpty());
            q.add("country");
            q.add(country);
            q.add("category");
            q.add("technology");
            q.add("apiKey");
            q.add(APP_ID);
            
            newstopheadlines+=ParameterStringBuilder.getParamsString(q);
            System.out.println(newstopheadlines);
            URL url = new URL(newstopheadlines);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            int count = 0;
            JSONObject response = null;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
                count++;
            }
            in.close();
            con.disconnect();
            String result = content.toString();
            JSONObject res = new JSONObject(result);
            
            JSONArray array = (JSONArray) res.get("articles");
//        System.out.println(array);
            
            for (int i = 0; i < array.length(); i++) {
                list.add((JSONObject) array.get(i));
            }
            
            String stat = res.getString("status");
            System.out.println(stat);
            System.out.println(result);
            
        }
        
        Collections.sort(list, new MyJSONComparator());
        return NewsArray.convert(list);

        
        
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