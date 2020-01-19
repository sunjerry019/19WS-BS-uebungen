import java.util.*; 

public class Server 
{	
	private int maxClients;
	private int anzahlClients;
	private boolean sicherungswunsch;
	private ArrayList<Integer> activeClients;
	private ArrayList<Integer> waitList;

	public Server(int maxClients)
	{
		this.maxClients = maxClients;
		this.anzahlClients = 0;
		this.sicherungswunsch = false;
		this.activeClients = new ArrayList<Integer>();
	}	
	public synchronized void daten_ablegen(Client c) throws InterruptedException
	{
		System.out.println("Client " + c.ID + " will Daten ablegen");
		
		while(this.anzahlClients >= this.maxClients || this.sicherungswunsch)
		{
			try { System.out.println("STATUS: Must wait; Total Count "+ anzahlClients + " Wunsch: " + this.sicherungswunsch); wait(); }
			catch(InterruptedException e) {}
		}
		anzahlClients ++; 
		this.activeClients.add(c.ID);
		this.printActive();
		System.out.println(anzahlClients +" Clients legen Daten ab.");
	}
	public synchronized void daten_ablegen_beenden(Client c)
	{
		if (anzahlClients <= 0) {System.out.println("!!!!!! Unexpected Behaviour!"); }
		this.activeClients.removeIf(n -> (n == c.ID)); 
		anzahlClients--;
		System.out.println("Client " + c.ID + " Done");
		this.printActive();
		notifyAll();
	}	
	public synchronized void sicherungAktivieren() throws InterruptedException
	{
		System.out.println("Sicherungswunsch angemeldet!");
		sicherungswunsch = true;

		notifyAll();
		
		while(this.anzahlClients > 0)
		{
			try { System.out.println("Must wait; Total Count "+ anzahlClients); wait(); }
			catch(InterruptedException e) {}
		}
		// Zum Sichern bereit.
	}	
	public synchronized void sicherungDeaktivieren()
	{
		this.sicherungswunsch = false;
		System.out.println("Sicherungswunsch deaktiviert.");
		notifyAll();
	}
	public synchronized void printActive()
	{
		boolean first = true;
		System.out.printf("ActiveClients = [");
		for (int i : this.activeClients) { 
			System.out.printf((first ? "": " ") + "%d", i);
            first = false;
        } 
        System.out.printf("] %n");
	}
}
