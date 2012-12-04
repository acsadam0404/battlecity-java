package components.animation;

public class test {
	public static void main(String[] args) {
	    int n = 5;
		int[] bevetel = new int[n];
	    int[] kiadas = new int[n];
	    bevetel[0] = 100;
	    bevetel[1] = 100;
	    bevetel[2] = 0;
	    bevetel[3] = 0;
	    bevetel[4] = 100;
	    
	    kiadas[0] = 50;
	    kiadas[1] = 50;
	    kiadas[2] = 100;
	    kiadas[3] = 50;
	    kiadas[4] = 0;
		
		
		
	    int sumPenz = 0;
	    int sumBevetel = 0;
	    int sumKiadas = 0;

	    int sum = 0;
	    int melyikNaponVolt = 0;
	    for (int i = 0; i < n; i++)
	    {
	        sumBevetel += bevetel[i];
	        sumKiadas += kiadas[i];
	        sum = sumBevetel - sumKiadas;
	        if (sumPenz < sum) {
	            sumPenz = sum;
	            melyikNaponVolt = i + 1; /* i + 1 ,mert a tömb nullától indexel, de nekünk rendes sorszám kell*/
	        }
	    }
	    
	    System.out.println(melyikNaponVolt);
	    System.out.println(sumPenz);
	}
}
