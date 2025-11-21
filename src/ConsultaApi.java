import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import java.util.Map;

public class ConsultaApi {

    // 👇 CAMBIÁ SOLO TU_API_KEY por la clave que aparece en la URL del Trello
    private static final String URL_BASE =
            "https://v6.exchangerate-api.com/v6/6923c1f67afec891d46e728c/latest/";

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public double obtenerTasa(String monedaBase, String monedaDestino) {

        // La URL final queda: .../latest/USD  (o ARS, BRL, etc.)
        String url = URL_BASE + monedaBase;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            RespuestaApi datos = gson.fromJson(json, RespuestaApi.class);

            // La API debe devolver "success"
            if (!"success".equalsIgnoreCase(datos.result)) {
                throw new RuntimeException("La API devolvió un estado: " + datos.result);
            }

            Double tasa = datos.conversion_rates.get(monedaDestino);

            if (tasa == null) {
                throw new RuntimeException("No se encontró la moneda: " + monedaDestino);
            }

            return tasa;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consultar la API: " + e.getMessage(), e);
        }
    }

    private static class RespuestaApi {
        String result;
        String base_code;
        Map<String, Double> conversion_rates;
    }
}
