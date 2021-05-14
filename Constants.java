package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

public class Constants {
	
	public static final String GAME_NAME="Tamagolem";
	public static final String[] MENU_SELECTION_OPTIONS= {"Gioca","Regole","Cambia elementi"};
	public static final String[] DEFAULT_ELEMENTS_NAMES= {"Fuoco","Acqua","Terra","Aria","Fulmine","Ghiaccio","Ombra","Luce","Natura","Metallo"};
	
	public static final String[] LORE={
			"Il delicato Equilibrio del Mondo si basa da sempre sull’interazione fra le diverse forze naturali, dalle più miti alle più distruttive.",
			"Ogni elemento in natura ha i suoi punti forti e le sue debolezze, caratteristiche che mantengono il nostro Universo stabile e sicuro.",
			"Da migliaia di anni, L’Accademia studia le tecniche per governare tali elementi: utilizzando alcune pietre particolari e dandole in pasto a strane creature denominate TamaGolem, infatti, è possibile conservare il potere degli elementi per liberarlo al bisogno.",
			"Gli allievi dell’Accademia, per questo motivo, sono soliti sfidarsi in combattimenti clandestini fra TamaGolem.",
			"L’abilità dei combattenti, in questo caso, sta nella scelta delle giuste Pietre degli Elementi in modo che lo scontro abbia il risultato sperato.",
			"Tale scelta non è scontata, poiché gli Equilibri del Mondosono mutevoli, e possono modificarsi radicalmente da una battaglia all’altra.",
			"Solamente il TamaGolem che resiste fino alla fine decreta la vittoria del proprio combattente.\n"
	};
	public static final String[] RULES={
			"All'inizio di ogni partita viene generato l'equilibrio del mondo e bisogna essere sempre molto attenti a cercare di comprenderlo quantopiù possibile durante il corso della partita, in quanto, essendo mutevole, cambia con ogni scontro.",
			"Dopodiche' ogni giocatore evoca il proprio golem; nella fase di evocazione bisogna scegliere le pietre elementali che andranno ad alimentare il potere dei golem.",
			"Il numero di gemme da scegliere dipende dalla quantità totale di elementi e le gemme vengono prelevate da una scorta comune.",
			"Una volta completata l'evocazione, i due golem si scontrano, scagliandosi contemporaneamente contro le loro gemme, una alla volta nell'ordine selezionato, finche' eventualmente uno dei due non viene distrutto.",
			"Bisogna fare attenzione pero' perche' solo il golem che ha scagliato la gemma dell'elemento dominante può danneggiare il suo avversario, per ogni lancio e per determinare quale elemento domina, si fa appello all'equilibrio del mondo(ogni elemento non fa alcun danno a se' stesso, ma un elemento domina sempre nelle altre situazioni).",
			"Il processo continua finché uno dei due golem non viene sconfitto e durante questo scontro le gemme non vengono consumate finché il golem non muore, le può riutilizzare(sono dei condotti arcani estremamente efficienti) ma l'ordine si ripete in maniera ciclica.",
			"Quando un golem viene sconfitto ne viene evocato uno nuovo per il giocatore che l'ha perso, ma attenzione, ognuno ha un numero limitato di golem e l'ultimo giocatore che ne avrà sul campo, sarà il vincitore.\n"
	};
	
	public static final int N_MASSIMO=10;
	public static final int N_MINIMO=3;
	public static final int V=10;
	public static final int DANNO_MAX=V-1;
	
	public static final String CHANGE_ELEMENTS_MESSAGE="Per scegliere i nuovi elementi del tuo mondo scrivine i nomi di seguito su righe separate.\nTieni presente che un mondo non può essere completo senza almeno "+N_MASSIMO+" elementi, ma aggiungendone di più, una mente mortale non sarebbe in grado di comprenderli.\nSi prega di scriverli con degli underscore _ al posto degli spazi, altrimenti potrebbero non essere uditi nella loro interezza nella creazione del mondo.";
	
	
	public static final String CONTINUE_MESSAGE = "Premi invio per continuare> ";
	public static final String SECRET_INPUT_MESSAGE="%s evoca il tuo golem; %s non guardare, chiunque osservi un evocatore durante il rituale verra' distrutto nel processo!";
	
