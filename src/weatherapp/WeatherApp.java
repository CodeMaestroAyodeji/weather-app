package weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {
    private static final String API_KEY = "817fed35b7ce9538504d53ae0e469457";

    public static JSONObject getWeatherData(String locationName) {
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + locationName + "&appid=" + API_KEY + "&units=metric";
        return fetchWeather(urlString);
    }

    public static JSONArray getWeatherForecast(String locationName) {
        String urlString = "https://api.openweathermap.org/data/2.5/forecast?q=" + locationName + "&appid=" + API_KEY + "&units=metric";
        JSONObject response = fetchWeather(urlString);
        if (response != null) {
            return (JSONArray) response.get("list"); // Get the list of forecasts
        }
        return null;
    }

    private static JSONObject fetchWeather(String urlString) {
        try {
            HttpURLConnection conn = fetchApiResponse(urlString);
            if (conn.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }
            scanner.close();
            conn.disconnect();

            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(resultJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection fetchApiResponse(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        return conn;
    }
}
