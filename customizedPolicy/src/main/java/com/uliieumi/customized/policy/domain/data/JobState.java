package com.uliieumi.customized.policy.domain.data;


public enum JobState {

    WORKER("재직자"),
    EMPLOYER("자영업자"),
    JOBLESS("미취업자"),
    FREELANCER( "프리랜서"),
    DAILY("일용근로자"),
    FOUNDER("(예비)창업자"),
    SHORT("단기근로자"),
    OTHER("기타"),
    NOLIMIT("제한없음");


    public String text;


    JobState(String text) {
        this.text = text;
    }


    public String getName(){
        return this.name();
    }
}
