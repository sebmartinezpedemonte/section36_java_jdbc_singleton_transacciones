package org.smartinez.java.jdbc;

import modelo.Producto;
import modelo.repositorio.ProductoRepositorioImp;
import modelo.repositorio.Repositorio;
import org.smartinez.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcDelete {
    public static void main(String[] args) {
        //Usamos singleton porque para una aplicacion de consola con una conexion es mas que necesario
        //Pero para una aplicacion web (con varios usuarios conectados, con concurrencia) necesitamos un pool de conexiones pero estos vienen implementados por spring entonces es mas facil
        //poniendolos adentro del parentesis del try no necesitamos llamar al .close() (conn.close(), stmt.close(), resultado.close()
        try (Connection conn = ConexionBaseDatos.getInstance()){

            Repositorio<Producto> repositorio = new ProductoRepositorioImp();
            System.out.println("=============== listar =============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("=============== obtener por id =============");
            System.out.println(repositorio.porId(1L));

            System.out.println("=============== eliminar producto=============");
            repositorio.eliminar(12L);
            System.out.println("Producto eliminado con exito");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
