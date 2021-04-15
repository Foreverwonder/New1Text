package cn.edu.lingnan.util;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.HashMap;

public class XmlParser {
    public static HashMap<String,String> parse(String xmlPath){
        HashMap<String,String> hm =new HashMap<String,String>();
        try{
            //实例化一个SAXParserFactory对象：
            SAXParserFactory factory=SAXParserFactory.newInstance();
            //通过factory获得一个SAXParser对象，即SAX解析器：
            SAXParser saxParser=factory.newSAXParser();
            //saxParse对象调用parse方法解析XML文件：
            File f =new File(xmlPath);
            XmlHandler xh =new XmlHandler();
            saxParser.parse(f,xh);
            hm=xh.getHashMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }


//    public static void main(String[] args) {
//        String xmlPath="src//database.conf.xml";
//        HashMap<String,String> hm=XmlParser.parse(xmlPath);
//        System.out.println(hm);
//    }
}
