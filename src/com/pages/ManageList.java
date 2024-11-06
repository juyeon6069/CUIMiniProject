package com.pages;

import java.util.Scanner;

public class ManageList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 데이터베이스 기본 로드 데이터
        Database db = new Database("./src/com/pages/config.properties");
        db.connect();
        db.prep();

        Util u = new Util(db);

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
                    u.show();
                    break;
                case "2":
                    u.create(sc);
                    break;
                case "3":
                    u.update(sc);
                    break;
                case "4":
                    u.delete(sc);
                    break;
                case "5":
                    u.sendMessage(sc);
                    break;
                case "6":
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    db.close();
                    break whileLoop;
                default:
                    System.out.println("[에러]: 잘못된 값이 입력되었습니다");
                    break;
            }
        }
    }


}
