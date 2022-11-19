package com.panchuk.tax.dao.xml.util;

import com.panchuk.tax.constant.XMLConstant;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class XMLWriter {

    public static void buildXML(List<User> userList, String path) {

        Document doc = docBuilder();

        // root elements
        Element rootElement = doc.createElement("root");
        doc.appendChild(rootElement);


        for (User u : userList) {

            Element user = doc.createElement(XMLConstant.TAG_USER);

            Element userId = doc.createElement(XMLConstant.TAG_ID_USER);
            userId.setTextContent(String.valueOf(u.getId()));
            user.appendChild(userId);

            Element firstName = doc.createElement(XMLConstant.TAG_FIRST_NAME);
            firstName.setTextContent(u.getFirstName());
            user.appendChild(firstName);

            Element lastName = doc.createElement(XMLConstant.TAG_LAST_NAME);
            lastName.setTextContent(u.getLastName());
            user.appendChild(lastName);

            Element email = doc.createElement(XMLConstant.TAG_EMAIL);
            email.setTextContent(u.getEmail());
            user.appendChild(email);

            user.appendChild(createElementTaxes(doc, u));

            rootElement.appendChild(user);

        }


        try (FileOutputStream file = new FileOutputStream(path)) {

            // print XML to system console
            writeXml(doc, file);

        } catch (IOException | TransformerException e) {
            throw new RuntimeException(e);
        }

    }

    private static Document docBuilder() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error: ", e);
        }
    }

    private static Element createElementTaxes(Document doc, User u) {
        Element taxes = doc.createElement(XMLConstant.TAG_TAXES);

        for (TaxType t : u.getTax()) {

            Element tax = doc.createElement(XMLConstant.TAG_TAX);
            tax.setAttribute("idTax", String.valueOf(t.getType()));
            tax.setTextContent(String.valueOf(t.getValue()));
            taxes.appendChild(tax);

        }

        return taxes;
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
