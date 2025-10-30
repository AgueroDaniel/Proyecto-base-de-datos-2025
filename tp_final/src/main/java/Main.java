import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReclamoDAO dao = new ReclamoDAO();
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("=== Sistema de Reclamos ===");
            System.out.println("1. Insertar usuario");
            System.out.println("2. Eliminar reclamo");
            System.out.println("3. Listar reclamos de un usuario");
            System.out.println("4. Salir del sistema");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    sc.nextLine(); // limpiar buffer
                    System.out.print("Dirección: ");
                    String dir = sc.nextLine();
                    System.out.print("Teléfono: ");
                    int tel = sc.nextInt();
                    dao.insertarUsuario(dir, tel);
                }
                case 2 -> {
                    System.out.print("Número de reclamo a eliminar: ");
                    int nro = sc.nextInt();
                    dao.eliminarReclamo(nro);
                }
                case 3 -> {
                    System.out.print("Número de usuario: ");
                    int nroUser = sc.nextInt();
                    dao.listarReclamosUsuario(nroUser);
                }
                case 4 -> {
                    exit = true;
                }
                default -> System.out.println("Opción inválida");
            }
        }

        dao.disconnect();
    }
}