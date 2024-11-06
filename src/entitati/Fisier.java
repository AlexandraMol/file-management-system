package entitati;
import entitati.exceptii.ExceptiiFisiere;
import java.io.Serializable;
import java.util.Objects;

public class Fisier implements Serializable {
    private String denumire;
    private TipFisier tipFisier;
    private String extensie = "";
    private int dimensiune; // in KB
    private String locatie;
    private String directorParinte;

    public Fisier(String denumire, String directorParinte) throws ExceptiiFisiere {
        this.denumire = denumire;
        this.extensie = obtinereExtensie(denumire);
        this.tipFisier = obtinereTipFisier(this.extensie.toLowerCase());
        this.dimensiune = this.generareDimensiuneFisier();
        this.directorParinte = directorParinte;
        this.locatie = calculareLocatie(directorParinte);
    }

    private String calculareLocatie(String directorParinte) {
        String locatie = "C:\\Users\\Alexandra\\Desktop\\";
        if(directorParinte != null) {
             locatie += directorParinte + "\\";
        }
        return locatie;
    }

    // se calculeaza in functie de numele fisierului
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
        // if(extensie) nu e in vectorul de extensii acceptate
        if(ok == 0) {
            throw new ExceptiiFisiere("Fisierul dumneavoastra nu are o extensie potrivita");
        }
        return extensie;
    }

    // generare random a dimensiunii unui fisier in functie de tipul de fisier;
    private int generareDimensiuneFisier() {
        int dimensiune = 0; // fisier corupt
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
                throw new IllegalStateException("Nu se poate calcula dimensiunea pentru acest tip de fisier " + this.tipFisier);
        }
        return dimensiune;
    }

    // sa extragem tipul in functie de extensie;
    private TipFisier obtinereTipFisier(String extensie){
        // String[] extensiiAcceptate = {"jpg","png","mp3","wav","mp4","mov"};
        if(Objects.equals(extensie, "jpg") || Objects.equals(extensie, "png")) {
            return TipFisier.IMAGINE;
        }
        if(Objects.equals(extensie, "mp3") || Objects.equals(extensie, "wav")) {
            return  TipFisier.AUDIO;
        }
        if(Objects.equals(extensie, "mp4") || Objects.equals(extensie, "mov")) {
            return TipFisier.VIDEO;
        }
        return TipFisier.CORUPT;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire; // cand setez denumirea sa setez si extensia
    }

    public TipFisier getTipFisier() {
        return tipFisier;
    }

    public void setTipFisier(TipFisier tipFisier) {
        this.tipFisier = tipFisier;
    }

    public String getExtensie() {
        return extensie;
    }

    public int getDimensiune() {
        return dimensiune;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getDirectorParinte() {
        return directorParinte;
    }

    public void setDirectorParinte(String directorParinte) {
        this.directorParinte = directorParinte;
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
