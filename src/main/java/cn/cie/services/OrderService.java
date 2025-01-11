package cn.cie.services;

import cn.cie.utils.Result;

import java.util.List;


public interface OrderService {

    /**
     * Create a new order
     * @param uid User ID
     * @param gids List of game IDs
     * @return Result of order creation
     */
    Result addOrders(int uid, List<Integer> gids);

    /**
     * Cancel an order
     * @param orderid Order ID
     * @return Result of order cancellation
     */
    Result cancelOrder(int uid, int orderid);

    /**
     * Pay for an order
     * @param uid User ID
     * @param orderid Order ID
     * @return Result of payment
     */
    Result pay(int uid, int orderid);

    /**
     * Check if an order exists
     * @param orderid Order ID
     * @return True if the order exists, false otherwise
     */
    boolean exists(int orderid);

    /**
     * Retrieve unpaid orders
     * @param uid User ID
     * @return Result containing unpaid orders
     */
    Result getNotPayOrders(int uid, int page);

    /**
     * Retrieve paid orders
     * @param uid User ID
     * @return Result containing paid orders
     */
    Result getPaidOrders(int uid, int page);

    /**
     * Retrieve canceled orders
     * @param uid User ID
     * @return Result containing canceled orders
     */
    Result getCancelOrders(int uid, int page);

    /**
     * Automatically cancel orders that have not been paid within 15 minutes.
     * A scheduler checks every 1 minute.
     */
    void autoCancelOrder();

}