package entitati;

import entitati.exceptii.ExceptiiDenumiri;

import java.util.Objects;
import java.util.Scanner;

// meniul si mesajele sale
public final class Meniu {

    public String citesteString(Scanner input) {
        String inputIntrodus = "";
        boolean inputValid = false;

        while(!inputValid) {
           inputIntrodus = input.nextLine();
           try {
               if(Objects.equals(inputIntrodus, "")) {
                   throw new ExceptiiDenumiri("Numele fisierului nu trebuie sa fie gol. Introduceti un nume valid");
               } else {
                   inputValid = true;
               }
           } catch (ExceptiiDenumiri e) {
              System.out.println(e.getMessage());
           }
        }
        return inputIntrodus;
    }

    public String getMesajIntroductiv() {
        return "Bun venit in aplicatia de gestiune a fisierelor multimedia!";
    }

    public String getMeniuGeneral() {
        return "\nSelectati o instructiune din meniu de la 1 la 7: \n" +
                "1. Afisarea listei de directoare (locatii) existente \n" +
                "2. Cautarea unui fisier in directoarele existente \n" +
                "3. Accesarea un director si afisarea listei de fisiere existente \n" +
                "4. Adaugarea unui director nou \n" +
                "5. Stergerea unui director \n" +
                "6. Afiseaza statistici \n" +
                "7. Iesire din aplicatie ";
    }

    public String getMeniuFisiereDinDirector() {
        return "\n Selectati o instructiune din meniu: \n" +
                "1. Cautarea unui fisier din directorul curent \n" +
                "2. Adaugarea unui fisier in acest director \n" +
                "3. Stergerea unui fisier din folder \n" +
                "4. Revenirea la meniul principal \n";

    }

    public String getMesajDeIesire() {
        return "Situatia directoarelor si a fisierelor a fost salvata in fisierul date.txt";
    }

    public String getOptiuneInexistenta() {
        return "Eroare! Aceasta instructiune nu exista.";
    }

    public String getMesajGenerareRaport() {
        return "Raportul dumneavoastra a fost generat cu succes";
    }
}