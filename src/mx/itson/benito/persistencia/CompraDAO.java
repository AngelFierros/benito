/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Compra;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * La clase CompraDAO es responsable de realizar operaciones de persistencia
 * relacionadas con la entidad Compra. Contiene métodos para obtener todas las
 * compras, guardar una nueva compra en la base de datos, editar una compra
 * existente, obtener una compra por su ID y eliminar una compra.
 */
public class CompraDAO {

    /**
     * Obtiene todas las compras registradas en la base de datos.
     *
     * @return una lista de compras
     */
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

    /**
     * Guarda una nueva compra en la base de datos.
     *
     * @param folio el folio de la compra
     * @param fecha la fecha de la compra
     * @param articulo el articulo comprado
     * @param proveedor el proveedor del articulo
     * @param cantidad la cantidad de articulos comprados
     * @param estado el estado de la compra
     * @param iva el valor del IVA aplicado a la compra
     * @param total el total de la compra
     * @return true si la compra fue guardada exitosamente, false en caso
     * contrario
     */
    public static boolean guardar(String folio, Date fecha, Articulo articulo, Proveedor proveedor, int cantidad, String estado, Double iva, Double total) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Compra c = new Compra();
            c.setFolio(folio);
            c.setFecha(fecha);
            c.setArticulo(articulo);
            c.setProveedor(proveedor);
            c.setCantidad(cantidad); // Agregamos la cantidad
            c.setEstado(estado);
            c.setIva(iva);
            c.setTotal(total);

            session.save(c);

            session.getTransaction().commit();

            resultado = c.getId() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }

    /**
     *
     *
     * Actualiza los datos de una compra en la base de datos.
     *
     * @param id El id de la compra a editar.
     * @param folio El nuevo folio de la compra.
     * @param fecha La nueva fecha de la compra.
     * @param articulo El nuevo articulo de la compra.
     * @param proveedor El nuevo proveedor de la compra.
     * @param cantidad La nueva cantidad de la compra.
     * @param estado El nuevo estado de la compra.
     * @param iva El nuevo IVA de la compra.
     * @param total El nuevo total de la compra.
     * @return true si se actualizó correctamente, false si no se pudo
     * actualizar.
     */
    public static boolean editar(int id, String folio, Date fecha, Articulo articulo, Proveedor proveedor, int cantidad, String estado, Double iva, Double total) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Compra compra = obtenerPorId(id);
            if (compra != null) {
                compra.setFolio(folio);
                compra.setFecha(fecha);
                compra.setArticulo(articulo);
                compra.setProveedor(proveedor);
                compra.setCantidad(cantidad); // Agregamos la cantidad
                compra.setEstado(estado);
                compra.setIva(iva);
                compra.setTotal(total);

                session.saveOrUpdate(compra);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }

    /**
     *
     * Obtiene una compra por su id.
     *
     * @param id el id de la compra a obtener.
     *
     * @return la compra encontrada o null si no se encuentra.
     */
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

    /**
     *
     * Método para eliminar una compra de la base de datos.
     *
     * @param id el identificador de la compra a eliminar
     *
     * @return true si se eliminó la compra exitosamente, false si no
     */
    public static boolean eliminar(int id) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Compra compra = obtenerPorId(id);
            if (compra != null) {
                session.delete(compra);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
