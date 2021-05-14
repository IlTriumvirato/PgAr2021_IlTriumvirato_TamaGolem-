package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Giocatore {
	
	private String nome;
	private ArrayList<Golem> squadra;

	public Giocatore(String nome) {
		this.nome = nome;
	}

	/**
	 * Inizializza i golem del giocatore(non ancora con le gemme, sono pronti per essere evocati, in ordine dall'ultimo al primo)
	 * @param Golem_amount quantità
	 * @param Golem_health
	 */
	public void createTeam(int Golem_amount, int Golem_health) { //attenzione al fatto che resetta un giocatore alla posizione iniziale della partita se chiamato dopo, quindi potrebbe interferire con la quantità di gemme nella scorta comune
		this.squadra = new ArrayList<Golem>();
		
		for(int i=0;i<Golem_amount;i++) {
			squadra.add(new Golem(Golem_health));
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * 
	 * @return numero di golem rimasti al giocatore
	 */
	public int golemRimasti() {
		return squadra.size();
	}
	
	/**
	 * Ritorna il golem attualmente in campo
	 */
	public Golem getGolemInPlay() { //il golem che è sul campo a combattere al momento è sempre l'ultimo dell'array, l'alternativa era fare il primo, ma così è leggermente meglio l'esecuzione
		return squadra.get(squadra.size()-1);
	}
	
	/**
	 * Evoca il golem, facendo anche il processo di selezione delle gemme(chiamando loadCurrentGolem)
	 */
	public void summonGolem() {
		System.out.println(String.format(Constants.SECRET_INPUT_SUMMONING_MESSAGE, nome,BattleHandler.getOtherPlayerName(this)));
		loadCurrentGolem();
		System.out.println("\n"+String.format(Constants.GOLEM_SUMMON_MESSAGE,this.nome));
		BattleHandler.pressEnterToContinue();
	}
	
	/**
	 * Fa morire il golem attualmente in gioco rimuovendolo, il quale è sempre l'ultimo dell'array della squadra dei golem, si noti che è sempre quello nell'ultima posizione e che non viene rimosso neinte se non sono presenti golem
	 */
	public void golemDeath() {
		System.out.println(String.format(Constants.GOLEM_DEAD_MESSAGE, this.nome));
		BattleHandler.pressEnterToContinue();
		this.squadra.remove(squadra.size()-1);
		if(squadra.size()>0&&BattleHandler.canKeepFighting()) {
			summonGolem();
		}
	}

	
	private int sceltaGemma(int numeroScelta) { //abbiamo optato per lasciarla in giocatore anziché battleHandler perché alla fine è un metodo di appoggio per l'
		ArrayList<String> elencoGemme=BattleHandler.getScortaComune().getElencoGemme();

		System.out.println(String.format(Constants.GOLEM_GEM_CHOICE_MESSAGE, numeroScelta));
		for(int j=0;j<elencoGemme.size();j++) {
			System.out.println((j+1)+" "+elencoGemme.get(j));
		}

		int gemChoice=InputDati.leggiIntero("",1,elencoGemme.size());
		
		
		return gemChoice;
	}
	
	/**
	 * Questo metodo viene usato quando il giocatore evoca un golem: Gli permette di scegliere un insieme di gemme data una lista iniziale, ogni volta che una gemma viene scelta da un giocatore, questa viene rimossa dalla lista delle gemme disponibili
	 */	
	private void loadCurrentGolem() {
		
		BattleHandler.pressEnterToContinue();
		this.getGolemInPlay().resetGems();
		
		for(int i=1;i<=BattleHandler.getP();i++) {
			
			
			ArrayList<Pietra> primePerTipo=BattleHandler.getScortaComune().getArrayFirstGemsByType();
			
			int gemChoice=sceltaGemma(i);
			Pietra riferimentoGemma=primePerTipo.get(gemChoice-1);//despite the user choosing between 1 and the number of gems, indexing starts from 0
			
			this.getGolemInPlay().addGem(riferimentoGemma); //copy happens, this object is thus different and will not be removed with the following deletion
			BattleHandler.getScortaComune().removeGem(riferimentoGemma); //this object is a pointer and thus the gem will be effectively removed from the global list		
			
		
		}
	}
	

}
