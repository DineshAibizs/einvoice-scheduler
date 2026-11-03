package com.aibizs.einvoice.einvoice_scheduler.application;

import com.aibizs.einvoice.einvoice_scheduler.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
@Slf4j
public class FailedRetrySchedulerService {

    private final AtomicBoolean running = new AtomicBoolean(false);

    @Scheduled(cron = "${failed.retry.scheduler.expression}")
    @SchedulerLock(name = "documentScheduler",
            lockAtLeastFor = "${failed.retry.scheduler.lock.at.least}",
            lockAtMostFor = "${failed.retry.scheduler.lock.at.most}")
    public void retryFailedDocument() {

        CommonUtil.generateTraceId();
        log.info("scheduler starting ");

        if (!running.compareAndSet(false, true)) {
            return;
        }
        try {
            log.info("scheduler running");


        } finally {
            running.set(false);
        }
    }
}