	public static final String N_CHART="Definendo con N il numero di elementi scelti, il livello di difficolta' e' il seguente: \n3<=N<=5 livello facile, ma N=3 potrebbe rendere la partita meno interessante;\n6<=N<=8 livello intermedio;\n9<=N<=10 livello difficile;\nI comuni mortali non possono concepire un universo con piu' di 10 elementi, il caos sarebbe eccessivo.";
	public static final String INPUT_MESSAGE_ELEMENTS_NUMBER_CHOICE = "Inserisci il numero di elementi con cui desideri giocare, tieni a mente però che a seconda di questi valori la partita potrebbe cambiare considerevolmente.\n"+N_CHART+"\n\nInserisci il numero di elementi N>";
	public static final String INPUT_MESSAGE_NOME_GIOCATORE = "Giocatore %d, inserisci il tuo nome> ";
	public static final String VICTORY_MESSAGE_COLTA_CIT="%s vince! Tuuu(%s) hai perso!";
	public static final String TIE_MESSAGE = "Pareggio... Perfettamente bilanciato, come ogni cosa dovrebbe essere...";
	public static final String UNCOMPUTABLE_WIN_MESSAGE="Non è stato possibile determinare il vincitore... Per caos elementale o per errori di altro genere.";
	public static final String INVITE_TO_PLAY_AGAIN="In un mondo in cui il controllo del caos e' esso stesso imprevedibile chi potra' sapere cosa avverra'?\nTu puoi deciderlo nel prossimo scontro! Premi 1 per giocare di nuovo.";
	
	public static final String GOLEM_INITIAL_SUMMONING_MESSAGE="Bene, senza ulteriori indugi...\n\nAvvio fase di EVOCAZIONE!";
	public static final String GOLEM_SUMMON_MESSAGE="%s ha evocato un golem!";
	public static final String GOLEM_GEM_CLASH_MESSAGE="Il golem di %s scaglia una pietra di %s - Il golem di %s scaglia una pietra di %s ...";
	public static final String GOLEM_SAME_ENERGY_MESSAGE = "Le due gemme si scontrano, ma la loro affinita' non provoca nessun effetto!";
	public static final String GOLEM_ELEMENTAL_INTERACTION_MESSAGE="Prevale %s e il golem di %s subisce %d danni";
	public static final String GOLEMS_SAME_ELEMENTS_MESSAGE="Le gemme dei due golem sono tutte degli stessi elementi! Vengono scagliate tutte e lo scontro degli stessi elementi così tante volte ha portato a un sovraccarico di energia!\nentrambi i golem sono stati distrutti!";
	public static final String GOLEM_DEAD_MESSAGE="Il Golem di %s e' stato sconfitto!";
	public static final String BALANCE_DISPLAY_MESSAGE="Vuoi guardare l'equilibrio o continuare a giocare? Inserisci 0 per la visualizzazione oppure 1 per continuare";
	
	//gem selection
	public static final String ERROR_MESSAGE_UNEXISTING_GEM = "La gemma selezionata non è presente";
	public static final String GEMMA_DI="Gemma di %s";
	public static final String RAGGRUPPAMENTO_GEMME="%d gemme di %s";
	public static final String GOLEM_GEM_CHOICE_MESSAGE="Scegli la gemma numero %d del tuo golem";
	public static final String LAST_HIT_MESSAGE = "Viene sferrato in fine un decisivo colpo di grazia!...";
	
	//win computing
	public static final int TIE_RESULT_CODE = 0;
	public static final int WIN_PLAYER1_CODE=1;
	public static final int WIN_PLAYER2_CODE=2;
	public static final int WIN_NOT_COMPUTABLE_CODE=-1;
	
	
}
