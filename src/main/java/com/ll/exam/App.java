package com.ll.exam;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner sc;
    int wisesayingfindid;
    List<WiseSaying> wiseSayings;
    App() {
        sc = new Scanner(System.in);
        wisesayingfindid = 0;
        wiseSayings = new ArrayList<>();
    }
    public void run() {
        System.out.println("=== 명언 SSG ===");

        outer:
        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            switch (cmd){
                case "종료":
                    break outer;
                case "등록":
                    ++wisesayingfindid;

                    System.out.print("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    String author = sc.nextLine().trim();

                    WiseSaying wiseSaying = new WiseSaying(wisesayingfindid, content, author);
                    wiseSayings.add(wiseSaying);
                    System.out.println(wisesayingfindid + "번 명언이 등록되었습니다.");
                    System.out.println(wiseSaying.toString());
                    continue;
                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("------------------");

                    WiseSaying wiseSaying_;
                    for (int i = wiseSayings.size() - 1; i >= 0; i--){
                        wiseSaying_ = wiseSayings.get(i);
                        System.out.println(wiseSaying_.id + " / " + wiseSaying_.content + " / " + wiseSaying_.author);
                    }
                    continue;

            }
        }
    }
}
