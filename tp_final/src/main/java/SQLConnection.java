import java.sql.*;

public class SQLConnection {

    // Parámetros de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/MI_BASE_DE_DATOS?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root123";

    private Connection connection;
    private static SQLConnection sqlConnection;

    private SQLConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Conexión establecida correctamente.");
    }

    /**
     * Intenta abrir la conexión con el servidor MySQL.
     */
    public static SQLConnection getInstance() throws ClassNotFoundException, SQLException {
        if (sqlConnection == null) {
            sqlConnection = new SQLConnection();
        }
        return sqlConnection;
    }

    /**
     * Devuelve la conexión actual (puede ser null si no se ha conectado
     * correctamente).
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Ejecuta una consulta SELECT y devuelve el ResultSet.
     * Recuerda cerrar el ResultSet y Statement luego de usarlos.
     * 
     * @throws SQLException
     */
    public ResultSet executeQuery(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        setParameters(statement, params);
        return statement.executeQuery();
    }

    /**
     * Ejecuta una instrucción INSERT, UPDATE o DELETE.
     * Devuelve el número de filas afectadas.
     * @throws SQLException 
     */
    public int executeUpdate(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        setParameters(statement, params);
        return statement.executeUpdate();
    }

    /**
     * Asigna los parámetros al PreparedStatement.
     */
    private void setParameters(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    /**
     * Cierra la conexión si está abierta.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}