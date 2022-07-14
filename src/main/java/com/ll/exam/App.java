package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc;
    private int wiseSayingLastId;
    private List<WiseSaying> wiseSayings;
    int paramId;
    public App() {
        sc = new Scanner(System.in);
        wiseSayingLastId = 0;
        wiseSayings = new ArrayList<>();
    }
    public void run() {
        System.out.println("=== 명언 SSG ===");

        outer:
        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);
            switch (rq.getPath()){

                case "등록":
                    write(rq);
                    continue;
                case "목록":
                    list(rq);
                    continue;
                case "수정":
                    modify(rq);
                    continue;
                case "삭제":
                    remove(rq);
                    continue;
                case "종료":
                    break outer;
            }
        }
    }
    private void write(Rq rq) {
        ++wiseSayingLastId;

        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();

        WiseSaying wiseSaying = new WiseSaying(wiseSayingLastId, content, author);
        wiseSayings.add(wiseSaying);
        System.out.println(wiseSayingLastId + "번 명언이 등록되었습니다.");
        System.out.println(wiseSaying.toString());
    }
    private void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------");

        WiseSaying wiseSaying_;
        for (int i = wiseSayings.size() - 1; i >= 0; i--){
            wiseSaying_ = wiseSayings.get(i);
            System.out.println(wiseSaying_.id + " / " + wiseSaying_.content + " / " + wiseSaying_.author);
        }
    }
    private void remove(Rq rq) {
        paramId = rq.getIntParam("id",0);
        if (paramId == 0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying wiseSaying__ = null;
        for(WiseSaying wiseSaying___ : wiseSayings){
            if (wiseSaying___.id == paramId)
                wiseSaying__ = wiseSaying___;
        }
        if (wiseSaying__ == null){
            System.out.println(paramId+ "번째 명언은 존재하지 않습니다.");
            return;
        }
        wiseSayings.remove(wiseSaying__);
        System.out.println(paramId+ "번째 명언이 삭제되었습니다.");
    }
    private void modify(Rq rq) {
        paramId = rq.getIntParam("id",0);
        if (paramId == 0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying wiseSaying__ = null;
        for(WiseSaying wiseSaying___ : wiseSayings){
            if (wiseSaying___.id == paramId)
                wiseSaying__ = wiseSaying___;
        }
        if (wiseSaying__ == null){
            System.out.println(paramId+ "번째 명언은 존재하지 않습니다.");
            return;
        }
    }
    private WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings){
            if (wiseSaying.id == paramId)
                return wiseSaying;
        }
        return null;
    }
}
