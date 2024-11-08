package entitati.clase;

import entitati.exceptii.ExceptiiFisiere;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clasa ce se ocupa de gestionarea directoarelor si a fisierelor din aplicatie si salveaza informatii despre fisierele
 * create cat si statistici.
 */
public class ManagerFisiereDirectoare {
    public ListaDirectoare listaDirectoare;

    public ManagerFisiereDirectoare() {
        listaDirectoare = new ListaDirectoare();
    }

    /**
     * Metoda ce se ocupa cu salvarea informatiilor fisierelor create in aplicatie. Acestea sunt stocate intr-un fisier text.
     */
    public void salveazaInformatiiFisiere() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("informatiiFisiere.txt"))) {
            for (Director director : listaDirectoare.listaDirectoare) {
                ArrayList<Fisier> listaFisiere = director.getListaFisiere().listaFisiere;
                for (Fisier f : listaFisiere) {
                    writer.write(f.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda genereaza statistici despre fisiere si directoare si le salveaza intr-un fisier text.
     * @throws IOException daca fisierul nu s-a putut crea
     */
    public void genereazaRaport() throws IOException {
        int numarDirectoare = listaDirectoare.listaDirectoare.size() - 1;
        int numarFisiereAudio = 0, numarFisiereImagine = 0, numarFisiereVideo = 0;
        int dimensiuneFisiereAudio = 0, dimensiuneFisiereImagine = 0, dimensiuneFisiereVideo = 0;
        String fisierDimensiuneMaximaAudio = "", fisierDimensiuneMaximaImagine = "", fisierDimensiuneMaximaVideo = "";

        int dimensiuneMaximaFisierAudio =0, dimensiuneMaximaFisierImagine = 0, dimensiuneMaximaFisierVideo = 0;
        for(Director director : listaDirectoare.listaDirectoare) {
            ArrayList<Fisier> listaFisiere = director.getListaFisiere().listaFisiere;
            for (Fisier f : listaFisiere) {
                TipFisier tipFisier = f.getTipFisier();
                if(tipFisier.equals(TipFisier.AUDIO)) {
                    numarFisiereAudio +=1;
                    dimensiuneFisiereAudio += f.getDimensiune();
                    if(dimensiuneMaximaFisierAudio < f.getDimensiune()) {
                        dimensiuneMaximaFisierAudio = f.getDimensiune();
                        fisierDimensiuneMaximaAudio = f.getDenumire();
                    }
                }
                if(tipFisier.equals(TipFisier.IMAGINE)) {
                    numarFisiereImagine +=1;
                    dimensiuneFisiereImagine += f.getDimensiune();
                    if(dimensiuneMaximaFisierImagine < f.getDimensiune()) {
                        dimensiuneMaximaFisierImagine = f.getDimensiune();
                        fisierDimensiuneMaximaImagine = f.getDenumire();
                    }
                }
                if(tipFisier.equals(TipFisier.VIDEO)) {
                    numarFisiereVideo +=1;
                    dimensiuneFisiereVideo += f.getDimensiune();
                    if(dimensiuneMaximaFisierVideo < f.getDimensiune()) {
                        dimensiuneMaximaFisierVideo = f.getDimensiune();
                        fisierDimensiuneMaximaVideo= f.getDenumire();
                    }
                }
            }
        }
        int numarTotalFisiere = numarFisiereAudio + numarFisiereImagine + numarFisiereVideo;
        int dimensiuneTotalOcupata = dimensiuneFisiereAudio + dimensiuneFisiereImagine + dimensiuneFisiereVideo;

        File fisier = new File("raportGestiuneFisiere.txt");
        fisier.createNewFile();
        FileWriter writer = new FileWriter(fisier);

        writer.write("In aplicatie exista create in total " + numarDirectoare + " directoare si " + numarTotalFisiere + " fisiere" + "\n");

        writer.write("Numarul fisierelor audio: " + numarFisiereAudio +"\n");
        writer.write("Numarul fisierelor de tip imagine: " + numarFisiereImagine +"\n");
        writer.write("Numarul fisierelor video: " + numarFisiereVideo +"\n");

        writer.write("Fisierele ocupa in total: " + dimensiuneTotalOcupata +"\n");
        writer.write("Fisierele audio ocupa: " + dimensiuneFisiereAudio +"\n");
        writer.write("Fisierele de tip imagine ocupa: " + dimensiuneFisiereImagine +"\n");
        writer.write("Fisierele video ocupa: " + dimensiuneFisiereVideo +"\n");

        writer.write("Fisierul audio cu dimensiunea cea mai mare de " + dimensiuneMaximaFisierAudio + "KB: " + fisierDimensiuneMaximaAudio +"\n");
        writer.write("Fisierul de tip imagine cu dimensiunea cea mai mare de " + dimensiuneMaximaFisierImagine + "KB: " + fisierDimensiuneMaximaImagine +"\n");
        writer.write("Fisierul video cu dimensiunea cea mai mare de " + dimensiuneMaximaFisierVideo + "KB: " + fisierDimensiuneMaximaVideo +"\n");
        writer.close();
    }

    /**
     * Metoda ce cauta path-ul unui fisier in lista de directoare.
     * @param denumire fisierelui de gasit
     * @return o lista de locatii in care fisierul se afla. Daca acesta nu exista se returneaza null
     */
    public String[] cautaLocatieFisier(String denumire) {
        String[] locatiiFisierCautat = {};
        for(Director director : listaDirectoare.listaDirectoare) {
            Fisier fisierCautat = director.getListaFisiere().cautaFisier(denumire);
            if(fisierCautat != null) {
                locatiiFisierCautat = Arrays.copyOf(locatiiFisierCautat, locatiiFisierCautat.length + 1);
                locatiiFisierCautat[locatiiFisierCautat.length - 1] = fisierCautat.getLocatie();
            }
        }
        if(locatiiFisierCautat.length == 0) {
            return null;
        }
        return locatiiFisierCautat;
    }

    /**
     * Metoda salveaza intr-un fisier locatiile fisierelor create si situatia directoarelor din aplicatie
     * @param fisier
     * @throws IOException
     */
    public void salvareFisier(File fisier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fisier))) {
            for (Director director : listaDirectoare.listaDirectoare) {
                ArrayList<Fisier> listaFisiere = director.getListaFisiere().listaFisiere;
                for (Fisier f : listaFisiere) {
                    writer.write("C:\\Users\\Alexandra\\Desktop\\" + director.getDenumire() + "\\" + f.getDenumire());
                    writer.newLine();
                }
                if(listaFisiere.isEmpty()) {
                    writer.write("C:\\Users\\Alexandra\\Desktop\\" + director.getDenumire());
                    writer.newLine();
                }
            }
        }
    }

    /**
     * Metoda ce citeste fisierul primit si pe baza acestuia creeaza lista de directoare si de fisiere
     * @param fisier ce contine locatiile fisierelor si a directoarelor
     * @throws IOException
     * @throws ExceptiiFisiere daca fisierul nu contine o extensie sau nu este acceptata
     */
    public void restaurareFisier(File fisier) throws IOException, ExceptiiFisiere {
        try (BufferedReader reader = new BufferedReader(new FileReader(fisier))) {
            String inregistrare;
            while ((inregistrare = reader.readLine()) != null) {

                Path path = Paths.get(inregistrare);
                String numeFisier = path.getFileName().toString();
                boolean areExtensie = numeFisier.lastIndexOf(".") > 0;

                if(!areExtensie) {
                    String numeDirector = path.getFileName().toString();
                    Director director = this.listaDirectoare.cautaDirector(numeDirector);

                    if(director == null) {
                        director = new Director(numeDirector);
                        listaDirectoare.adaugaDirector(director);
                    }
                } else {
                    Path directorParinte = path.getParent();
                    String numeDirector = directorParinte.getFileName().toString();

                    Director director = this.listaDirectoare.cautaDirector(numeDirector);

                    if(director == null) {
                        director = new Director(numeDirector);
                        listaDirectoare.adaugaDirector(director);
                    }

                    Fisier f = new Fisier(numeFisier,numeDirector);
                    director.getListaFisiere().adaugaFisier(f);
                }
            }
        }
    }
}
