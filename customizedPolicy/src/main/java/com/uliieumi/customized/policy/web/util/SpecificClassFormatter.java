package com.uliieumi.customized.policy.web.util;

import com.uliieumi.customized.policy.domain.data.SpecificClass;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;

//@Converter
@Component
public class SpecificClassFormatter implements Formatter<SpecificClass> {


    // 넘어온 값을 파싱해서 SpecificClass 변환
    @Override
    public SpecificClass parse(String text, Locale locale) throws ParseException {
        return Arrays.stream(SpecificClass.values())
                .filter(data -> data.param.equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 parameter"));
    }

    // SpecificClass의 name값을 반환
    @Override
    public String print(SpecificClass object, Locale locale) {
        return object.text;
    }
}
