package server.dao;

import entity.hall.Column;
import entity.hall.Hall;
import entity.hall.Seat;
import entity.session.Session;

import java.util.List;

public interface HallDAO {
    /**
     * @param id - id for hall to get from database
     * @return Hall object
     */
    Hall getHall(int id);

    /**
     * @param id - id for Column(Row) to get from database
     * @return
     */
    Column getColumn(int id);

    /**
     * @return List of all Columns from database
     */
    List<Column> getColumns();

    /**
     * @param session - session to get seats from
     * @return List of booked seats from cinema session
     */
    List<Seat> getBookedSeats(Session session);
}
