package com.pages;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Util {
    private Database db;

    public Util(Database db) {
        this.db = db;
    }

    public void show(){
        System.out.println("회원 관리 프로그램");
        System.out.println("=========================================");
        System.out.println("번호\t 이름\t 연락처\t\t 이쪽지\t\t 그룹\t 생년월일\t 등록일\t");
        System.out.println("=========================================");
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM users";
            rs = db.pExecuteQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String group = rs.getString("group");
                Date birth = rs.getDate("birth");
                Date date = rs.getDate("date");

                System.out.printf("%s\t %s\t %s\t\t %s\t\t %s\t %s\t %s\t%n", id, name, phone, email, group, birth, date);
            }
            String sqlCount = "SELECT count(*) as count FROM users";
            rs = db.pExecuteQuery(sqlCount);
            String count = "";
            if (rs.next()) {
                count = rs.getString("count");
            }
            System.out.printf("총 %s명=====================================", count);
        } catch (SQLException e) {
            System.out.println("[에러]: " + e.getMessage());
        } finally {
            if(rs != null) { try { rs.close(); } catch (SQLException e) {} }
        }
        System.out.println();
    }

    public void create(Scanner sc){
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
            String sql = String.format("INSERT INTO users (name, phone, email, `group`, birth, `date`) VALUES ('%s', '%s', '%s', '%s', '%s', CURDATE())",
                    name, phone, email, group, birth);
            db.executeUpdate(sql);
        } else {
            System.out.println("회원정보등록에 실패했습니다.");
        }

    }

    public void update(Scanner sc){
        ResultSet rs = null;
        System.out.print("수정할 회원의 등록번호를 입력해주세요 ? ");
        String sid = sc.nextLine();
        int id = Integer.parseInt(sid);

        String name = "";
        String phone = "";
        String email = "";
        String group = "";
        String birth = "";
        String sql = String.format("SELECT * FROM users WHERE id = %d", id);
        rs = db.pExecuteQuery(sql);

        try {
            if(id == rs.getInt("id")){

                name = rs.getString("name");
                phone = rs.getString("phone");
                email = rs.getString("email");
                group = rs.getString("group");
                birth = rs.getString("birth");
                Date date = rs.getDate("date");


                System.out.printf("[%s]님의 회원정보\n", id);
                System.out.printf("이름: %s\n", name);
                System.out.printf("연락처 : %s\n", phone);
                System.out.printf("이쪽지 : %s\n", email);
                System.out.printf("그룹 : %s\n", group);
                System.out.printf("생년월일 : %s\n", birth);
                System.out.println();


                System.out.print("회원정보수정을 계속하시겠습니까(y/n) ? ");
                if (sc.next().equals("y")) {
                    System.out.println("** 입력하지 않으면 기존의 정보가 그대로 유지됩니다.");
                    System.out.printf("▶▶ 수정할 이름 : ");
                    name = sc.nextLine();
                    System.out.printf("▶▶ 수정할 연락처 : ");
                    phone = sc.nextLine();
                    System.out.printf("▶▶ 수정할 이쪽지 : ");
                    email = sc.nextLine();
                    System.out.printf("▶▶ 수정할 그룹 : ");
                    group = sc.nextLine();
                    System.out.printf("▶▶ 수정할 생년월일 : ");
                    birth = sc.nextLine();

                    String sqlUpdate = String.format("UPDATE users SET name = '%s', phone = '%s', email = '%s', group = '%s', birth = '%s' WHERE id = %d",
                            name, phone, email, group, birth, id);
                    db.executeUpdate(sqlUpdate);

                    System.out.println("회원정보를 정상적으로 수정하였습니다.");
                } else {
                    System.out.println("회원정보수정에 실패했습니다.");
                }

            } else  {
                System.out.println("입력하신 회원등록번호에 해당하는 회원은 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            System.out.println("[에러]"+e.getMessage());
        }finally {
            if(rs != null) { try { rs.close(); } catch (SQLException e) {} }
        }

    }

    public void delete(Scanner sc){
        ResultSet rs = null;

        System.out.print("삭제할 회원의 등록번호를 입력해주세요 ?");
        String sid = sc.nextLine();
        int id = Integer.parseInt(sid);

        String name = "";
        String phone = "";
        String email = "";
        String group = "";
        String birth = "";

        String sql = String.format("SELECT * FROM users WHERE id = %d", id);
        rs = db.pExecuteQuery(sql);

        try {
            if(id == rs.getInt("id")){

                name = rs.getString("name");
                phone = rs.getString("phone");
                email = rs.getString("email");
                group = rs.getString("group");
                birth = rs.getString("birth");
                Date date = rs.getDate("date");

                System.out.printf("[%s]님의 회원정보\n", id);
                System.out.printf("이름: %s\n", name);
                System.out.printf("연락처 : %s\n", phone);
                System.out.printf("이쪽지 : %s\n", email);
                System.out.printf("그룹 : %s\n", group);
                System.out.printf("생년월일 : %s\n", birth);
                System.out.println();

                System.out.print("회원정보삭제를 계속하시겠습니까(y/n) ? ");
                if (sc.next().equals("y")) {
                    System.out.println("회원정보를 정상적으로 삭제하였습니다.");
                    String sqlDelete = String.format("DELETE FROM users WHERE id = %d", id);
                    db.executeUpdate(sqlDelete);
                } else {
                    System.out.println("회원정보삭제에 실패했습니다.");
                }
            } else {
                System.out.println("입력하신 회원등록번호에 해당하는 회원은 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            System.out.println("[에러]"+e.getMessage());
        } finally {
            if(rs != null){try { rs.close(); } catch (SQLException e) {} }
        }
    }

    public void sendMessage(Scanner sc){
        ResultSet rs = null;
        System.out.print("쪽지를 보낼 회원의 등록번호를 입력해주세요 ? ");
        String sid = sc.nextLine();
        int id = Integer.parseInt(sid);

        String sql = String.format("SELECT * FROM users WHERE id = %d", id);
        rs = db.pExecuteQuery(sql);

        try {
            if(id == rs.getInt("id")){

                System.out.print("▶▶ 쪽지 제목 : ");
                String msgTitle = sc.nextLine();
                System.out.print("▶▶ 쪽지 내용 : ");
                String msgBody = sc.nextLine();

                System.out.print("쪽지 보내시겠습니니까(y/n) ? ");
                if (sc.next().equals("y")) {
                    System.out.println("쪽지가 정상적으로 발송되었습니다.");
                } else {
                    System.out.println("쪽지 발송에 실패했습니다.");
                }
            } else {
                System.out.println("입력하신 회원등록번호에 해당하는 회원은 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            System.out.println("[에러]"+e.getMessage());
        } finally {
            if(rs != null){try { rs.close(); } catch (SQLException e) {} }
        }
    }
}
