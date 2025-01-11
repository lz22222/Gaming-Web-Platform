package cn.cie.entity.dto;

import cn.cie.entity.Order;
import cn.cie.entity.Orderitem;

import java.util.Date;
import java.util.List;


public class OrderDTO {

    /**
     * Order ID
     */
    private Integer id;

    /**
     * User ID associated with the order
     */
    private Integer uid;

    /**
     * Total price of the order
     */
    private Double total;

    /**
     * Order creation time
     */
    private Date ctime;

    /**
     * Order update time
     */
    private Date utime;

    /**
     * Order status
     */
    private Byte stat;

    /**
     * Detailed information about the order
     */
    private List<OrderItemDTO> orderitems;

    public OrderDTO(Order order, List<OrderItemDTO> orderitems) {
        this.id = order.getId();
        this.uid = order.getUid();
        this.total = order.getPrice();
        this.ctime = order.getCtime();
        this.utime = order.getUtime();
        this.stat = order.getStat();
        this.orderitems = orderitems;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUid() {
        return uid;
    }

    public Double getTotal() {
        return total;
    }

    public Date getCtime() {
        return ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public Byte getStat() {
        return stat;
    }

    public List<OrderItemDTO> getOrderitems() {
        return orderitems;
    }
}
