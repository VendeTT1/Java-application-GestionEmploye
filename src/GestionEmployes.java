import ma.enset.projet.Employe;

import static ma.enset.projet.Employe.comparerParSalaire;


public class GestionEmployes {

    private static final Employe empTab[] = new Employe[50];
    private static int nbEmpl = 0;

    //    methode afficher menu
    static void printMenu() {
        System.out.println("\n--- Menu de Gestion des Employes ---");
        System.out.println("1. Ajouter un employe ");
        System.out.println("2. Modifier un employe");
        System.out.println("3. Supprimer un employe");
        System.out.println("4. Afficher la liste des employes");
        System.out.println("5. Rechercher un employer");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employer selon l'ordre croissant/décroissant");
        System.out.println("8. Quitter");
        System.out.print("Choisissez une option : ");
    }

    //    methode ajouter employe
    static void ajouterEmploye(Employe employe) {
        if (nbEmpl >= empTab.length) { // verifie si le tableau est plein
            System.out.println("Erreur : La capacité maximale est atteinte.");
            return;
        }
        for (int i = 0; i < nbEmpl; i++) { // assure l,unicite de l'id de l'employe
            if (empTab[i].getId() == employe.getId()) {
                System.out.println("Erreur : Le code du de l employe doit être unique.");
                return;
            }
        }
        empTab[nbEmpl++] = employe;
        System.out.println("Employe ajouté avec succès !");
    }

    // methode pour supprimer un employe.
    static void supprimerEmploye(int id) {
        for (int i = 0; i < nbEmpl; i++) {
            if (empTab[i].getId() == id) {
                empTab[i] = empTab[nbEmpl - 1]; // Remplacement par le dernier Employer.
                empTab[--nbEmpl] = null; // Suppression logique.
                System.out.println("Employe supprimé avec succès !");
                return;
            }
        }
        System.out.println("Erreur : employe non trouvé.");
    }

    // methode pour modifier un employe existant.
    static void modifierEmploye(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire) {
        for (int i = 0; i < nbEmpl; i++) {
            if (empTab[i].getId() == id) {
                empTab[i].setNom(nouveauNom);
                empTab[i].setPoste(nouveauPoste);
                empTab[i].setSalaire(nouveauSalaire);
                System.out.println("Employe modifié avec succès !");
                return;
            }
        }
        System.out.println("Erreur : Employe non trouvé.");
    }

    //    methode pour afficher tous les Employes.
    static void afficherEmployes() {
        if (nbEmpl == 0) {
            System.out.println("Aucun Employe.");
            return;
        }
        for (int i = 0; i < nbEmpl; i++) {
            System.out.println(empTab[i]);
        }
    }

    // methode pour rechercher un employe par nom.
    static void rechercherEmploye(String critere) {
        for (int i = 0; i < nbEmpl; i++) {
            if (empTab[i].getNom().equalsIgnoreCase(critere)) { // ignoreCase au cas ou on ne connait pas le format du nom majiscule ou miniscule
                System.out.println(empTab[i]);
                return;
            }
        }
        System.out.println("Employe non trouvé.");
    }

    // methode pour calculer la valeur de la masse salarial
    static void calculerMasseSalariale() {
        double valeurTotale = 0;
        for (int i = 0; i < nbEmpl; i++) {
            valeurTotale += empTab[i].getSalaire();
        }
        System.out.println("Valeur totale de la masse salariale : " + valeurTotale + " MAD");
    }

    // methode de tri
    public static void trierEmployesParSalaire(boolean ordreCroissant) {
        for (int i = 0; i < empTab.length - 1; i++) {
            for (int j = 0; j < empTab.length - i - 1; j++) {
                if (empTab[j] == null || empTab[j + 1] == null) {
                    continue;
                }

                boolean condition;
                if (ordreCroissant) {
                    condition = comparerParSalaire(empTab[j], empTab[j + 1]) > 0; // Croissant : echanger si le salaire du premier est plus grand
                } else {
                    condition = comparerParSalaire(empTab[j], empTab[j + 1]) < 0; // Decroissant : echanger si le salaire du premier est plus petit
                }

                if (condition) { // effectuer les echanges au niveau du tableau au cas ou condition is true
                    Employe temp = empTab[j];
                    empTab[j] = empTab[j + 1];
                    empTab[j + 1] = temp;
                }
            }
        }

        // Affichage des employes tries
        for (Employe employe : empTab) {
            if (employe != null) {
                System.out.println(employe);
            }
        }
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            GestionEmployes.printMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne restante.

            switch (choix) {
                case 1:
                    System.out.print("Id : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nom : ");
                    String nom = scanner.next();
                    scanner.nextLine();
                    System.out.print("Poste: ");
                    String poste = scanner.nextLine();
                    System.out.print("Salaire : ");
                    double salaire = scanner.nextDouble();
                    GestionEmployes.ajouterEmploye(new Employe(id, nom, poste, salaire));
                    break;

                case 2:
                    System.out.print("Id de l'employer à modifier : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau nom : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Nouveau poste : ");
                    String nouveauPoste = scanner.nextLine();
                    System.out.print("Nouveau Salaire : ");
                    double nouveauSalaire = scanner.nextDouble();
                    GestionEmployes.modifierEmploye(id, nouveauNom, nouveauPoste, nouveauSalaire);
                    break;
                case 3:
                    System.out.print("Id de l'employer à supprimer : ");
                    id = scanner.nextInt();
                    GestionEmployes.supprimerEmploye(id);
                    break;
                case 4:
                    GestionEmployes.afficherEmployes();
                    break;
                case 5:
                    System.out.print("Nom de l'employer à rechercher : ");
                    nom = scanner.nextLine();
                    GestionEmployes.rechercherEmploye(nom);
                    break;
                case 6:
                    GestionEmployes.calculerMasseSalariale();
                    break;
                case 7:
                    boolean ordre;
                    System.out.println("Entre l'ordre souhaiter " +
                            "\t '>'pour l'ordre croissant et" +
                            " \t'<' pour l'ordre décroissant");
                    String ordres = scanner.nextLine();
                    ordre = ordres.equals(">");
                    GestionEmployes.trierEmployesParSalaire(ordre);
                    break;
                case 8:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");


            }
        }
    }
}