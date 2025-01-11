package cn.cie.schedule;

import cn.cie.services.OrderService;
import cn.cie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled Task
 */
@Component
public class Scheduler {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Scheduled(fixedRate = 1000 * 60 * 3)
    public void delNotValidateUser() {
        System.out.println("delNotValidateUser");

        userService.delNotValidateUser();
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void expireToken() {
        System.out.println("expireToken");
        userService.expireToken();
    }

    @Scheduled(fixedRate = 1000 * 6)
    public void cancelOrder() {
        System.out.println("cancelOrder");
        orderService.autoCancelOrder();
    }

}
