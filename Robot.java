import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Robot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot extends Enemigo
{
    private int vida = 4;
    private GreenfootSound BOP = new GreenfootSound("Robot.wav");
    private int punto = 0;

    private int[][] ruta = {
        {520, 560},
        {500, 540},
        {350, 440},
        {120, 560},
        {190, 230},
        {280, 300},
        {570, 230},
        {600, 200},
        {600, 70},
        {400, 80},
        {400, 140},
        {140, 140}
    };

    public Robot()
    {
        GreenfootImage img = getImage();
        img.scale(64, 64);
        BOP.setVolume(80);
    }

    public void act()
    {
        moverRuta();
        llegarBase();
    }

    public void moverRuta()
    {
       if (punto >= ruta.length)
        {
        return;
        }

        int destinoX = ruta[punto][0];
        int destinoY = ruta[punto][1];

        turnTowards(destinoX, destinoY);

        move(1);

        if (Math.abs(getX() - destinoX) < 5 &&
        Math.abs(getY() - destinoY) < 5)
        {
        punto++;
        }
    }

    public void recibirDanio(int dano)
    {
        vida -= dano;

        if (vida <= 0)
        {
            BOP.play();
            Mapa mapa = (Mapa)getWorld();
            
            mapa.ganarDinero(20);
            getWorld().removeObject(this);
        }
    }

    public void llegarBase()
    {
        BASE base =
        (BASE)getOneIntersectingObject(BASE.class);

        if (base != null)
        {
            Mapa mapa = (Mapa)getWorld();

            mapa.perderVida();

            getWorld().removeObject(this);
        }
    }
}