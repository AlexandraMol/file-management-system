package entitati;

import java.io.*;

public class ManagerFisiereDirectoare {
    // asta ar putea fi un singletone
    public ListaDirectoare listaDirectoare;
    public ListaFisiere listaFisiere;

    public ManagerFisiereDirectoare() {
        listaFisiere = new ListaFisiere();
        listaDirectoare = new ListaDirectoare();
    }

    public void salvareFisier(File fisier) {

    }

    public void restaurareFisier(File fisier) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fisier));
        if (br.readLine() != null) {
            System.out.println("Fisierul nu exista!");
        }
    }
    // metoda de save si restore from file;
    // aici sa implementez si statisticile;
}
