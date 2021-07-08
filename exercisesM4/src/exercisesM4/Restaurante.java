package exercisesM4;

import java.util.*;

public class Restaurante {
	
	static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		
		String plats[]=new String[10];
		int preus[]=new int[10];
		
		int preu_total=0;
		
		//monedas
		int m1, m2;
		//billetes
		int b5, b10, b20, b50, b100, b200, b500;
		
		//lista comida seleccionada
		List<String> seleccion= new ArrayList<String>();		
		
		
		//llenar array preus
		int contador=0;
		
		HashMap<String, Integer> preuPlat=new HashMap<String, Integer>();
		
		preuPlat.put("Bolognese", 15);
		preuPlat.put("4 Formaggi", 10);
		preuPlat.put("Calabrese", 11);
		preuPlat.put("Rustica", 9);
		preuPlat.put("Carbonara", 12);
		preuPlat.put("Pesto", 11);
		preuPlat.put("Napolitana", 12);
		preuPlat.put("Alla Norma", 14);
		preuPlat.put("Ragù", 13);
		preuPlat.put("Salami", 15);
		
		for(Map.Entry<String, Integer> entrada:preuPlat.entrySet()) {
			
			plats[contador]=entrada.getKey();
			preus[contador]=entrada.getValue();
			contador++;
		}
				
		//opción del menu		
		int opcion=0;	
		
		//opcion de salir del menú
		int salir = 0;	
		
		//evalúa si pide o no
		boolean exit = false;
		
		
		System.out.println("=======================================");	
		
		System.out.println("  Bienvenido a la pizzeria La Secilia");
		
		System.out.println("=======================================");
		
		
		while(exit!=true) {
			
			try {			
			
				//Preguntar si quiere seguir pidiendo		
				System.out.println("Pedir: [1]Si / [0]No");
				salir=sc.nextInt();			
				
				if(salir==0) {
					
					exit=true;				
				}
				
				if(salir==1) {
					
					try {
					//mostrar el menú		
					opcion=mostrarMenu(plats,preus);
					}catch(InputMismatchException e) {
						System.out.println("La opción no existe en el menú");
						sc.next();
					}
					
					if(opcion<=10 && opcion>=1) {	
												
						//guardar la seleccion en la lista
						String	plato_seleccionado=plats[opcion-1];
						
						seleccion.add(plato_seleccionado);
						
						//comprobar si la lista<seleccion> con el contenido del array plats	
						int indice=comprobarPedido(seleccion, plats);		
						
						if(indice!=-1) {
							//sumar precios
							preu_total+=preus[indice];																	
						}
						else {
							System.out.println("El producto pedido no existe");
						}	
						
					}else {
						System.out.println("Selecciona una opción de 1 a 10");
					}
																								
					
					//System.out.println(seleccion);				
				
				}
				
				if(salir>1 || salir<0) {
					System.out.println("Seleccione [1] para pedir/ [0] para salir");
				}
								
			
			}catch(InputMismatchException e) {
				System.out.println("Opcion incorrecta");
				sc.next();
			}
		}//while	
		
		//mostrar el precio total del pedido
		if(preu_total>0) {
			
			System.out.println("El precio total de su cuenta es: " + preu_total + " euros");
			
			System.out.println();
			
			//desglose del precio total
			if(preu_total >=1 && preu_total<=500) {
				
				b500=preu_total/500;
				System.out.println(b500 + " billetes de 500");
				preu_total=preu_total%500;
				
				b200=preu_total/200;
				System.out.println(b200 + " billetes de 200");
				preu_total=preu_total%200;
				
				b100=preu_total/100;
				System.out.println(b100 + " billetes de 100");
				preu_total=preu_total%100;
				
				b50=preu_total/50;
				System.out.println(b50 + " billetes de 50");
				preu_total=preu_total%50;
				
				b20=preu_total/20;
				System.out.println(b20 + " billetes de 20");
				preu_total=preu_total%20;
				
				b10=preu_total/10;
				System.out.println(b10 + " billetes de 10");
				preu_total=preu_total%10;
				
				b5=preu_total/5;
				System.out.println(b5 + " billetes de 5");
				preu_total=preu_total%5;
				
				m2=preu_total/2;
				System.out.println(m2 + " monedas de 2");
				preu_total=preu_total%2;
				
				m1=preu_total/1;
				System.out.println(m1 + " monedas de 1");					
				
			}else {
				
				System.out.println("Esa cantidad no está disponible");
			}
			
		}	
		
	}	
	
	
	private static int mostrarMenu(String[] plats, int[] preus){
			
		System.out.println("1. " + plats[0] + " -- " + preus[0]);
		System.out.println("2. " + plats[1] + " -- " + preus[1]);
		System.out.println("3. " + plats[2] + " -- "  + preus[2]);
		System.out.println("4. " + plats[3] + " -- "  + preus[3]);
		System.out.println("5. " + plats[4] + " -- "  + preus[4]);
		System.out.println("6. " + plats[5] + " -- "  + preus[5]);
		System.out.println("7. " + plats[6] + " -- "  + preus[6]);
		System.out.println("8. " + plats[7] + " -- "  + preus[7]);
		System.out.println("9. " + plats[8] + " -- "  + preus[8]);
		System.out.println("10. " + plats[9] + " -- "  + preus[9]);
		
		System.out.println("Escoge las opciones del menú:");
		int op=sc.nextInt();
				
		return op;		
	}
	
	//método comparar la lista<seleccion> con el contenido del array plats
	private static int comprobarPedido(List<String>seleccion, String plats[]) {
		
		int indice = 0;
		
		List<String>listaPlatos=Arrays.asList(plats);
		
		for(String i: seleccion) {
			
			indice=listaPlatos.indexOf(i);				
			
		}			
		
		return indice;		
	}

}
