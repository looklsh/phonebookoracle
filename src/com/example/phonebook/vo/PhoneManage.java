package com.example.phonebook.vo;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.example.phonebook.dao.PhoneBookDAO;
import com.example.phonebook.dao.PhoneBookDAOimpl;

public class PhoneManage {
	public static void printPhoneBooks() {
		System.out.println();
		System.out.println("<1.리스트>");

		PhoneBookDAO dao = new PhoneBookDAOimpl();

		List<PhoneBookVO> phones = dao.getList();

		Iterator<PhoneBookVO> iter = phones.iterator();

		while (iter.hasNext()) {
			PhoneBookVO phone = iter.next();
			
			System.out.println(phone);

		}

	}

	public static void insertPhoneBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("<2.등록>");
		System.out.println();
		System.out.print(">이름: ");
		String name = scanner.next();
		System.out.print(">휴대전화: ");
		String hp = scanner.next();
		System.out.print(">집전화: ");
		String tel = scanner.next();
		System.out.println();
		System.out.println("[등록되었습니다]");

		PhoneBookVO phoneBook = new PhoneBookVO(null, name, hp, tel);
		PhoneBookDAO dao = new PhoneBookDAOimpl();
		boolean isSuccess = dao.insert(phoneBook);

//		printPhoneBooks();

//		scanner.close();

	}

	public static void deletePhoneBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("<3.삭제>");
		System.out.println();
		System.out.print(">번호: ");
		int id = scanner.nextInt();
		System.out.println();
		System.out.println("[삭제 되었습니다]");

		PhoneBookDAO dao = new PhoneBookDAOimpl();
		boolean isSuccess = dao.delete(Long.valueOf(id));

//		printPhoneBooks();

//		scanner.close();

	}

	public static void searchPhoneBook() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("<4.검색>");
		System.out.print(">이름: ");
		String name = scanner.next();

		PhoneBookDAO dao = new PhoneBookDAOimpl();
		List<PhoneBookVO> phoneBook = dao.search(name);

		Iterator<PhoneBookVO> iter = phoneBook.iterator();
		
		while (iter.hasNext()) {
			PhoneBookVO phone = iter.next();
			System.out.println(phone);
		}

	}

	public static void main(String[] args) {
		// printPhoneBooks();

		System.out.println("");
		System.out.println(" ************************************************* ");
		System.out.println(" *               전화번호 관리 프로그램                             * ");
		System.out.println(" ************************************************* ");

		Scanner sc = new Scanner(System.in);

		boolean circle = true;
		// loop
		while (circle) {
			System.out.println();
			System.out.println("1. 리스트   2.등록   3.삭제   4.검색   5.종료");
			System.out.println(" --------------------------------------------- ");
			System.out.print(">메뉴번호: ");

			int menuNo = sc.nextInt();

			switch (menuNo) {

			case 1:
				printPhoneBooks();
				break;
			case 2:
				insertPhoneBook();
				break;
			case 3:
				deletePhoneBook();
				break;
			case 4:
				searchPhoneBook();
				break;
			case 5:
				System.out.println();
				System.out.println("****************************************");
				System.out.println("*                감사합니다                          *");
				System.out.println("****************************************");
				circle = false;
				break;
			default:
				System.out.println("[다시 입력해주세요]");
				break;
			}
		}
		// 입력
		// 메뉴가 1번

		// 메뉴 2번
		// 데이터 INSERT
		sc.close();

	}// main

}// class
