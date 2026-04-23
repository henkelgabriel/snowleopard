package fetcher;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import logging.AppLogger; 
import model.Lead;     

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URI;  
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LeadsFetcher {

    private static final String LEADS_URL = "URL_KOMMER_HÄR";

    public List<Lead> fetchLeads() {
        List<Lead> leads = new ArrayList<>();

        try {
            AppLogger.info("Hämtar leads från: " + LEADS_URL);

            URL url = URI.create(LEADS_URL).toURL();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("lead");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String name = getTagValue("name", element);
                String phone = getTagValue("phone", element);
                String email = getTagValue("email", element);
                String company = getTagValue("company", element);

                Lead lead = new Lead(name, phone, email, company);
                leads.add(lead);
            }

            AppLogger.info(leads.size() + " leads hämtade.");

        } catch (Exception e) {
            AppLogger.error("Fel vid hämtning av leads: " + e.getMessage());
        }

        return leads;
    }

    private String getTagValue(String tag, Element element) {
        NodeList list = element.getElementsByTagName(tag);
        if (list.getLength() > 0) {
            return list.item(0).getTextContent().trim();
        }
        return "";
    }
}