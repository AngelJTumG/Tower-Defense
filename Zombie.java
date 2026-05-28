import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Zombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zombie extends Enemigo
{
    private int vida = 3;
    private GreenfootSound grito = new GreenfootSound("Zombie.wav");
    private int punto = 0;

    private int[][] ruta = {
        {120, 460},
        {120, 250},
        {190, 230},
        {300, 320},
        {600, 250},
        {600, 200},
        {600, 70},
        {400, 80},
        {400, 140},
        {140, 140}
    };

    public Zombie()
    {
        GreenfootImage img = getImage();
        img.scale(64, 64);
        grito.setVolume(80);
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

        move(2);

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
            grito.play();
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
