package com.csii.xmljson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

public class SaxXml {

    @Test
    public void test(){
        List<Book> bList = new ArrayList<Book>();
        Book b = new Book();
        b.setName("�����֮��");
        b.setAuthor("������");
        b.setId("1");
        b.setLanguage("English");
        b.setPrice("86");
        b.setYear("2014");
        bList.add(b);
        Long start = System.currentTimeMillis();
        createXml(bList);
        System.out.println("����ʱ�䣺"+ (System.currentTimeMillis() - start));
    }

    // ����xml
    public static void createXml(List<Book> bList){     
        // 1������һ��SAXTransformerFactory��Ķ���
        SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

        try {
            // 2��ͨ��SAXTransformerFactory����һ��TransformerHandler�Ķ���
            TransformerHandler handler = tff.newTransformerHandler();
            // 3��ͨ��handler����һ��Transformer����
            Transformer tr = handler.getTransformer();
            // 4��ͨ��Transformer��������ɵ�xml�ļ���������
            // ���ñ��뷽ʽ
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // �����Ƿ���
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            // 5������һ��Result����
            File f = new File("newbooks.xml");
            // �ж��ļ��Ƿ����
            if(!f.exists()){
                f.createNewFile();
            }
            Result result = new StreamResult(new FileOutputStream(f));
            // 6��ʹRESULT��handler����
            handler.setResult(result);

            // ��document
            handler.startDocument();
            AttributesImpl attr = new AttributesImpl();         
            handler.startElement("", "", "bookstore", attr);
            attr.clear();

            for (Book book : bList) {
                attr.clear();
                attr.addAttribute("", "", "id", "", book.getId());
                handler.startElement("", "", "book", attr);

                // ����name
                attr.clear();
                handler.startElement("", "", "name", attr);
                handler.characters(book.getName().toCharArray(), 0, book.getName().length());
                handler.endElement("", "", "name");

                // ����year
                attr.clear();
                handler.startElement("", "", "year", attr);
                handler.characters(book.getYear().toCharArray(), 0, book.getYear().length());
                handler.endElement("", "", "year");

                // ����author
                if(book.getAuthor() != null && !"".equals(book.getAuthor().trim())){
                    attr.clear();
                    handler.startElement("", "", "author", attr);
                    handler.characters(book.getAuthor().toCharArray(), 0, book.getAuthor().length());
                    handler.endElement("", "", "author");
                }

                // ����price
                if(book.getPrice() != null && !"".equals(book.getPrice().trim())){
                    attr.clear();
                    handler.startElement("", "", "price", attr);
                    handler.characters(book.getPrice().toCharArray(), 0, book.getPrice().length());
                    handler.endElement("", "", "price");
                }

                // ����language
                if(book.getLanguage() != null && !"".equals(book.getLanguage().trim())){
                    attr.clear();
                    handler.startElement("", "", "language", attr);
                    handler.characters(book.getLanguage().toCharArray(), 0, book.getLanguage().length());
                    handler.endElement("", "", "language");
                }

                handler.endElement("", "", "book");
            }

            handler.endElement("", "", "bookstore");
            // �ر�document
            handler.endDocument();
            System.out.println("����newbooks.xml�ɹ�");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("����newbooks.xmlʧ��");
        }
    }
}
