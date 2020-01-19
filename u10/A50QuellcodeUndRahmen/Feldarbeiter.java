public class Feldarbeiter extends Thread {
    private int id;
    private Lager lager;

    public Feldarbeiter(int id, Lager lager) {
        this.id = id;
        this.lager = lager;
    }

    public void run() {
        while (true) {
            try {
                lager.apfelmusEntnehmen(id, 1);
                
                // Arbeiter pflückt Äpfel
                Thread.sleep(1000);
                
                lager.aepfelEinlagern(2);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
