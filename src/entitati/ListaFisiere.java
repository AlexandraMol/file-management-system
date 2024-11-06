package entitati;

import entitati.exceptii.ExceptiiFisiere;

import java.util.ArrayList;
import java.util.Objects;

public class ListaFisiere {
    public ArrayList<Fisier> listaFisiere;

    public ListaFisiere() {
        this.listaFisiere = new ArrayList<Fisier>();
    }

    public Fisier cautaFisier(String denumire) throws ExceptiiFisiere{
        for(Fisier f : listaFisiere) {
            if(Objects.equals(f.getDenumire(), denumire)) {
                return f;
            }
        }
        return null;
    }

    public void adaugaFisier(Fisier fisier){
//        if() numele exista in file nu e voie
        if(this.cautaFisier(fisier.getDenumire()) == null) {
            listaFisiere.add(fisier);
        }
    }

    public void stergeFisier(String denumire) {
        // daca nu exista fisierul, eroare
        Fisier fisierDeSters = cautaFisier(denumire);
        if(fisierDeSters != null) {
            System.out.println("Fisierul a fost sters cu succes");
            listaFisiere.remove(fisierDeSters);
        } else {
            System.out.println("Fisierul cu aceasta denumire nu a fost gasit");
        }
    }

    public void afiseazaListaFisiere() {
       if(this.listaFisiere.isEmpty()) {
           System.out.println("Directorul nu contine niciun fisier");
       } else {
           System.out.println("Lista de fisiere:");
           for(Fisier fisier:listaFisiere) {
               System.out.println(fisier);
           }
       }
    }
}
