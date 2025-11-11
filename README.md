
3rd)
Arduino Code (Temperature, Humidity & Rain Detection)

#include <DHT.h>
#define DHTTYPE DHT11

int SensorPin = 2;           // DHT11 signal pin connected to D2
DHT dht(SensorPin, DHTTYPE); // Create DHT object
int raindropPin = A0;        // Raindrop sensor analog output to A0
float temperature = 0;
float humidity = 0;
int raindropValue = 0;

void setup() {
  Serial.begin(115200);      // Start Serial Monitor
  dht.begin();               // Initialize DHT11 sensor
  delay(2000);
  pinMode(SensorPin, INPUT); // DHT sensor input
  pinMode(raindropPin, INPUT); // Raindrop sensor input
}

void loop() {
  // Read temperature and humidity
  temperature = dht.readTemperature();
  humidity = dht.readHumidity();

  // Read raindrop sensor value (analog)
  raindropValue = analogRead(raindropPin);

  // Display results
  Serial.print("Temperature: ");
  Serial.print(temperature);
  Serial.print(" °C | ");

  Serial.print("Humidity: ");
  Serial.print(humidity);
  Serial.print(" % | ");

  Serial.print("RaindropValue: ");
  Serial.println(raindropValue);

  delay(2000);  // Wait 2 seconds before next reading
}

DHT11 Sensor

DHT11 Pin	Connect to Arduino	Description

VCC	5V	Power supply
GND	GND	Ground
OUT / DATA	D2	Digital data pin


Raindrop Sensor Module

Raindrop Module Pin	Connect to Arduino	Description

VCC	5V	Power supply
GND	GND	Ground
AO (Analog Output)	A0	Analog output (used in code)
DO (Digital Output)	(optional, e.g., D3)	Digital output if threshold detection is used












2nd

#include <DHT.h>               // Include DHT library

#define DHTTYPE DHT11          // Define sensor type as DHT11
int SensorPin = 2;             // DHT11 data pin connected to digital pin 2
DHT dht(SensorPin, DHTTYPE);   // Create DHT sensor object

int buzzerPin = 3;             // Buzzer connected to pin 3
int Temperature = 0;           // Variable to store temperature
int thresholdValue = 25;       // Temperature limit (in °C)

void setup() {
  Serial.begin(9600);          // Start serial communication
  dht.begin();                 // Initialize DHT sensor
  delay(2000);                 // Give sensor time to start
  pinMode(SensorPin, INPUT);   // Sensor pin as input
  pinMode(buzzerPin, OUTPUT);  // Buzzer pin as output
}

void loop() {
  Temperature = dht.readTemperature();    // Read temperature from DHT11
  Serial.print("Temperature: ");
  Serial.print(Temperature);
  Serial.println(" °C");

  // Check if temperature exceeds threshold
  if (Temperature > thresholdValue) {
    digitalWrite(buzzerPin, HIGH);  // Turn ON buzzer
  } else {
    digitalWrite(buzzerPin, LOW);   // Turn OFF buzzer
  }

  delay(2000); // Wait for 2 seconds before next reading
}






Supply

DHT11 VCC	5V	Power supply
DHT11 GND	GND	Ground
DHT11 DATA	D2	Sensor data output to Arduino
Buzzer + (positive)	D3	Control signal from Arduino
Buzzer – (negative)	GND	Connect to Arduino ground


















1st


int irPin = 2;       // IR sensor output pin connected to digital pin 2
int ledPin = 3;      // LED connected to digital pin 3
int irValue;         // Variable to store IR sensor reading

void setup() {
  Serial.begin(9600);        // Start serial communication
  pinMode(irPin, INPUT);     // Set IR sensor pin as input
  pinMode(ledPin, OUTPUT);   // Set LED pin as output
}

void loop() {
  irValue = digitalRead(irPin);   // Read data from IR sensor
  Serial.print("IR Value: ");
  Serial.println(irValue);        // Display value on Serial Monitor

  if (irValue == 1) {
    digitalWrite(ledPin, HIGH);   // Turn ON LED when object detected
  } 
  else {
    digitalWrite(ledPin, LOW);    // Turn OFF LED when no object
  }

  delay(200); // Small delay for stability
}




IR Sensor OUT pin	D2	Signal output to Arduino
IR Sensor VCC	5V	Power supply to sensor
IR Sensor GND	GND	Ground connection
LED Anode (+)	D3 (through 220Ω resistor)	LED control pin
LED Cathode (-)	GND	Ground connection











