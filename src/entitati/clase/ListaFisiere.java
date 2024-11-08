package entitati.clase;

import entitati.exceptii.ExceptiiFisiere;

import java.util.ArrayList;
import java.util.Objects;

public class ListaFisiere {
    public ArrayList<Fisier> listaFisiere;

    public ListaFisiere() {
        this.listaFisiere = new ArrayList<Fisier>();
    }

    /**
     * Verifica existenta unui fisier pe baza numelui
     * @param denumire fisierului de cautat
     * @return fisierul gasit si daca acesta nu exista, null
     */
    public Fisier cautaFisier(String denumire) {
        for(Fisier f : listaFisiere) {
            if(Objects.equals(f.getDenumire(), denumire)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Adauga un fisier in lista de fisiere daca acesta nu exista
     * @param fisier
     */
    public void adaugaFisier(Fisier fisier){
        if(this.cautaFisier(fisier.getDenumire()) == null) {
            listaFisiere.add(fisier);
        }
    }

    /**
     * Sterge un fisier din lista de fisiere daca acesta exista
     * @param denumire
     */
    public void stergeFisier(String denumire) {
        Fisier fisierDeSters = cautaFisier(denumire);
        if(fisierDeSters != null) {
            System.out.println("Fisierul a fost sters cu succes");
            listaFisiere.remove(fisierDeSters);
        } else {
            System.out.println("Fisierul cu aceasta denumire nu a fost gasit");
        }
    }

    /**
     * Afiseaza numele fisierelor create in directorul parinte. Daca nu exista fisiere
     * se va afisa un mesaj sugestiv
     */
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
