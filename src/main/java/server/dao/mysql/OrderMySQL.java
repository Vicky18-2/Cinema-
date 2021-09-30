package server.dao.mysql;

import entity.Ticket;
import entity.hall.Seat;
import entity.order.Order;
import entity.order.OrderStatus;
import entity.user.User;
import org.apache.log4j.Logger;
import server.dao.OrderDAO;
import server.dao.mysql.queries.OrderQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMySQL implements OrderDAO, Connectable {
    private static final Logger log = Logger.getLogger(OrderMySQL.class);

    private static OrderMySQL dao;

    public static synchronized OrderMySQL getInstance() {
        log.trace("Try get instance.");
        if (dao == null) {
            log.trace("Instance not found. Create new.");
            dao = new OrderMySQL();
        }
        return dao;
    }

    public void createOrder(Order order) {
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        int orderId;
        List<Ticket> tickets = order.getCart();
        ArrayList<Integer> ticketsId = new ArrayList<>();
        try {
            log.debug("Try to create order");
            log.debug("Setting AutoCommit to false");
            con.setAutoCommit(false);
            st = con.prepareStatement(OrderQueries.SQL_ADD_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
            st = mapOrder(st, order);
            st.executeUpdate();
            log.debug("Order added");
            rs = st.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
                log.debug("Inserted order id --> " + orderId);
            } else {
                log.error("No data inserted");
                throw new SQLException("addOrder: No data inserted");
            }
            log.debug("Adding selected tickets to db");
            st = con.prepareStatement(OrderQueries.SQL_ADD_TICKET, PreparedStatement.RETURN_GENERATED_KEYS);
            for (Ticket t : tickets
            ) {
                st = mapTicket(st, t);
                st.executeUpdate();
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    ticketsId.add(rs.getInt(1));
                    log.debug("Getting ticket id");
                } else {
                    log.error("No data inserted");
                    throw new SQLException("addTicket: No data inserted");
                }
            }
            log.debug("Tickets added");
            st.executeBatch();
            log.debug("Try to add tickets to order");
            st = con.prepareStatement(OrderQueries.SQL_ADD_TO_CART);
            for (Integer t : ticketsId
            ) {
                st.setInt(1, orderId);
                st.setInt(2, t);
                st.addBatch();
            }
            st.executeBatch();
            MysqlDAOFactory.commit(con);
        } catch (SQLException e) {
            log.debug("Error while creating new order");
            MysqlDAOFactory.rollback(con);
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.setAutocommit(con, true);
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
    }

    /**
     * @param st    PreparedStatement to be filled
     * @param order to fill st with information
     * @return filled PreparedStatement
     * @throws SQLException if error occures during PreparedStatement.set
     */
    public PreparedStatement mapOrder(PreparedStatement st, Order order) throws SQLException {
        st.setInt(1, order.getId());
        st.setString(2, order.getStatus().toString());
        st.setInt(3, order.getUser().getId());
        st.setDate(4, order.getDate());
        st.setFloat(5, order.getTotal_price());
        return st;
    }

    /**
     * @param st     PreparedStatement to fill with data
     * @param ticket object to fill st with data
     * @return filled PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement mapTicket(PreparedStatement st, Ticket ticket) throws SQLException {
        st.setInt(1, ticket.getId());
        st.setInt(2, ticket.getSeat().getId());
        st.setInt(3, ticket.getSession().getId());
        st.setInt(4, ticket.getColumn().getId());
        st.setString(5,"UNBLOCKED" );
        return st;
    }

    public Ticket unmapTicket(ResultSet rs) throws SQLException {
        Ticket result = new Ticket(rs.getInt("id_ticket")
                , SessionMySQL.getInstance().getSession(rs.getInt("id_session"))
                ,HallMySQL.getInstance().getSeatById(rs.getInt("id_seat"))
                , HallMySQL.getInstance().getColumn(rs.getInt("id_column")));
        result.getSeat().setColumn(result.getColumn());
        return result;
    }

    @Override
    public ArrayList<Ticket> reportForFilm(int id) {
        ArrayList<Ticket> result = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement(OrderQueries.SQL_REPORT_FOR_FILM);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()){
                result.add(unmapTicket(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }

    @Override
    public ArrayList<Order> getOrdersForUser(User user) {
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Order> result = new ArrayList<>();
        int order_id;
        try{
            st = con.prepareStatement(OrderQueries.SQL_GET_ORDERS_FOR_USER);
            st.setInt(1, user.getId());
            rs = st.executeQuery();
            while(rs.next()){
                ArrayList ticks = new ArrayList();
                Order newOrder = new Order(rs.getInt("id_order"), ticks, OrderStatus.valueOf(rs.getString("order_status")),
                        user, rs.getDate("date"), rs.getInt("total_price"));
                order_id = newOrder.getId();
                st = con.prepareStatement(OrderQueries.SQL_GET_TICKET_FOR_ORDER);
                st.setInt(1, order_id);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()){
                    ticks.add(unmapTicket(resultSet));
                }
                newOrder.setCart(ticks);
                result.add(newOrder);
            }
            return result;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            MysqlDAOFactory.closeResultSet(rs);
            MysqlDAOFactory.closeStatement(st);
            MysqlDAOFactory.close(con);
        }
        return result;
    }
}
