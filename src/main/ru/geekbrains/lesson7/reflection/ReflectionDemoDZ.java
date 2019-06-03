package ru.geekbrains.lesson7.reflection;

import ru.geekbrains.lesson7.orm.Repository;
import ru.geekbrains.lesson7.orm.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReflectionDemoDZ {

    public static void main(String[] args) {

        String rez;
        final String connectionString = "jdbc:mysql://localhost:3306/network_chat" +
                "?allowPublicKeyRetrieval=TRUE" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";

        try {
            Connection conn = DriverManager.getConnection(connectionString,
                    "root", "DiasTopaz3922");
            Repository<User> userRepository = new Repository<>(conn, User.class);
            rez = userRepository.buildCreateTableStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
