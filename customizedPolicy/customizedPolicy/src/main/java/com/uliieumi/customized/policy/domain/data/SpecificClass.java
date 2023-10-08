package com.uliieumi.customized.policy.domain.data;


public enum SpecificClass {

    LOWINC("저소득층"),
    DISABILITY("장애인"),
    MILITARY("군인"),
    FARMER("농업인"),
    LOCALTALENT( "지역인재"),
    NOLIMIT("제한없음");


    public String text;


    SpecificClass(String text) {
        this.text = text;
    }


    public String getName(){
        return this.name();
    }
}
