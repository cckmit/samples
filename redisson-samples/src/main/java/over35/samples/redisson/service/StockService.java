package over35.samples.redisson.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import over35.samples.redisson.repository.mapper.StockMapper;
import over35.samples.redisson.model.Stock;

/**
 * @author: daibin
 * @date: 2021/8/3 12:09 下午
 */
@Service
public class StockService {


    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 扣减库存
     *
     * @param productId
     * @param purchaseQuantity
     */
    private void operateStock(Long productId, Long purchaseQuantity) {

        Stock stock = stockMapper.queryByProductId(productId);
        if (stock == null) {
            throw new RuntimeException("库存无此商品,productId:" + productId);
        }
        Long quantity = stock.getQuantity();
        int mode;
        Long quantityAfterOperation;
        if (quantity == null) {
            // 新增库存必须是正数
            if (purchaseQuantity < 0) {
                throw new RuntimeException("新增库存必须是正数,purchaseQuantity:" + purchaseQuantity);
            }
            mode = 0;
            quantityAfterOperation = purchaseQuantity;
        } else {
            // 如果存在库存
            // computeQuantity 带符号的
            Long computeQuantity = purchaseQuantity + quantity;
            if (!verifyQuantity(computeQuantity)) {
                throw new RuntimeException("计算后库存负值，错误 computeQuantity:" + computeQuantity);
            }
            mode = 1;
            quantityAfterOperation = computeQuantity;
        }


        switch (mode) {
            case 0:
                Stock po = new Stock();
                po.setId(stock.getId());
                po.setProductId(stock.getProductId());
                po.setQuantity(stock.getQuantity());
                stockMapper.save(po);
                break;
            case 1:
                Long id = stock.getId();
                stockMapper.updateQuantityById(id, quantityAfterOperation);
                break;
            default:
                throw new RuntimeException("无效操作");
        }

    }

    /**
     * 购买 减少库存
     *
     * @param productId
     * @param purchaseQuantity
     */
    public void decreaseStock(Long productId, Long purchaseQuantity) {
        if (!verifyQuantity(purchaseQuantity)) {
            throw new RuntimeException("purchaseQuantity:" + purchaseQuantity);
        }
        RLock rLock = redissonClient.getLock(String.valueOf(productId));

        try {
            rLock.lock();
            operateStock(productId, -1 * purchaseQuantity);
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 进货 增加库存
     *
     * @param productId
     * @param purchaseQuantity
     */
    public void increaseInventory(Long productId, Long purchaseQuantity) {
        if (!verifyQuantity(purchaseQuantity)) {
            throw new RuntimeException("purchaseQuantity:" + purchaseQuantity);
        }
        operateStock(productId, purchaseQuantity);
    }


    boolean verifyQuantity(Long quantity) {
        return quantity >= 0;
    }

}
