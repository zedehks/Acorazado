import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
public class BattleshipMenus
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		int main_seleccion,inicial_seleccion;
		Scanner scan = new Scanner(System.in);
		boolean menu_main = true,menu_inicial = true;

		//String usuarios[] = new String[];

		System.out.println("\n\n\t\t~~~~~~~~ BattleShip ~~~~~~~~\n");
		//Menu Inicial
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
				case 1:
					menu_inicial = false;
					System.out.println("supongamos que el login funciona…");
					break;

				case 2:
					menu_inicial = false;
					String usuario[] = new String[2];
					System.out.print("\nNombre: ");
					usuario[0] = scan.next();
					System.out.print("\nContraseña: ");
					usuario[1] = scan.next();

					
					try 
					{
	    	  			File savefile = new File("players.sav");
 	    				if(!savefile.exists())
 	    				{
 	    					System.out.println("Archivo de almacenaje creado.");
    						savefile.createNewFile();
    					}

    					FileWriter fileWritter = new FileWriter(savefile,true);
    	      	  		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	     	  		bufferWritter.write(usuario[0]+"\t");
    	     	  		bufferWritter.write(usuario[1]+"\n");
    	     	   		bufferWritter.close();
	 
 			   		} 
   	 				catch (IOException e)
   	 				{
	     		    	e.printStackTrace();
					}

					


					break;

				case 3:
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
				System.out.println("Aqui va el juego");
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
}