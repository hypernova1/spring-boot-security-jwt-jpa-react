package com.sam.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class ApiApplicationTests {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/polling_app?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PW = "1111";

    @Test
    void testConnection() throws ClassNotFoundException {
//        Class.forName(DRIVER);
//
//        try(Connection conn = DriverManager.getConnection(URL, USER, PW)) {
//            System.out.println(conn);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

    }

}
