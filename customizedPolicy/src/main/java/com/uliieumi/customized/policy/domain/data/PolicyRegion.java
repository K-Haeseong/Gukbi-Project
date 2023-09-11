package com.uliieumi.customized.policy.domain.data;


import java.util.Arrays;

public enum PolicyRegion {

    SEOUL("Seoul", "서울"),
    BUSAN("Busan", "부산"),
    DAEGU("Daegu", "대구"),
    INCHEON("Incheon", "인천"),
    GWANGJU("Gwangju", "광주"),
    DAEJUN("Daejun", "대전"),
    ULSAN("Ulsan", "울산"),
    GYUNGI("Gyungi", "경기"),
    GANGWON("Gangwon", "강원"),
    CHUNGBUK("ChungBuk", "충북"),
    CHUNGNAM("ChungNam", "충남"),
    JEANBUK("JeanBuk", "전북"),
    JEANNAM("JeanNam", "전남"),
    GYUNGBUK("GyungBuk", "경북"),
    GYUNGNAM("GyungNam", "경남"),
    JEJU("Jeju", "제주"),
    SEJONG("Sejong", "세종");


    public String param;
    public String text;

    public static PolicyRegion findByName(String name){
        return Arrays.stream(PolicyRegion.values())
                .filter(data -> data.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못 저장된 파라미터입니다."));
    }

    PolicyRegion(String param, String text) {
        this.param = param;
        this.text = text;
    }
}
