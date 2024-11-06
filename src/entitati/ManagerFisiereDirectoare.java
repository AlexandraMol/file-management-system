package entitati;

import java.io.*;

public class ManagerFisiereDirectoare {
    // asta ar putea fi un singletone
    public ListaDirectoare listaDirectoare;

    public ManagerFisiereDirectoare() {
        listaDirectoare = new ListaDirectoare();
    }

    public String cautaLocatieFisier(String denumire) {
        for(Director director : listaDirectoare.listaDirectoare) {
            Fisier fisierCautat = director.getListaFisiere().cautaFisier(denumire);
            if(fisierCautat != null) {
                return fisierCautat.getLocatie();
            }
        }
        return null;
    }

    public void salvareFisier(File fisier) {

    }

    public void restaurareFisier(File fisier) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fisier));
        if (br.readLine() != null) {
            // System.out.println("Fisierul nu exista!");
        }
    }
    // metoda de save si restore from file;
    // aici sa implementez si statisticile;
}
