/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author PC
 */
public class ArticuloDAO {
    
    public static List<Articulo> obtenerTodos() {
        List<Articulo> articulos = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Articulo> criteriaQuery = session.getCriteriaBuilder().createQuery(Articulo.class);
            criteriaQuery.from(Articulo.class);

            articulos = session.createQuery(criteriaQuery).getResultList();

        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return articulos;
    }
    
    public static boolean guardar(String nombre, Double precio, String clave, Proveedor proveedor) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Articulo a = new Articulo();
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setClave(clave);
            a.setProveedor(proveedor);
            
            
            

            session.save(a);

            session.getTransaction().commit();

            resultado = a.getId() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return resultado;
    }
    
    public static boolean editar(int id, String nombre, Double precio, String clave, Proveedor proveedor){
       boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Articulo articulo = obtenerPorId(id);
            if(articulo !=null){
                articulo.setNombre(nombre);
                articulo.setPrecio(precio);
                articulo.setClave(clave);
                articulo.setProveedor(proveedor);
               
                
                session.saveOrUpdate(articulo);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public static Articulo obtenerPorId(int id) {
        Articulo articulo = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            articulo = session.get(Articulo.class, id);

        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return articulo;
    }
    
     public static boolean eliminar(int id){
        boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Articulo articulo = obtenerPorId(id);
            if(articulo !=null){
                session.delete(articulo);
                session.getTransaction().commit();
                resultado = true;
            }    
        }catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
