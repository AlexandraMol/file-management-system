import entitati.*;
import entitati.exceptii.ExceptiiFisiere;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ExceptiiFisiere {
        System.out.println("Bun venit in aplicatia de gestiune a  fisierelor multimedia!");

        ManagerFisiereDirectoare managerFisiereDirectoare = new ManagerFisiereDirectoare();
        File file = new File("date.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        managerFisiereDirectoare.listaDirectoare.afiseazaDirectoare();

        try {
            managerFisiereDirectoare.restaurareFisier(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // colectie de directoare default,  -> Map de String si director; dar de verificat cu fiserele
//        Scanner input = new Scanner(System.in);
//
//        int i = input.nextInt();

//        Fisier f = new Fisier("test1.wav", TipFisier.AUDIO);
//        // Fisier f2 = new Fisier("test1.wav", TipFisier.AUDIO);
//        ListaFisiere listaFisiere = new ListaFisiere();
//        listaFisiere.adaugaFisier(f);
//        listaFisiere.adaugaFisier(f2);
//        listaFisiere.afiseazaListaFisiere();
//        Fisier f1 = new Fisier("test1.jpg", TipFisier.AUDIO);
//        listaFisiere.adaugaFisier(f1);
//        listaFisiere.afiseazaListaFisiere();
//
//        Director director = new Director();
//        director.setDenumire("dosar");
//        Director director3 = new Director();
//        director3.setDenumire("dosar2");
//        director.setListaFisiere(listaFisiere);
//        director.afiseazaDirector();
//        System.out.println("-------------------");
//        ListaDirectoare listaDirectoare = new ListaDirectoare();
//        listaDirectoare.adaugaDirector(director);
//        listaDirectoare.adaugaDirector(director3);
//        listaDirectoare.afiseazaDirectoare();
//
//        listaDirectoare.stergereDirector("dosar2");
//        listaDirectoare.afiseazaDirectoare();

       // Director director2 = listaDirectoare.cautaDirector("");

        // director2.afiseazaDirector();
//        listaFisiere.stergeFisier("test1.txt");
//        listaFisiere.stergeFisier("test1.jpg");
//        listaFisiere.afiseazaListaFisiere();

    }
}