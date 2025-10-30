import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

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
    public int insertarUsuario(String dir, int tel) {
        return 0;
    }

    /**
     * @param dir
     * @param tel
     * @return User Id in db
     */
    public int eliminarReclamo(int nro) {
        Scanner sc = new Scanner(System.in); // Permite ingresar el número del reclamo por consola
        System.out.print("Ingrese el número del reclamo que desea eliminar: ");
        int nroReclamo = sc.nextInt(); // Lee el número entero que se ingresa(el número de reclamo) y lo guarda en la
                                       // variable nroReclamo.
        StringBuilder sb = new StringBuilder("DELETE FROM reclamo WHERE nro = ");
        sb.append(nro);


        if (PreparedStatement ps = conn.prepareStatement()) {
            // Lo de arriba crea un objeto PrepareStatement que ejecuta una sentencia SQL
            // para eliminar un registro de la tabla reclamo
            // El signo ? es un parametro que se reemplaza por el numero de reclamo
            ps.setInt(1, nroReclamo); // Reemplaza el primer parametro (?) del SQL por el valor ingresado
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) { // Si hubo modificacion se informara por pantalla los siguientes mensajes
                System.out.println("Reclamo eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún reclamo con ese número.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el reclamo: " + e.getMessage());
        }

        db.disconnect(); // Cierra la conexion con la base de datos
        sc.close(); // Cierra el Scanner
        return 0;
    }

    /**
     * @param dir
     * @param tel
     * @return User Id in db
     */
    public void listarReclamosUsuario(int nroUser) {
    }
}
