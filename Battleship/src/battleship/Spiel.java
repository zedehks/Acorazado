package battleship;

import java.util.Random;
import java.util.Scanner;

public class Spiel
{

    //Attributes
    public static int player;
    public static Boat tablero1[][] = new Boat[8][8];
    public static Boat tablero2[][] = new Boat[8][8];
    public static boolean devMode = false;
    public static boolean win = false;

    //Methods
    public static void game()
    {

        Scanner scan = new Scanner(System.in);
        Random rng_jesus = new Random();
        int isBoat = 0;
        int boatCount = 0;
        win = false;

        System.out.print("\nModo de developer activado? (y/n): ");//imprime el tablero oculto
        char devChoice = scan.next().charAt(0);
        if (devChoice == 'y')
        {
            devMode = true;
        }
        else
            devMode = false;
        setBoats(scan, boatCount);
        do
        {
            System.out.println();

            printTablero();
            attack(scan);
            checkIfWin();

        } while (win == false);
        System.out.println("\nBye.");

    }

    public static void setBoats(Scanner scan, int boatCount)
    {
        System.out.print("Ingrese las Posiciones de los Barcos\n");
        System.out.println();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                tablero1[i][j] = new Boat('~');
            }
        }
        do
        {

            // Boat tablero[][] = new Boat[8][8];//tablero del oponente
            System.out.println("Barcos Puestos: " + boatCount);
            System.out.print("Ingrese la Fila: ");
            int contador_v = scan.nextInt() - 1;
            System.out.print("Ingrese la Columna: ");
            int contador_h = scan.nextInt() - 1;
            System.out.print("Ingrese el nombre del barco: ");
            String nombre = scan.next().toLowerCase();

            if (contador_v >= 0 && contador_v < 8 && contador_h >= 0 && contador_h < 8)
            {
                if (tablero1[contador_v][contador_h].boatType.equals("Mar"))
                {
                switch (nombre)
                {
                    case "p":
                        tablero1[contador_v][contador_h] = new Boat('p');
                        System.out.println("Posicion Disponible, Portaaviones Colocado\n");
                        boatCount++;
                        break;
                    case "a":
                        tablero1[contador_v][contador_h] = new Boat('a');
                        System.out.println("Posicion Disponible, Acorazado Colocado\n");
                        boatCount++;
                        break;
                    case "s":
                        tablero1[contador_v][contador_h] = new Boat('s');
                        System.out.println("Posicion Disponible, Submarino Colocado\n");
                        boatCount++;
                        break;
                    case "d":
                        tablero1[contador_v][contador_h] = new Boat('d');
                        System.out.println("Posicion Disponible, Destructor Colocado\n");
                        boatCount++;
                        break;
                    default:
                        System.out.print("Barco Inválido\n");

                }
                }
                else
                    System.out.println("Posición Ocupada");
            } else
            {
                System.out.println("Coordenadas Inválidas");
            }

        } while (boatCount < 5);
        //return (tablero1);
    }

    public static void printTablero()
    {
        System.out.println("    1 2 3 4 5 6 7 8\n");

        for (int contador_v = 0; contador_v < 8; contador_v++)
        {

            System.out.print((contador_v + 1) + "   ");

            for (int contador_h = 0; contador_h < 8; contador_h++)
            {

                if (tablero1[contador_v][contador_h].isDead == true)
                {
                    if (tablero1[contador_v][contador_h].boatType.equals("Miss"))
                    {
                        System.out.print("F");
                    } else
                    {
                        if (tablero1[contador_v][contador_h].boatType.equals("Mar"))
                        {
                            System.out.print("~");
                        } else
                        {
                            System.out.print("H");
                        }
                    }
                } else
                {
                    if (tablero1[contador_v][contador_h].wasHit == true)
                    {
                        System.out.print("X");
                    } else
                    {
                        System.out.print("~");
                    }
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
        if (devMode)
        {
            System.out.println();
            System.out.println("    1 2 3 4 5 6 7 8\n");

            for (int contador_v = 0; contador_v < 8; contador_v++)
            {
                System.out.print((contador_v + 1) + "   ");

                for (int contador_h = 0; contador_h < 8; contador_h++)
                {
                    if (tablero1[contador_v][contador_h].isDead)
                    {
                        switch (tablero1[contador_v][contador_h].boatType)
                        {
                            case "Mar":
                                System.out.print(" ");
                                break;
                            case "Miss":
                                System.out.print("–");
                                break;
                            default:
                                System.out.print("H");
                        }
                    } else
                    {
                        switch (tablero1[contador_v][contador_h].boatType)
                        {
                            case "Miss":
                                System.out.print("W");
                                break;
                            case "Acorazado":
                                System.out.print("A");
                                break;
                            case "Portaaviones":
                                System.out.print("P");
                                break;
                            case "Destructor":
                                System.out.print("D");
                                break;
                            case "Submarino":
                                System.out.print("S");
                                break;
                        }
                    }

                    System.out.print(" ");
                }
                System.out.println("");
            }
        }
    }

    public static void attack(Scanner scan)
    {
        System.out.print("\nEntre numero de fila: ");
        int fila = scan.nextInt() - 1;

        System.out.print("\nEntre numero de columna: ");
        int columna = scan.nextInt() - 1;

        if (tablero1[fila][columna].boatType.equals("Miss"))// && !tablero1[fila][columna].equals("miss"))
        //tablero_visual[fila][columna] = 'x';
        //tablero1[fila][columna] = 'H';
        {
            System.out.println("\nCasilla ya atacada.\n");
        } else
        {
            tablero1[fila][columna].getHit();
        }

        //return (tablero1);
    }

    public static void checkIfWin()
    {
        for (int contador_v = 0; contador_v < 8; contador_v++)
        {
            for (int contador_h = 0; contador_h < 8; contador_h++)
            {
                if (tablero1[contador_v][contador_h].isDead == true)
                {
                    win = true;
                } else
                {
                    win = false;
                    break;
                }

            }
            if (win == false)
            {
                break;
            }
        }
    }
}
