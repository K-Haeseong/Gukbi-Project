package com.uliieumi.customized.policy.domain.data;


public enum EducationLevel {

    PREHS("preHS", "고졸 미만"),
    HSENROLL("hsEnroll", "고졸 재학"),
    HSEXP("hsExp", "고졸 예정"),
    HSGRAD("hsGrad", "고교 졸업"),
    UNIVENROLL("univEnroll", "대학 재학"),
    UNIVEXP("univExp", "대졸 예정"),
    UNIVGRAD("univGrad", "대학 졸업"),
    ADVANCED("advanced", "석·박사"),
    NOLIMIT("noLimit", "제한없음");


    public String param;
    public String text;


    EducationLevel(String param, String text) {
        this.param = param;
        this.text = text;
    }
}
