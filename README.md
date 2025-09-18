
# weather data storage system 
Java console-based application that stores and manages temperature data for multiple cities over multiple years.  
The system allows inserting, deleting, retrieving, and displaying weather records in a clean tabular format.

# features
- Insert new weather records (date, city, temperature).  
- Delete existing records.  
- Retrieve temperature for a specific city and year.  
- Display all data in **row-major** and **column-major** format.  
- View **sparse data** (only valid records).  
- Provides **time and space complexity analysis** for key operations.  
- Easy-to-use **menu-driven interface**.

# Sample Output
Row-Major View: 

Year    | Delhi   Mumbai  Chennai
-----------------------------
2022    | 25.5    30.2    -
2023    | 22.4    31.1    -
2024    | -       -       29.9


Column-Major View:

Delhi   | 25.5    22.4    -
Mumbai  | 30.2    31.1    -
Chennai | -       -       29.9


Sparse Data (Valid Records Only):

Year    City    Temperature
2022    Delhi   25.5
2022    Mumbai  30.2
2023    Delhi   22.4
2023    Mumbai  31.1
2024    Chennai 29.9

# complexity analysis
Operation	Time Complexity	Space Complexity
Insert	O(1)	O(1)
Delete	O(1)	O(1)
Retrieve	O(1)	O(1)
Row-Major View	O(n*m)	O(1)
Column-Major View	O(n*m)	O(1)
Sparse Handling	O(n*m)	O(k) (valid entries)
