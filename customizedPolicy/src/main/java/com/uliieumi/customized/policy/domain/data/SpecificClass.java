package com.uliieumi.customized.policy.domain.data;


public enum SpecificClass {

    LOWINC("lowInc", "저소득층"),
    DISABILITY("disability", "장애인"),
    MILITARY("military", "군인"),
    FARMER("farmer", "농업인"),
    LOCALTALENT("localTalent", "지역인재"),
    NOLIMIT("noLimit", "제한없음");


    public String param;
    public String text;


    SpecificClass(String param, String text) {
        this.param = param;
        this.text = text;
    }
}
