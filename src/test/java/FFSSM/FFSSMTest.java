/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author anaellejolivet
 */
public class FFSSMTest {
    
    public FFSSMTest() {
    }
    //club, moniteur, plongee, licence
    
    Club c;
    Moniteur m;
    Plongee plongee, plongee2;
    Licence l;
    Personne p;
    Site lieu;
    LocalDate date, d, d1, d3;
    Plongeur plongeur, plongeur2;
    
    
    @BeforeEach
	public void setUp() {
            date = LocalDate.of(1980, 2, 6);
            d = LocalDate.of(2020, 2, 6);
            d1 = LocalDate.of(2022, 2, 6);
            d3 = LocalDate.of(2022, 1, 1);
            lieu = new Site("Mer", "La mer");
            p = new Personne("5342", "Dupond", "Jean", "Castres", "0780909090", date);
            m = new Moniteur("5372", "Dupont", "Pierre", "Castres", "0790909090", date , 56770);
            c = new Club(m, "Le meilleur club", "0690909090");
            l = new Licence(p, "5321", d, c);
            plongee = new Plongee(lieu, m, d1, 12, 6);
            plongee2 = new Plongee(lieu, m, d1, 12, 6);
            plongeur = new Plongeur("5342", "Jacques", "Jean", "Castres", "0780909090", date);
            plongeur2 = new Plongeur("5342", "Jacques", "Jean", "Castres", "0780909090", date);

		
	}
      
    // -------- Test Club --------
        
    /**
     * Test of plongeesNonConformes method, of class Club.
     */
    @Test
    public void testPlongeesNonConformes() {
        plongeur.ajouterLicence("245", d3, c);
        plongee.ajouteParticipant(plongeur);
        
        c.organisePlongee(plongee);
        assertFalse(c.plongeesNonConformes().contains(plongee),
                        "La plongee est conformes");
        
        plongeur2.ajouterLicence("245", d, c);
        plongee2.ajouteParticipant(plongeur2);
        
        c.organisePlongee(plongee2);
        assertTrue(c.plongeesNonConformes().contains(plongee2),
                        "Les plongee non conformes ne sont pas detectées");
               
    }

    /**
     * Test of organisePlongee method, of class Club.
     */
    @Test
    public void testOrganisePlongee() {
        c.organisePlongee(plongee);

	assertTrue(c.lesPlongee.contains(plongee),
                        "La plongée n'a pas été ajoutée");
    }

    // -------- Test Moniteur --------
    
    /**
     * Test of toString method, of class Club.
     */
    @Test
    public void testEmployeurActuel() {
        m.nouvelleEmbauche(c, d);
        Optional<Club> o = Optional.empty();
        assertEquals(o = Optional.of(c), m.employeurActuel(),
                "Le dernier employeur n'est pas le bon");
        m.terminerEmbauche(d1);
        assertEquals(o = Optional.empty(), m.employeurActuel(),
                "Le dernier employeur n'est pas le bon");
    }
    
    @Test
    public void testNouvelleEmbauche() {
        m.nouvelleEmbauche(c, d);
        assertEquals(d, m.lesEmbauches.get(0).getDebut(),
                "La date du debut d'emploi n'est pas bonne");
        assertFalse(m.lesEmbauches.isEmpty(),
                "L'embauche n'a pas été ajoutée");
    }
    
    @Test
    public void testTerminerEmbauche() {
        m.nouvelleEmbauche(c, d);
        m.terminerEmbauche(d1);
        assertEquals(d1, m.lesEmbauches.get(0).getFin(),
                "L'embauche n'a pas été terminée");
    }
    
    // -------- Test Plongee --------
    
    @Test
    public void testAjouteParticipant() {
        plongee.ajouteParticipant(plongeur);
        assertTrue(plongee.lesParticipant.contains(plongeur),
                        "Le participant n'a pas été ajoutée");
    }
    
    @Test
    public void testEstConforme() {
        plongeur.ajouterLicence("245", d, c);
        plongee.ajouteParticipant(plongeur);
        
        plongeur2.ajouterLicence("245", d1, c);
        plongee.ajouteParticipant(plongeur2);
        assertFalse(plongee.estConforme(),
                "La plongee n'est pas conforme");
    }
    
    // -------- Test Licence --------
    
    @Test
    public void testEstValide() {
        assertFalse(l.estValide(d1),
                "La licence n'est pas valide");
    }
}
