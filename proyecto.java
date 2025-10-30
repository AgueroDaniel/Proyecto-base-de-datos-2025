import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class proyecto {
    // conección a una base de datos
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/reclamoempresaelectrica?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "22@$Pape";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida.");
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return connection;
    }

    public static int Agregar(Scanner sc) {
        // leo lo que ingresa la persona con el scanner
        System.out.print("Ingrese los datos del usuario,\nPrimero su nro de identificación: ");
        System.out.print("IDE: ");
        int nro_ide = sc.nextInt();
        sc.nextLine();
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Teléfono: ");
        int telefono = sc.nextInt();
        // intento de inserción
        try {
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuario (nro_ide,direccion,tel) VALUES (?,?,?)");
            ps.setInt(1, nro_ide);
            ps.setString(2, direccion);
            ps.setInt(3, telefono);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("ERROR al ingresar: " + e.getMessage());
        }
        return 0;
    }

    public static int Eliminar(Scanner sc) {
        // leo lo que ingresa la persona con el scanner
        System.out.print("Ingrese el numero,\nde reclamo a eliminar: ");
        System.out.print("nro: ");
        int nro_ide = sc.nextInt();
        sc.nextLine();
        // intento de inserción
        try {
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM reclamo WHERE nro = ?");
            ps.setInt(1, nro_ide);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("ERROR al ingresar: " + e.getMessage());
        }
        return 0;
    }

    public static int Listar(Scanner sc) {
        // leo lo que ingresa la persona con el scanner
        System.out.print("Ingrese el numero de usuario,\nal que se le quiere ver sus reclamos\n");
        System.out.print("nro: ");
        int nro_ide = sc.nextInt();
        sc.nextLine();
        try {
            Connection conn = connect();
            PreparedStatement ps = conn
                    .prepareStatement("SELECT COUNT(*) AS cantidad FROM reclamo WHERE nro_usuario = ?");
            ps.setInt(1, nro_ide);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("\nTotal de reclamos: " + rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // intento de inserción
        try {
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT nro, fecha, hora, fecha_resol, cod_motivo FROM reclamo WHERE nro_usuario = ?");
            ps.setInt(1, nro_ide);
            ResultSet rs = ps.executeQuery();
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
        return 0;
    }

    public static int verificar(String ing, Scanner sc) {
        ing = ing.toLowerCase();
        if (ing.equals("salir") || ing.equals("exit") || ing.equals("4")) {
            return -1;
        } else {
            if (ing.equals("agregar") || ing.equals("1")) {
                return Agregar(sc);
            } else {
                if (ing.equals("eliminar") || ing.equals("2")) {
                    return Eliminar(sc);
                } else {
                    if (ing.equals("reclamos") || ing.equals("3")) {
                        return Listar(sc);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int selection = 0;
        Scanner sc = new Scanner(System.in);
        while (selection > -1) {
            System.out.println("\nQue desea realizar:\n    1) Agregar un usuario(agregar).\n    " +
                    "2) Eliminar un reclamo(eliminar).\n    3) Listar reclamos(listar)\n    4) Salir:");
            String ingreso = sc.nextLine();
            selection = verificar(ingreso, sc);
        }
    }
}