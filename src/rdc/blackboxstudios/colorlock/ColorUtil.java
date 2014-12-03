package rdc.blackboxstudios.colorlock;
import java.util.Random;


public class ColorUtil {
	
	
	private String [] repositorioColores = {"#0000CD", //azul
											"#00FF00", //lima
											"#00FFFF", //aqua
											"#FFD700", //oro
											"#FFFAFA", //snow
											"#FF0000"}; //rojo
	private int variedad = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorUtil util = new ColorUtil();
		util.setColorVariedad(5);
		
		
		String [] colores = util.getColores();
		for(String s : colores){
			System.out.println(s);
		}
		
	}
	
	public String[] getColores(){
		
		String [] colores = new String[this.variedad];
		final int [] numAzar = fillArrayEnteros();
		for(int i = 0 ; i < this.variedad ; i++){
			colores[i] = repositorioColores[numAzar[i]];
		}
		return colores;
	}
	
	private int [] fillArrayEnteros(){	
		
		int [] numAzar = new int [variedad];
		
			for(int i = 0; i < this.variedad ; i++){
				///si es la primer posicion del arreglo, no debiera haber problema 
				///el colocar cualquier numero generado al azar
				if(i == 0){
					numAzar[i] = getRandom();
				}
				else{
					int temp = getRandom();
					while(elNumeroExiste(temp, numAzar, i)){
						temp = getRandom();
					}
					numAzar[i] = temp;
				}
		
		}
		return numAzar;
	}
	
	private boolean elNumeroExiste(int candidato,
								   int [] lista,
								   int currentPosition){
		boolean existe = false;
		for(int i = 0; i < currentPosition ; i++){
			if(lista[i] == candidato){
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	private int getRandom(){
		Random r = new Random();
		int random = r.nextInt(this.variedad);
		return random;
	}
	
	public ColorUtil setColorVariedad(final int variedad){
		
		this.variedad = variedad;		
		return this;
	}
}
