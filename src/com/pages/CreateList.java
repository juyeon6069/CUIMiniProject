package com.pages;

import java.util.Scanner;

public class CreateList {
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
}
