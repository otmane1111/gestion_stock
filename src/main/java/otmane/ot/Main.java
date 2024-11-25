import java.util.Scanner;

public class Main {
    // Tableaux parallèles
    static int[] codesProduits = new int[100];
    static String[] nomsProduits = new String[100];
    static int[] quantites = new int[100];
    static double[] prix = new double[100];
    static int nbProduits = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne

            switch (choix) {
                case 1:
                    System.out.print("Code : ");
                    int code = scanner.nextInt();
                    scanner.nextLine(); // Consommer le saut de ligne
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Quantité : ");
                    int quantite = scanner.nextInt();
                    System.out.print("Prix : ");
                    double prixUnitaire = scanner.nextDouble();
                    ajouterProduit(code, nom, quantite, prixUnitaire);
                    break;

                case 2:
                    System.out.print("Code du produit à modifier : ");
                    int codeModif = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau nom : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Nouvelle quantité : ");
                    int nouvelleQuantite = scanner.nextInt();
                    System.out.print("Nouveau prix : ");
                    double nouveauPrix = scanner.nextDouble();
                    modifierProduit(codeModif, nouveauNom, nouvelleQuantite, nouveauPrix);
                    break;

                case 3:
                    System.out.print("Code du produit à supprimer : ");
                    int codeSuppr = scanner.nextInt();
                    supprimerProduit(codeSuppr);
                    break;

                case 4:
                    afficherProduits();
                    break;

                case 5:
                    System.out.print("Nom du produit à rechercher : ");
                    String nomRecherche = scanner.nextLine();
                    rechercherProduit(nomRecherche);
                    break;

                case 6:
                    System.out.println("Valeur totale du stock : " + calculerValeurStock());
                    break;

                case 0:
                    System.out.println("Au revoir ");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 0);

        scanner.close();
    }

    static void printMenu() {
        System.out.println("\nMenu :");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher les produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
    }

    static void ajouterProduit(int code, String nom, int quantite, double prixUnitaire) {
        if (nbProduits < 100) {
            codesProduits[nbProduits] = code;
            nomsProduits[nbProduits] = nom;
            quantites[nbProduits] = quantite;
            prix[nbProduits] = prixUnitaire;
            nbProduits++;
            System.out.println("Produit ajouté avec succès !");
        } else {
            System.out.println("Impossible d'ajouter, stock plein !");
        }
    }

    static void modifierProduit(int code, String nouveauNom, int nouvelleQuantite, double nouveauPrix) {
        for (int i = 0; i < nbProduits; i++) {
            if (codesProduits[i] == code) {
                nomsProduits[i] = nouveauNom;
                quantites[i] = nouvelleQuantite;
                prix[i] = nouveauPrix;
                System.out.println("Produit modifié avec succès !");
                return;
            }
        }
        System.out.println("Produit introuvable !");
    }

    static void supprimerProduit(int code) {
        for (int i = 0; i < nbProduits; i++) {
            if (codesProduits[i] == code) {
                for (int j = i; j < nbProduits - 1; j++) {
                    codesProduits[j] = codesProduits[j + 1];
                    nomsProduits[j] = nomsProduits[j + 1];
                    quantites[j] = quantites[j + 1];
                    prix[j] = prix[j + 1];
                }
                nbProduits--;
                System.out.println("Produit supprimé avec succès !");
                return;
            }
        }
        System.out.println("Produit introuvable !");
    }

    static void afficherProduits() {
        if (nbProduits == 0) {
            System.out.println("Aucun produit en stock !");
            return;
        }
        System.out.println("\nListe des produits :");
        for (int i = 0; i < nbProduits; i++) {
            System.out.println("Code : " + codesProduits[i] +
                    ", Nom : " + nomsProduits[i] +
                    ", Quantité : " + quantites[i] +
                    ", Prix : " + prix[i]);
        }
    }

    static void rechercherProduit(String nom) {
        for (int i = 0; i < nbProduits; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nom)) {
                System.out.println("Produit trouvé : Code : " + codesProduits[i] +
                        ", Nom : " + nomsProduits[i] +
                        ", Quantité : " + quantites[i] +
                        ", Prix : " + prix[i]);
                return;
            }
        }
        System.out.println("Produit introuvable !");
    }

    static double calculerValeurStock() {
        double valeurTotale = 0;
        for (int i = 0; i < nbProduits; i++) {
            valeurTotale += quantites[i] * prix[i];
        }
        return valeurTotale;
    }
}