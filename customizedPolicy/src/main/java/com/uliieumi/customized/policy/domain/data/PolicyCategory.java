package com.uliieumi.customized.policy.domain.data;


public enum PolicyCategory {

    SPACE("space", "주거"),
    EDUCATION("education", "교육"),
    FINANCE("finance", "금융"),
    LIVING("living", "생활지원"),
    HEALTH("health", "건강"),
    JOB("job", "일자리"),
    HOUSE("house", "주거"),
    CAREER("career", "진로"),
    STARTUP("startup", "창업"),
    CULTURE("culture", "문화·예술");


    public String text;
    public String name;


    PolicyCategory(String value, String name) {
        this.text = value;
        this.name = name;
    }
}
