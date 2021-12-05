/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    public ArrayList<Embauche> lesEmbauches = new ArrayList<>();

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numeroDiplome) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.numeroDiplome = numeroDiplome;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est
     * terminée, ce moniteur n'a pas d'employeur.
     *
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
        Optional<Club> o = Optional.empty();
        if (lesEmbauches.size() > 0) {
            Embauche e = lesEmbauches.get(lesEmbauches.size() - 1);
            if (e.estTerminee()) {
                o = Optional.empty();
            }else o = Optional.of(e.getEmployeur());
        }
        return o;
    }

    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     *
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {
        Embauche e = new Embauche(debutNouvelle, this, employeur);
        lesEmbauches.add(e);
    }

    public void terminerEmbauche(LocalDate fin) {
        lesEmbauches.get(lesEmbauches.size()-1).terminer(fin);
    }

    public List<Embauche> emplois() {
        return lesEmbauches;
    }
    
}
