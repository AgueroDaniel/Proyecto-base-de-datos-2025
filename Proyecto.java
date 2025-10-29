//Acceder a base de datos
import conector.SQLConnection;
//Para printear
import java.io.*;

private int verificar(String ing){
    if (ing.equals.("salir")){
        return -1;
    }else{
        if (ing.equals.("agregar")){
            return 1;
        }else{
            if(ing.equals.("eliminar")){
                return 0;
            }else{
                if(ing.equals.("reclamos")){}
            }
        }
    }
    return 0;
}

public static void main(String args[]){
    int selection = 0;
    Scanner scanner = new Scanner(System.in);
    String ingreso;
    while (selection > -1){
        System.out.println("\nQue desea realizar.\n1) Agregar un usuario(agregar).\n2) Eliminar un reclamo(eliminar).\n3) Listar reclamos:");
        String ingreso = scanner.nextLine();
        selection=verificar(ingreso.toLowerCase());
    }

}
