public class Note {
    private final Nominal nominal;

    Note(Nominal nominal) {
        this.nominal = nominal;
    }

    Nominal getNominal() {
        return nominal;
    }
}
