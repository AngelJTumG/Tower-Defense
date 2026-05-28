import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Torreta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Torreta extends TORRETAS
{
    protected int tiempoDisparo = 0;
    private GreenfootSound disparo = new GreenfootSound("Piu.wav");

    public Torreta()
    {
        GreenfootImage img = getImage();
        img.scale(74, 74);
        disparo.setVolume(80);
    }

    public void act()
    {
        disparar();
    }

    public void disparar()
    {
        tiempoDisparo++;

        List<Enemigo> enemigos =
            getObjectsInRange(150, Enemigo.class);

        if (!enemigos.isEmpty() && tiempoDisparo > 50)
        {
            Enemigo objetivo = enemigos.get(0);

            Bala bala = new Bala(objetivo,1);
            disparo.play();

            getWorld().addObject(
                bala,
                getX(),
                getY()
            );

            tiempoDisparo = 0;
        }
    }
}
