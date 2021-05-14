package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;
import java.util.Collections;

import it.unibs.fp.mylib.ManipolaArray;

public class ArrayWithFixedSumGenerator {
	private static int rangeMax;
	private static int rangeMin;
	private static int[] arrayComune;

	private static int getRandomIntNonZero(int min, int max) {
		int valore=0;
		if(min==max) {  //ci si assicura che la funzione non venga mai chiamata con max=min=0
			return max;
		}else {
			if(min>max) {
				int temp=max;
				max=min;
				min=temp;
			}
			
			while(valore==0) {
				int rangeWidth=max-min;
				valore=((int)(Math.random()*(rangeWidth+1)))+min;
			}
			
			return valore;
		}
	}
	

	//l'array arrayComune assume valori casuali tra le posizioni start e end(incluse) in modo da avere somma sum, stando nel range prefissato
	private static void subArraySumRecursion(int start, int end, int sum) {
		int random1,random2;
		do{
			if(sum>0) { //se è 0 i due casi sono uguali
				random1=getRandomIntNonZero(sum+rangeMin,rangeMax);//senza prendere la precauzione, siccome rangeMin è negativo, il rischio è che poi random2 diventi random2=sum-(rangeMin)=sum+rangeMax e quindi ecceda rangeMax perché sum>0
			}else {
				random1=getRandomIntNonZero(rangeMin,sum+rangeMax);//senza prendere la precauzione, siccome rangeMax è positivo, il rischio è che poi random2 diventi random2=sum-(rangeMax)=sum+rangeMin e quindi ecceda rangeMin in negativo, perché sum<0
			}
			
			random2=sum-random1;
		}while(random1==0||random2==0);//non sono accettabili valori nulli, se per qualche motivo dovessero risultare, si può ri-rollare, non risulta mai ciclo infinito perché almeno uno dei due estremi di estrazione è non nullo
		
		int elementsAmount=end-start+1;
		if(elementsAmount>2) {
			//se nel sottoarray ci sono più di due elementi, questo deve essere suddiviso in due sotto-array dove si re-itera la funzione.
			//Si noti che l'efficacia di questo algoritmo sta nel fatto che i due sottoarray avranno ciascuno valore di somma pari a random1(il primo) e random2(il secondo) e quindi la loro somma darà sempre sum=random1+random2
			int middleIndex=(end+start)/2;
			subArraySumRecursion(start,middleIndex,random1);
			subArraySumRecursion(middleIndex+1,end,random2);
		}else {
			if(elementsAmount==1) { //se questo sottoarray contiene un solo elemento, questo elemento deve necessariamente prendere il valore pari alla somma che ci si aspetta da questo sottoarray
				arrayComune[start]=sum;
			}else if(elementsAmount==2){ //se questo sottoarray ha due elementi, perché abbiano somma sum=random1+random2 va benissimo dare al primo random1 e al secondo random2
				arrayComune[start]=random1;
				arrayComune[end]=random2;
			}

			//un caso all'infuori di questi non avviene mai perché la funzione non viene mai lanciata con 0 elementi o con valori di indexing che generano valori negativi
		}
	}
	
	/**
	 * Genera un ArrayList di valori interi casuali, di somma e dimensione prefissate
	 * @param size è la dimensione che si richiede abbia l'ArrayList generato
	 * @param rangeMaximum è il massimo dell'intervallo di casualità in cui i numeri vengono generati, l'intervallo è sempre del tipo [-rangeMaximum;+rangeMaximum] con rangeMaximum>0 (viene cambiato di segno se negativo)
	 * @param sum è la somma che si richiede abbiano gli elementi dell'ArrayList
	 * @return ritorna l'ArrayList coi parametri richiesti
	 */
	public static ArrayList<Integer> generateArrayListWithFixedSum(int size,int rangeMaximum, int sum){
		arrayComune=new int[size];
		if(rangeMaximum==0) { //si suppone un range esclusivamente del tipo  [-a;a] con a>0
			return null;
		}else {
			//rangeMaximum=(int)(Math.abs(rangeMaximum));
			rangeMax=rangeMaximum;
			rangeMin=-rangeMaximum;   
			subArraySumRecursion(0,size-1,sum);//l'array dalla posizione 0 alla posizione size-1(incluse) genera valori casuali in modo da avere somma sum
			
			ArrayList<Integer> finalArray=ManipolaArray.convertToArrayListInteger(arrayComune);
			
			Collections.shuffle(finalArray);
			
			//this new is basically just out of fear of deletion in future situations
			return new ArrayList<Integer>(finalArray);
		}
		
	}

	/**
	 * Genera un array di valori interi casuali, di somma e dimensione prefissate
	 * @param size è la dimensione che si richiede abbia l'array generato
	 * @param rangeMaximum è il massimo dell'intervallo di casualità in cui i numeri vengono generati, l'intervallo è sempre del tipo [-rangeMaximum;+rangeMaximum] con rangeMaximum>0 (viene cambiato di segno se negativo)
	 * @param sum è la somma che si richiede abbiano gli elementi dell'array
	 * @return ritorna l'array coi parametri richiesti
	 */
	public static int[] generateCommonArrayWithFixedSum(int size,int rangeMaximum,int sum){
		int[] values=ManipolaArray.convertToCommonArrayInt(generateArrayListWithFixedSum(size,rangeMaximum,sum));
		
		return values;
	}


}
