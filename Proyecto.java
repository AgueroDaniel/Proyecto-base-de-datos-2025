//Acceder a base de datos
import conector.SQLConnection;
import java.util.Scanner;
//import conector.op;
//Para printear
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public int Agregar() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Ingrese los datos del usuario,\nPrimero su nro de identificación: ");
    int nro_ide = sc.nextInt();
    System.out.print("Dirección del usuario(entre 35 caracteres): ");
    String direccion = sc.nextLine();
    System.out.print("Telefono(unico): ");
    int telefono = sc.nextInt();
    try (Connection conn = connect();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO usuario (nro_ide,dirrecion,tel) VALUES (?,?,?)")) {
        ps.setInt(1, nro_ide);
        ps.setString(2, direccion);
        ps.setInt(3, telefono);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.print("ERROR al ingresar.\n");
    }
    sc.close();
    return 0;
}




public static int verificar(String ing){
    ing=ing.toLowerCase();
    if (ing.equals("salir")||ing.equals("exit")||ing.equals("4")){
        return -1;
    }else{
        if (ing.equals("agregar")||ing.equals("1")){
            return Agregar();
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
