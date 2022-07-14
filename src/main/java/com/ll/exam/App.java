package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc;
    private int wiseSayingLastId;
    private List<WiseSaying> wiseSayings;
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
        int paramId = rq.getIntParam("id",0);
        if (paramId == 0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWiseSaying = findById(paramId);

        // 찾지 못했다면 중지
        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }
        wiseSayings.remove(foundWiseSaying);
        System.out.println(paramId+ "번째 명언이 삭제되었습니다.");
    }
    private void modify(Rq rq) {
        int paramId = rq.getIntParam("id",0);
        if (paramId == 0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWiseSaying = findById(paramId);

        // 찾지 못했다면 중지
        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }
        System.out.println("명언(기존) : " + foundWiseSaying.content);
        System.out.print("명언) ");
        foundWiseSaying.content = sc.nextLine().trim();
        System.out.println("작가(기존) : " + foundWiseSaying.author);
        System.out.print("작가) ");
        foundWiseSaying.author = sc.nextLine().trim();

        System.out.println(paramId + "번째 명언이 수정되었습니다.");
    }
    private WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings){
            if (wiseSaying.id == paramId)
                return wiseSaying;
        }
        return null;
    }
}
