package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;

public class Plongeur extends Personne{
    private int niveau;
    private ArrayList<Licence> lesLicences = new ArrayList<>();

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
    }
    
    public void ajouterLicence(String numero, LocalDate delivrance, Club c){
        Licence l = new Licence(this, numero, delivrance, c);
        lesLicences.add(l);
    }
    
    public Licence derniereLicence(){
        return lesLicences.get(lesLicences.size()-1);
    }
	
}
