package battleship;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.util.Random;
public class Battleship
{
	public static void main(String[] args) throws FileNotFoundException 
	{
            int main_seleccion,inicial_seleccion;
            Scanner scan = new Scanner(System.in);
            boolean menu_main = true,menu_inicial = true;
            Players loggedin_player = new Players();

		System.out.println("\n\n\t\t~~~~~~~~ BattleShip ~~~~~~~~\n");
		//Menu Inicial
		menu:
		while(menu_inicial)
		{
			
			try
			{
				System.out.println("Menu Inicial \n\n");
				System.out.println("Opciones de menu: \n1)Login\n2)Crear Jugador Nuevo\n3)Salir");
				System.out.print("\nSeleccione una opción del menu: ");
				inicial_seleccion = scan.nextInt();
			}
			catch (InputMismatchException e) 
			{
   				System.out.println("\nSelección inválida.\n");
   				scan.nextLine();
   				continue;
			}

			switch (inicial_seleccion)
			{
				case 1://login
                                    menu_inicial = loggedin_player.login(scan);
                                    if (menu_inicial)
                                        continue menu;
                                    else
                                         break;

				case 2://crear jugador nuevo
                                    menu_inicial = loggedin_player.createNewPlayer(scan);
                                    if (menu_inicial)
                                         continue menu;
                                    else
                                         break;
					
				case 3://exit
					System.out.println("bye desde menu_inicial");
					System.exit(0);

				default:
				System.out.println("\nSelección inválida.\n");
			}
		}
		//fin menu inicial

		//Menu Principal
		while(menu_main)
		{
			
			try
			{
				System.out.println("Menu Principal \n\n");
				System.out.println("Opciones de menu: \n1)Jugar BattleShip\n2)Configuración\n3)Reportes\n4)Mi Perfil\n5)Salir");
				System.out.print("\nSeleccione una opción del menu: ");
				main_seleccion = scan.nextInt();
			}catch (InputMismatchException e) {
   				System.out.println("\nSelección inválida.\n");
   				scan.nextLine();
   				continue;
			}

			switch (main_seleccion)
			{
				case 1:
				//menu_main = false;
				//System.out.println("Aqui va el juego");
				//Game
				game();
				break;

				case 2:
				//menu_main = false;
				System.out.println("Configuración");
				break;

				case 3:
				//menu_main = false;
				System.out.println("Reportes");
				break;

				case 4:
				//menu_main = false;
				System.out.println("mi Perfil");
				break;

				case 5:
				System.out.println("bye desde menu_main");
				menu_main = false;
				System.exit(0);

				default:
				System.out.println("\nSelección inválida.\n");
			}
		}	
		//fin menu principal
	}

	private static void game()
	{	//filas, luego columnas
		//char tablero_visual[][] = new char[8][8];//tablero que ve el jugador
		char tablero[][] = new char[8][8];//tablero del oponente
		Scanner scan = new Scanner(System.in);
		Random rng_jesus = new Random();
		int isBoat = 0;
		int boatCount = 0;
		boolean win = false;
		boolean devMode = false;

		System.out.print("\nModo de developer activado? (y/n): ");//imprime el tablero oculto
		char devChoice = scan.next().charAt(0);
		System.out.println();
		System.out.print("Ingrese las Posiciones de los Barcos\n");
		System.out.println();
		if (devChoice == 'y')
			devMode = true;

		do{		
				//Por ahora todos los abarcos tienen que ingresarse con la variable 'a'
				System.out.print("Ingrese la Fila: ");
				int contador_v = scan.nextInt()-1;
				System.out.print("Ingrese la Columna: ");
				int contador_h = scan.nextInt()-1;
				System.out.print("Ingrese el nombre del barco: ");
				char nombre = scan.next().charAt(0);

				if(contador_v>=0 && contador_v<8 && contador_h>=0 && contador_h<8){
					if(tablero[contador_v][contador_h] == nombre){
						System.out.print("Posicion Ocupada");
					}
					else{
						tablero[contador_v][contador_h] = nombre;
						System.out.println("Posicion Disponible y Colocada\n");
						boatCount++;
					}
			}
			else{
				System.out.println("Posision incorrecta");
			}	
		}while(boatCount<5);	


		do
		{
			System.out.println("    1 2 3 4 5 6 7 8\n");

			for (int contador_v = 0; contador_v < 8 ; contador_v++)
			{
				
				System.out.print((contador_v+1)+"   ");

				for(int contador_h = 0;contador_h < 8; contador_h++)
				{	
					
					if (tablero[contador_v][contador_h]=='H')
						System.out.print("x");
					else if (tablero[contador_v][contador_h]=='M')
						System.out.print("-");
					else
						System.out.print("~");
					System.out.print(" ");
				}
				System.out.println("");
			}

			if(devMode)
			{
				System.out.println();
				System.out.println("    1 2 3 4 5 6 7 8\n");
					
				for (int contador_v = 0; contador_v < 8 ; contador_v++)
				{
					System.out.print((contador_v+1)+"   ");

					for(int contador_h = 0; contador_h < 8; contador_h++)
					{
						System.out.print(tablero[contador_v][contador_h]);
						System.out.print(" ");
					}
					System.out.println("");
				}
			}

			System.out.print("\nEntre numero de fila: ");
			int fila = scan.nextInt() - 1;

			System.out.print("\nEntre numero de columna: ");
			int columna = scan.nextInt() - 1;	

			
		
			if (tablero[fila][columna] == 'a' )
			{
				//tablero_visual[fila][columna] = 'x';
				tablero[fila][columna] = 'H';
				
			}

			else if (tablero[fila][columna]=='M')
				System.out.println("\nCasilla ya atacada.\n");
			else
			{
				tablero[fila][columna]='M';
				//tablero_visual[fila][columna] = '-';
			}

			for (int contador_v= 0; contador_v < 8 ; contador_v++)
			{
				for(int contador_h= 0;contador_h< 8; contador_h++)
				{
					if (tablero[contador_v][contador_h] == 'a')
					{
						win = false;
						break;
					}

					else 
						win = true; 
					
				}
				if (win == false)
				break;
			}


		
		}while (win == false);



		System.out.println("\nBye.");
	}
}
