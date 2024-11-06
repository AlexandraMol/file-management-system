package entitati;

import java.util.Objects;
import java.util.Scanner;

// meniul si mesajele sale
public final class Meniu {

    public String citesteString(Scanner input) {
        String inputIntrodus = "";
        boolean inputValid = false;

        while(!inputValid) {
           inputIntrodus = input.nextLine();
            if(Objects.equals(inputIntrodus, "")) {
                System.out.println("Numele fisierului nu trebuie sa fie gol");
                System.out.println("Introduceti numele fisierlui");
            } else {
                inputValid = true;
            }
        }

        return inputIntrodus;
    }

    public String getMesajIntroductiv() {
        return "Bun venit in aplicatia de gestiune a fisierelor multimedia!";
    }
    // 2 as putea modifica cu cautarea unui fisier global;
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
                "4. Revenirea la meniul principal \n" +
                "5. Iesire din aplicatie \n";
    }

    public String getMesajDeIesire() {
        return "Situatia directoarelor si a fisierelor a fost salvata in fisierul date.txt";
    }

    public String getOptiuneInexistenta() {
        return "Eroare! Aceasta instructiune nu exista.";
    }
}