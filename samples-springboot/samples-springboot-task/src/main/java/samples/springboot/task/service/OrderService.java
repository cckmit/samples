package samples.springboot.task.service;

import lombok.extern.slf4j.Slf4j;
import over35.commons.ttl.TtlMDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: daibin
 * @date: 2021/8/4 3:01 下午
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private NotifyTask notifyTask;

    public void notificationSuccessful() throws InterruptedException {
        log.info("订单已完成，准备通知");
        String uuid = UUID.randomUUID().toString();
        log.info("线程传递tv1池值：{}", uuid);

        TtlMDC.put("T_V_1", uuid);
        notifyTask.doTask();
        TtlMDC.clear();
        log.info("线程传递值已清除：{}", TtlMDC.getCopyOfContextMap());

    }
}
