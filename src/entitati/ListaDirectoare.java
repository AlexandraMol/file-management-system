package entitati;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ListaDirectoare {
    public Set<Director> listaDirectoare;
    public ListaDirectoare() {
        listaDirectoare = new HashSet<>();
    }
    public void afiseazaDirectoare() {
        if(this.listaDirectoare.isEmpty()) {
            System.out.println("Nu exista niciun director.");
        } else {
            System.out.println("Lista de directoare:");
            for(Director dir:listaDirectoare) {
                System.out.println(dir.getDenumire());
            }
        }
    }
    // ne vom folosi de aceasta metoda in metoda de add;
    public Director cautaDirector(String denumire) {
        for(Director dir:listaDirectoare) {
            if(Objects.equals(dir.getDenumire(), denumire)) {
                return dir;
            }
        }
        return null;
    }

    public void adaugaDirector(Director director) {
        if(cautaDirector(director.getDenumire()) != null) {
            System.out.println("Exista deja un director cu acest nume");
        } else {
            listaDirectoare.add(director);
        }
    }

    public void stergereDirector(String denumire) {
        if(cautaDirector(denumire) == null) {
            System.out.println("Directorul pe care incercati sa-l stergeti nu exista.");
        } else {
            Director director = cautaDirector(denumire);
            listaDirectoare.remove(director);
            System.out.println("Directorul a fost sters cu succes.");
        }
    }
}
