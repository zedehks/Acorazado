package battleship;

public class Boat
{
    //attributes

    private int boatHealth;

    public String boatType;

    public boolean isDead = false;
    public boolean wasHit = false;

    //constructors
    public Boat(char boatType)
    {
        switch (boatType)
        {
            case 'p':
                this.boatType = "Portaaviones";
                boatHealth = 5;
                break;
            case 'a':
                this.boatType = "Acorazado";
                boatHealth = 4;
                break;
            case 's':
                this.boatType = "Submarino";
                boatHealth = 3;
                break;
            case 'd':
                this.boatType = "Destructor";
                boatHealth = 2;
                break;
            case '~':
                this.boatType = "Mar";
                isDead = true;
                break;

        }

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
