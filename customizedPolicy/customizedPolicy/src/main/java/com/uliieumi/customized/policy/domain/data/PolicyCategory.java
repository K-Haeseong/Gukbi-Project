package com.uliieumi.customized.policy.domain.data;


public enum PolicyCategory {

    SPACE("공간"),
    EDUCATION("교육"),
    FINANCE("금융"),
    LIVING("생활지원"),
    HEALTH("건강"),
    JOB( "일자리"),
    HOUSE("주거"),
    CAREER("진로"),
    STARTUP("창업"),
    CULTURE("문화·예술"),
    NOLIMIT("제한없음");


    public String text;


    PolicyCategory(String text) {
        this.text = text;
    }

    public String getName(){
        return this.name();
    }
}
