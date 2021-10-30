package sample;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String DB_NAME = "workapp";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private Connection getConnection() throws ClassNotFoundException {
        String connection = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection dbConn = null;
        try {
            dbConn = DriverManager.getConnection(connection, LOGIN, PASSWORD);
            System.out.println("Есть контакт с базой данних");
        } catch (SQLException e) {
            System.out.println("Нет контакта с базой данних");
        }
        return dbConn;
    }
    public void insertTask(String task) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `task` (tasks) VALUES (?)";

        PreparedStatement prSt = getConnection().prepareStatement(sql);
        prSt.setString(1, task);

        prSt.executeUpdate();
    }

    public ArrayList<String> getTasks() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `task` ORDER BY `id` DESC";
        Statement statement = getConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> tasks = new ArrayList<>();
        while (res.next()){
            tasks.add(res.getString("tasks"));
        }
        return tasks;
    }
}
