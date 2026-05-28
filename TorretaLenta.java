import greenfoot.*;
import java.util.List;

public class TorretaLenta extends Torreta
{
    private GreenfootSound canon = new GreenfootSound("BANG.wav");
    
    public TorretaLenta()
    {
        canon.setVolume(35);
    }
    
    public void disparar()
    {
        tiempoDisparo++;

        List<Enemigo> enemigos =
            getObjectsInRange(250, Enemigo.class);

    
        if (!enemigos.isEmpty() && tiempoDisparo > 120)
        {
            Enemigo objetivo = enemigos.get(0);

            Bala bala = new Bala(objetivo,2);
            canon.play();

            getWorld().addObject(
                bala,
                getX(),
                getY()
            );

            tiempoDisparo = 0;
        }
    }
}
