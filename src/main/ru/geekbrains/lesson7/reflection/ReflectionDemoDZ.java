package ru.geekbrains.lesson7.reflection;

import ru.geekbrains.lesson7.orm.Repository;
import ru.geekbrains.lesson7.orm.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReflectionDemoDZ {

    public static void main(String[] args) {

        String stringSQL;
        Connection conn = null;
        final String connectionString = "jdbc:mysql://localhost:3306/network_chat" +
                "?allowPublicKeyRetrieval=TRUE" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";

        try {
            conn = DriverManager.getConnection(connectionString,
                    "root", "DiasTopaz3922");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Repository<User> userRepository = null;
        try {
            userRepository = new Repository<>(conn, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stringSQL = userRepository.buildCreateTableStatement();
        System.out.println(stringSQL);
        try {
            userRepository.createTableIfNotExists(stringSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = new User(0, "Вася", "1001");
        try {
            userRepository.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
