package server.dao.mysql.queries;

/**
 * Store strings which contains queries for SQL to work with order entity
 */
public interface OrderQueries {
    String SQL_ADD_ORDER = "INSERT INTO `order`\n" +
            "(`id_order`,`order_status`,`id_user`,`date`,`total_price`)\n" +
            "VALUES\n" +
            "(?, ?, ?, ?, ?);";

    String SQL_ADD_TICKET = "INSERT INTO `ticket`\n" +
            "(`id_ticket`,`id_seat`,`id_session`,`id_column`,`status`)\n" +
            "VALUES\n" +
            "(? ,? ,? ,?, ? );";

    String SQL_ADD_TO_CART = "INSERT INTO `orderhasticket`\n" +
            "(`id_order`,`id_ticket`)\n" +
            "VALUES\n" +
            "(? ,? );";

    String SQL_REPORT_FOR_FILM = "select * from ticket \n" +
            "join session on session.id_session = ticket.id_session\n" +
            "join film on film.id_film = session.id_film\n" +
            "where film.id_film = ?";

    String SQL_GET_ORDERS_FOR_USER = "select * from `order` where id_user = ?";

    String SQL_GET_TICKET_FOR_ORDER = "select * from ticket" +
            " join orderhasticket on ticket.id_ticket = orderhasticket.id_ticket" +
            " where id_order = ?";
}
