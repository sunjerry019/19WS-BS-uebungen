public class Koch extends Thread {
    private int id;
    private Lager lager;

    public Koch(int id, Lager lager) {
        this.id = id;
        this.lager = lager;
    }

    public void run() {
        while (true) {
            try {
                lager.apfelmusEntnehmen(id, 1);
                lager.aepfelEntnehmen(12);
                System.out.println("Koch kocht");
                Thread.sleep(1000);
                lager.apfelmusEinlagern(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
