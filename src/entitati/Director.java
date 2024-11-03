package entitati;

import java.io.Serializable;
import java.util.Objects;

public class Director implements Serializable {
    private ListaFisiere listaFisiere = new ListaFisiere();
    private String denumire;
    private String locatie = "C:\\Users\\Alexandra\\Desktop\\";

    public ListaFisiere getListaFisiere() {
        return listaFisiere;
    }

    public void setListaFisiere(ListaFisiere listaFisiere) {
        this.listaFisiere = listaFisiere;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public void afiseazaDirector() {
        System.out.println("Denumire director: " + denumire);
        System.out.println("Locatie director: " + locatie);
        listaFisiere.afiseazaListaFisiere();
    }

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
