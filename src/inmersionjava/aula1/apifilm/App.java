package inmersionjava.aula1.apifilm;

import inmersionjava.aula2.generadorstikers.GeneradorDeStickers;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {

    public static final String AMARILLO = "\u001B[33m";
    public static final String RESET_COLOR = "\u001B[0m";
    public static final String STAR_EMOJI = "\u2605";

    public static void main(String[] args) throws Exception {

        // Leer la clave de la API del archivo de configuración
        Properties prop = new Properties();
        String propFileName = "config/config.txt";

        ClassLoader classLoader = App.class.getClassLoader();
        URL resource = classLoader.getResource(propFileName);

        if (resource == null) {
            throw new IOException("Archivo de configuración '" + propFileName + "' no se encuentra en el classpath");
        }

        prop.load(resource.openStream());
        String apiKey = prop.getProperty("api_key");

        //hacer conexión HTTP y  motsrar las peliculas más populares
        URI adress = URI.create(apiKey);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extraer solo los datos que nos interesan (titulo, poster, clasificación)
        //extrar y manipular los datos
        var parser = new JsonParser();

        List<Map<String, String>> listaDeFilms = parser.parse(body);

        for (Map<String, String> film : listaDeFilms) {

            System.out.println(AMARILLO + "Título: " + RESET_COLOR + film.get("title"));
            System.out.println(AMARILLO + "Poster: " + RESET_COLOR + film.get("image"));

            String urlImage = film.get("image");
            String title = film.get("title");
            String imdbRating = film.get("imDbRating");

            if (imdbRating != null && !imdbRating.isEmpty()) {
                double ratingDouble = Double.parseDouble(imdbRating);
                int ratingInt = (int) Math.round(ratingDouble);
                String ratingStars = AMARILLO;
                for (int i = 0; i < ratingInt; i++) {
                    ratingStars += STAR_EMOJI + " ";
                }
                ratingStars += RESET_COLOR;
                System.out.println(AMARILLO + "imDbRating: " + RESET_COLOR + ratingStars);
            } else {
                System.out.println("imDbRating no disponible");
            }
            System.out.println();

            InputStream inputStream = new URL(urlImage).openStream();
            String nombreArchivo = "STK " + title + ".png";

            //crear la carpeta donde se guardaran las imagenes
            File carpeta = new File("src/imagenes");
            carpeta.mkdirs(); // se crea la carpeta si no existe

            // Ruta de la carpeta donde se guarda la imagen
            String ruta = "src/imagenes/";

            GeneradorDeStickers generador = new GeneradorDeStickers();

            String calificacion = film.get("imDbRating");

            int calificacionDouble;

            try {
                calificacionDouble = (int) Math.round(Double.parseDouble(calificacion));
            } catch (NumberFormatException e) {
                System.out.println("La calificación no existe o es invalida.");
                calificacionDouble = 0; // o cualquier otro valor predeterminado

            }

            generador.crear(inputStream, ruta, nombreArchivo, calificacionDouble);

        }

    }

}
