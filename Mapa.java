import greenfoot.*;

public class Mapa extends World
{
    private int tiempoSpawn = 0;
    private int vidaBase = 10;
    private int cantidadTorres= 0;
    private int velocidadOleada = 180;
    private int tiempoJuego = 0;
    private int siguienteAumento = 300;
    private int contadorDificultad = 0;

    public Mapa()
    {    
        super(800, 600, 1);
        addObject(new BASE(), 180, 70);
        addObject(new Spot(), 520, 140);
        addObject(new Spot(), 190, 330);
        addObject(new Spot(), 500, 340);
        addObject(new Spot(), 200, 450);
    }

    public void act()
    {
        tiempoJuego++;
        generarZombie();
        colocarTorre();
        showText("VIDA: " + vidaBase, 70, 20);
        showText("TIEMPO: " + (60 - tiempoJuego/60), 700, 20);
        showText("Spawn: " + velocidadOleada, 700, 20);
        aumentarDificultad();
        verificarVictoria();
    }
    
    public void perderVida()
    {
        vidaBase--;

        if (vidaBase <= 0)
        {
            showText("GAME OVER", 400, 300);

            Greenfoot.stop();
        }
    }

    public void generarZombie()
    {
        tiempoSpawn++;

        if (tiempoSpawn >= velocidadOleada)
        {
            addObject(new Zombie(), 120, 560);

            tiempoSpawn = 0;
        }
    }

    public void colocarTorre()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();

        if (mouse != null &&
            Greenfoot.mouseClicked(null))
        {
            Actor actor = mouse.getActor();

            if (actor instanceof Spot)
            {
                if (cantidadTorres >= 4)
                {
                    return;
                }

                int x = actor.getX();
                int y = actor.getY();

                addObject(new Torreta(), x, y);

                removeObject(actor);

                cantidadTorres++;
            }   
        }
    }
    
    public void aumentarDificultad()
    {
         if (tiempoJuego >= siguienteAumento)
        {
            velocidadOleada -= 20;

            if (velocidadOleada < 30)
            {
                velocidadOleada = 30;
            }

            siguienteAumento += 300;
        }   
    }
    
    public void verificarVictoria()
    {

        if (tiempoJuego >= 3600)
        {
            showText("¡GANASTE!", 400, 300);

            Greenfoot.stop();
        }
    }
}