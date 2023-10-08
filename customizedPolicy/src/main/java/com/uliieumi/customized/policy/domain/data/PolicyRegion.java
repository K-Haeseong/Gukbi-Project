package com.uliieumi.customized.policy.domain.data;


import java.util.Arrays;

public enum PolicyRegion {

    SEOUL("서울"),
    GYUNGGI("경기"),
    INCHEON("인천"),
    BUSAN("부산"),
    DAEGU("대구"),
    GWANGJU("광주"),
    DAEJUN("대전"),
    ULSAN("울산"),
    GANGWON("강원"),
    CHUNGBUK("충북"),
    CHUNGNAM("충남"),
    JEONBUK("전북"),
    JEONNAM("전남"),
    GYUNGBUK("경북"),
    GYUNGNAM("경남"),
    JEJU("제주"),
    SEJONG("세종"),
    NOLIMIT("제한없음");


    public String text;

    public static PolicyRegion findByName(String name){
        return Arrays.stream(PolicyRegion.values())
                .filter(data -> data.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못 저장된 파라미터입니다."));
    }

    PolicyRegion(String text) {
        this.text = text;
    }


    public String getName(){
        return this.name();
    }
}
