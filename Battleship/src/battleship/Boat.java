package battleship;

public class Boat
{
    //attributes

    private int boatHealth;

    public String boatType;

    public boolean isBoat = false;
    public boolean isDead = false;
    public boolean wasHit = false;
    public boolean wasMoved = false;

    //constructors
    public Boat(char boatType)
    {
        switch (boatType)
        {
            case 'p':
                this.boatType = "Portaaviones";
                boatHealth = 5;
                isBoat = true;
                break;
            case 'a':
                this.boatType = "Acorazado";
                boatHealth = 4;
                isBoat = true;
                break;
            case 's':
                this.boatType = "Submarino";
                boatHealth = 3;
                isBoat = true;
                break;
            case 'd':
                this.boatType = "Destructor";
                boatHealth = 2;
                isBoat = true;
                break;
            case '~':
                this.boatType = "Mar";
                isDead = true;
                break;

        }

    }
    
    public Boat(Boat another)
    {
        this.boatType = another.boatType;
        this.boatHealth = another.boatHealth;
        this.isBoat = another.isBoat;
        this.wasHit = another.wasHit;
    }

    //methods
    public void getHit()
    {
        if (!boatType.equals("Miss") && !boatType.equals("Mar"))
        {

            if (boatHealth > 0)
            {   
                boatHealth -= 1;
                if (boatHealth < 1)
                    isDead = true;
                else
                    wasHit = true;
            }

            else if (boatHealth < 1)
            {
                wasHit = false;
                isDead = true;
            }
        } 
        else if (boatType.equals("Mar"))
        {
           //wasHit = true;
           boatType = "Miss";
           isDead = true;
        }

    }
}
