package battleship;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Data
{

    public static File savefile = new File("savefile.sav");
    public static Jugador players[];
    public static Scanner scan = new Scanner(System.in);
    public static Scanner reader;

    public static void makeSaveFile() throws FileNotFoundException//crea savefile e inicializa su scanner
    {
        try
        {
            if (!savefile.exists())
            {

                savefile.createNewFile();
                System.out.println("\nArchivo de almacenaje creado.\n");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void startFileScanner() throws FileNotFoundException//inicia "reader"
    {
        reader = new Scanner(savefile);
    }

    public static Jugador createPlayer() throws FileNotFoundException//crea un objeto "Jugador" nuevo 
    {
        boolean usuario_nuevo = true;
        String usuario, clave;
        do
        {
            System.out.println("\n\nCrear Jugador Nuevo\n~~~");
            System.out.print("\nUsername Nuevo: (o entre \"cancel\" para cancelar): ");
            usuario = scan.next();
            if (usuario.equals("cancel"))
            {
                return (null);
            }

            if (savefile.exists())
            {
                startFileScanner();
                while (reader.hasNextLine())
                {
                    if (usuario.equals(reader.nextLine()))
                    {
                        System.out.print("\nUsuario ya existente.");
                        usuario_nuevo = false;
                        break;
                    } else
                    {
                        usuario_nuevo = true;
                    }
                }
            }
        } while (usuario_nuevo == false);

        System.out.print("\nContraseña: ");
        clave = scan.next();
        Jugador newPlayer = new Jugador(usuario, clave);
        newPlayer.isLoggedIn = true;
        return (newPlayer);

    }

    public static void startPlayers(int jugadorNumber) throws FileNotFoundException//inicia arreglo de jugadores
    {
        players = new Jugador[jugadorNumber];
        for (int i = 0; i < jugadorNumber; i++)
        {
            players[i] = new Jugador("foo", "bar");
        }
    }

    public static void writeToFile(Jugador newPlayer) throws IOException //escribe datos de un player a savefile
    {
        if (!newPlayer.usuario.equals("nil"))
        {
            try
            {
                try (FileWriter escritor = new FileWriter(savefile, true))
                {
                    escritor.write("~\n");

                    escritor.write(newPlayer.usuario + "\n");
                    escritor.write(newPlayer.clave + "\n");
                    escritor.write(newPlayer.puntos + "\n");
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void fillPlayers(Scanner scan, int jugadorNumber) throws FileNotFoundException//llena el arreglo de players con datos de savefile
    {
        startFileScanner();
        while (reader.hasNextLine())
        {
            for (int i = 0; i < jugadorNumber; i++)
            {
                if (reader.nextLine().equals("~"))
                {
                    players[i].usuario = reader.nextLine();
                    //System.out.println(players[i].usuario);
                    players[i].clave = reader.nextLine();
                    players[i].puntos = Integer.parseInt(reader.next());
                    reader.nextLine();
                }
            }
        }
    }

    public static int getPlayerNumbers() throws FileNotFoundException//Que tantos jugadores hay?
    {
        startFileScanner();
        int jugadorNumber = 0;
        while (reader.hasNextLine())
        {
            if (reader.nextLine().equals("~"))
            {
                jugadorNumber++;
            }
        }

        return (jugadorNumber);
    }

    public static boolean login(Scanner scan, int jugadorNumber) throws FileNotFoundException//login
    {
        boolean menu_inicial = false;
        System.out.println("\n\nLogin\n~~~");
        boolean login = false;
        while (true)
        {
            System.out.print("\nIngrese su username (o entre \"cancel\" para cancelar login): ");
            String usuario = scan.next();

            if (usuario.equals("cancel"))
            {
                menu_inicial = true;
                return menu_inicial;
            }

            for (int i = 0; i < jugadorNumber; i++)
            {
                if (usuario.equals(players[i].usuario))
                {
                    System.out.print("\nIngrese su contraseña: ");
                    String clave = scan.next();
                    if (clave.equals(players[i].clave))
                    {
                        System.out.println("\nLogin valido. Bienvenido.\n");
                        players[i].isLoggedIn = true;
                        //checkIfLogin();
                        Spiel.pause();
                        System.out.println("\n\n~~~~~~~~~");
                        login = true;
                        break;
                    } else
                    {
                        System.out.println("Login incorrecto. Intente de nuevo.\n");
                        //login = false;

                    }

                }
            }

            if (login)
            {
                break;
            } else
            {
                System.out.println("Login inválido\n");
            }
        }

        return (menu_inicial);
    }
    
    public static void setLoggedIn(Jugador newPlayer)
    {
        for (int i = 0; i < players.length ; i++)
        {
            if (newPlayer.usuario.equals(players[i].usuario))
            {
                players[i].isLoggedIn = true;
            }
        }
    }

    public static boolean saveFileExists(boolean menu_inicial)throws FileNotFoundException//existe el savefile?
    {
        if (!savefile.exists())
        {
            System.out.println("\nArchivo de almacenaje no existe.\nCree un jugador nuevo primero.\n-----\n");
            menu_inicial = true;
            //continue menu;
            //return menu_inicial;
        }
        else
            makeSaveFile();
        return menu_inicial;
    }

    public static void deleteSaveFile()
    {
        savefile.delete();
    }

    public static void cleanSaveFile()
    {
        try
        {
            try (FileWriter escritor = new FileWriter(savefile))
            {
                escritor.write("");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //tomó inspiracion de: http://www.careerbless.com/samplecodes/java/beginners/sorting/BubbleSortDescending.php
    public static void getRank()
    {
        Jugador temporal =  new Jugador();
        for (int i = 0; i < players.length; i++)
        {
            for (int j = 1; j < players.length - i; j++)
            {
                if (players[j - 1].puntos < players[j].puntos)
                {
                    temporal = players[j - 1];
                    players[j - 1] = players[j];
                    players[j] = temporal;
                }
            }
        }
        for (int i = 0; i < players.length; i++)
        {
            System.out.println("Usuario: " + players[i].usuario+ "\nPuntos: "+players[i].puntos);
        }

    }

    //debugging tools, not used in game
    public static void printPlayers(Scanner scan, int jugadorNumber) throws FileNotFoundException// esta no funciona de todos modos >:/
    {
        System.out.println("Printing players...");
        startFileScanner();
        while (reader.hasNextLine())
        {
            for (int i = 0; i < players.length; i++)
            {

                if (reader.nextLine().equals("~"))
                {
                    System.out.println("Player " + i);
                    System.out.println("in file: " + reader.nextLine() + " in array: " + players[i].usuario);
                    System.out.println("in file: " + reader.nextLine() + " in array: " + players[i].clave);
                    System.out.println("in file: " + reader.nextLine() + " in array: " + players[i].puntos);
                    // System.out.println("is this dude logged in?"+players[i].isLoggedIn);
                }
            }
        }
    }

    public static void printArray()
    {
        for (int i = 0; i < players.length; i++)
        {
            System.out.println(players[i].usuario);
        }
    }

    public static void checkIfPlayerExists(String usuario) throws FileNotFoundException
    {
        for (int i = 0; i < getPlayerNumbers(); i++)
        {
            if (usuario.equals(players[i].usuario))
            {
                System.out.println("true");
            }
        }
    }

    public static void checkIfLogin() throws FileNotFoundException
    {
        //printArray();
        //printPlayers(scan, getPlayerNumbers());
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].isLoggedIn)
            {
                System.out.println("Usuario: " + players[i].usuario);
                System.out.println("Clave: " + players[i].clave);
                System.out.println("Puntos: " + players[i].puntos);
                break;
            }
        }
    }

    public static void editloggedIn(String usuario, String clave, int puntos) throws IOException
    {
        //printArray();
        //printPlayers(scan, getPlayerNumbers());
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].isLoggedIn)
            {
                delloggedIn();
                players[i].usuario = usuario;
                players[i].clave = clave;
                players[i].puntos = puntos;
                players[i].isLoggedIn = true;
                writeToFile(players[i]);
                break;
            }
        }
    }

    public static void delloggedIn() throws IOException
    {

        for (int i = 0; i < players.length; i++)
        {
            if (players[i].isLoggedIn)

            {
                players[i] = new Jugador("nil", "iseriouslyhopeyouguysdonttrytologinwiththis");
                cleanSaveFile();
                for (int j = 0; j < players.length; j++)
                {
                    writeToFile(players[j]);
                }
                break;
            }
        }
    }

}
