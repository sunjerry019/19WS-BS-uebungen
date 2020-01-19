public class Lager {
    private int aepfel;
    private int apfelmus;

    public Lager(int aepfel, int apfelmus) {
        this.aepfel = aepfel;
        this.apfelmus = apfelmus;
    }

    public synchronized void aepfelEinlagern(int anzahl) {
        this.aepfel += anzahl;
        System.out.println(anzahl + " Aepfel eingelagert, Anz. Aepfel:" + this.aepfel);
        notifyAll();
    }

    public synchronized void apfelmusEntnehmen(int id, int anzahl) throws InterruptedException {
        while (this.apfelmus < anzahl) {
            System.out.println("Identitaet " + id + 
					" muss warten. Anz. apfelmus:" + apfelmus);
            wait();
        }
        this.apfelmus -= anzahl;
        System.out.println(anzahl + " Apfelmus entnommen von " + id + 
					" , Anz. Apfelmus:" + this.apfelmus);
    }

    public synchronized void aepfelEntnehmen(int anzahl) throws InterruptedException {
        while (this.aepfel < anzahl) {
            System.out.println("Koch muss warten. Anz. Aepfel:" + aepfel);
            wait();
        }
        this.aepfel -= anzahl;
        System.out.println(anzahl + " Aepfel entnommen, Anz. Aepfel:" + this.aepfel);
    }

    public synchronized void apfelmusEinlagern(int anzahl) {
        this.apfelmus += anzahl;
        System.out.println(anzahl + " Apfelmus eingelagert, Anz. Apfelmus:" + this.apfelmus);
        notifyAll();
    }
}
