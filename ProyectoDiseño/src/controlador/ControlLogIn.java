package controlador;

import modelo.BD;
import modelo.DataBase;
import modelo.User;
import modelo.Usuario;
import vistas.AdminV;
import vistas.ManejarStock;
import vistas.Pedidos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LuisEduardo
 */
public class ControlLogIn {

    public static final DataBase db = new BD();
    public static final Connection cn = db.getConexion();

    public boolean verificar(String usuario, String contra) {
        try {
            String sql = "{call verificar(?,?)}";
            CallableStatement cst = cn.prepareCall(sql);
            cst.setString(1, usuario);
            cst.setString(2, contra);
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                String user = rs.getString(1);
                String pass = rs.getString(2);
                if (usuario.equals(user) && contra.equals(pass)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(e.toString());
            return false;
        }
    }

    private ResultSet getUsuario(String sql, String usuario, String contra) {
        try {
            CallableStatement cst = cn.prepareCall(sql);
            cst.setString(1, usuario);
            cst.setString(2, contra);
            return cst.executeQuery();

        } catch (SQLException e) {
            Logger.getLogger(e.toString());
            return null;
        }

    }

    public User obtenerAdministrador(String usuario, String contra) throws SQLException {
        try{
            String sql = "{call obtenerAdmins(?,?)}";
            ResultSet rs = getUsuario(sql, usuario, contra);
            if (rs.next()) {
                return new Usuario(usuario, contra, "Administrador", null, rs.getString(1), rs.getString(2), rs.getString(3));}
        }catch(NullPointerException n){
            Logger.getLogger(n.toString());
        }
        return null;
    }

    public User obtenerGerente(String usuario, String contra) throws SQLException {
        try{
            String sql = "{call obtenerGerente(?,?)}";
        ResultSet rs = getUsuario(sql, usuario, contra);
        if (rs.next()) {
            return new Usuario(usuario, contra, "Gerente", null, rs.getString(1), rs.getString(2), rs.getString(3)); 
        }}catch(NullPointerException n){
            Logger.getLogger(n.toString());
        }
        return null;
    }

    public User obtenerVendedor(String usuario, String contra) throws SQLException {
        try{
        String sql = "{call obtenerVendedor(?,?)}";
        ResultSet rs = getUsuario(sql, usuario, contra);
        if (rs.next()) {
            return new Usuario(usuario, contra, "Vendedor", null, rs.getString(1), rs.getString(2), rs.getString(3));
            
        }}catch(NullPointerException n){
            Logger.getLogger(n.toString());
        }
        return null;
    }

    public User obtenerJefeBodega(String usuario, String contra) throws SQLException {
        try{
        String sql = "{call obtenerJefeBodega(?,?)}";
        ResultSet rs = getUsuario(sql, usuario, contra);
        if (rs.next()) {
            return new Usuario(usuario, contra, "JefeBodega", null, rs.getString(1), rs.getString(2), rs.getString(3));
            
        }}catch(NullPointerException n){
            Logger.getLogger(n.toString());
        }
        return null;
    }

    public void siguientePantalla(String usuario, String cont, Stage st, Scene scp) throws SQLException {
        User u = obtenerAdministrador(usuario, cont);
        if (u != null) {
            //cabiar al menu de admin
            Logger.getLogger("Admin");
            st.setScene(AdminV.menuAdmin(u, st, scp));
        }
        u = obtenerGerente(usuario, cont);
        if (u != null) {
            Logger.getLogger("Gerente");
            st.setScene(ManejarStock.menuGerente(u, st, scp));
        }
        u = obtenerVendedor(usuario, cont);
        if (u != null) {
            Logger.getLogger("Vendedor");
        }
        u = obtenerJefeBodega(usuario, cont);
        if (u != null) {
            st.setScene(Pedidos.menuJefeBodega(u, st, scp));
            Logger.getLogger("Jefebodega");
        }
    }
}
