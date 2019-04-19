/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author nilu1
 */
public class NewsArray
{
    String title;
    String image;
    String desc;
    String hyperl; //vandita all the time
    
    @Override
    public String toString()
    {
        String str=new String();
        str+="Title : "+this.title+"\n";
        str+="Description : "+this.desc+"\n";
        return str;
    }
    
    public static ArrayList<NewsArray> convert(ArrayList<JSONObject> arr)
    {
        ArrayList<NewsArray> response=new ArrayList<NewsArray>();
        for(int i=0;i<arr.size();i++)
        {
            NewsArray news=new NewsArray();
//            Object aObj = arr.get(i).get("description");
//            if (aObj instanceof String) {
//                System.out.println(aObj);
//            }
            //System.out.println(js);
            news.desc=arr.get(i).get("description").toString();
            news.title=arr.get(i).getString("title");
            news.image=arr.get(i).get("urlToImage").toString();
            news.hyperl=arr.get(i).getString("url");
            response.add((NewsArray) news);
        }
        return response;
    }
}
