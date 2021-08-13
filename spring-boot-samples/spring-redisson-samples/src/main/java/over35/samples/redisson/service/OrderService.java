package over35.samples.redisson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import over35.samples.redisson.model.Order;

/**
 * @author: daibin
 * @date: 2021/8/3 12:28 下午
 */
@Service
public class OrderService {

    @Autowired
    private StockService stockService;

    void operatePurchaseOrders(Order order) {
        Long productId = order.getProductId();
        Long purchaseQuantity = order.getPurchaseQuantity();
//        stockService.deductionOfInventory(productId,purchaseQuantity);
    }
}
