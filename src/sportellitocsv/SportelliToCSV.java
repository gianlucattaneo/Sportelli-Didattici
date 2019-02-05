/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportellitocsv;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Gianluca
 */
public class SportelliToCSV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<NodeList> tabella = null;
        Parser dom = new Parser();
        try {
            tabella = dom.parseDocument("C:/Users/Gianluca/Desktop/file.xml");
            String CSV = dom.ArraytoCSV(tabella);
            System.out.println(CSV);
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            System.out.println("Errore!");
        }
        // iterazione della lista e visualizzazione degli oggetti
//        System.out.println("Numero di HREF: " + tabella.size());
//        Iterator iterator = tabella.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().toString());
//        }

    }
    
}
