
package vallegrande.edu.pe.misistema.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/salon_belleza"
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASSWORD = "salon123";

    private Conexion() {
    }

    public static Connection getConexion() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("¡Conexión a MySQL Docker exitosa!");

            return conexion;

        } catch (ClassNotFoundException e) {

            throw new SQLException(
                    "No se encontró el controlador MySQL.",
                    e
            );
        }
    }
}
