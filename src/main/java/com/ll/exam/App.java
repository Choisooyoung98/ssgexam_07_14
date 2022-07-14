package com.ll.exam;

import java.util.Scanner;

public class App {
    Scanner sc;
    String cmd;
    App() {
        sc = new Scanner(System.in);
    }
    void run() {
        System.out.println("=== 명언 SSG ===");
        while(true){
            cmd = sc.nextLine().trim();
            outer:
            switch (cmd){
                case "종료":
                    break outer;
            }
        }

    }
}
