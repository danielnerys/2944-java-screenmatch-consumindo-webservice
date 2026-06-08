package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do filme: ");
        String busca = sc.nextLine();
        //para corrigir inserção de espaço no nome do filme;
        String busca_corrigida = busca.replace(' ', '+');

        //Padronização, mais profissional
        String termo = URLEncoder.encode(busca, StandardCharsets.UTF_8);

        System.out.println(termo);

        System.out.println(busca_corrigida);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://www.omdbapi.com/?t=%S&apikey=41aa0740", termo)))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        Gson gson = new Gson();

        Titulo meutitulo = gson.fromJson(response.body(), Titulo.class);

        System.out.println(meutitulo.getNome());

        sc.close();


    }

}
