package weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;
    private JLabel weatherConditionImage;
    private JTextField searchTextField;
    private JPanel forecastPanel;
    private DefaultListModel<String> historyListModel;

    public WeatherAppGui() {
        super("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 700); // Reduced the height by 100px
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {
        searchTextField = new JTextField();
        searchTextField.setBounds(15, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchTextField);

        weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));
        weatherConditionImage.setBounds(0, 125, 450, 217);
        add(weatherConditionImage);

        // History Panel
        JLabel historyLabel = new JLabel("Search History:");
        historyLabel.setBounds(15, 525, 150, 25); // Pushed upward by 100px
        historyLabel.setForeground(Color.WHITE); // Set text color to white
        add(historyLabel);

        historyListModel = new DefaultListModel<>();
        JList<String> historyList = new JList<>(historyListModel);
        JScrollPane historyScrollPane = new JScrollPane(historyList);
        historyScrollPane.setBounds(15, 550, 410, 100); // Pushed upward by 100px
        add(historyScrollPane);
        
        // Forecast Panel
        JLabel forecastLabel = new JLabel("Forecast:");
        forecastLabel.setBounds(15, 425, 150, 25); // Pushed upward by 100px
        forecastLabel.setForeground(Color.WHITE); // Set text color to white
        add(forecastLabel);
        
        forecastPanel = new JPanel();
        forecastPanel.setBounds(15, 450, 410, 70); // Pushed upward by 100px
        forecastPanel.setLayout(new GridLayout(1, 3));
        add(forecastPanel);

        JButton searchButton = new JButton(loadImage("src/assets/search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String locationName = searchTextField.getText().trim();
                if (locationName.isEmpty()) {
                    return;
                }
                weatherData = WeatherApp.getWeatherData(locationName);
                updateGui();
                updateForecast(locationName);
                updateHistory(locationName);
            }
        });
        add(searchButton);
    }

    private void updateGui() {
        if (weatherData == null) {
            return;
        }

        // No need to update temperature and weather condition text anymore
        // You can still update the image if needed
        String weatherCondition = (String) weatherData.get("weather_condition");
        String weatherImage = getWeatherImage(weatherCondition);
        weatherConditionImage.setIcon(loadImage("src/assets/" + weatherImage));
        weatherConditionImage.repaint();
    }

    private void updateForecast(String locationName) {
        forecastPanel.removeAll();
        JSONArray forecastData = WeatherApp.getWeatherForecast(locationName);
        if (forecastData != null) {
            for (int i = 0; i < 3; i++) { // Show 3-day forecast
                JSONObject forecast = (JSONObject) forecastData.get(i * 8); // Data every 3 hours, select 1 per day
                JSONObject main = (JSONObject) forecast.get("main");
                double temp = (double) main.get("temp");
                String condition = (String) ((JSONObject) ((JSONArray) forecast.get("weather")).get(0)).get("main");

                // Get the relevant weather image
                String weatherImage = getWeatherImage(condition);

                // Create a panel for each day's forecast
                JPanel dayPanel = new JPanel();
                dayPanel.setLayout(new BorderLayout());

                // Add the temperature label (on the left)
                JLabel tempLabel = new JLabel(String.format("%.1f°C", temp));
                tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
                tempLabel.setFont(new Font("Dialog", Font.PLAIN, 16)); // Adjust font size
                dayPanel.add(tempLabel, BorderLayout.CENTER);

                // Load and resize the weather icon
                ImageIcon icon = loadImage("src/assets/" + weatherImage);
                if (icon != null) {
                    Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH); // Resize to 24x24
                    icon = new ImageIcon(img);
                }

                // Add the weather icon label (on the right)
                JLabel weatherIconLabel = new JLabel(icon);
                weatherIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
                dayPanel.add(weatherIconLabel, BorderLayout.SOUTH);

                // Add the day panel to the forecast panel
                forecastPanel.add(dayPanel);
            }
        }
        forecastPanel.revalidate();
        forecastPanel.repaint();
    }



    private void updateHistory(String locationName) {
        historyListModel.addElement(locationName + " - " + java.time.LocalDateTime.now());
    }

    private ImageIcon loadImage(String resourcePath) {
        try {
            BufferedImage image = ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getWeatherImage(String weatherCondition) {
        if (weatherCondition == null) return "cloudy.png"; // Default image
        weatherCondition = weatherCondition.toLowerCase();
        if (weatherCondition.contains("clear")) {
            return "clear.png";
        } else if (weatherCondition.contains("cloud")) {
            return "cloudy.png";
        } else if (weatherCondition.contains("rain")) {
            return "rain.png";
        } else if (weatherCondition.contains("snow")) {
            return "snow.png";
        } else if (weatherCondition.contains("storm")) {
            return "storm.png";
        } else {
            return "cloudy.png"; // Fallback image
        }
    }

}
