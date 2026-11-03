package com.aibizs.einvoice.einvoice_scheduler.util;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommonUtil {
    public static final String TRACE_ID = "traceId";

    public static void generateTraceId() {
        MDC.put(CommonUtil.TRACE_ID, getTraceId());
    }

    public static String getTraceId() {
        return UUID.randomUUID().toString();
    }
}
