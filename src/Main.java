import entitati.*;
import entitati.exceptii.ExceptiiFisiere;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ExceptiiFisiere {
        System.out.println("Bun venit in aplicatie!");

        // colectie de directoare default,  -> Map de String si director; dar de verificat cu fiserele
//        Scanner input = new Scanner(System.in);
//
//        int i = input.nextInt();

        Fisier f = new Fisier("test1.wav", TipFisier.AUDIO);

        ListaFisiere listaFisiere = new ListaFisiere();
        listaFisiere.adaugaFisier(f);
        listaFisiere.afiseazaListaFisiere();
        Fisier f1 = new Fisier("test1.jpg", TipFisier.AUDIO);
        listaFisiere.adaugaFisier(f1);
        listaFisiere.afiseazaListaFisiere();

        Director director = new Director();
        director.setDenumire("dosar");
        Director director3 = new Director();
        director3.setDenumire("dosar2");
        director.setListaFisiere(listaFisiere);
        director.afiseazaDirector();
        System.out.println("-------------------");
        ListaDirectoare listaDirectoare = new ListaDirectoare();
        listaDirectoare.adaugaDirector(director);
        listaDirectoare.adaugaDirector(director3);
        listaDirectoare.afiseazaDirectoare();


       // Director director2 = listaDirectoare.cautaDirector("");

        // director2.afiseazaDirector();
//        listaFisiere.stergeFisier("test1.txt");
//        listaFisiere.stergeFisier("test1.jpg");
//        listaFisiere.afiseazaListaFisiere();

    }
}