//Acceder a base de datos
import conector.SQLConnection;
import java.util.Scanner;
//import conector.op;
//Para printear
import java.io.*;

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
