package inmersionjava.aula2.generadorstikers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeneradorDeStickers {

    public void crear(InputStream inputStream, String ruta, String nombreArchivo, double calificacion) throws Exception {
        

        // InputStream inputStream = new FileInputStream (new File("src/imagenes/grogu.jpg")) ;
        //InputStream inputStream = new URL("https://th.bing.com/th/id/OIP.RRSkiOyHXhPXUXFqJjcTSAHaFI?pid=ImgDet&rs=1").openStream();
        
        //leer la imagen
        BufferedImage imageOriginal = ImageIO.read(inputStream);

        //crear nueva imagen en memoria con transparencia y nuevo tamaño
        int largo = imageOriginal.getWidth();
        int altura = imageOriginal.getHeight();
        int nuevaAltura = altura + 100;
        BufferedImage nuevaImagen = new BufferedImage(largo, nuevaAltura, BufferedImage.TRANSLUCENT);

        //copiar la imagen original para la nueva imagen (en memoria)
        Graphics2D graphics = (Graphics2D) nuevaImagen.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);

        //configurar la fuente
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        graphics.setColor(Color.WHITE); //relleno
        graphics.setFont(font);

        //escribir una frase en la nueva imagen
        String texto;

        if (calificacion >= 7) {
            texto = "Good";

        } else {
            texto = "Bad";

        }
        var glyphVector = font.createGlyphVector(graphics.getFontRenderContext(), texto);
        var outline = glyphVector.getOutline(14, nuevaAltura - 55);

        graphics.setStroke(new BasicStroke(7));
        graphics.setColor(Color.BLACK); //borde
        graphics.draw(outline);

        graphics.setColor(Color.WHITE); //relleno
        graphics.fill(outline);

        graphics.dispose();

        //Escribir una nueva imagen en un archivo
        ImageIO.write(nuevaImagen, "png", new File(ruta, nombreArchivo));

    }

}
