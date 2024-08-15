package weatherapp;

import javax.swing.*;
import java.awt.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGui gui = new WeatherAppGui();
            gui.getContentPane().setBackground(getBackgroundColor());
            gui.setVisible(true);
        });
    }

    private static Color getBackgroundColor() {
        int hour = java.time.LocalTime.now().getHour();
        if (hour >= 6 && hour < 18) {
            return Color.CYAN; // Daytime
        } else {
            return Color.DARK_GRAY; // Nighttime
        }
    }
}
