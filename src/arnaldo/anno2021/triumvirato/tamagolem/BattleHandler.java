package arnaldo.anno2021.triumvirato.tamagolem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


import it.unibs.fp.mylib.InputDati;

public class BattleHandler {
	//battle variables
	private static Equilibrio balance;
	private static int N; //numero di elementi
	private static int P; //numero di pietre elementali in possesso di ogni golem
	private static int V; //valore di hp(vita) per ogni golem)
	private static int G; //numero di golem per ogni giocatore
	private static int S; //numero di pietre nella scorta comune
	private static Giocatore giocatore1,giocatore2;
	private static ScortaGemme scorta_comune;



	/**
	 * Fa partire e coordina tutta la battaglia, è il metodo principale della classe
	 */
	public static void doBattle() {
		battleStartPhase();
		
		initialSummoning();
		
		do {
			scontroGolem(giocatore1,giocatore2);
			
		}while(canKeepFighting());
		
		announceVictory();
	}
	
		
	
	private static void initialSummoning() {
		//clearScreen();
		System.out.println(Constants.GOLEM_INITIAL_SUMMONING_MESSAGE);
		
		giocatore1.summonGolem();
		giocatore2.summonGolem();
	}

	private static void scontroGolem(Giocatore giocatore1, Giocatore giocatore2) {
		Golem golem1=giocatore1.getGolemInPlay();
		Golem golem2=giocatore2.getGolemInPlay();
		
		if(golem1.hasSameElementsOrder(golem2)) {
			System.out.println(Constants.GOLEMS_SAME_ELEMENTS_MESSAGE);
			giocatore1.golemDeath();
			giocatore2.golemDeath();
			
		}else {
			
			do{
				turnoGolem(golem1,golem2);
				
			}while(golem1.isAlive()&&golem2.isAlive());
			
			
			
			if(golem1.isAlive()) {
				giocatore2.golemDeath();
			}else {
				giocatore1.golemDeath();
			}
		}
		
		
		
		
	}

	private static void outputAttack(Elemento element1, Elemento element2) {
		String output=String.format(Constants.GOLEM_GEM_CLASH_MESSAGE, giocatore1.getNome(),element1.getNome(),giocatore2.getNome(),element2.getNome());
		System.out.println(output);
	}
	
	private static void outputAttackEffect(Elemento element, int damage, Giocatore losingPlayer) {
		System.out.println(String.format(Constants.GOLEM_ELEMENTAL_INTERACTION_MESSAGE, element.getNome(),losingPlayer.getNome(),damage));
	}
	
	private static void turnoGolem(Golem golem1, Golem golem2) {

		
		Pietra golem1Attack=golem1.hurlGem();
		Pietra golem2Attack=golem2.hurlGem();
		Elemento golem1Element=golem1Attack.getTipo();
		Elemento golem2Element=golem2Attack.getTipo();
		
		
		
		int interaction=golem1Element.getInterazione(golem2Element);
		int damage=(int)Math.abs(interaction);
		if(interaction==0) {
			outputAttack(golem1Element,golem2Element);
			System.out.println(Constants.GOLEM_SAME_ENERGY_MESSAGE);
		}else {
			
			if(interaction>0) { //l'elemento 1 ha vinto
				
				golem2.takeDamage(damage);
				if(golem2.isAlive()) {
					outputAttack(golem1Element,golem2Element);
					outputAttackEffect(golem1Element,damage,giocatore2);					
				}else{
					System.out.println(Constants.LAST_HIT_MESSAGE);
				}
				//System.out.println(golem2.getCurrentHealth());
			
			}else { //l'elemento 2 ha vinto
				
				golem1.takeDamage(damage);
				if(golem1.isAlive()) {
					outputAttack(golem1Element,golem2Element);
					outputAttackEffect(golem2Element,damage,giocatore1);
				}else{
					System.out.print(Constants.LAST_HIT_MESSAGE);
				}

				
				//System.out.println(golem2.getCurrentHealth());
			}
		}
		
		System.out.println("\n");
	}


	private static void battleStartPhase(){
		N=InputDati.leggiIntero(Constants.INPUT_MESSAGE_ELEMENTS_NUMBER_CHOICE,3,10);

		initializeParameters(N);
		balance=new Equilibrio(N);
		//balance.printBalance();
		//scorta_comune=new ArrayList<Pietra>();
		scorta_comune=new ScortaGemme();
		for(int i=0;i<N;i++) {
			//S/N is always an integer value as S is always multiple of N
			for(int j=0;j<S/N;j++) {
				scorta_comune.add(new Pietra(balance.getListaElementi().get(i)));
			}
		}
		
		String nome1=InputDati.leggiStringa(String.format(Constants.INPUT_MESSAGE_NOME_GIOCATORE, 1));
		String nome2=InputDati.leggiStringa(String.format(Constants.INPUT_MESSAGE_NOME_GIOCATORE, 2));
		//Collections.shuffle(scorta_comune); //might need
		giocatore1=new Giocatore(nome1);
		giocatore2=new Giocatore(nome2);
		
		giocatore1.createTeam(G,V);
		giocatore2.createTeam(G,V);
	}
	
	
	private static void initializeParameters(int N) {
		P=generateP(N);
		G=generateG(N,P);
		S=generateS(N,P,G);
		V=Constants.V;
	}
	
	
	public static ScortaGemme getScortaComune() {
		return scorta_comune;
	}
	
	

