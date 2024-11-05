package com.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database {
    String filePath;
    Connection connection = null;

    public Database(String filePath) {
        this.filePath = filePath;
    }

    public void connect(){
        try {
            // MariaDB JDBC 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("[에러] 드라이버 로딩 실패: " + e.getMessage());
        }
        Properties props = new Properties();

        String url = null;
        String user = null;
        String password = null;
        try {
            InputStream input = new FileInputStream(filePath);
            props.load(input);
            // 데이터베이스 연결 정보
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("[에러] 데이터베이스 연결 실패: " + e.getMessage());
        }

        Connection conn = null;

        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스에 연결
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("데이터베이스 연결 성공");
            // Statement 객체 생성
            this.connection = conn;

        } catch (SQLException e) {
            // 연결 실패 시 오류 메시지 출력
            System.out.println("[에러] 데이터베이스 연결 실패: " + e.getMessage());
        } finally {

        }
    }

    public void prep(){
        Statement stmt = null;

        try {
            stmt = connection.createStatement();

            String sqlDrop = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sqlDrop);


            String sqlCreate = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT(5) AUTO_INCREMENT PRIMARY KEY , " +
                    "name VARCHAR(14) NOT NULL, " +
                    "phone VARCHAR(13), " +
                    "email VARCHAR(50), " +
                    "`group` VARCHAR(13), " +
                    "birth DATE, " +
                    "`date` DATE" +
                    ")";
            stmt.executeUpdate(sqlCreate);

            String sqlInsert = "INSERT INTO users (name, phone, email, `group`, birth, `date`) VALUES " +
                    "('홍길동', '010-2222-3333', 'hong@test.com', '친구', '2018-01-01', CURDATE())," +
                    "('박문수', '010-2222-3333', 'park@test.com', '친구', '2018-01-01', CURDATE())," +
                    "('이몽룡', '010-2222-3333', 'lee@test.com', '가족', '2018-01-01', CURDATE())";

            stmt.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            System.out.println("[에러]: " + e.getMessage());
        } finally {
            if(stmt != null) { try { stmt.close(); } catch (SQLException e) {} }

        }
    }

    public void close(){
        if (connection != null) { try { connection.close(); } catch (SQLException e) {} }
    }

}
