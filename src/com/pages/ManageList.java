package com.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class ManageList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 데이터베이스 기본 로드 데이터
        try {
            // MariaDB JDBC 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("[에러] 드라이버 로딩 실패: " + e.getMessage());
        }
        Properties props = new Properties();
        String filePath = "./src/com/pages/config.properties";
        System.out.println("Working Directory= " + System.getProperty("user.dir"));

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
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스에 연결
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("데이터베이스 연결 성공");
            // Statement 객체 생성

            String sqlDrop = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sqlDrop);

            stmt = conn.createStatement();
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
                    "('이몽룡', '010-2222-3333', 'lee@test.com', '가족', '2018-01-01', CURDATE()),";

            stmt.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            // 연결 실패 시 오류 메시지 출력
            System.out.println("[에러] 데이터베이스 연결 실패: " + e.getMessage());
        } finally {
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) {} }
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} }
        }

        whileLoop:
        while(true) {
            // 기본 페이지
            System.out.println("회원 관리 프로그램");
            System.out.println("=========================================");
            System.out.println("1. 회원정보목록");
            System.out.println("2. 회원정보등록");
            System.out.println("3. 회원정보수정");
            System.out.println("4. 회원정보삭제");
            System.out.println();
            System.out.println("5. 쪽지 보내기");
            System.out.println("6. 종료");
            System.out.println("=========================================");
            System.out.print("메뉴를 입력하세요 : ");
            String option = sc.next();

            switch (option) {
                case "1":
                    list(stmt, rs);
                    break;
                case "2":
                    create(sc);
                    break;
                case "3":
                    update(sc);
                    break;
                case "4":
                    delete(sc);
                    break;
                case "5":
                    sendMessage(sc);
                    break;
                case "6":
                    System.out.println("프로그램을 종료합니다.");
                    break whileLoop;
                default:
                    System.out.println("[에러]: 잘못된 값이 입력되었습니다");
                    break;
            }
        }
    }

    public static void list(Statement stmt, ResultSet rs){
        System.out.println("회원 관리 프로그램");
        System.out.println("=========================================");
        System.out.println("번호\t 이름\t 연락처\t\t 이쪽지\t\t 그룹\t 생년월일\t 등록일\t");
        System.out.println("=========================================");

        // TODO: DB에서 정보 불러오기
        try {
            String sql = "SELECT *, count(*) as count FROM users";
            rs = stmt.executeQuery(sql);
            String count = "";
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String group = rs.getString("group");
                Date birth = rs.getDate("birth");
                Date date = rs.getDate("date");
                count = rs.getString("count");


                System.out.printf("%s\t %s\t %s\t\t %s\t\t %s\t %s\t %s\t%n", id, name, phone, email, group, birth, date);
            }
            System.out.printf("총 %s명=====================================", count);
            System.out.println();
        } catch (Exception e) {
            System.out.println("[에러]: " + e.getMessage());
        } finally {
            if(stmt != null) { try { stmt.close(); } catch (SQLException e) {} }
            if(rs != null) try { rs.close(); } catch (SQLException e) {}
        }
    }

    public static void create(Scanner sc){
        System.out.println("등록할 회원정보를 입력해주세요");
        System.out.print("▶▶ 이름 : ");
        String name = sc.nextLine();
        System.out.print("▶▶ 연락처 : ");
        String phone = sc.nextLine();
        System.out.print("▶▶ 이쪽지 : ");
        String email = sc.nextLine();
        System.out.print("▶▶ 그룹 : ");
        String group = sc.nextLine();
        System.out.print("▶▶ 생년월일 : ");
        String birth = sc.nextLine();
        System.out.println();

        System.out.print("회원정보를 등록하시겠습니까(y/n) ? ");
        if(sc.next().equals("y")) {
            System.out.println("회원정보를 정상적으로 등록하였습니다.");
            // TODO: 회원정보 DB에 저장

        } else {
            System.out.println("회원정보등록에 실패했습니다.");
        }

    }

    public static void update(Scanner sc){
        System.out.print("수정할 회원의 등록번호를 입력해주세요 ? ");
        String id = sc.nextLine();

        // TODO: id와 DB에서의 id 비교
        //      exist =>
        //      not exist => System.out.println("입력하신 회원등록번호에 해당하는 회원은 존재하지 않습니다.");
        String name = "";
        String phone = "";
        String email = "";
        String group = "";
        String birth = "";
        System.out.printf("[%s]님의 회원정보\n", id);
        System.out.printf("이름: %s\n", name);
        System.out.printf("연락처 : %s\n", phone);
        System.out.printf("이쪽지 : %s\n", email);
        System.out.printf("그룹 : %s\n", group);
        System.out.printf("생년월일 : %s\n", birth);
        System.out.println();

        // TODO: 검색된 회원정보 불러오기
        System.out.print("회원정보수정을 계속하시겠습니까(y/n) ? ");
        if(sc.next().equals("y")) {
            System.out.println("** 입력하지 않으면 기존의 정보가 그대로 유지됩니다.");
            System.out.printf("▶▶ 수정할 이름 : ");
            String updateName = sc.nextLine();
            System.out.printf("▶▶ 수정할 연락처 : ");
            String updatePhone = sc.nextLine();
            System.out.printf("▶▶ 수정할 이쪽지 : ");
            String updateEmail = sc.nextLine();
            System.out.printf("▶▶ 수정할 그룹 : ");
            String updateGroup = sc.nextLine();
            System.out.printf("▶▶ 수정할 생년월일 : ");
            String updateBirth = sc.nextLine();
            System.out.println("회원정보를 정상적으로 수정하였습니다.");
        } else {
            System.out.println("회원정보수정에 실패했습니다.");
        }

        // TODO: DB에 정보 UPDATE
    }

    public static void delete(Scanner sc){
        System.out.print("삭제할 회원의 등록번호를 입력해주세요 ?");
        String id = sc.nextLine();

        // TODO: DB에서 검색
        //      exist =>
        //      not exist => System.out.println("입력하신 회원등록번호에 해당하는 회원은 존재하지 않습니다.");
        String name = "";
        String phone = "";
        String email = "";
        String group = "";
        String birth = "";
        System.out.printf("[%s]님의 회원정보\n", id);
        System.out.printf("이름: %s\n", name);
        System.out.printf("연락처 : %s\n", phone);
        System.out.printf("이쪽지 : %s\n", email);
        System.out.printf("그룹 : %s\n", group);
        System.out.printf("생년월일 : %s\n", birth);
        System.out.println();

        System.out.print("회원정보삭제를 계속하시겠습니까(y/n) ? ");
        if(sc.next().equals("y")) {
            System.out.println("회원정보를 정상적으로 삭제하였습니다.");
            // TODO: DB에서 정보 삭제
        }else{
            System.out.println("회원정보삭제에 실패했습니다.");
        }
    }

    public static void sendMessage(Scanner sc){
        System.out.print("삭제할 회원의 등록번호를 입력해주세요 ?");
        String id = sc.nextLine();

        // TODO: DB에서 검색
        //      exist =>
        //      not exist => System.out.println("입력하신 회원등록번호에 해당하는 회원은 존재하지 않습니다.");
        System.out.print("▶▶ 쪽지 제목 : ");
        String msgTitle = sc.nextLine();
        System.out.print("▶▶ 쪽지 내용 : ");
        String msgBody = sc.nextLine();

        System.out.print("쪽지 보내시겠습니니까(y/n) ? ");
        if(sc.next().equals("y")) {
            System.out.println("쪽지가 정상적으로 발송되었습니다.");
        }else{
            System.out.println("쪽지 발송에 실패했습니다.");
        }
    }

}
