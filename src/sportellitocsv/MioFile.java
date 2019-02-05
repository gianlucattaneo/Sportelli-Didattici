/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportellitocsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gianluca
 */
public class MioFile {

    FileReader f;
    Writer writer;

    public MioFile() {

    }

    public String Leggi(String percorsoFile) throws IOException {

        try {
            f = new FileReader(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MioFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader b;
        b = new BufferedReader(f);
        String s = "";
        while (true) {
            s += b.readLine();
            if (b.readLine() == null) {
                f.close();
                break;
            }
        }
        return s;
    }

    public void Sovrascrivi(String percorso, String messaggio) throws IOException {
        File file = new File(percorso);
        writer = new FileWriter(file.getPath());
        writer.write(messaggio);
        writer.close();
    }

}
