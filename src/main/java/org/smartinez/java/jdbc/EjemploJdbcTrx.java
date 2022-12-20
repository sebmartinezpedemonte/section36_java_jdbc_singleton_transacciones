package org.smartinez.java.jdbc;

import modelo.Categoria;
import modelo.Producto;
import modelo.repositorio.ProductoRepositorioImp;
import modelo.repositorio.Repositorio;
import org.smartinez.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcTrx {
    public static void main(String[] args) throws SQLException {

        try (Connection conn = ConexionBaseDatos.getInstance()) {
            if(conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                Repositorio<Producto> repositorio = new ProductoRepositorioImp();
                System.out.println("============= listar =============");
                repositorio.listar().forEach(System.out::println);

                System.out.println("============= obtener por id =============");
                System.out.println(repositorio.porId(1L));

                System.out.println("============= insertar nuevo producto =============");
                Producto producto = new Producto();
                producto.setNombre("Teclado IBM mecanico");
                producto.setPrecio(1550);
                producto.setFechaRegistro(new Date());
                Categoria categoria = new Categoria();
                categoria.setId(3L);    //computacion
                producto.setCategoria(categoria);
                producto.setSku("abcde12345");
                repositorio.guardar(producto);
                System.out.println("Producto insertado con exito");


                System.out.println("=============== editar producto=============");

                producto = new Producto();
                producto.setId(4L);
                producto.setNombre("Teclado Corsair k95 Mecanico");
                producto.setPrecio(1000);
                producto.setSku("abcdef1234 ");
                categoria = new Categoria();
                categoria.setId(2L);
                producto.setCategoria(categoria);
                repositorio.guardar(producto);
                System.out.println("Producto editado con exito");

                repositorio.listar().forEach(System.out::println);

                conn.commit();
            }catch (SQLException ex){
                conn.rollback();
                ex.printStackTrace();
            }
        }
    }

}
