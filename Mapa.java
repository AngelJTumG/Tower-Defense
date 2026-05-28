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
    private int dinero = 100;
    private GreenfootSound musica = new GreenfootSound("Musikita.wav");

    public Mapa()
    {    
        super(800, 600, 1);
        musica.playLoop();
        addObject(new BASE(), 180, 70);
        addObject(new Spot(), 520, 140);
        addObject(new Spot(), 190, 330);
        addObject(new Spot(), 500, 340);
        addObject(new Spot(), 200, 450);
        addObject(new Spot(), 350, 500);
        addObject(new Spot(), 650, 250);
    }

    public void act()
    {
        tiempoJuego++;
        generar();
        colocarTorre();
        showText("VIDA: " + vidaBase, 70, 20);
        showText("TIEMPO: " + (60 - tiempoJuego/60), 700, 20);
        showText("Spawn: " + velocidadOleada, 700, 20);
        showText("DINERO: " + dinero, 70, 40);
        showText("1 = Torreta (50)", 700, 40);
        showText("2 = Torreta Lenta (80)", 700, 60);
        aumentarDificultad();
        verificarVictoria();
    }
    
    public void perderVida()
    {
        vidaBase--;

        if (vidaBase <= 0)
        {
            showText("GAME OVER", 400, 300);
            Greenfoot.playSound("GAME OVER.wav");
            musica.stop();
            Greenfoot.stop();
        }
    }
    
    public void ganarDinero(int cantidad)
    {
        dinero += cantidad;
    }

    public void generar()
    {
        tiempoSpawn++;

        if (tiempoSpawn >= velocidadOleada)
        {
            int random = Greenfoot.getRandomNumber(2);

            if (random == 0)
            {
                addObject(new Zombie(), 120, 560);
            }
            else
            {
                addObject(new Robot(), 520, 560);
            }

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
            if (cantidadTorres >= 6)
                {
                    return;
                }

            int x = actor.getX();
            int y = actor.getY();

            
            if (Greenfoot.isKeyDown("1"))
            {
                if (dinero >= 50)
                    {
                        addObject(new Torreta(), x, y);

                        dinero -= 50;

                        removeObject(actor);

                        cantidadTorres++;
                    }
            }

            else if (Greenfoot.isKeyDown("2"))
            {
                if (dinero >= 80)
                {
                        addObject(new TorretaLenta(), x, y);

                        dinero -= 80;

                        removeObject(actor);

                        cantidadTorres++;
                    }
                }
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
            Greenfoot.playSound("VICTORY.wav");
            musica.stop();
            Greenfoot.stop();
        }
    }
}