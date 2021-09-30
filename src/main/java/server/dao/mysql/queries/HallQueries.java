package server.dao.mysql.queries;


/**
 * Store strings which contains queries for SQL to work with hall, rows and seats entities
 */
public interface HallQueries {
    String GET_HALL = "Select * from hall where id_hall=?";
    String GET_ALL_COLUMN = "Select * from `column`";
    String GET_ALL_SEATS_FOR_COLUMN = "Select * from seat join column_has_seat on seat.id_seat = column_has_seat.id_seat where id_column=?";
    String GET_BOOKED_SEATS = "call get_booked_seats(?)";
    String GET_COLUMN = "select * from `column` where id_column = ?";
}
