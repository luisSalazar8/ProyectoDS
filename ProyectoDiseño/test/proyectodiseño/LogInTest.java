/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodiseño;

import controlador.ControlJefeBodega;
import modelo.Bodega;
import modelo.BD;
import modelo.Pedido;
import modelo.Producto;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/** 
 *
 * @author Charles
 */
public class LogInTest { 
    //Se espera que al cargar la Bodega, se tenga un id
    @Test
    public void testBodega(){
        BD bdtest = new BD();
        bdtest.getConexion();
        Bodega bodeg = new Bodega(bdtest);
        bodeg.cargarBodega("0940940940");
        assertEquals("Costa Este",bodeg.getDireccion());
        
    }
    //Se espera que al consultar un pedido por su id, lo devuelva
    @Test
    public void testConsultaPedido(){
         BD bdtest = new BD();
        bdtest.getConexion();
        ControlJefeBodega cjb = new ControlJefeBodega();
        Pedido p = cjb.consultarPedido("1");
        assertNotNull(p);
    }
    //Se espera que se regrese una lista de productos con un determinado nombre
    @Test
    public void testBuscoProductoNombre(){
        BD bdtest = new BD();
        bdtest.getConexion();
        ArrayList<Producto> h = Producto.buscarNombre("Aromatel");
        assertNotNull(h);
        
    }
    
    
}
