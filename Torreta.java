import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Torreta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Torreta extends Actor
{
    private int tiempoDisparo = 0;

    public Torreta()
    {
        GreenfootImage img = getImage();
        img.scale(74, 74);
    }

    public void act()
    {
        disparar();
    }

    public void disparar()
    {
        tiempoDisparo++;

        List<Zombie> enemigos =
            getObjectsInRange(150, Zombie.class);

        if (!enemigos.isEmpty() && tiempoDisparo > 50)
        {
            Zombie objetivo = enemigos.get(0);

            Bala bala = new Bala(objetivo);

            getWorld().addObject(
                bala,
                getX(),
                getY()
            );

            tiempoDisparo = 0;
        }
    }
}
