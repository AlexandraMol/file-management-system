package entitati;

import entitati.exceptii.ExceptiiFisiere;

import java.util.ArrayList;
import java.util.Objects;

public class ListaFisiere {
    public ArrayList<Fisier> listaFisiere;

    public ListaFisiere() {
        this.listaFisiere = new ArrayList<Fisier>();
    }

    public void adaugaFisier(Fisier fisier) throws ExceptiiFisiere {
//        if() numele exista in file nu e voie
        for(Fisier f : listaFisiere) {
            if(Objects.equals(f.getDenumire(), fisier.getDenumire())) {
                throw new ExceptiiFisiere("In director exista un fisier cu aceasta denumire");
            }
        }
        listaFisiere.add(fisier);
    }

    public void stergeFisier(String denumire) {
        // daca nu exista fisierul, eroare
        for(Fisier f : listaFisiere) {
            if(Objects.equals(f.getDenumire(), denumire)) {
                listaFisiere.remove(f);
                System.out.println("Fisierul a fost sters cu succes");
                return;
            }
        }
        System.out.println("Fisierul cu aceasta denumire nu a fost gasit");
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
