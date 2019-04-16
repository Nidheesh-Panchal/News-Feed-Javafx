/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author nilu1
 */
public class ParameterStringBuilder {
    public static String getParamsString(Queue<String> q) 
      throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        
        while(q.size()!=0)
        {
            result.append(q.remove());
            result.append("=");
            result.append(q.remove());
            result.append("&");
        }
 
//        for(Map.Entry<String, String> entry : params.entrySet()) {
//          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//          result.append("=");
//          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//          result.append("&");
//        }
        
        String resultString = result.toString();
        if(resultString.length()>0)
        {
            resultString=resultString.substring(0,resultString.length()-1);
            System.out.println(resultString);
            return resultString;
        }
        else
        {
            return resultString;
        }
    }
}
