/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Compra;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author PC
 */
public class CompraDAO {
    
    public static List<Compra> obtenerTodos() {
        List<Compra> compras = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Compra> criteriaQuery = session.getCriteriaBuilder().createQuery(Compra.class);
            criteriaQuery.from(Compra.class);

            compras = session.createQuery(criteriaQuery).getResultList();

        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return compras;
    }
    
    public static boolean guardar(String folio, Double iva, Double total, Date fecha) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Compra c = new Compra();
            c.setFolio(folio);
            c.setIva(iva);
            c.setTotal(total);
            c.setFecha(fecha);
          
            session.save(c);

            session.getTransaction().commit();

            resultado = c.getId() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return resultado;
    }
     public static boolean editar(int id, String folio, Double iva, Double total, Date fecha){
       boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Compra compra = obtenerPorId(id);
            if(compra !=null){
                compra.setFolio(folio);
                compra.setIva(iva);
                compra.setTotal(total);
                compra.setFecha(fecha);
               
                
                session.saveOrUpdate(compra);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
     
     public static Compra obtenerPorId(int id) {
        Compra compra = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            compra = session.get(Compra.class, id);

        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return compra;
    }
    
     public static boolean eliminar(int id){
        boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Compra compra = obtenerPorId(id);
            if(compra !=null){
                session.delete(compra);
                session.getTransaction().commit();
                resultado = true;
            }    
        }catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
