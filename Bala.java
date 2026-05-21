import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bala here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bala extends Actor
{
    private Zombie objetivo;

    public Bala(Zombie enemigo)
    {
        objetivo = enemigo;
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
        Zombie enemigo =
            (Zombie)getOneIntersectingObject(Zombie.class);

        if (enemigo != null)
        {
            enemigo.recibirDanio(1);

            getWorld().removeObject(this);
        }
    }
}
