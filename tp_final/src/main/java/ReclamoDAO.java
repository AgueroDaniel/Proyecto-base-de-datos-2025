import java.sql.ResultSet;
import java.sql.SQLException;

public class ReclamoDAO {
    private SQLConnection connection;

    public ReclamoDAO() {
        try {
            connection = SQLConnection.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("An error has been occurred, please try it again later");
        }
    }

    public void disconnect() {
        connection.disconnect();
    }

    /**
     * @param dir
     * @param tel
     * @return User Id in db
     */
    public void insertarUsuario(int id, String dir, int tel) {
        try {
            int affected = connection.executeUpdate(
                    "INSERT INTO usuario (nro_ide, direcion, tel) VALUES (?, ?, ?)",
                    id, dir, tel);

            if (affected > 0) {
                System.out.println("Usuario insertado correctamente.");
            } else {
                System.out.println("ERROR al ingresar.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR al ingresar: " + e.getMessage());
        }
    }

    /**
     * @param dir
     * @param tel
     * @return User Id in db
     */
    public void eliminarReclamo(int nro) {
        try {
            int affected = connection.executeUpdate("DELETE FROM reclamo WHERE nro = ?", nro);
            if (affected > 0) {
                System.out.println("Reclamo eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún reclamo con ese número.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el reclamo: ");
        }
    }

    /**
     * @param dir
     * @param tel
     * @return User Id in db
     */
    public void listarReclamosUsuario(int nroUser) {
        try (ResultSet rs = connection.executeQuery(
            "SELECT nro, fecha, hora, fecha_resol, cod_motivo FROM reclamo WHERE nro_usuario = ?", 
            nroUser
        )) {
            while (rs.next()) {
                System.out.printf("- Reclamo #%d | Fecha: %s | Hora: %s | Fecha Resolución: %s | Motivo: %d%n",
                        rs.getInt("nro"), // Número de reclamo
                        rs.getDate("fecha"), // Fecha del reclamo
                        rs.getTime("hora"), // Hora del reclamo
                        rs.getDate("fecha_resol"), // Fecha de resolución
                        rs.getInt("cod_motivo") // Código del motivo (ya no se muestra la descripción)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = connection.executeQuery(
            "SELECT COUNT(*) AS cantidad FROM reclamo WHERE nro_usuario = ?", 
            nroUser
        )) {
            while (rs.next()) {
                System.out.println("\n Total de reclamos: " + rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
