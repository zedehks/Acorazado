package battleship;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Spiel
{

    //Attributes
    public static int winner = 0;
    public static int difficulty = 4;
    public static int player;//player 1 = 0, player 2 = 1
    public static Boat tableros[][][] = new Boat[2][8][8];
    public static boolean devMode = true;
    public static boolean lose = false;
    public static boolean mustScramble = false;
    public static Jugador oponentes[] = new Jugador[2];//controla los dos jugadores que juegan
    public static File gameslog = new File("games.list");

    //1 portaaviones, 1 acorazado, 1 submarino y 2 destructores
    //                                    P  A  S  D
    public static int availableBoats[] =
    {
        1, 1, 1, 2
    };

    //Methods
    public static void game() throws IOException//el "main", por asi decir
    {
        startGameLog();
        boolean cancel = false;
        Scanner scan = new Scanner(System.in);
        int pn = Data.getPlayerNumbers();
        if (pn == 1)
        {
            System.out.println("\nSolo existe un jugador. Cree otro jugador.\n");
            return;
        }
        int boatCount = 0;

        lose = false;

        cancel = setPlayers(scan, pn);
        if(cancel)
            return;

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

            if (lose == true)// si se retira...
            {
                return;
            }

            switchPlayer();// cambia a 2
            printTablero();
            checkIfLose();//revisa si 2 ha perdido
            if (lose == true)// si 2 perdió...
            {
                winner = 1;//gana 1
                break;
            }

            if (mustScramble)
            {
                shuffleTablero();
                cleanBoard();
            }

            //printTablero();//imprime su tablero
            pause();

            player = 1;//turno de player 2
            switchPlayer();//cambia a 1
            printTablero();//imprime su tablero

            switchPlayer();// cambia a 2
            attack(scan);//attaca 2, switch adentro, termina 2

            if (lose == true)// si se retira...
            {
                return;
            }

            switchPlayer();//cambia a 1
            printTablero();
            checkIfLose();//revisa si 1 ha perdido
            if (lose == true)//si 1 perdió,
            {
                winner = 2;// gana 2
                break;
            }
            pause();

            if (mustScramble)
            {

                shuffleTablero();
                cleanBoard();
            }
            //printTablero();//imprime su tablero

        } while (lose == false);
        System.out.println("Gana jugador " + winner);

        setWinner();
        recordGameVictory();

    }

    public static void setBoats(Scanner scan, int boatCount)//ponemos los barcos
    {
        for (int i = 0; i < 3; i++)
        {
            availableBoats[i] = 1;
        }
        availableBoats[3] = 2;
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
        setboat:
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
                            if (availableBoats[0] > 0)
                            {
                                tableros[player][contador_v][contador_h] = new Boat('p');
                                System.out.println("Posicion Disponible, Portaaviones Colocado\n");
                                boatCount++;
                                availableBoats[0] -= 1;
                            } else
                            {
                                System.out.println("Portaaviones ya puesto. Ingrese otro barco.\n");
                            }
                            break;
                        case "a":
                            if (availableBoats[1] > 0)
                            {
                                tableros[player][contador_v][contador_h] = new Boat('a');
                                System.out.println("Posicion Disponible, Acorazado Colocado\n");
                                boatCount++;
                                availableBoats[1] -= 1;
                            } else
                            {
                                System.out.println("Acorazado ya puesto. Ingrese otro barco.\n");
                            }
                            break;
                        case "s":
                            if (availableBoats[2] > 0)
                            {
                                tableros[player][contador_v][contador_h] = new Boat('s');
                                System.out.println("Posicion Disponible, Submarino Colocado\n");
                                boatCount++;
                                availableBoats[2] -= 1;
                            } else
                            {
                                System.out.println("Submarino ya puesto. Ingrese otro barco.\n");
                            }
                            break;
                        case "d":
                            if (availableBoats[3] > 0)
                            {
                                if (difficulty == 5)
                                {
                                    tableros[player][contador_v][contador_h] = new Boat('d');
                                    System.out.println("Posicion Disponible, Destructor Colocado\n");
                                    boatCount++;
                                    availableBoats[3] -= 1;
                                } else
                                {
                                    tableros[player][contador_v][contador_h] = new Boat('d');
                                    System.out.println("Posicion Disponible, Destructor Colocado\n");
                                    boatCount++;
                                    availableBoats[3] = 0;
                                }
                            } else
                            {
                                System.out.println("Destructores ya puestos. Ingrese otro barco.\n");
                            }
                            break;
                        default:
                            System.out.print("Barco Inválido\n");

                    }
                } else
                {
                    System.out.println("Posición Ocupada.\n");
                }
            } else
            {
                System.out.println("Coordenadas Inválidas.\n");
            }

        } while (boatCount < difficulty);
        //return (tablero1);
    }

    public static void printTablero()//imprimimos el tablero del usuario activo
    {
        System.out.println("    1 2 3 4 5 6 7 8\n");

        for (int contador_v = 0; contador_v < 8; contador_v++)
        {

            System.out.print((contador_v + 1) + "   ");

            for (int contador_h = 0; contador_h < 8; contador_h++)
            {

                if (tableros[player][contador_v][contador_h].isBoat == false)
                {
                    if (tableros[player][contador_v][contador_h].boatType.equals("Miss"))
                    {
                        System.out.print("F");
                        tableros[player][contador_v][contador_h].boatType = "Mar";
                    } else
                    {
                        if (tableros[player][contador_v][contador_h].boatType.equals("Mar"))
                        {
                            System.out.print("~");
                        } else
                        {
                            if (tableros[player][contador_v][contador_h].boatType.equals("hundido"))
                            {
                                System.out.print("H");
                                tableros[player][contador_v][contador_h].boatType = "Mar";
                            }
                        }
                    }
                } else
                {
                    if (tableros[player][contador_v][contador_h].wasHit == true)
                    {
                        System.out.print("X");

                    } else
                    {
                        if (devMode)
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
                        } else
                        {
                            System.out.print("~");
                        }
                    }
                }
                System.out.print(" ");

            }
            System.out.println("");
        }

        System.out.println("Barco(s) restantes: " + getBoatCount() + ".");
    }

    public static void attack(Scanner scan) throws IOException
    {
        int fila, columna;
        attack:
        do
        {
            System.out.println("Ataca Player " + (player + 1) + ".");//jugador atacante
            System.out.print("\nEntre numero de fila: ");
            fila = scan.nextInt() - 1;

            System.out.print("\nEntre numero de columna: ");
            columna = scan.nextInt() - 1;

            if (fila == -2 && columna == -2)
            {
                while (true)
                {
                    System.out.print("\n¿Seguro que desea retirarse? Esto contará como una pérdida (y/n): ");
                    char choice = scan.next().toLowerCase().charAt(0);

                    switch (choice)
                    {
                        case 'y':
                            switchPlayer();
                            winner = player + 1;
                            System.out.println("Gana jugador " + winner);

                            setWinner();
                            recordGameRetire();
                            lose = true;
                            return;
                        case 'n':
                            continue attack;
                        default:
                            System.out.println("\nOpción inválida\n");
                    }
                }

            }

            if (fila >= 0 && fila < 8 && columna >= 0 && columna < 8)
            {
                break;
            } else
            {
                System.out.println("Coordenadas inválidas.\n");
            }
        } while (true);

        switchPlayer();//cambia jugador a atacado

        if (!tableros[player][fila][columna].boatType.equals("Mar") && !tableros[player][fila][columna].boatType.equals("Miss"))
        {
            //printTablero();
            System.out.println("\n" + tableros[player][fila][columna].boatType + " bombardeado.\n");
            mustScramble = true;
        }
        if (!tableros[player][fila][columna].boatType.equals("Mar") && !tableros[player][fila][columna].boatType.equals("Miss") && tableros[player][fila][columna].boatHealth == 1)
        {
            System.out.println("\n¡" + tableros[player][fila][columna].boatType + " hundido!\n");
        }

        tableros[player][fila][columna].getHit();

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
    public static void pause()//"press enter to continue..."
    {
        System.out.println("Presione \"enter\" para continuar...");
        try
        {
            System.in.read();
        } catch (Exception e)
        {
        }
    }

    public static void switchPlayer()//cambia de un jugador activo al otro
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

    public static void shuffleTablero()
    {
        Random rng_jesus = new Random();
        // boolean shuffling = true;
        int shuffledboats = 0;
        int boatCount = getBoatCount();
        int fila, columna;
        //int shuffle_fila, shuffle_columna;
        Boat boatCloner[] = new Boat[difficulty];
        for (fila = 0; fila < 8; fila++)
        {
            for (columna = 0; columna < 8; columna++)
            {
                if (tableros[player][fila][columna].isBoat && tableros[player][fila][columna].isDead == false)
                {

                    boatCloner[shuffledboats] = new Boat(tableros[player][fila][columna]);// tableros se vuelve el another
                    shuffledboats++;
                }
                //tableros[player][fila][columna] = new Boat('~');
            }
        }

        for (fila = 0; fila < 8; fila++)
        {
            for (columna = 0; columna < 8; columna++)
            {
                tableros[player][fila][columna] = new Boat();
            }
        }

        shuffledboats = 0;

        while (shuffledboats < boatCount)
        {
            for (fila = 0; fila < 8; fila++)
            {
                for (columna = 0; columna < 8; columna++)
                {
                    int placeboat = rng_jesus.nextInt(4);
                    if (placeboat == 2 && tableros[player][fila][columna].boatType.equals("Mar"))
                    {

                        tableros[player][fila][columna] = new Boat(boatCloner[shuffledboats]);//boatCloner es el another
                        tableros[player][fila][columna].wasMoved = true;
                        shuffledboats++;
                    }
                    if (shuffledboats >= boatCount)
                    {
                        break;
                    }

                }
                if (shuffledboats >= boatCount)
                {
                    break;
                }
            }
            if (shuffledboats >= boatCount)
            {
                break;
            }
        }
        mustScramble = false;
    }

    public static void setDifficulty(char choice)
    {
        switch (choice)
        {
            case 'e':
                difficulty = 5;
                break;
            case 'n':
                difficulty = 4;
                break;
            case 'h':
                difficulty = 2;
                break;
            case 'g':
                difficulty = 1;
                break;
            default:
                System.out.println("\nOpción inválida.\n");
        }
    }

    public static void cleanBoard()
    {
        for (int contador_v = 0; contador_v < 8; contador_v++)
        {
            for (int contador_h = 0; contador_h < 8; contador_h++)
            {
                tableros[player][contador_v][contador_h].wasHit = false;
                tableros[player][contador_v][contador_h].wasMoved = false;
            }
        }
    }

    public static int getBoatCount()
    {
        int boatCount = 0;

        for (int contador_v = 0; contador_v < 8; contador_v++)
        {
            for (int contador_h = 0; contador_h < 8; contador_h++)
            {
                if (tableros[player][contador_v][contador_h].isBoat)
                {
                    boatCount++;
                }
            }
        }

        return boatCount;
    }

    public static boolean setPlayers(Scanner scan, int jugadorNumber) throws FileNotFoundException
    {
        Data.checkIfLogin();
        String usuario = "";
        boolean userNotSame = false;
        boolean cancel = false;
        oponentes[0] = new Jugador();
        oponentes[1] = new Jugador();

        for (int i = 0; i < jugadorNumber; i++)
        {
            if (Data.players[i].isLoggedIn == true)
            {
                oponentes[0] = new Jugador(Data.players[i]);
                break;
            }
        }

        while (true)
        {
            while (userNotSame == false)
            {
                System.out.print("\nIngrese el username del oponente con quien desea enfrentarse (o entre \"cancel\" para cancelar: ");
                usuario = scan.next();
                if (usuario.equals(oponentes[0].usuario))
                {
                    System.out.println("\nNo puede jugar contra sí mismo. Ingrese otro jugador.\n");
                    userNotSame = false;
                } else
                {
                    if (usuario.equals("cancel"))
                    {
                        System.out.println("\nJuego Cancelado.\n");
                        cancel = true;
                        return cancel;
                    } else
                    {
                        break;
                    }
                }

            }

            for (int number = 0; number < jugadorNumber; number++)
            {
                if (usuario.equals(Data.players[number].usuario))
                {
                    System.out.println("Usuario existente. Iniciando jugador 2...");
                    oponentes[1] = new Jugador(Data.players[number]);
                    return cancel;
                }
            }
            System.out.println("\nUsuario inválido. Intente nuevamente.\n");
            userNotSame = false;
            
        }
    }

    public static void setWinner() throws IOException
    {
        if (winner == 1)
        {
            System.out.println("3 puntos sumados a score del ganador");
            oponentes[0].puntos += 3;

            for (int i = 0; i < Data.players.length; i++)
            {
                if (oponentes[0].usuario.equals(Data.players[i].usuario))
                {
                    Data.players[i] = new Jugador();
                    Data.players[i] = new Jugador(oponentes[0]);
                    Data.cleanSaveFile();
                    for (int j = 0; j < Data.players.length; j++)
                    {
                        Data.writeToFile(Data.players[j]);
                    }
                }
            }

        } else
        {
            System.out.println("3 puntos sumados a score del ganador");
            oponentes[1].puntos += 3;

            for (int i = 0; i < Data.players.length; i++)
            {
                if (oponentes[1].usuario.equals(Data.players[i].usuario))
                {
                    Data.players[i] = new Jugador();
                    Data.players[i] = new Jugador(oponentes[1]);
                    Data.cleanSaveFile();
                    for (int j = 0; j < Data.players.length; j++)
                    {
                        Data.writeToFile(Data.players[j]);
                    }
                }
            }
        }

    }

    public static void setTutorialMode(char choice)
    {
        switch (choice)
        {
            case 't':
                devMode = true;
                break;

            case 'a':
                devMode = false;
                break;
            default:
                System.out.println("\nOpción inválida.\n");
        }
    }

    public static void startGameLog() throws IOException
    {
        if (!gameslog.exists())
        {
            gameslog.createNewFile();
        }
    }

    public static void recordGameVictory() throws IOException
    {
        try
        {
            try (FileWriter logwriter = new FileWriter(gameslog, true))
            {
                if (winner == 1)
                {
                    logwriter.write(oponentes[0].usuario + " venció a " + oponentes[1].usuario + " en dificultad " + victoryDifficulty() + ".\n");
                } else
                {
                    if (winner == 2)
                    {
                        logwriter.write(oponentes[1].usuario + " venció a " + oponentes[0].usuario + " en dificultad " + victoryDifficulty() + ".\n");
                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void recordGameRetire() throws IOException
    {
        try
        {
            try (FileWriter logwriter = new FileWriter(gameslog, true))
            {
                if (winner == 1)
                {
                    logwriter.write(oponentes[1].usuario + " se retiró, dejando como ganador a " + oponentes[0].usuario + ".\n");
                } else
                {
                    if (winner == 2)
                    {
                        logwriter.write(oponentes[0].usuario + " se retiró, dejando como ganador a " + oponentes[1].usuario + ".\n");
                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String victoryDifficulty()
    {
        String dificultad_impresa = "";
        switch (difficulty)
        {
            case 5:
                dificultad_impresa = "Easy";
                break;
            case 4:
                dificultad_impresa = "Normal";
                break;
            case 2:
                dificultad_impresa = "Hard";
                break;
            case 1:
                dificultad_impresa = "Genius";
                break;

        }
        return dificultad_impresa;
    }

    public static void printTop10() throws FileNotFoundException
    {
        if (!gameslog.exists())
        {
            System.out.println("\nNo se han jugado juegos todavía.\n");
            return;
        }
        Scanner logreader = new Scanner(gameslog);
        int j = 0;

        while (logreader.hasNextLine())
        {

            logreader.nextLine();
            j++;
        }
        String games[] = new String[j];
        // System.out.println(j + " lines");
        logreader = new Scanner(gameslog);
        for (int i = j - 1; i >= 0; j--)
        {
            while (logreader.hasNextLine())
            {
                games[i] = logreader.nextLine();
                //System.out.println(i+" : "+ games[i]);
                i--;
                //logreader.nextLine();
            }
        }
        try
        {
            //System.out.println("~~~");
            for (int i = 0; i < 10; i++)
            {
                System.out.println((i + 1) + " : " + games[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.print("");
        }

    }
}
