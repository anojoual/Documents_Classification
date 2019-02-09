/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

/**
 *
 * @author JOUAL.A
 */
public class frequence {
    
    private double fre = 0 ;
    private String nom ;

    public frequence(String nom) {
        this.nom = nom;
    }
    
        public frequence() {
      
    }


    public double getFre() {
        return fre;
    }

    public void setFre(double fre) {
        this.fre = fre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}
