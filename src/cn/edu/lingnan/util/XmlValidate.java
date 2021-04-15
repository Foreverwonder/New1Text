package cn.edu.lingnan.util;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidate {

    //用XSD文件验证XML文件
    public static boolean validate(String xmlPath,String xsdPath){
        boolean flag=false;

        try {
            //创建模式工厂SchemaFactory
            File f=new File(xsdPath);
            SchemaFactory sf = SchemaFactory.newInstance
                    ("http://www.w3.org/2001/XMLSchema");
            //通过XSD文件创建模式Schema
            Schema s = sf.newSchema(f);
            //由模式创建验证器Validate
            Validator v=s.newValidator();
            //使用验证器验证XML文件
            Source so =new StreamSource(xmlPath);
            v.validate(so);
            flag =true;
        } catch (SAXException e)  {
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Xml文件验证失败...");
        }
        return flag;
    }


//    public static void main(String[] args) {
//        String xmlPath ="src//database.conf.xml";
//        String xsdPath ="src\\database.conf.xsd";
//        System.out.println(XmlValidate.validate(xmlPath,xsdPath));
//    }



}










