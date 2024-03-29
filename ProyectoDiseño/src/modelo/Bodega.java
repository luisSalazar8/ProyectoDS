/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

/**
 *
 * @author LuisEduardo
 */
public class Bodega {
    private int idbodega; 
    private List<Repartidor> listaRepartidores = new ArrayList<>();
    private Queue<Repartidor> colaRepartidores;
    private List<Usuario> listaEmpleados= new ArrayList<>();
    private List<Pedido> listaPedidos= new ArrayList<>();
    private JefeBodega jefeBodega; 
    private List<Producto> listaProductos = new ArrayList<>();
    private String direccion;
    private DataBase db;

    public Bodega(DataBase db) {
        this.db = db;
    }

    public List<Repartidor> getListaRepartidores() {
        return listaRepartidores;
    }

    public void setListaRepartidores(List<Repartidor> listaRepartidores) {
        this.listaRepartidores = listaRepartidores;
    }

    public Queue<Repartidor> getColaRepartidores() {
        return colaRepartidores;
    }

    public void setColaRepartidores(Queue<Repartidor> colaRepartidores) {
        this.colaRepartidores = colaRepartidores;
    }

    public List<Usuario> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Usuario> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public JefeBodega getJefeBodega() {
        return jefeBodega;
    }

    public void setJefeBodega(JefeBodega jefeBodega) {
        this.jefeBodega = jefeBodega;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public DataBase getDb() {
        return db;
    }

    public void setDb(DataBase db) {
        this.db = db;
    }
    
    public Repartidor repartidorDisponible(){
        return this.colaRepartidores.poll();
    }
    
    public void agregarRepartidor(String idrep){
        
        for(Repartidor rep:this.listaRepartidores){
            if(rep.getCedula().equalsIgnoreCase(idrep)){
                this.colaRepartidores.offer(rep);
            }
        }
    }
    
    public void cargarBodega(String idJefe){
        try{
            String sql= "{call obtenerBodega(?)}";
            CallableStatement cst=this.db.getC().prepareCall(sql);
            cst.setString(1, idJefe);
            ResultSet rs = cst.executeQuery();
            rs.next();
            this.idbodega=rs.getInt(1);
            this.direccion=rs.getString(2);
        }catch (Exception e){
            Logger.getLogger(Bodega.class.getName()).warning("Error en conexión cargar bodega");
        }
        this.cargarDatos();
        
    }
    
    private void cargarDatos(){
        this.listaRepartidores=Repartidor.cargarDatos(db,this.idbodega);
        this.colaRepartidores=new LinkedList<>(this.listaRepartidores);
        this.listaProductos=Producto.cargarDatosBodega(db, idbodega);
        this.cargarPedidos();
    }
    
    public void cargarPedidos(){
        ArrayList<Pedido> listp=new ArrayList<>();
        PedidoLocal.cargarDatosSucursalBodega(db, idbodega,listp);
        PedidoCliente.cargarDatosClienteBodega(db, idbodega, listp);
        this.listaPedidos=listp;
    }
    
    public List<Pedido> pedidosAEntregar(){
        ArrayList<Pedido> listp=new ArrayList<>();
        for(Pedido p:this.listaPedidos){
            if(!p.getEstadoEntrega().equalsIgnoreCase("Entregado") && !p.getEstadoEntrega().equalsIgnoreCase("En_Proceso")){
                listp.add(p);
            }
        }
        return listp;
    }
    
    public static int obteneridBodega(int idproducto, int cantidad,DataBase db){
        try{
            String sql= "{call obtenerBodegaPorProducto(?,?)}";
            CallableStatement cst=db.getC().prepareCall(sql);
            cst.setInt(1, idproducto);
            cst.setInt(2, cantidad);
            ResultSet rs = cst.executeQuery();
            rs.next();
            return rs.getInt(1);
            
        }catch (Exception e){
            Logger.getLogger(Bodega.class.getName()).warning("Error en conexión obtener bodega por producto");
        }
        return 0;
    } 
    
    public static void actualizarProductoBodega(int idBodega, int idProducto, int cantidad,DataBase db){
        try{
            String sql= "{call actualizarBodegaPorProducto(?,?,?)}";
            CallableStatement cst=db.getC().prepareCall(sql);
            cst.setInt(1, idBodega);
            cst.setInt(2, idProducto);
            cst.setInt(3, cantidad);
            cst.executeQuery();
            
        }catch (Exception e){
            Logger.getLogger(Bodega.class.getName()).warning("Error en conexión actualizar bodega por producto");
        }
    }
    
}
