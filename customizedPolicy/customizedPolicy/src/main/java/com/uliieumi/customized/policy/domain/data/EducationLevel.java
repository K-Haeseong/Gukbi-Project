package com.uliieumi.customized.policy.domain.data;


public enum EducationLevel {

    PREHS("고졸 미만"),
    HSENROLL("고졸 재학"),
    HSEXP( "고졸 예정"),
    HSGRAD("고교 졸업"),
    UNIVENROLL("대학 재학"),
    UNIVEXP("대졸 예정"),
    UNIVGRAD("대학 졸업"),
    ADVANCED( "석·박사"),
    NOLIMIT("제한없음");


    public String text;


    EducationLevel(String text) {
        this.text = text;
    }

    public String getName(){
        return this.name();
    }
}
