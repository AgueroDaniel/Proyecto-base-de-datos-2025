//Acceder a base de datos
//import conector.SQLConnection;
import java.util.Scanner;
//import conector.op;
//Para printear
import java.io.*;

public int Agregar() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese los datos del usuario,\nPrimero su nro de identificación: ");
    int nro_ide = sc.nextInt();
    System.out.print("Dirección del usuario(entre 35 caracteres): ");
    String direccion = sc.next();
    System.out.print("Telefono(unico): ");
    int telefono = sc.nextInt();
    SQLException e;
   try (Connection conn = connect();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO usuario (nro_ide,dirrecion,tel) VALUES (?,?,?)")) {
        ps.setInt(1, nro_ide);
        ps.setString(2, direccion);
        ps.setInt(3, telefono);
        NoIngresado = false;
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.print("ERROR al ingresar.\n");
    }
    if (e.isEmpty()){
        System.out.println("Usuario agregado correctamente.");
    }
    scanner.close();
}

public int Eliminar() {
    Scanner scanner = new Scanner(System.in);
        System.out.print("nro del reclamo a eliminar: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM empleados WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Empleado eliminado.");
            else System.out.println("Empleado no encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
public int Reclamos() {
//listar reclamos por usuario con cantidad de reclamos que tuvo

}

 private static void listarEmpleados() {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM empleados")) {

            System.out.println("\n--- LISTA DE EMPLEADOS ---");
            while (rs.next()) {
                System.out.printf("%d - %s - %s - %.2f%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("puesto"),
                        rs.getDouble("salario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void actualizarEmpleado(Scanner sc) {
        System.out.print("ID del empleado a actualizar: ");
        int id = sc.nextInt();
        System.out.print("Nuevo salario: ");
        double salario = sc.nextDouble();

        String sql = "UPDATE empleados SET salario = ? WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, salario);
            ps.setInt(2, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Empleado actualizado.");
            else System.out.println("Empleado no encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}







public static int verificar(String ing){
    ing=ing.toLowerCase();
    if (ing.equals("salir")||ing.equals("exit")||ing.equals("4")){
        return -1;
    }else{
        if (ing.equals("agregar")||ing.equals("1")){
            return 1;
        }else{
            if(ing.equals("eliminar")||ing.equals("2")){
                return 0;
            }else{
                if(ing.equals("reclamos")||ing.equals("3")){
                    return 0;
                }
            }
        }
    }
    return 0;
}

public static void main(String[] args){
    int selection = 0;
    Scanner scanner = new Scanner(System.in);
    while (selection > -1){
        System.out.println("\nQue desea realizar:\n    1) Agregar un usuario(agregar).\n"+
                        "    2) Eliminar un reclamo(eliminar).\n    3) Listar reclamos(listar)\n    4) Salir:");
        String ingreso = scanner.nextLine();
        selection = verificar(ingreso);
    }
    scanner.close();
}
