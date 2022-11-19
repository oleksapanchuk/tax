package com.panchuk.tax.dao.xml.util.sax_parser;

import com.panchuk.tax.model.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SAXParser {

    private static final String FILE_PATH = "xml/user.xml";

    public List<User> parse() {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParserHandler handler = new SAXParserHandler();
        javax.xml.parsers.SAXParser parser;

        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            System.out.println("Open sax parser error " + e);
            return null;
        }

        File file = new File(FILE_PATH);
        try {
            parser.parse(file, handler);
        } catch (SAXException e) {
            System.out.println("Sax parsing error " + e);
            return null;
        } catch (IOException e) {
            System.out.println("IO parsing error " + e);
            return null;
        }


        return handler.getUserList();
    }


    public static void main(String[] args) {
        SAXParser saxParser = new SAXParser();
        System.out.println(saxParser.parse());
    }
}
