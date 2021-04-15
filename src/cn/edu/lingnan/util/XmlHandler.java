package cn.edu.lingnan.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


//import javax.management.Attribute;
//import javax.xml.stream.events.Attribute;
import java.util.HashMap;
import java.util.Locale;

public class XmlHandler extends DefaultHandler {
    StringBuffer sb=new StringBuffer();
    HashMap<String,String> hm=new HashMap<String,String>();

    public void startElement(String uri, String localName,
                             String qName, Attributes attribute)throws SAXException
    {
        sb.delete(0,sb.length());
    }
    public void endElement(String uri, String localName,
                             String qName)throws SAXException
    {
        hm.put(qName.toLowerCase(),sb.toString().trim());
    }

    public void characters(char ch[],int start,int length)
            throws SAXException
    {
        sb.append(ch,start,length);
    }
    public HashMap<String,String>getHashMap(){
        return hm;
    }





}
