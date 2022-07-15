package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    WiseSayingRepository wiseSayingRepository;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingRepository = new WiseSayingRepository();
    }
    public void write(Rq rq) {
        ++wiseSayingRepository.wiseSayingLastId;

        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();

        WiseSaying wiseSaying = new WiseSaying(wiseSayingRepository.wiseSayingLastId, content, author);
        wiseSayingRepository.wiseSayings.add(wiseSaying);
        System.out.println(wiseSayingRepository.wiseSayingLastId + "번 명언이 등록되었습니다.");
        System.out.println(wiseSaying.toString());
    }
    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------");

        WiseSaying wiseSaying_;
        for (int i = wiseSayingRepository.wiseSayings.size() - 1; i >= 0; i--){
            wiseSaying_ = wiseSayingRepository.wiseSayings.get(i);
            System.out.println(wiseSaying_.id + " / " + wiseSaying_.content + " / " + wiseSaying_.author);
        }
    }
    public void remove(Rq rq) {
        int paramId = rq.getIntParam("id",0);
        if (paramId == 0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

        // 찾지 못했다면 중지
        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }
        wiseSayingRepository.wiseSayings.remove(foundWiseSaying);
        System.out.println(paramId+ "번째 명언이 삭제되었습니다.");
    }
    public void modify(Rq rq) {
        int paramId = rq.getIntParam("id",0);
        if (paramId == 0){
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

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

}
