public class Apfelplantage {
    public final static int Aepfel = 6;
    public final static int Apfelmus = 3;
    
    public static void main(String[] args) {
        Lager lager = new Lager(Aepfel, Apfelmus);

        Koch koch = new Koch(-1, lager);
        koch.start();

        for (int i = 0; i < 2; i++) {
            Feldarbeiter arbeiter = new Feldarbeiter(i, lager);
            arbeiter.start();
        }
    }
}
