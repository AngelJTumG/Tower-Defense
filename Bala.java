import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bala here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bala extends Actor
{
    private Enemigo objetivo;
    private int dano=1;

    public Bala(Enemigo enemigo,int d)
    {
        objetivo = enemigo;
        dano = d; 
    }

    public void act()
    {
        mover();
        golpear();
    }

    public void mover()
    {
        if (objetivo != null &&
            objetivo.getWorld() != null)
        {
            turnTowards(
                objetivo.getX(),
                objetivo.getY()
            );

            move(5);
        }
    }

    public void golpear()
    {
        Enemigo enemigo =
            (Enemigo)getOneIntersectingObject(Enemigo.class);

        if (enemigo != null)
        {
            enemigo.recibirDanio(dano);

            getWorld().removeObject(this);
        }
    }
}
