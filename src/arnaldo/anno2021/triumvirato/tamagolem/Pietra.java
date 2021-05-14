package arnaldo.anno2021.triumvirato.tamagolem;

public class Pietra {
	Elemento tipo;

	public Pietra(Pietra copia) {
		this.tipo=new Elemento(copia.getTipo());
	}
	
	public Pietra(Elemento tipo) {
		super();
		this.tipo = tipo;
	}

	public Elemento getTipo() {
		return tipo;
	}
	
	public String toString() {
		return String.format(Constants.GEMMA_DI, tipo.getNome());
	}
	
	/**
	 * Controlla se due pietre sono dello stesso tipo
	 * @param confronto, la pietra da confrontare con this
	 * @return ritorna true se le due pietre sono dello stesso tipo, false in caso contrario
	 */
	public boolean hasSameElement(Pietra confronto) {
		return this.getTipo().equals(confronto.getTipo());
	}
}
