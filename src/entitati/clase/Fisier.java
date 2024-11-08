package entitati.clase;
import entitati.exceptii.ExceptiiFisiere;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa ce stocheaza informatii despre fisierele multimedia.
 */
public class Fisier implements Serializable {
    private String denumire;
    private TipFisier tipFisier;
    private String extensie = "";
    private int dimensiune;
    private String locatie;
    private String directorParinte;

    public Fisier(String denumire, String directorParinte) {
        this.denumire = denumire;
        this.extensie = obtinereExtensie(denumire);
        this.tipFisier = obtinereTipFisier(this.extensie.toLowerCase());
        this.dimensiune = this.generareDimensiuneFisier();
        this.directorParinte = directorParinte;
        this.locatie = calculareLocatie(directorParinte);
    }

    /**
     * Calculeaza path-ul fisierului in functie de existenta unui director parinte, locatia default fiind pe Desktop
     * @param directorParinte
     * @return locatia in care fisierul a fost creat
     */
    private String calculareLocatie(String directorParinte) {
        String locatie = "C:\\Users\\Alexandra\\Desktop\\";
        if(directorParinte != null) {
             locatie += directorParinte + "\\";
        }
        locatie += this.denumire;
        return locatie;
    }

    /**
     * Se extrage extensia fisierului din denumire, incadrand fisierul intr-o categorie
     * @param denumire
     * @return extensia fisierului
     * @throws ExceptiiFisiere daca denumirea fisierului nu are extensie sau nu se afla intr-o categorie de extensii acceptate
     */
    private String obtinereExtensie(String denumire) throws ExceptiiFisiere {
        String extensie = "";
        String[] extensiiAcceptate = {"jpg","png","mp3","wav","mp4","mov"};
        int ok = 0;
        int i = denumire.lastIndexOf('.');
        if (i > 0) {
            extensie = denumire.substring(i+1);
        } else {
            throw new ExceptiiFisiere("Fisierul dumneavoastra nu are extensie");
        }

        for (String e : extensiiAcceptate) {
            if(e.equals(extensie)) {
                ok = 1;
                break;
            }
        }

        if(ok == 0) {
            throw new ExceptiiFisiere("Fisierul dumneavoastra nu are o extensie potrivita");
        }
        return extensie;
    }

    /**
     *
     * @return dimensiunea fisierului creat in functie de categoria in care se afla
     */
    private int generareDimensiuneFisier() {
        int dimensiune = 0;
        switch (this.tipFisier) {
            case TipFisier.IMAGINE:
                dimensiune = (int) ((Math.random() * (6000 - 250)) + 250);
            break;
            case TipFisier.AUDIO:
                dimensiune = (int) ((Math.random() * (28600 - 250)) + 250);
            break;
            case TipFisier.VIDEO:
                dimensiune = (int) ((Math.random() * (85000 - 250)) + 250);
            break;
            default:
                System.out.println("Nu se poate calcula dimensiunea pentru acest tip de fisier " + this.tipFisier);
        }
        return dimensiune;
    }

    /**
     * Incadreaza fisierul intr-o categorie in functie de extensie.
     * @param extensie
     * @return categoria din care face parte fisierul. Daca extensia nu este una acceptata se returneaza null.
     */
    private TipFisier obtinereTipFisier(String extensie){
        if(Objects.equals(extensie, "jpg") || Objects.equals(extensie, "png")) {
            return TipFisier.IMAGINE;
        }
        if(Objects.equals(extensie, "mp3") || Objects.equals(extensie, "wav")) {
            return  TipFisier.AUDIO;
        }
        if(Objects.equals(extensie, "mp4") || Objects.equals(extensie, "mov")) {
            return TipFisier.VIDEO;
        }
        return null;
    }

    public String getDenumire() {
        return denumire;
    }

    public TipFisier getTipFisier() {
        return tipFisier;
    }

    public int getDimensiune() {
        return dimensiune;
    }

    public String getLocatie() {
        return locatie;
    }

    @Override
    public String toString() {
        return "Fisier{" +
                "denumire='" + denumire + '\'' +
                ", tipFisier=" + tipFisier +
                ", extensie='" + extensie + '\'' +
                ", dimensiune=" + dimensiune + "KB" +
                ", locatie='" + locatie + '\'' +
                ", directorParinte='" + directorParinte + '\'' +
                '}';
    }
}
