package com.uliieumi.customized.policy.domain.data;


public enum JobState {

    WORKER("worker", "재직자"),
    EMPLOYER("employer", "자영업자"),
    JOBLESS("jobless", "미취업자"),
    FREELANCER("freelancer", "프리랜서"),
    DAILY("daily", "일용근로자"),
    FOUNDER("founder", "(예비)창업자"),
    SHORT("short", "단기근로자"),
    OTHER("other", "기타"),
    NOLIMIT("noLimit", "제한없음");


    public String param;
    public String text;


    JobState(String param, String text) {
        this.param = param;
        this.text = text;
    }
}
