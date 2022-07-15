package com.ll.exam;

import java.util.Scanner;

public class App {
    private Scanner sc;

    public App() {
        sc = new Scanner(System.in);
    }
    public void run() {
        WiseSayingController wiseSayingController = new WiseSayingController(sc);
        System.out.println("=== 명언 SSG ===");

        outer:
        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);
            switch (rq.getPath()){

                case "등록":
                    wiseSayingController.write(rq);
                    continue;
                case "목록":
                    wiseSayingController.list(rq);
                    continue;
                case "수정":
                    wiseSayingController.modify(rq);
                    continue;
                case "삭제":
                    wiseSayingController.remove(rq);
                    continue;
                case "종료":
                    break outer;
            }
        }
    }

}
