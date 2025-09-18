
import java.util.*;

// WeatherRecord
class WeatherRecord {

    String date;       // format: DD/MM/YYYY
    String city;
    double temperature;

    WeatherRecord(String date, String city, double temperature) {
        this.date = date;
        this.city = city;
        this.temperature = temperature;
    }
}

// Data Storage Class
class WeatherDataSystem {

    private double[][] temperatureArray; // 2D array [year][cityIndex]
    private String[] cities;
    private int startYear;
    private int endYear;
    private double sentinel = -9999.0; // sentinel value for missing data

    // Constructor
    WeatherDataSystem(String[] cities, int startYear, int endYear) {
        this.cities = cities;
        this.startYear = startYear;
        this.endYear = endYear;
        int years = endYear - startYear + 1;
        this.temperatureArray = new double[years][cities.length];
        for (int i = 0; i < years; i++) {
            Arrays.fill(this.temperatureArray[i], sentinel);
        }
    }

    // Insert a new record
    public void insert(String date, String city, double temperature) {
        int year = Integer.parseInt(date.split("/")[2]);
        int row = year - startYear;
        int col = getCityIndex(city);
        if (row >= 0 && row < temperatureArray.length && col != -1) {
            temperatureArray[row][col] = temperature;
        }
    }

    // Delete a record
    public void delete(String date, String city) {
        int year = Integer.parseInt(date.split("/")[2]);
        int row = year - startYear;
        int col = getCityIndex(city);
        if (row >= 0 && row < temperatureArray.length && col != -1) {
            temperatureArray[row][col] = sentinel;
        }
    }

    // Retrieve a record
    public double retrieve(String city, int year) {
        int row = year - startYear;
        int col = getCityIndex(city);
        if (row >= 0 && row < temperatureArray.length && col != -1) {
            return temperatureArray[row][col];
        }
        return sentinel;
    }

    // Populate array with sample data
    public void populateArray() {
        insert("01/01/2022", "Delhi", 25.5);
        insert("01/01/2022", "Mumbai", 30.2);
        insert("01/01/2023", "Delhi", 22.4);
        insert("01/01/2023", "Mumbai", 31.1);
        insert("01/01/2024", "Chennai", 29.9);
    }

    // Display row-major
    public void rowMajorAccess() {
        System.out.println("\n--- Row-Major Access ---");
        printHeader();
        for (int i = 0; i < temperatureArray.length; i++) {
            System.out.print((startYear + i) + "\t| ");
            for (int j = 0; j < temperatureArray[i].length; j++) {
                if (temperatureArray[i][j] != sentinel) {
                    System.out.printf("%.1f\t", temperatureArray[i][j]); 
                }else {
                    System.out.print("-\t");
                }
            }
            System.out.println();
        }
    }

    // Display column-major
    public void columnMajorAccess() {
        System.out.println("\n--- Column-Major Access ---");
        printHeader();
        for (int j = 0; j < cities.length; j++) {
            System.out.print(cities[j] + "\t| ");
            for (int i = 0; i < temperatureArray.length; i++) {
                if (temperatureArray[i][j] != sentinel) {
                    System.out.printf("%.1f\t", temperatureArray[i][j]); 
                }else {
                    System.out.print("-\t");
                }
            }
            System.out.println();
        }
    }

    // Sparse data display
    public void handleSparseData() {
        System.out.println("\n--- Sparse Data (Only Valid Records) ---");
        System.out.println("Year\tCity\tTemperature");
        for (int i = 0; i < temperatureArray.length; i++) {
            for (int j = 0; j < cities.length; j++) {
                if (temperatureArray[i][j] != sentinel) {
                    System.out.printf("%d\t%s\t%.1f\n", startYear + i, cities[j], temperatureArray[i][j]);
                }
            }
        }
    }

    // Complexity analysis
    public void analyzeComplexity() {
        System.out.println("\n--- Complexity Analysis ---");
        System.out.println("Insert: Time O(1), Space O(1)");
        System.out.println("Delete: Time O(1), Space O(1)");
        System.out.println("Retrieve: Time O(1), Space O(1)");
        System.out.println("Row-Major Traversal: Time O(n*m), Space O(1)");
        System.out.println("Column-Major Traversal: Time O(n*m), Space O(1)");
        System.out.println("Sparse Handling: Time O(n*m), Space O(k), k = number of valid entries");
    }

    // Utility: get city index
    private int getCityIndex(String city) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equalsIgnoreCase(city)) {
                return i;
            }
        }
        return -1;
    }

    // Print table header
    private void printHeader() {
        System.out.print("Year\t| ");
        for (String city : cities) {
            System.out.print(city + "\t");
        }
        System.out.println("\n-----------------------------");
    }

    // Interactive terminal menu
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Weather Data System Menu ===");
            System.out.println("1. Insert Record");
            System.out.println("2. Delete Record");
            System.out.println("3. Retrieve Record");
            System.out.println("4. Row-Major View");
            System.out.println("5. Column-Major View");
            System.out.println("6. Sparse Data View");
            System.out.println("7. Complexity Analysis");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter date (DD/MM/YYYY): ");
                    String date = sc.nextLine();
                    System.out.print("Enter city: ");
                    String city = sc.nextLine();
                    System.out.print("Enter temperature: ");
                    double temp = sc.nextDouble();
                    sc.nextLine();
                    insert(date, city, temp);
                    System.out.println("Record inserted successfully!");
                }
                case 2 -> {
                    System.out.print("Enter date (DD/MM/YYYY): ");
                    String date = sc.nextLine();
                    System.out.print("Enter city: ");
                    String city = sc.nextLine();
                    delete(date, city);
                    System.out.println("Record deleted successfully!");
                }
                case 3 -> {
                    System.out.print("Enter city: ");
                    String city = sc.nextLine();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    double value = retrieve(city, year);
                    if (value != sentinel) {
                        System.out.println("Temperature: " + value); 
                    }else {
                        System.out.println("No data found!");
                    }
                }
                case 4 ->
                    rowMajorAccess();
                case 5 ->
                    columnMajorAccess();
                case 6 ->
                    handleSparseData();
                case 7 ->
                    analyzeComplexity();
                case 0 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default ->
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

// Main class
public class WeatherSystem {

    public static void main(String[] args) {
        String[] cities = {"Delhi", "Mumbai", "Chennai"};
        WeatherDataSystem system = new WeatherDataSystem(cities, 2022, 2024);
        system.populateArray();
        system.menu(); // run interactive menu
    }
}