	/**
	 * fornisce il numero di pietre che ogni golem equipaggia
	 * @param N è il numero di elementi
	 * @return P è il numero di pietre che ogni golem può possedere
	 */
	private static int generateP(int N) {
		double frazione=((double)(N+1))/3.0;
		return (int)(Math.ceil(frazione)+1);
	}
	
	/**
	 * fornisce il numero di Golem di ogni giocatore
	 * @param N è il numero di elementi
	 * @param P è il numero di pietre che ogni golem può possedere
	 * @return numero di Golem
	 */
	private static int generateG(int N,int P) {
		double numeratore=(double)((N-1)*(N-2));
		double denominatore=(double)(2*P);
		return (int)Math.ceil(numeratore/denominatore);
	}
	
	/**
	 * fornisce il numero di pietre nella scorta comune
	 * @param N è il numero di elementi
	 * @param P è il numero di pietre che possiede ogni golem
	 * @param G è il numero di golem che ha ogni giocatore
	 * @return
	 */
	private static int generateS(int N, int P, int G) {
		return N*getNPietrePerElemento(N, P, G);
	}
	
	/**
	 * fornisce il numero di pietre per ogni elemento contenute nella scorta comune
	 * @param N è il numero di elementi
	 * @param P è il numero di pietre che possiede ogni golem
	 * @param G è il numero di golem che ha ogni giocatore
	 */
	private static int getNPietrePerElemento(int N, int P, int G) {
		double frazione=((double)(2*P*G))/2.0;
		return (int)Math.ceil(frazione);
	}
	

	/**
	 * Metodo per verificare se la battaglia può ancora procedere oppure no
	 * @return ritorna true se è ancora possibile combattere
	 */
	public static boolean canKeepFighting() {
		
		return (giocatore1.golemRimasti()>0&&giocatore2.golemRimasti()>0)&&scorta_comune.size()>0;
	}
	
	
	private static int getVictoryResult() {
		boolean bothPlayersHaveGolems=giocatore1.golemRimasti()>0&&giocatore2.golemRimasti()>0;
		boolean allGolemsAreDead=giocatore1.golemRimasti()==0&&giocatore2.golemRimasti()==0;
		if(bothPlayersHaveGolems||allGolemsAreDead) {
			return Constants.TIE_RESULT_CODE;
		}else {
			if(giocatore1.golemRimasti()>0) {
				return Constants.WIN_PLAYER1_CODE;
			}else if(giocatore2.golemRimasti()>0){
				return Constants.WIN_PLAYER2_CODE;
			}else {
				return Constants.WIN_NOT_COMPUTABLE_CODE;
			}
		}
	}
	
	private static void announceVictory() {
		int victoryCode=getVictoryResult();
		System.out.println();
		if(victoryCode==Constants.TIE_RESULT_CODE) {
			System.out.println(Constants.TIE_MESSAGE);
		}else {
			if(victoryCode==Constants.WIN_PLAYER1_CODE) {
				System.out.println(String.format(Constants.VICTORY_MESSAGE_COLTA_CIT, giocatore2.getNome(),giocatore1.getNome(),giocatore2.getNome()));
			}else if(victoryCode==Constants.WIN_PLAYER2_CODE){
				System.out.println(String.format(Constants.VICTORY_MESSAGE_COLTA_CIT, giocatore1.getNome(),giocatore2.getNome(),giocatore1.getNome()));
			}else {
				System.out.println(Constants.UNCOMPUTABLE_WIN_MESSAGE);
			}
		}
		
		System.out.println(Constants.INVITE_TO_PLAY_AGAIN);
	}

	public static Giocatore getOtherPlayer(Giocatore currentPlayer) {
		if(giocatore1==currentPlayer) {
			return giocatore2;
		}else if(giocatore2==currentPlayer) {
			return giocatore1;
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo di appoggio che serve per ottenere il nome dell'altro giocatore di questa partita(rispetto a quello indicato e di cui sono note le informazioni)
	 * @param currentPlayer è il giocatore 
	 * @return ritorna il nome dell'altro giocatore
	 */
	public static String getOtherPlayerName(Giocatore currentPlayer) {
		
		Giocatore altroGiocatore=getOtherPlayer(currentPlayer);
		
		if(altroGiocatore==null) {
			return null;
		}else {
			return (String) altroGiocatore.getNome();
		}
	}
	
	

	
	
	/**
	 * P è il numero di elementi scelti per questa battaglia
	 * @return P
	 */	
	public static int getN() {
		return N;
	}
	

	/**
	 * P è il numero delle gemme che possiede ogni golem
	 * @return P
	 */
	public static int getP() {
		return P;
	}  

	/**
	 * Metodo che consente di attendere che il giocatore prema invio per avanzare (eventuali scritte del giocatore prima di premere invio sono ottime opportunità di sfogo quando questo perde il suo golem preferito, ma non vengono registrate)
	 */
	public static void pressEnterToContinue() {
		String temp=InputDati.leggiStringa(Constants.CONTINUE_MESSAGE);
		System.out.println();
	}
	
}
