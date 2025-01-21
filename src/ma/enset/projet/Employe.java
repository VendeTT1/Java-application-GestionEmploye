package ma.enset.projet;

import java.util.ArrayList;

public class Employe {
    private int id;
    private String nom;
    private String poste;
    private double salaire;

    //    Constructeur par défaut
    public Employe() {
    }

    //   Constructeur avec tous les attributs
    public Employe(int id, String nom, String poste, double salaire) {
        this.id = id;
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
    }

//    toString methode

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", poste='" + poste + '\'' +
                ", salaire=" + salaire +
                '}';
    }


//    Getters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getposte() {
        return poste;
    }

    public double getSalaire() {
        return salaire;
    }

//    Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }


    //    Methode static
    public static double comparerParSalaire(Employe employe1, Employe employe2) {
        return employe1.getSalaire() - employe2.getSalaire();
    }
}
