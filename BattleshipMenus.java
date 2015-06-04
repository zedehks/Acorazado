import java.util.Scanner;
import java.io.FileWriter;
//import java.io.BufferedWriter;
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
		File savefile = new File("players.sav");
		String user, pass;

		//String usuarios[] = new String[];

		//outer:
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

	    	  			//si no existe savefile...
 	    				if(!savefile.exists())
 	    				{
 	    					System.out.println("\nArchivo de almacenaje no existe.\nCree un jugador nuevo primero.\n-----\n");
 	    					menu_inicial = true;
 	    					continue menu;
    					}

					menu_inicial = false;
					System.out.println("\n\nLogin\n~~~");
					while(true)
					{

						System.out.print("\nIngrese su username (o entre \"cancel\" para cancelar login): ");
						user = scan.next();
						Scanner txt_checker = new Scanner(savefile);
						if (user.equals("cancel"))
						{
							menu_inicial = true;
							continue menu;
						}
						boolean login = false;
					

						while (txt_checker.hasNextLine())
						{
							if(user.equals(txt_checker.nextLine()))
							{
								System.out.print("\nIngrese su contraseña: ");
								pass = "*"+scan.next();
								if (pass.equals(txt_checker.nextLine()))
								{
									System.out.println("Login valido. Bienvenido.\n\n~~~~~~~~~");
									login = true;
									break;
								}

							}
						}

						if (login)
							break;
						else
							System.out.println("Login inválido");
					}



					

					break;

				case 2://crear jugador nuevo
					menu_inicial = false;
					boolean usuario_nuevo = true, first_run = false;
					String usuario[] = new String[2];
					

					do
					{
						System.out.println("\n\nCrear Jugador Nuevo\n~~~");
						System.out.print("\nUsername Nuevo: (o entre \"cancel\" para cancelar): ");
						user = scan.next();

						if (user.equals("cancel"))
						{
							menu_inicial = true;
							continue menu;
						}
						try
						{
							if(!savefile.exists())
 	    					{
 	    						//System.out.println("\nArchivo de almacenaje creado.");
    							savefile.createNewFile();
    							first_run = true;
   							}
   						}
   						catch (IOException e)
   	 					{
	     		    		e.printStackTrace();
						}

						Scanner txt_checker = new Scanner(savefile);
						while (txt_checker.hasNextLine())
							{
								if(user.equals(txt_checker.nextLine()))
								{
									System.out.print("\nUsuario ya existente.");
									usuario_nuevo = false;
									break;		
								}
								else
									usuario_nuevo = true;
							}
					}while (usuario_nuevo == false);

					System.out.print("\nContraseña: ");
					pass = scan.next();

					
					try 
					{
    					FileWriter fileWritter = new FileWriter(savefile,true);
    	      	  		//BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	     	  		fileWritter.write(user+"\n");
    	     	  		fileWritter.write("*"+pass+"\n_\n");
    	     	   		fileWritter.close();
    	     	   		if(first_run)
 	    				{
 	    					System.out.println("\nArchivo de almacenaje creado.");
    						//savefile.createNewFile();
   						}
    	     	   		System.out.println("\nJugador nuevo creado.\n\n~~~~~~~~~");
    	     	   		
	 
 			   		} 
   	 				catch (IOException e)
   	 				{
	     		    	e.printStackTrace();
					}

					
					menu_inicial = true;
					inicial_seleccion = 0;
					continue menu;

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