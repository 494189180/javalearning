package com.csii.xmljson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.XML;

import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

public class JsonLibUtils {
	public static void main(String[] args) {  
		String xml=getXmlString();
        String xml2 = "<class id="  
                + "'1'"  
                + "><student><name>aaaaaa</name><age>21</age></student><student><name>bbbbbb</name><age>22</age></student></class>";  
  
        /* ��һ�ַ�����ʹ��JSON-JAVA�ṩ�ķ��� */  
        //��xmlתΪjson  
        JSONObject xmlJSONObj = XML.toJSONObject(xml);  
        //��������  
        String jsonPrettyPrintString = xmlJSONObj.toString(4);  
        //�����ʽ�����json  
        System.out.println(jsonPrettyPrintString);  
  
        /* �ڶ��ַ�����ʹ��json-lib�ṩ�ķ��� */  
        //���� XMLSerializer����  
        
        XMLSerializer xmlSerializer = new XMLSerializer();  
        //��xmlתΪjson��ע�������Ԫ�ص����ԣ�����json���keyǰ��һ��@��ʶ��  
        String result = xmlSerializer.read(xml).toString();  
        //���json����  
        System.out.println(result);  
  
    }  
	public static String getXmlString() {
		String xmlString;
		byte[] strBuffer = null;
		int flen = 0;
		File xmlfile = new File("xmlTest.xml");
		try {
		InputStream in = new FileInputStream(xmlfile);
		flen = (int)xmlfile.length();
		strBuffer = new byte[flen];
		in.read(strBuffer, 0, flen);
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		xmlString = new String(strBuffer); //����Stringʱ������byte[]���ͣ�

		return xmlString;
		}
	
}
