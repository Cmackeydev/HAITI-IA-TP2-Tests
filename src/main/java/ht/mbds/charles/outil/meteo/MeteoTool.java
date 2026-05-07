package ht.mbds.charles.outil.meteo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Scanner;
import java.net.URI;

public class MeteoTool {
    @Tool("""
    Retourne true si la probabilité d'avoir de la pluie pour la ville dont on donne la latitude et la longitude
    est importante durant une période de nbJours jours. La période commence nbJourDebut jours après aujourd'hui.
    Par exemple, si nbJoursDebut = 1 et nbJours = 2, la période de 2 jours commence demain (1 jour après aujourd'hui).
    Si nbJoursDebut = 0 et nbJours = 3, la période de 3 jours commence aujourd'hui.
    Retourne false sinon.
    """)
    public boolean isPluiePrevue(@P("Latitude") double latitude, @P("Longitude") double longitude,
                                 @P("Nombre de jours après aujourd'hui pour indiquer le début de la période") int nbJoursDebut,
                                 @P("Nombre de jours de la période") int nbJours) {
        double seuilPluie = 25.0;

        try {
            String baseUrl = "https://api.open-meteo.com/v1/forecast";
            int finPeriode = Math.min(nbJoursDebut + nbJours, 7);
            String apiUri = String.format(Locale.US,
                "%s?latitude=%f&longitude=%f&daily=precipitation_probability_max&forecast_days=%d",
                baseUrl, latitude, longitude, finPeriode);

            HttpURLConnection connection = (HttpURLConnection) new URI(apiUri).toURL().openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(connection.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);

            JsonObject daily = jsonResponse.getAsJsonObject("daily");
            int[] precipitationProbabilities = gson.fromJson(
                daily.get("precipitation_probability_max"),
                int[].class
            );

            for (int i = nbJoursDebut; i < finPeriode; i++) {
                if (precipitationProbabilities[i] > seuilPluie) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la récupération de la météo pour ("
                    + latitude + ", " + longitude + ") : " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
