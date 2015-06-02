import java.util.Scanner;
import java.util.Random;
public class Acorazado
{
	public static void main(String[] args) 
	{	//filas, luego columnas
		//char tablero_visual[][] = new char[8][8];//tablero que ve el jugador
		int tablero[][] = new int[8][8];//tablero del oponente
		Scanner scan = new Scanner(System.in);
		Random rng_jesus = new Random();
		int isBoat = 0;
		int boatCount = 5;
		boolean win = false;
		boolean devMode = false;

		System.out.print("\nModo de developer activado? (y/n): ");
		char devChoice = scan.next().charAt(0);
		if (devChoice == 'y')
			devMode = true;

		for (int contador_v = 0; contador_v < 8; contador_v++)
		{
			for(int contador_h = 0;contador_h < 8; contador_h++)
			{
				//tablero_visual[contador_v][contador_h] = '~';
				isBoat = rng_jesus.nextInt(10);

				if (isBoat < 9)
					tablero[contador_v][contador_h] = 0;
				else if (boatCount > 0)
				{
					tablero[contador_v][contador_h] = 9;
					boatCount--;
				}
			}
		}

		do
		{
				
			
			for (int contador_v = 0; contador_v < 8 ; contador_v++)
			{
				for(int contador_h = 0;contador_h < 8; contador_h++)
				{	
					if (tablero[contador_v][contador_h] == 3)
						System.out.print("x");
					else if (tablero[contador_v][contador_h] == 2)
						System.out.print("-");
					else
						System.out.print("~");
					System.out.print(" ");
				}
				System.out.println("");
			}

			if(devMode)
			{
				for (int contador_v = 0; contador_v < 8 ; contador_v++)
				{
					for(int contador_h = 0;contador_h < 8; contador_h++)
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

		
			if (tablero[fila][columna] == 9)
			{
				//tablero_visual[fila][columna] = 'x';
				tablero[fila][columna] = 3;
				
			}

			else if (tablero[fila][columna] == 2)
				System.out.println("\nCasilla ya atacada.\n");
			else
			{
				tablero[fila][columna] = 2;
				//tablero_visual[fila][columna] = '-';
			}

			for (int contador_v = 0; contador_v < 8 ; contador_v++)
			{
				for(int contador_h = 0;contador_h < 8; contador_h++)
				{
					if (tablero[contador_v][contador_h] == 9)
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