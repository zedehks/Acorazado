package battleship;

import java.util.Random;
import java.util.Scanner;

public class Spiel
{

    //Attributes
    public static int winner;
    public static int player;//player 1 = 0, player 2 = 1
    public static Boat tableros[][][] = new Boat[2][8][8];
    public static boolean devMode = false;
    public static boolean lose = false;

    //Methods
    public static void game()
    {

        Scanner scan = new Scanner(System.in);
        Random rng_jesus = new Random();
        int isBoat = 0;
        int boatCount = 0;

        lose = false;

        System.out.print("\nModo de developer activado? (y/n): ");//imprime el tablero oculto
        char devChoice = scan.next().charAt(0);
        if (devChoice == 'y')
        {
            devMode = true;
        } else
        {
            devMode = false;
        }
        player = 0;// player 1
        setBoats(scan, boatCount);
        player = 1;//player 2. Confusing? yep :(
        setBoats(scan, boatCount);

        do
        {
            System.out.println();

            player = 0;// turno de player 1
            switchPlayer();//cambia a jugador 2
            printTablero();//imprime su tablero

            switchPlayer();//cambia a 1
            attack(scan);//ataca 1, hay un switch adentro, termina aun con 1

            switchPlayer();// cambia a 2
            printTablero();//imprime su tablero
            checkIfLose();//revisa si 2 ha perdido
            if (lose == true)// si 2 perdió...
            {
                winner = 1;//gana 1
                break;

            }
            pause();

            player = 1;//turno de player 2
            switchPlayer();//cambia a 1
            printTablero();//imprime su tablero

            switchPlayer();// cambia a 2
            attack(scan);//attaca 2, switch adentro, termina 2

            switchPlayer();//cambia a 1
            printTablero();//imprime su tablero
            checkIfLose();//revisa si 1 ha perdido
            if (lose == true)//si 1 perdió,
            {
                winner = 2;// gana 2
                break;
            }
            pause();

        } while (lose == false);
        System.out.println("Gana jugador " + winner);

    }

    public static void setBoats(Scanner scan, int boatCount)
    {
        boatCount = 0;
        System.out.print("Player " + (player + 1) + ", ingrese las Posiciones de los Barcos\n");
        System.out.println();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                tableros[player][i][j] = new Boat('~');
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
                if (tableros[player][contador_v][contador_h].boatType.equals("Mar"))
                {
                    switch (nombre)
                    {
                        case "p":
                            tableros[player][contador_v][contador_h] = new Boat('p');
                            System.out.println("Posicion Disponible, Portaaviones Colocado\n");
                            boatCount++;
                            break;
                        case "a":
                            tableros[player][contador_v][contador_h] = new Boat('a');
                            System.out.println("Posicion Disponible, Acorazado Colocado\n");
                            boatCount++;
                            break;
                        case "s":
                            tableros[player][contador_v][contador_h] = new Boat('s');
                            System.out.println("Posicion Disponible, Submarino Colocado\n");
                            boatCount++;
                            break;
                        case "d":
                            tableros[player][contador_v][contador_h] = new Boat('d');
                            System.out.println("Posicion Disponible, Destructor Colocado\n");
                            boatCount++;
                            break;
                        default:
                            System.out.print("Barco Inválido\n");

                    }
                } else
                {
                    System.out.println("Posición Ocupada");
                }
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

                if (tableros[player][contador_v][contador_h].isDead == true)
                {
                    if (tableros[player][contador_v][contador_h].boatType.equals("Miss"))
                    {
                        System.out.print("F");
                    } else
                    {
                        if (tableros[player][contador_v][contador_h].boatType.equals("Mar"))
                        {
                            System.out.print("~");
                        } else
                        {
                            System.out.print("H");
                        }
                    }
                } else
                {
                    if (tableros[player][contador_v][contador_h].wasHit == true)
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
                    if (tableros[player][contador_v][contador_h].isDead)
                    {
                        switch (tableros[player][contador_v][contador_h].boatType)
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
                        switch (tableros[player][contador_v][contador_h].boatType)
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
        int fila, columna;
        do
        {
            System.out.println("Ataca Player " + (player + 1) + ".");//jugador atacante
            System.out.print("\nEntre numero de fila: ");
            fila = scan.nextInt() - 1;

            System.out.print("\nEntre numero de columna: ");
            columna = scan.nextInt() - 1;
            if (fila >= 0 && fila < 8 && columna >= 0 && columna < 8)
            {
                break;
            } else
            {
                System.out.println("Coordenadas inválidas.\n");
            }
        } while (true);

        switchPlayer();//cambia jugador a atacado
        if (tableros[player][fila][columna].boatType.equals("Miss"))
        {
            System.out.println("\nCasilla ya atacada.\n");
        } else
        {
            tableros[player][fila][columna].getHit();
        }
        switchPlayer();//cambia jugador al atacante
    }

    public static void checkIfLose()
    {
        for (int contador_v = 0; contador_v < 8; contador_v++)
        {
            for (int contador_h = 0; contador_h < 8; contador_h++)
            {
                if (tableros[player][contador_v][contador_h].isDead == true)
                {
                    lose = true;
                } else
                {
                    lose = false;
                    break;
                }

            }
            if (lose == false)
            {
                break;
            }
        }
    }

    //tomado de: http://stackoverflow.com/questions/19870467/how-do-i-get-press-any-key-to-continue-to-work-in-my-java-code
    public static void pause()
    {
        System.out.println("Presione \"enter\" para continuar...");
        try
        {
            System.in.read();
        } catch (Exception e)
        {
        }
    }

    public static void switchPlayer()
    {
        switch (player)
        {
            case 0:
                player = 1;
                break;
            case 1:
                player = 0;
                break;

        }
    }
}
