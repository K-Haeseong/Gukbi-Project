package com.uliieumi.customized.policy.web.util;

import com.uliieumi.customized.policy.domain.data.JobState;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;

//@Converter
@Component
public class JobStateFormatter implements Formatter<JobState> {


    // 넘어온 값을 파싱해서 JobState 변환
    @Override
    public JobState parse(String text, Locale locale) throws ParseException {
        return Arrays.stream(JobState.values())
                .filter(data -> data.param.equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 parameter"));
    }

    @Override
    public String print(JobState object, Locale locale) {
        return object.text;
    }
}
