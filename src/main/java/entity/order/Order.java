package entity.order;

import entity.Ticket;
import entity.user.User;

import java.sql.Date;
import java.util.List;
import java.util.Objects;


/**
 * Java bean for order entity
 */
public class Order {
    /**
     * id - order id
     * cart - number of tickets that was ordered
     * status - order status(paid/unpaid)
     * user - user that created this order
     * date - order creation date
     * total_price - full price for all ordered tickets
     */
    private int id;
    private List<Ticket> cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private OrderStatus status;
    private User user;
    private Date date;
    private int total_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ticket> getCart() {
        return cart;
    }

    public void setCart(List<Ticket> cart) {
        this.cart = cart;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    /**
     * Constructor for new order
     *
     * @param cart
     * @param user
     */
    public Order(List<Ticket> cart, User user) {
        this.status = OrderStatus.valueOf("UNPAID");
        this.date = new Date(System.currentTimeMillis());
        this.cart = cart;
        this.user = user;
        this.total_price = 0;
    }

    public Order(int id, List<Ticket> cart, OrderStatus status, User user, Date date, int total_price) {
        this.id = id;
        this.cart = cart;
        this.status = status;
        this.user = user;
        this.date = date;
        this.total_price = total_price;
    }

    public Order(List<Ticket> cart, OrderStatus status, User user, Date date, int total_price) {
        this.cart = cart;
        this.status = status;
        this.user = user;
        this.date = date;
        this.total_price = total_price;
    }

}
