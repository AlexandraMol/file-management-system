package entitati.exceptii;

public class ExceptiiFisiere extends RuntimeException {
    public ExceptiiFisiere (String errorMessage) {
        super(errorMessage);
    }
}
