package entitati.clase;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa ce stocheaza informatii despre un director si implicit a fisierelor din directorul respectiv.
 * Locatia tuturor directoarelor este pe Desktop.
 */
public class Director implements Serializable {
    private ListaFisiere listaFisiere = new ListaFisiere();
    private String denumire;
    private String locatie = "C:\\Users\\Alexandra\\Desktop\\";

    public Director(String denumire) {
        this.denumire = denumire;
        this.locatie += denumire;
    }

    public ListaFisiere getListaFisiere() {
        return listaFisiere;
    }

    public String getDenumire() {
        return denumire;
    }

    public void afiseazaDirector() {
        System.out.println("Denumire director: " + denumire);
        System.out.println("Locatie director: " + locatie);
        listaFisiere.afiseazaListaFisiere();
    }

    /**
     * Metoda ce verifica daca directoarele comparate sunt diferite in functie de nume
     * @param o director
     * @return true daca directoarele contin acelasi nume
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(denumire, director.denumire);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(denumire);
    }
}
