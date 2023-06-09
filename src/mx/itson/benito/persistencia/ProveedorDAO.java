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
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author PC
 */
public class ProveedorDAO {
    
     public static List<Proveedor> obtenerTodos() {
        List<Proveedor> proveedores = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Proveedor> criteriaQuery = session.getCriteriaBuilder().createQuery(Proveedor.class);
            criteriaQuery.from(Proveedor.class);

            proveedores = session.createQuery(criteriaQuery).getResultList();

        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return proveedores;
    }
    
     
     public static boolean guardar(String nombre, String clave,String contacto, String email, String direccion, String telefono) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Proveedor p = new Proveedor();
            p.setNombre(nombre);
            p.setClave(clave);
            p.setEmail(email);
            p.setContacto(contacto);
            p.setDireccion(direccion);
            p.setTelefono(telefono);

            session.save(p);

            session.getTransaction().commit();

            resultado = p.getId() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return resultado;
    }
     
     public static boolean editar(int id, String nombre, String clave,String contacto, String email, String direccion, String telefono){
       boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Proveedor proveedor = obtenerPorId(id);
            if(proveedor !=null){
                proveedor.setNombre(nombre);
                proveedor.setClave(clave);
                proveedor.setContacto(contacto);
                proveedor.setEmail(email);
                proveedor.setDireccion(direccion);
                proveedor.setTelefono(telefono);
                
                session.saveOrUpdate(proveedor);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
     
     public static Proveedor obtenerPorId(int id) {
        Proveedor proveedor = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            proveedor = session.get(Proveedor.class, id);

        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return proveedor;
    }
     
     public static boolean eliminar(int id){
        boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Proveedor proveedor = obtenerPorId(id);
            if(proveedor !=null){
                session.delete(proveedor);
                session.getTransaction().commit();
                resultado = true;
            }    
        }catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
     
     public void borrarProveedorConArticulos(Proveedor proveedor) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        // Borra todos los artículos del proveedor
        Query<Articulo> query = session.createQuery("delete from Articulo where proveedor.id = :idProveedor");
        query.setParameter("idProveedor", proveedor.getId());
        query.executeUpdate();

        // Borra el proveedor
        session.delete(proveedor);

        tx.commit();
        session.close();
    }
     
     
     
     
     
}
