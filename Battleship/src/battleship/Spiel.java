package battleship;

import java.util.Random;
import java.util.Scanner;

public class Spiel
{

    //Attributes
    public static int winner;
    public static Boat tablero1[][] = new Boat[8][8];
    public static Boat tablero2[][] = new Boat[8][8];
    public static boolean devMode = false;
    public static boolean lose = false;

    //Methods
    public static void game()
    {

        Scanner scan = new Scanner(System.in);
        Random rng_jesus = new Random();
        int isBoat = 0;
        int p1_boatCount = 0;
        int p2_boatCount = 0;
        lose = false;

        System.out.print("\nModo de developer activado? (y/n): ");//imprime el tablero oculto
        char devChoice = scan.next().charAt(0);
        if (devChoice == 'y')
        {
            devMode = true;
        }
        else
            devMode = false;
        p1_setBoats(scan, p1_boatCount);
        p2_setBoats(scan, p2_boatCount);
        do
        {
            System.out.println();

            p2_printTablero();
            p1_attack(scan);
            p2_printTablero();
            p2_checkIfLose();
            if (lose == true)
            {
                winner = 1;
                break;
                        
            }
            pause();
            
            p1_printTablero();
            p2_attack(scan);
            p1_printTablero();
            p1_checkIfLose();
            if (lose == true)
            {
                winner = 2;
                break;
            }
            pause();

        } while (lose == false);
        System.out.println("Gana jugador "+winner);

    }

    public static void p1_setBoats(Scanner scan, int p1_boatCount)
    {
        
        System.out.print("Player 1, ingrese las Posiciones de los Barcos\n");
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
            System.out.println("Barcos Puestos: " + p1_boatCount);
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
                        p1_boatCount++;
                        break;
                    case "a":
                        tablero1[contador_v][contador_h] = new Boat('a');
                        System.out.println("Posicion Disponible, Acorazado Colocado\n");
                        p1_boatCount++;
                        break;
                    case "s":
                        tablero1[contador_v][contador_h] = new Boat('s');
                        System.out.println("Posicion Disponible, Submarino Colocado\n");
                        p1_boatCount++;
                        break;
                    case "d":
                        tablero1[contador_v][contador_h] = new Boat('d');
                        System.out.println("Posicion Disponible, Destructor Colocado\n");
                        p1_boatCount++;
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

        } while (p1_boatCount < 5);
        //return (tablero1);
    }
    
    public static void p2_setBoats(Scanner scan, int p2_boatCount)
    {
        
        System.out.print("Player 2, ingrese las Posiciones de los Barcos\n");
        System.out.println();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                tablero2[i][j] = new Boat('~');
            }
        }
        do
        {

            // Boat tablero[][] = new Boat[8][8];//tablero del oponente
            System.out.println("Barcos Puestos: " + p2_boatCount);
            System.out.print("Ingrese la Fila: ");
            int contador_v = scan.nextInt() - 1;
            System.out.print("Ingrese la Columna: ");
            int contador_h = scan.nextInt() - 1;
            System.out.print("Ingrese el nombre del barco: ");
            String nombre = scan.next().toLowerCase();

            if (contador_v >= 0 && contador_v < 8 && contador_h >= 0 && contador_h < 8)
            {
                if (tablero2[contador_v][contador_h].boatType.equals("Mar"))
                {
                switch (nombre)
                {
                    case "p":
                        tablero2[contador_v][contador_h] = new Boat('p');
                        System.out.println("Posicion Disponible, Portaaviones Colocado\n");
                        p2_boatCount++;
                        break;
                    case "a":
                        tablero2[contador_v][contador_h] = new Boat('a');
                        System.out.println("Posicion Disponible, Acorazado Colocado\n");
                        p2_boatCount++;
                        break;
                    case "s":
                        tablero2[contador_v][contador_h] = new Boat('s');
                        System.out.println("Posicion Disponible, Submarino Colocado\n");
                        p2_boatCount++;
                        break;
                    case "d":
                        tablero2[contador_v][contador_h] = new Boat('d');
                        System.out.println("Posicion Disponible, Destructor Colocado\n");
                       p2_boatCount++;
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

        } while (p2_boatCount < 5);
        //return (tablero1);
    }

    public static void p1_printTablero()
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
    
    public static void p2_printTablero()
    {
        System.out.println("    1 2 3 4 5 6 7 8\n");

        for (int contador_v = 0; contador_v < 8; contador_v++)
        {

            System.out.print((contador_v + 1) + "   ");

            for (int contador_h = 0; contador_h < 8; contador_h++)
            {

                if (tablero2[contador_v][contador_h].isDead == true)
                {
                    if (tablero2[contador_v][contador_h].boatType.equals("Miss"))
                    {
                        System.out.print("F");
                    } else
                    {
                        if (tablero2[contador_v][contador_h].boatType.equals("Mar"))
                        {
                            System.out.print("~");
                        } else
                        {
                            System.out.print("H");
                        }
                    }
                } else
                {
                    if (tablero2[contador_v][contador_h].wasHit == true)
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
                    if (tablero2[contador_v][contador_h].isDead)
                    {
                        switch (tablero2[contador_v][contador_h].boatType)
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
                        switch (tablero2[contador_v][contador_h].boatType)
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

    public static void p1_attack(Scanner scan)
    {
        System.out.println("Ataca Player 1.");
        System.out.print("\nEntre numero de fila: ");
        int fila = scan.nextInt() - 1;

        System.out.print("\nEntre numero de columna: ");
        int columna = scan.nextInt() - 1;

        if (tablero2[fila][columna].boatType.equals("Miss"))// && !tablero1[fila][columna].equals("miss"))
        //tablero_visual[fila][columna] = 'x';
        //tablero1[fila][columna] = 'H';
        {
            System.out.println("\nCasilla ya atacada.\n");
        } else
        {
            tablero2[fila][columna].getHit();
        }

        //return (tablero1);
    }
    
    public static void p2_attack(Scanner scan)
    {
        System.out.println("Ataca Player 2.");
        System.out.print("\nEntre numero de fila: ");
        int fila = scan.nextInt() - 1;

        System.out.print("\nEntre numero de columna: ");
        int columna = scan.nextInt() - 1;

        if (tablero1[fila][columna].boatType.equals("Miss"))
            System.out.println("\nCasilla ya atacada.\n");
        else
            tablero1[fila][columna].getHit();
        

        //return (tablero1);
    }

    public static void p1_checkIfLose()
    {
        for (int contador_v = 0; contador_v < 8; contador_v++)
        {
            for (int contador_h = 0; contador_h < 8; contador_h++)
            {
                if (tablero1[contador_v][contador_h].isDead == true)
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
    
    public static void p2_checkIfLose()
    {
        for (int contador_v = 0; contador_v < 8; contador_v++)
        {
            for (int contador_h = 0; contador_h < 8; contador_h++)
            {
                if (tablero2[contador_v][contador_h].isDead == true)
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
        }  
        catch(Exception e)
        {}  
 }
}
