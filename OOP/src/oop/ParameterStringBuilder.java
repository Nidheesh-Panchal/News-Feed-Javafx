/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 *
 * @author nilu1
 */
public class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params) 
      throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
 
        for(Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
          result.append("&");
        }
        
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
