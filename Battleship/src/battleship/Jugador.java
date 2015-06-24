package battleship;

public class Jugador
{
    public String usuario = "foo";
    public String clave = "bar";
    public int puntos = 0;
    public boolean isLoggedIn = false;
    
    public Jugador(String usuario, String clave)
    {
        this.usuario = usuario;
        this.clave = clave;
        this.puntos = 0;
        this.isLoggedIn = false;
    }
    
    public Jugador(Jugador another)//Clonando datos de user en otro
    {
        this.usuario = another.usuario;
        this.clave = another.clave;
        this.puntos = another.puntos;
        this.isLoggedIn = another.isLoggedIn;
    }
    
    public Jugador()
    {}
    
}
