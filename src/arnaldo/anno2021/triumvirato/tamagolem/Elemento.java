package arnaldo.anno2021.triumvirato.tamagolem;

public class Elemento {
	private String nome;
	private int id;
	private MappaElementale mappa;
	
	Elemento(String nome, int id){
		this.nome=nome;
		this.id=id;
		this.mappa=new MappaElementale();
	}
	
	public Elemento(Elemento elemento) {
		this.nome=elemento.nome;
		this.id=elemento.id;
		this.mappa=new MappaElementale(elemento.mappa);
	}

	/**
	 * Controlla l'interazione di questo elemento con un altro dato come parametro
	 * @param nemico è l'elemento contro il quale questo viene confrontato
	 * @return ritorna il numero di vantaggio che questo elemento ha rispetto all'altro(ritorna un valore negativo se è invece l'elemento nemico ad essere in vantaggio), questo si tradurrà nel danno subito(in valore assoluto) dal golem che ha scagliato l'elemento debole tra i due
	 */
	public int getInterazione(Elemento nemico){
		int indice=nemico.getId();
		if(indice<mappa.getSize()){
			return mappa.getValore(indice);
		}else {
			return 0;			
		}

	}

	public String getNome() {
		return nome;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addToMappa(int valore) {
		mappa.aggiungiValore(valore);
	}
	
	public int getFromMappa(int index) {
		return mappa.getValore(index);
	}

	/**
	 * Questo metodo ritorna true se due elementi sono concettualmente lo stesso(hanno lo stesso nome)
	 */
	@Override
	public boolean equals(Object confronto) {
		
		if(confronto instanceof Elemento) {
			if(this.nome.equals(((Elemento)confronto).getNome())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	/**
	 * ritorna la lista dei vantaggi(ogni vantaggio è nella posizione dell'id dell'elemento contro cui si conta il vantaggio. Ovviamente il vantaggio negativo è invece il vantaggio che ha l'elemento in quella posizione contro questo
	 * @return
	 */
	public int[] getListaVantaggi(){
		int N=BattleHandler.getN();
		int[] array=new int[N];
		for(int i=0;i<N;i++) {
			array[i]=this.mappa.getValore(i);
		}
		
		return array;
	}
}
