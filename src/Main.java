import entitati.*;
import entitati.exceptii.ExceptiiFisiere;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ExceptiiFisiere {
        Meniu meniu = new Meniu();
        Scanner scanner = new Scanner(System.in);
        int selectieMeniuGeneral = 0;
        int selectieMeniuDirector = 0;
        String directorSpecificat;
        ManagerFisiereDirectoare managerFisiereDirectoare = new ManagerFisiereDirectoare();

        Fisier f = new Fisier("test1.wav", "dosar");
        // Fisier f2 = new Fisier("test1.wav", TipFisier.AUDIO);
        ListaFisiere listaFisiere = new ListaFisiere();
        listaFisiere.adaugaFisier(f);


        Fisier f1 = new Fisier("test1.jpg", "");
        listaFisiere.adaugaFisier(f1);


        Director director = new Director("dosar");
        director.setDenumire("dosar");
        Director director3 = new Director("dosar2");
        director3.setDenumire("dosar2");
        director.setListaFisiere(listaFisiere);

        System.out.println("-------------------");
        ListaDirectoare listaDirectoare = new ListaDirectoare();
        listaDirectoare.adaugaDirector(director);
        listaDirectoare.adaugaDirector(director3);

        managerFisiereDirectoare.listaDirectoare = listaDirectoare;

        managerFisiereDirectoare.listaDirectoare.cautaDirector("dosar").afiseazaDirector();
        System.out.println(meniu.getMesajIntroductiv());

        do {
            System.out.println(meniu.getMeniuGeneral());
            boolean inputValid = false;

            while (!inputValid) {
                if (scanner.hasNextInt()) {
                    selectieMeniuGeneral = scanner.nextInt();
                    inputValid = true;
                } else {
                    System.out.println(meniu.getOptiuneInexistenta());
                    System.out.println("Va rog alegeti un numar de la 1 la 7:");
                    scanner.next();
                }
            }
            scanner.nextLine();

            if(selectieMeniuGeneral < 1 || selectieMeniuGeneral > 7) {
                System.out.println(meniu.getOptiuneInexistenta());

            }

            switch (selectieMeniuGeneral) {
                case 1:
                    managerFisiereDirectoare.listaDirectoare.afiseazaDirectoare();
                    break;
                case 2:
                    System.out.println("Scrieti numele fisierului pe care vreti sa-l cautati");
                    directorSpecificat = scanner.nextLine();
                    String locatieFisierCautat = managerFisiereDirectoare.cautaLocatieFisier(directorSpecificat);
                    if(locatieFisierCautat == null) {
                        System.out.println("Fisierul pe care il cautati nu exista");
                    } else {
                        System.out.println("Fisierul se afla la locatia: " + locatieFisierCautat);
                    }
                    break;
                case 3:
                    System.out.println("Scrieti numele directorului cautat");
                    directorSpecificat = scanner.nextLine();
                    Director directorGasit = managerFisiereDirectoare.listaDirectoare.cautaDirector(directorSpecificat);

                    if(directorGasit != null) {
                        directorGasit.afiseazaDirector();
                    } else {
                        System.out.println("Directorul cautat nu exista!");
                        break;
                    }
                    /// operatiile ptr files
                    do {
                        System.out.println(meniu.getMeniuFisiereDinDirector());
                        boolean inputValidDirector = false;

                        while (!inputValidDirector) {
                            System.out.println("Va rog alegeti un numar de la 1 la 5:");
                            if (scanner.hasNextInt()) {
                                selectieMeniuDirector = scanner.nextInt();
                                inputValidDirector = true;
                            } else {
                                System.out.println(meniu.getOptiuneInexistenta());
                                scanner.next();
                            }
                        }

                        scanner.nextLine();

                        if(selectieMeniuDirector < 1 || selectieMeniuDirector > 7) {
                            System.out.println(meniu.getOptiuneInexistenta());

                        }
                        String numeIntrodus = "";
                        switch (selectieMeniuDirector) {

                            case 1:
                                numeIntrodus ="";
                                System.out.println("Introduceti numele fisierului pe care doriti sa-l cautati.");
                                numeIntrodus = meniu.citesteString(scanner);
                                Fisier fisierCautat = directorGasit.getListaFisiere().cautaFisier(numeIntrodus);
                                if( fisierCautat== null) {
                                    System.out.println("Fisierul pe care il cautati nu exista.");
                                } else {
                                    System.out.println(fisierCautat);
                                }
                                break;
                            case 2:
                                numeIntrodus = "";
                                do {
                                    System.out.println("Introduceti numele fisierului, acesta nu trebuie sa existe in director");
                                    numeIntrodus = meniu.citesteString(scanner);
                                } while(directorGasit.getListaFisiere().cautaFisier(numeIntrodus) != null);
                                // de refactorizat exceptia cand nu e o extensie ok
                                try {
                                    Fisier fisier = new Fisier(numeIntrodus, directorGasit.getDenumire());
                                    System.out.println(fisier);
                                    directorGasit.getListaFisiere().adaugaFisier(fisier);
                                } catch (ExceptiiFisiere e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                numeIntrodus = "";
                                do {
                                    System.out.println("Introduceti numele fisierului pe care vreti sa-l stergeti.");
                                    numeIntrodus = meniu.citesteString(scanner);
                                } while(directorGasit.getListaFisiere().cautaFisier(numeIntrodus) == null);
                                directorGasit.getListaFisiere().stergeFisier(numeIntrodus);
                                break;
                            case 5:
                                // salvarea in fisier
                                System.out.println(meniu.getMesajDeIesire());
                                break;
                        }
                    } while(selectieMeniuDirector != 4);
                    break;
                case 4:
                    do {
                        System.out.println("Scrieti numele directorului pe care vreti sa-l adaugati.");
                        directorSpecificat = scanner.nextLine();
                    } while(managerFisiereDirectoare.listaDirectoare.cautaDirector(directorSpecificat) != null);
                    managerFisiereDirectoare.listaDirectoare.adaugaDirector(new Director(directorSpecificat));
                    break;
                case 5:
                    do {
                        System.out.println("Scrieti numele directorului pe care vreti sa-l stergeti.");
                        directorSpecificat = scanner.nextLine();
                    } while(managerFisiereDirectoare.listaDirectoare.cautaDirector(directorSpecificat) == null);
                    managerFisiereDirectoare.listaDirectoare.stergereDirector(directorSpecificat);
                    System.out.println("Directorul s-a realizat cu succes.");
                    break;
                case 6:
                    // de gandit statistici
                    break;
                case 7:
                    // salvare in fisier
                    System.out.println(meniu.getMesajDeIesire());
                    break;
            }
        } while(selectieMeniuGeneral != 7);
        // citire fisier
        File file = new File("date.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            managerFisiereDirectoare.restaurareFisier(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}