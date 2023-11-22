package apiController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    private TimerRepository timerRepository = TimerRepository.getInstance();

    @SneakyThrows
    public void run() throws JsonProcessingException {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleWithFixedDelay(() -> {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = null;
            try {
                request = HttpRequest.newBuilder()
                        .GET()
                        .uri(new URI("https://islomapi.uz/api/present/day?region=toshkent"))
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                        .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                        .header("Sec-Ch-Ua-Mobile", "?0")
                        .build();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            ObjectMapper mapper = new ObjectMapper();

            String body = response.body();
            JsonNode jsonNode = null;
            try {
                jsonNode = mapper.readTree(body);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            String getBomdod = jsonNode.get("times").get("tong_saharlik").asText();
            LocalTime bomdod = LocalTime.of(Integer.parseInt(getBomdod.split(":")[0]), Integer.parseInt(getBomdod.split(":")[1]));

            String getPeshin = jsonNode.get("times").get("peshin").asText();
            LocalTime peshin = LocalTime.of(Integer.parseInt(getPeshin.split(":")[0]), Integer.parseInt(getPeshin.split(":")[1]));

            String getAsr = jsonNode.get("times").get("asr").asText();
            LocalTime asr = LocalTime.of(Integer.parseInt(getAsr.split(":")[0]), Integer.parseInt(getAsr.split(":")[1]));

            String getShom = jsonNode.get("times").get("shom_iftor").asText();
            LocalTime shom = LocalTime.of(Integer.parseInt(getShom.split(":")[0]), Integer.parseInt(getShom.split(":")[1]));

            String getXufton = jsonNode.get("times").get("hufton").asText();
            LocalTime xufton = LocalTime.of(Integer.parseInt(getXufton.split(":")[0]), Integer.parseInt(getXufton.split(":")[1]));

            TimeTimer timer = new TimeTimer(bomdod, peshin, asr, shom, xufton);
            timerRepository.add(timer);
            new SenderController().run();
        }, 0, 1, TimeUnit.DAYS);
    }
}