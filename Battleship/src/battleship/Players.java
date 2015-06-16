package battleship;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Players
{

    public String username;
    public String password;
    public File savefile = new File("savefile.sav");

    public boolean login(Scanner scan) throws FileNotFoundException
    {
        // File savefile = new File("players.sav");
        boolean menu_inicial = false;
        //si no existe savefile...
        if (!savefile.exists())
        {
            System.out.println("\nArchivo de almacenaje no existe.\nCree un jugador nuevo primero.\n-----\n");
            menu_inicial = true;
            //continue menu;
            return menu_inicial;
        }

        menu_inicial = false;
        System.out.println("\n\nLogin\n~~~");
        while (true)
        {
            System.out.print("\nIngrese su username (o entre \"cancel\" para cancelar login): ");
            username = scan.next();
            Scanner txt_checker = new Scanner(savefile);
            if (username.equals("cancel"))
            {
                menu_inicial = true;
                //continue menu;
                return menu_inicial;
            }
            boolean login = false;

            while (txt_checker.hasNextLine())
            {
                if (username.equals(txt_checker.nextLine()))
                {
                    System.out.print("\nIngrese su contraseña: ");
                    password = "*" + scan.next();
                    if (password.equals(txt_checker.nextLine()))
                    {
                        System.out.println("Login valido. Bienvenido.\n\n~~~~~~~~~");
                        login = true;
                        break;
                    }

                }
            }

            if (login)
            {
                break;
            } else
            {
                System.out.println("Login inválido");
            }
        }

        return (menu_inicial);
    }

    public boolean createNewPlayer(Scanner scan) throws FileNotFoundException
    {
        boolean usuario_nuevo = true, first_run = false, menu_inicial = false;
	//String usuario[] = new String[2];

        do
        {
            System.out.println("\n\nCrear Jugador Nuevo\n~~~");
            System.out.print("\nUsername Nuevo: (o entre \"cancel\" para cancelar): ");
            username = scan.next();
            if (username.equals("cancel"))
            {
                menu_inicial = true;
                return (menu_inicial);
            }
            try
            {
                if (!savefile.exists())
                {
                    savefile.createNewFile();
                    first_run = true;
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            Scanner txt_checker = new Scanner(savefile);
            while (txt_checker.hasNextLine())
            {
                if (username.equals(txt_checker.nextLine()))
                {
                    System.out.print("\nUsuario ya existente.");
                    usuario_nuevo = false;
                    break;
                } else
                {
                    usuario_nuevo = true;
                }
            }
        } while (usuario_nuevo == false);

        System.out.print("\nContraseña: ");
        password = scan.next();

        try
        {
            FileWriter fileWritter = new FileWriter(savefile, true);

            fileWritter.write(username + "\n");
            fileWritter.write("*" + password + "\n_\n");
            fileWritter.close();
            if (first_run)
            {
                System.out.println("\nArchivo de almacenaje creado.");
            }

            System.out.println("\nJugador nuevo creado.\n\n~~~~~~~~~");

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        menu_inicial = false;

        return (menu_inicial);
    }

}
