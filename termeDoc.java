/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.util.LinkedList;

/**
 *
 * @author JOUAL.A
 */
public class termeDoc {
    
    
    private LinkedList<frequence> terme ;
    private LinkedList doc ;

    public termeDoc() {
    }

  

    public LinkedList<frequence> getTerme() {
        return terme;
    }

    public void setTerme(LinkedList<frequence> terme) {
        this.terme = terme;
    }



    public LinkedList getDoc() {
        return doc;
    }

    public void setDoc(LinkedList doc) {
        this.doc = doc;
    }
    
}
