package Perseverance;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException {
        new Menu().crearVentana();

        String file = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        System.out.println(file);
    }
}
