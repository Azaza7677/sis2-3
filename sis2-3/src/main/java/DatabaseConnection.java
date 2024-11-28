import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // URL
        String user = "root"; // login
        String password = "azasql123$$"; // password

        String createTable = "CREATE TABLE IF NOT EXISTS Employees (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "age INT)";

        String insertData = "INSERT INTO Employees (name, age) VALUES " +
                "('Alice', 25), " +
                "('Bob', 30), " +
                "('Charlie', 28)";

        String selectData = "SELECT * FROM Employees"; // Чтение данных
        String deleteData = "DELETE FROM Employees WHERE name = 'Charlie'";
        String updateData = "UPDATE Employees SET age = 35 WHERE name = 'Alice'";




        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Создание таблицы
            stmt.executeUpdate(createTable);
            System.out.println("table created successfully!");

            // Вставка данных
            stmt.executeUpdate(insertData);
            System.out.println("data added successfully!");


            try (ResultSet rs = stmt.executeQuery(selectData)) {
                System.out.println("All information from the table Employees:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
                }
            } // Закрываем ResultSet после использования

            // Удаление данных
            stmt.executeUpdate(deleteData);
            System.out.println("Employee successfully deleted!");

            // Обновление данных
            stmt.executeUpdate(updateData);
            System.out.println("Data was successfully updated!");
            // Вывод данных после обновлений
            try (ResultSet rs = stmt.executeQuery(selectData)) {
                System.out.println("Data from Employees after updated:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
                }
            }

        } catch (SQLException e) {
            System.err.println("error!!: " + e.getMessage());
        }
    }
}

