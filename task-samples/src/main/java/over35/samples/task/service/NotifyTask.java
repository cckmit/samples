package over35.samples.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import over35.commons.ttl.TtlMDC;

/**
 * @author: daibin
 * @date: 2021/8/4 2:59 下午
 */
@Slf4j
@Service
public class NotifyTask {

    @Async("taskExecutor")
    void doTask() throws InterruptedException {
        log.info("线程池测试");
//        TimeUnit.MILLISECONDS.sleep(100);

        String tv1 = TtlMDC.get("T_V_1");
        log.info("线程tv1池值：{}", tv1);
    }
}
