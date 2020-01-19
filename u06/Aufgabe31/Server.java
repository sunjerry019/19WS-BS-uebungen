public class Server 
{   
    private int maxClients;
    private int anzahlClients;
    private boolean sicherungswunsch;

    public Server(int maxClients)
    {
        this.maxClients = maxClients;
        this.anzahlClients = 0;
        this.sicherungswunsch = false;
    }   
    public synchronized void daten_ablegen(Client c) throws InterruptedException
    {
        System.out.println("Client " + c.ID + " will Daten ablegen");
        
    // ----- kritischer Bereich -----
        while(this.anzahlClients >= this.maxClients || this.sicherungswunsch)
        {
            try { wait(); }
            catch(InterruptedException e) {}
        }
        anzahlClients ++; 
        System.out.println(anzahlClients + " Clients legen Daten ab.");
    }
    public synchronized void daten_ablegen_beenden()
    {
        anzahlClients --;
    // ----- kritischer Bereich verlassen -----
        notifyAll();
    }   
    public synchronized void sicherungAktivieren() throws InterruptedException
    {
        this.sicherungswunsch = true;
        System.out.println("Sicherungswunsch angemeldet!");
        
        notifyAll();
        
    // ----- kritischer Bereich -----
        while(this.anzahlClients > 0)
        {
            try { wait(); }
            catch(InterruptedException e) {}
        }
        // Zum Sichern bereit.
    }   
    public synchronized void sicherungDeaktivieren()
    {
        this.sicherungswunsch = false;
    // ----- kritischer Bereich verlassen -----
        System.out.println("Sicherungswunsch deaktiviert.");
        notifyAll();
    }
}
