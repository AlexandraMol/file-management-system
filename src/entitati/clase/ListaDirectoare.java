package entitati.clase;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ListaDirectoare {
    public Set<Director> listaDirectoare;
    public ListaDirectoare() {
        listaDirectoare = new HashSet<>();
    }

    /**
     * Metoda ce afiseaza numele directoarelor create in aplicatie. Daca nu exista directoare create
     * se va afisa un mesaj intuitiv
     */
    public void afiseazaDirectoare() {
        if(this.listaDirectoare.isEmpty()) {
            System.out.println("Nu exista directoare create in aplicatie.");
        } else {
            System.out.println("Lista de directoare:");
            for(Director dir:listaDirectoare) {
                System.out.println(dir.getDenumire());
            }
        }
    }

    /**
     * Se verifica existenta unui director in lista pe baza numelui.
     * @param denumire directorului de cautat
     * @return directorul a carui nume este cel trimis ca parametru, sau daca nu exista, null
     */
    public Director cautaDirector(String denumire) {
        for(Director dir:listaDirectoare) {
            if(Objects.equals(dir.getDenumire(), denumire)) {
                return dir;
            }
        }
        return null;
    }

    /**
     * Adauga un director in lista de directoare daca nu se afla deja in lista.
     * @param director
     */
    public void adaugaDirector(Director director) {
        if(cautaDirector(director.getDenumire()) != null) {
            System.out.println("Exista deja un director cu acest nume");
        } else {
            listaDirectoare.add(director);
        }
    }

    /**
     * Sterge un director din lista de directoare daca acesta exista in lista.
     * @param denumire
     */
    public void stergereDirector(String denumire) {
        if(cautaDirector(denumire) == null) {
            System.out.println("Directorul pe care incercati sa-l stergeti nu exista.");
        } else {
            Director director = cautaDirector(denumire);
            listaDirectoare.remove(director);
        }
    }
}
