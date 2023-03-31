package inmersionjava.aula1.apifilm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class App {

    public static void main(String[] args) throws Exception {

        // Leer la clave de la API del archivo de configuración
        Properties prop = new Properties();
        String propFileName = "config/config.txt";

        InputStream inputStream = App.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new IOException("Archivo de configuración '" + propFileName + "' no se encuentra en el classpath");
        }

        String apiKey = prop.getProperty("api_key");

        //hacer conexión HTTP y  motsrar las peliculas más populares
        URI adress = URI.create(apiKey);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extraer solo los datos que nos interesan (titulo, poster, clasificación)
        //extrar y manipular los datos
        String amarillo = "\033[33m";
        String resetColor = "\033[0m";

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilms = parser.parse(body);

        for (Map<String, String> film : listaDeFilms) {
            System.out.println(amarillo + "Título: " + resetColor + film.get("title"));
            System.out.println(amarillo + "Poster: " + resetColor + film.get("image"));

            String imdbRating = film.get("imDbRating");
            if (imdbRating != null && !imdbRating.isEmpty()) {
                double ratingDouble = Double.parseDouble(imdbRating);
                int ratingInt = (int) Math.round(ratingDouble);
                String starEmoji = "\u2605";
                String ratingStars = amarillo;
                for (int i = 0; i < ratingInt; i++) {
                    ratingStars += starEmoji + " ";
                }
                ratingStars += resetColor;
                System.out.println(amarillo + "imDbRating: " + resetColor + ratingStars);
            } else {
                System.out.println("imDbRating no disponible");
            }
            System.out.println();
        }

    }
}
