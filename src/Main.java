import entitati.*;
import entitati.exceptii.ExceptiiFisiere;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Meniu meniu = new Meniu();
        Scanner scanner = new Scanner(System.in);
        int selectieMeniuGeneral = 0;
        int selectieMeniuDirector = 0;
        String directorSpecificat;
        ManagerFisiereDirectoare managerFisiereDirectoare = new ManagerFisiereDirectoare();

        // citire fisier
        File fisierDeIntrare = new File("date.txt");
        try {
            fisierDeIntrare.createNewFile();
            managerFisiereDirectoare.restaurareFisier(fisierDeIntrare);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                                do {
                                    System.out.println("Introduceti numele fisierului, acesta nu trebuie sa existe in director");
                                    numeIntrodus = meniu.citesteString(scanner);
                                } while(directorGasit.getListaFisiere().cautaFisier(numeIntrodus) != null);
                                try {
                                    Fisier fisier = new Fisier(numeIntrodus, directorGasit.getDenumire());
                                    System.out.println(fisier);
                                    directorGasit.getListaFisiere().adaugaFisier(fisier);
                                } catch (ExceptiiFisiere e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                do {
                                    System.out.println("Introduceti numele fisierului pe care vreti sa-l stergeti.");
                                    numeIntrodus = meniu.citesteString(scanner);
                                } while(directorGasit.getListaFisiere().cautaFisier(numeIntrodus) == null);
                                directorGasit.getListaFisiere().stergeFisier(numeIntrodus);
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
                    System.out.println("Directorul s-a sters cu succes.");
                    break;
                case 6:
                    try {
                        managerFisiereDirectoare.genereazaRaport();
                        managerFisiereDirectoare.salvareFisier(fisierDeIntrare);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Raportul dumneavoastra a fost generat cu succes!");
                    break;
                case 7:
                    System.out.println(meniu.getMesajDeIesire());
                    try {
                        managerFisiereDirectoare.salvareFisier(fisierDeIntrare);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } while(selectieMeniuGeneral != 7 && selectieMeniuGeneral != 6);
        managerFisiereDirectoare.salveazaInformatiiFisiere();
    }
}