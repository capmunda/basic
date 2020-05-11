package com.cap.camunda.msone.demo.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
public static void main(String[] args) {
	 StringBuilder abc =new StringBuilder("APPID=123659 		     On Friday, May 8, 2020, 05:19:54 AM GMT+5:30, <sai9261@gmail.com> wrote:  		 		 Please send your application id in below format 		APPID=");
String a = abc.toString();
//Pattern pattern = Pattern.compile("\\d{5,}");
Pattern pattern = Pattern.compile("(?<=APPID=)\\d+");
Matcher matcher = pattern.matcher(a);

if (matcher.find()) {
    System.out.println(matcher.group(0));
}

}
}
