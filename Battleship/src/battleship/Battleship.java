package battleship;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Battleship
{

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        /* for (Jugador player : Data.players)
         {
         player.isLoggedIn = false;
         }*/
        do
        {
            int main_seleccion, inicial_seleccion;
            Scanner scan = new Scanner(System.in);
            boolean menu_main = true, menu_inicial = true;
            //Players loggedin_player = new Players();

            System.out.println("\n\n\t\t~~~~~~~~ BattleShip ~~~~~~~~\n");
            //Menu Inicial
            menu:
            while (menu_inicial)
            {

                try
                {
                    System.out.println("Menu Inicial \n\n");
                    System.out.println("Opciones de menu: \n1)Login\n2)Crear Jugador Nuevo\n3)Salir");
                    System.out.print("\nSeleccione una opción del menu: ");
                    inicial_seleccion = scan.nextInt();
                } catch (InputMismatchException e)
                {

                    System.out.println("\nSelección inválida.\n");
                    scan.nextLine();
                    continue;
                }

                switch (inicial_seleccion)
                {
                    case 1://login
                        menu_inicial = false;
                        
                            //Data.deleteSaveFile();
                        menu_inicial = Data.saveFileExists(menu_inicial);
                        if (menu_inicial)
                        {
                            continue;
                        }

                        System.out.println("\nSavefile existe. Continuando...");

                        Data.startFileScanner();

                        int numberOfPlayers = Data.getPlayerNumbers();

                        Data.startPlayers(numberOfPlayers);

                        //System.out.println(Data.getPlayerNumbers() + " jugadores\n");
                        Data.fillPlayers(scan, Data.players.length);

                        //ignorar, usado para debugging.
                        //Data.printPlayers(scan, numberOfPlayers);
                        menu_inicial = Data.login(scan, numberOfPlayers);
                        if (!menu_inicial)
                        {
                            continue menu;
                        } else
                        {
                            break;
                        }

                    case 2://crear jugador nuevo

                        Jugador newPlayer = Data.createPlayer();
                        if (newPlayer == null)
                        {
                            continue;
                        }
                        newPlayer.isLoggedIn = true;
                        Data.makeSaveFile();
                        Data.startFileScanner();

                        //System.out.println("\nUsuario: " + newPlayer.usuario + "\nClave: " + newPlayer.clave + "\n");
                        Data.writeToFile(newPlayer);

                        Data.startFileScanner();

                        numberOfPlayers = Data.getPlayerNumbers();

                        Data.startPlayers(numberOfPlayers);

                        //System.out.println(Data.getPlayerNumbers()+ " jugadores\n");
                        Data.fillPlayers(scan, Data.getPlayerNumbers());
                        
                        Data.setLoggedIn(newPlayer);

                        menu_inicial = false;
                        continue;

                    case 3://exit

                        System.out.println("¡Hasta Pronto!");

                        System.exit(0);

                    default:

                        System.out.println("\nSelección inválida.\n");
                }
            }
		//fin menu inicial

            //Menu Principal
            //Data.checkIfLogin();
            menu_main:
            while (menu_main)
            {

                try
                {
                    System.out.println("Menu Principal \n\n");
                    System.out.println("Opciones de menu: \n1)Jugar BattleShip\n2)Configuración\n3)Reportes\n4)Mi Perfil\n5)Salir");
                    System.out.print("\nSeleccione una opción del menu: ");
                    main_seleccion = scan.nextInt();
                } catch (InputMismatchException e)
                {
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

                        Spiel.game();
                        break;

                    case 2:
                        config:
                        while (true)
                        {
                            //menu_main = false;
                            System.out.println("\nConfiguración\n");

                            System.out.println("a. Dificultad\nb. Modo de Juego\nc. Volver al Menú Principal\n");
                            System.out.print("Seleccione una opción de menu: ");
                            char mainchoice = scan.next().toLowerCase().charAt(0);

                            switch (mainchoice)
                            {
                                case 'a':
                                    while (true)
                                    {
                                        System.out.print("\nDificultad (Easy, Normal, Hard, Genius): ");
                                        char choice = scan.next().toLowerCase().charAt(0);
                                        Spiel.setDifficulty(choice);
                                        if (choice != 'e' && choice != 'n' && choice != 'h' && choice != 'g')
                                        {
                                            continue;
                                        }

                                        continue config;
                                    }

                                case 'b':
                                    while (true)
                                    {
                                        System.out.print("\nModo de juego (Arcade, Tutorial): ");
                                        char choice = scan.next().toLowerCase().charAt(0);
                                        Spiel.setTutorialMode(choice);
                                        if (choice != 't' && choice != 'a')
                                        {
                                            continue;
                                        }

                                        continue config;
                                    }
                                case 'c':
                                    continue menu_main;

                                default:
                                    System.out.println("\nOpción Inválida.\n");
                                    break;

                            }
                        }
                    //break;

                    case 3:
                        //menu_main = false;
                        while (true)
                        {
                            System.out.println("\nReportes\n");

                            System.out.println("a. 10 Últimos Juegos\nb. Ranking de Jugadores\nc. Regresar a Menú Inicial\n");
                            System.out.print("Seleccione una opción del menu: ");
                            char choice = scan.next().toLowerCase().charAt(0);

                            switch (choice)
                            {
                                case 'a':
                                    Spiel.printTop10();
                                    break;
                                case 'b':
                                    Data.getRank();
                                    break;
                                case 'c':
                                    continue menu_main;
                                default:
                                    System.out.println("\nOpción Inválida.\n");
                                    break;
                            }

                        }
                    case 4:
                        //menu_main = false;
                        while (true)
                        {
                            System.out.println("\nMi Perfil\n");

                            System.out.println("a. Datos\nb. Editar Datos\nc. Eliminar Cuenta\nd. Regresar a Menú Inicial\n");
                            System.out.print("Seleccione una opción del menu: ");
                            char choice = scan.next().toLowerCase().charAt(0);

                            switch (choice)
                            {
                                case 'a':
                                    Data.checkIfLogin();
                                    break;
                                case 'b':
                                    String usuario = "";
                                    int puntos = 0;
                                    start:
                                    while (true)
                                    {
                                        System.out.print("Ingrese su usuario nuevo (o deje el mísmo ): ");
                                        usuario = scan.next();

                                        for (int i = 0; i < Data.players.length; i++)
                                        {
                                            if (usuario.equals(Data.players[i].usuario) && !Data.players[i].isLoggedIn)
                                            {
                                                System.out.println("\nUsuario ya existe.\n");
                                                continue start;
                                            }
                                        }
                                        break;
                                    }

                                    for (int i = 0; i < Data.players.length; i++)
                                    {
                                        if (Data.players[i].isLoggedIn)
                                        {
                                            puntos = Data.players[i].puntos;
                                        }
                                    }

                                    System.out.print("Ingrese su clave nueva: ");
                                    String password = scan.next();

                                    Data.editloggedIn(usuario, password, puntos);

                                    break;
                                case 'c':
                                    
                                    Data.delloggedIn();
                                    //Data.deleteSaveFile();
                                    menu_main = false;
                                    continue menu_main;
                                case 'd':
                                    continue menu_main;
                                default:
                                    System.out.println("\nOpción Inválida.\n");
                                    break;
                            }
                        }
                    // break;

                    case 5:

                        System.out.println("\nRegresando al Menú Inicial...\n");
                        for (Jugador player : Data.players)
                        {
                            player.isLoggedIn = false;
                        }
                        Spiel.pause();
                        menu_main = false;
                        //continue menu;
                        break;
                    default:

                        System.out.println("\nSelección inválida.\n");
                }
            }
            //fin menu principal
        } while (true);
    }

}
