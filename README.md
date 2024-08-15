# Weather App


This repository contains the source code for a simple Weather Application built in Java. The project is part of my coursework for the Programming 2 course in the Bachelor's degree in Computer Science program at the University of the People (UoP).

## Student Details

- **Name:** Ayodeji Ajuwon
- **Student ID:** S419561
- **University:** [University of the People](https://www.uopeople.edu/)
- **Course:** CS 1103-01 Programming 2 - AY2024-T5
- **Instructor:** Shuchi Dhir
- **Term:** Term 5


## Description
The Weather App is a desktop application built using Java and Swing that allows users to search for and view the current weather conditions and a 3-day weather forecast for any location. The app fetches data from a weather API and displays it in a user-friendly graphical interface. It also maintains a search history for quick access to previous queries.

## Features
- **Search by Location**: Users can enter the name of a city or town to retrieve weather data.
- **Current Weather Display**: Shows the current weather condition, including temperature and weather type.
- **3-Day Forecast**: Provides a three-day weather forecast with relevant icons for conditions like rain, cloudy, and sunny.
- **Search History**: Keeps a log of recent searches for easy access.
- **Dynamic Background**: The app's background changes according to the weather conditions.

## Requirements
- Java 8 or later
- Internet connection (for fetching weather data)

## Installation and Setup
# 1. Clone the repository:
	 > git clone https://github.com/yourusername/weather-app.git
	 > cd weather-app
	
# Compile the project:
Ensure you have the Java Development Kit (JDK) installed. Then, compile the Java files:
	
	> javac -d bin src/weatherapp/*.java
# Run the application:
After compiling, you can run the application using:
	
	> java -cp bin weatherapp.WeatherAppGui
	
# Directory Structure
The project is organized as follows:

	WeatherAPP/
	├── src/
	│   └── weatherapp/
	│       ├── WeatherAppGui.java
	│       ├── WeatherService.java
	│       
	├── src/assets/
	│         ├── clear.png
	│         ├── cloudy.png
	│         ├── humidity.png
	│         ├── search.png
	│         ├── windspeed.png
	│         ├── rain.png
	│         └── snow.png
	│
	├── lib/
	│      ├── json-simple-1.1.1.jar
	│
	├── README.md
	│
	└── bin/
	
+ **src/weatherapp/:** Contains the Java source files.
+ **weatherapp/src/assets/:** Contains image assets used in the GUI.
+ **bin/:** Contains the compiled .class files.


## Usage
+ **Search for Weather:** Enter the name of a city or town in the search bar and click the search icon.
+ **View Forecast:** The current weather and a 3-day forecast will be displayed, with relevant icons.
+ **Check Search History:** The bottom panel displays your recent searches.

## License

## Acknowledgments
+ Weather data is provided by [OpenWeatherMap](https://openweathermap.org/).
+ Icons used in the app are sourced from the src/weatherapp/assets directory.

 