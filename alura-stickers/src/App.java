import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTPS e buscar TOP 250 Filmes!
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var newHttpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = newHttpClient.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair somente os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados.
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1m \u001b[34m Título: \u001b[m" + filme.get("title"));
            
            System.out.println("\u001b[1m \u001b[34m Poster: \u001b[m" + filme.get("image"));
            
            System.out.println("\u001b[1m \u001b[34m Avaliaçao: \u001b[m" + filme.get("imDbRating") + "\n");
            
        }
    }
}
