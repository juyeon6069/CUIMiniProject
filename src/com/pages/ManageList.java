package com.pages;

import java.util.Scanner;

public class ManageList {
    public static void main(String[] args) {
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

        Scanner sc = new Scanner(System.in);
        String option = sc.next();

            switch (option) {
                case "1":
                    list();
                    break;
                case "2":
                    create();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    sendMessage();
                    break;
                case "6":
                    System.out.println("프로그램을 종료합니다.");
                default:
                    System.out.println("[에러]: 정확한 옵션을 선택해주세요");
                    break;
            }
    }

    public static void list(){
        System.out.println("회원 관리 프로그램");
        System.out.println("=========================================");
        System.out.println("번호\t 이름\t 연락처\t\t 이쪽지\t\t 그룹\t 생년월일\t 등록일\t");
        System.out.println("=========================================");

        // TODO: DB에서 정보 불러오기

        int count = 0;
        System.out.printf("총 %d명=====================================", count);
        System.out.println();
    }

    public static void create(){
        Scanner scc = new Scanner(System.in);
        System.out.println("등록할 회원정보를 입력해주세요");
        System.out.print("▶▶ 이름 : ");
        String name = scc.nextLine();
        System.out.print("▶▶ 연락처 : ");
        String phone = scc.nextLine();
        System.out.print("▶▶ 이쪽지 : ");
        String email = scc.nextLine();
        System.out.print("▶▶ 그룹 : ");
        String group = scc.nextLine();
        System.out.print("▶▶ 생년월일 : ");
        String birth = scc.nextLine();
        System.out.println();

        System.out.print("회원정보를 등록하시겠습니까(y/n) ? ");
        if(scc.next().equals("y")) {
            System.out.println("회원정보를 정상적으로 등록하였습니다.");
            // TODO: 회원정보 DB에 저장
        } else {
            System.out.println("회원정보등록에 실패했습니다.");
        }

    }

    public static void update(){
        Scanner scu = new Scanner(System.in);
        System.out.print("수정할 회원의 등록번호를 입력해주세요 ? ");
        String id = scu.nextLine();

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
        if(scu.next().equals("y")) {
            System.out.println("** 입력하지 않으면 기존의 정보가 그대로 유지됩니다.");
            System.out.printf("▶▶ 수정할 이름 : ");
            String updateName = scu.nextLine();
            System.out.printf("▶▶ 수정할 연락처 : ");
            String updatePhone = scu.nextLine();
            System.out.printf("▶▶ 수정할 이쪽지 : ");
            String updateEmail = scu.nextLine();
            System.out.printf("▶▶ 수정할 그룹 : ");
            String updateGroup = scu.nextLine();
            System.out.printf("▶▶ 수정할 생년월일 : ");
            String updateBirth = scu.nextLine();
            System.out.println("회원정보를 정상적으로 수정하였습니다.");
        } else {
            System.out.println("회원정보수정에 실패했습니다.");
        }

        // TODO: DB에 정보 UPDATE
    }

    public static void delete(){
        Scanner scd = new Scanner(System.in);
        System.out.print("삭제할 회원의 등록번호를 입력해주세요 ?");
        String id = scd.nextLine();

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
        if(scd.next().equals("y")) {
            System.out.println("회원정보를 정상적으로 삭제하였습니다.");
            // TODO: DB에서 정보 삭제
        }else{
            System.out.println("회원정보삭제에 실패했습니다.");
        }
    }

    public static void sendMessage(){
        Scanner scd = new Scanner(System.in);
        System.out.print("삭제할 회원의 등록번호를 입력해주세요 ?");
        String id = scd.nextLine();

        // TODO: DB에서 검색
        //      exist =>
        //      not exist => System.out.println("입력하신 회원등록번호에 해당하는 회원은 존재하지 않습니다.");
        System.out.print("▶▶ 쪽지 제목 : ");
        String msgTitle = scd.nextLine();
        System.out.print("▶▶ 쪽지 내용 : ");
        String msgBody = scd.nextLine();

        System.out.print("쪽지 보내시겠습니니까(y/n) ? ");
        if(scd.next().equals("y")) {
            System.out.println("쪽지가 정상적으로 발송되었습니다.");
        }else{
            System.out.println("쪽지 발송에 실패했습니다.");
        }
    }

}
