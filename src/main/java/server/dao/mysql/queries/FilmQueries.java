package server.dao.mysql.queries;

/**
 * Store strings which contains queries for SQL to work with films, genres an director entities
 */
public interface FilmQueries {
    String SQL_ADD_FILM = "INSERT INTO `film`\n" +
            "(`id_film`,`description`,`name`,`date_entry`,`time_duration`,`id_director`,`descriptionUa`,\n" +
            "`nameUa`,`image`) VALUES (?,?,?,?,?,?,?,?,?);";

    String SQL_ADD_GENRE_TO_FILM = "insert into film_genre values(?, ?)";

    String SQL_GET_FILM = "Select * from film where id_film = ?";

    String SQL_GET_ALL_FILM = "Select * from film";

    String SQL_GET_ALL_FILM_PAGE = "Select * from film limit ?,?";

    String SQL_GET_DIRECTOR = "Select * from director where id_director = ?";

    String SQL_GET_ALL_DIRECTOR = "Select * from director";

    String SQL_GET_ALL_GENRE = "Select * from genre";

    String SQL_GET_GENRE = "Select * from genre where id_genre = ?";

    String SQL_GET_DIRECTOR_FROM_FILM = "Select * from director " +
            "join film on director.id_director = film.id_director" +
            " where id_film = ?";

    String SQL_GET_GENRES_FROM_FILM = "Select * from genre" +
            " join  film_genre on film_genre.id_genre = genre.id_genre" +
            " where id_film = ?";

    String SQL_GET_FILMS_FOR_DATE = "call get_film_for_date(?)";

    String SQL_GET_SESSION_FOR_FILM = "select * from session where id_film = ?";

    String SQL_ADD_DIRECTOR = "Insert into director(id_director,name) values(?,?)";

    String SQL_ADD_GENRE = "Insert into genre(id_genre,name) values(?,?)";

    String SQL_GET_FILM_COUNT = "Select count(*) as val from film";

    String SQL_UPDATE_FILM = "UPDATE `film`\n" +
            "SET\n" +
            "`description` = ?,\n" +
            "`name` = ?,\n" +
            "`date_entry` = ?,\n" +
            "`time_duration` = ?,\n" +
            "`id_director` = ?,\n" +
            "`descriptionUa` = ?,\n" +
            "`nameUa` = ?,\n" +
            "`image` = ?\n" +
            "WHERE `id_film` = ?;";

    String SQL_DELETE_FILM = "Delete from film where id_film = ?";
}
