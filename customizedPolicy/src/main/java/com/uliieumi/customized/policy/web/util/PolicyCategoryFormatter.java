package com.uliieumi.customized.policy.web.util;

import com.uliieumi.customized.policy.domain.data.PolicyCategory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;

//@Converter
@Component
public class PolicyCategoryFormatter implements Formatter<PolicyCategory> {


    // 넘어온 값을 파싱해서 PolicyCategory로 변환
    @Override
    public PolicyCategory parse(String text, Locale locale) throws ParseException {
        return Arrays.stream(PolicyCategory.values())
                .filter(data -> data.param.equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 parameter"));
    }

    @Override
    public String print(PolicyCategory object, Locale locale) {
        return object.text;
    }
}
