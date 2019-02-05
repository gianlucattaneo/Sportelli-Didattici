/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportellitocsv;

/**
 *
 * @author Gianluca
 */
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Parser {

    private List tabella;

    public Parser() {
        tabella = new ArrayList();
    }

    public List<NodeList> parseDocument(String filename)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element, TD;
        NodeList nodelist;
        NodeList TDlist;
        String href;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        // generazione della lista degli elementi "libro"
        boolean b = false;
        nodelist = root.getElementsByTagName("tr");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                TDlist = element.getElementsByTagName("td");
                if (TDlist != null && TDlist.getLength() > 0) {
                    for (int k = 0; k < TDlist.getLength(); k++){
                        TD = (Element) TDlist.item(k);
                        
                        String value = TD.getTextContent();
                        if (value != null){
                            if (value.equals("DISCIPLINA")){
                                tabella.add(element);
                                b = true;
                            }else if (TDlist.getLength()==4 && b == true ){
                                tabella.add(element);
                            }else{
                                b = false;  //quando un tr non contiene esattamente 4 td allora è finita la tabella
                            }
                        }
                        
                    }
                }
            }
        }
        return tabella;
    }
    
    public List<NodeList> parseDocumentV2(String filename)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element, TD;
        NodeList nodelist;
        NodeList TDlist;
        String href;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        // generazione della lista degli elementi "libro"
        boolean b = false;
        nodelist = root.getElementsByTagName("tr");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                String value = element.getFirstChild().getTextContent();
                
                        
                       
                     if (value != null){
                            if (value.equals("DISCIPLINA")){
                                tabella.add(element);
                                b = true;
                            }else if (element.getChildNodes().getLength()==4 && b == true ){
                                tabella.add(element);
                            }else{
                                b = false;  //quando un tr non contiene esattamente 4 td allora è finita la tabella
                            }
                     }
                        
                    
            }     
        }
        
        return tabella;
    }
    
    public String ArraytoCSVMIO(List<NodeList> lista){
        String s= "";
        List tmp = new ArrayList();
        List tmp1 = new ArrayList();
        tmp = getTextValueX4(lista.get(0),"td");
        
        s+=tmp.get(0)+";";
        s+=tmp.get(1)+";";
        s+=tmp.get(2)+";";
        s+=tmp.get(3)+"\r\n";
        
        for(int i = 1; i <lista.size()-1;i = i + 2){
            tmp = getTextValueX4(lista.get(i),"td");
            tmp1 = getTextValueX4(lista.get(i+1),"td");
            
            s+=tmp.get(0)+" "+ tmp1.get(0)+";";
            s+=tmp.get(1)+" "+ tmp1.get(1)+";";
            s+=tmp.get(2)+" "+ tmp1.get(2)+";";
            s+=tmp.get(3)+" "+ tmp1.get(3)+"\n";      
        }
        
        return s;
    }
    
    
    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > 0) {
            value = nodelist.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
    
    public List getTextValueX4(NodeList nodelist, String tag) {
        List lista = new ArrayList();
        
        //NodeList nodelist;
        //nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > 0) { // PRENDE I 4 TD PRESENTI
            lista.add(nodelist.item(0).getTextContent());
            lista.add(nodelist.item(1).getTextContent());
            lista.add(nodelist.item(2).getTextContent());
            lista.add(nodelist.item(3).getTextContent());
        }
        return lista;
    }
    
    // restituisce il valore intero dell’elemento figlio specificato
    private int getIntValue(Element element, String tag) {
        return Integer.parseInt(getTextValue(element, tag));
    }
    
    // restituisce il valore numerico dell’elemento figlio specificato
    private float getFloatValue(Element element, String tag) {
        return Float.parseFloat(getTextValue(element, tag));
    }
    
}