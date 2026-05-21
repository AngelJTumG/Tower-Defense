import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BASE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BASE extends Actor
{
     private int vida = 10;

    public BASE()
    {
        GreenfootImage img = getImage();
        img.scale(128, 128);
    }

    public void recibirDanio(int dano)
    {
        vida -= dano;

        System.out.println("Vida Base: " + vida);

        if (vida <= 0)
        {
            Greenfoot.stop();

            System.out.println("GAME OVER");
        }
    }
}
