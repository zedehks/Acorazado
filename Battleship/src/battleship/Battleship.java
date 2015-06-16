package battleship;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;

public class Battleship
{

    public static void main(String[] args) throws FileNotFoundException
    {
        int main_seleccion, inicial_seleccion;
        Scanner scan = new Scanner(System.in);
        boolean menu_main = true, menu_inicial = true;
        Players loggedin_player = new Players();

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
                    menu_inicial = loggedin_player.login(scan);
                    if (menu_inicial)
                    {
                        continue menu;
                    } else
                    {
                        break;
                    }

                case 2://crear jugador nuevo
                    menu_inicial = loggedin_player.createNewPlayer(scan);
                    if (menu_inicial)
                    {
                        continue menu;
                    } else
                    {
                        break;
                    }

                case 3://exit

                    System.out.println("bye desde menu_inicial");
                    System.exit(0);

                default:

                    System.out.println("\nSelección inválida.\n");
            }
        }
		//fin menu inicial

        //Menu Principal
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
