package vistas;

import controlador.ControlStock;
import modelo.Producto;
import modelo.User;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author LuisEduardo
 */
public class ManejarStock {
    
    private ManejarStock(){
        
    }
    
    public static Scene menuGerente(User u,Stage st,Scene scp){
        Label labeltit=new Label("MENU GERENTE");
        StackPane sp=new StackPane();
        sp.getChildren().add(labeltit);
        
        Button btnop=new Button("Otorgar Permisos");
        StackPane sp2=new StackPane();
        sp2.getChildren().add(btnop);
        
        Button btnpa=new Button("Pedido de Abastecimiento");
        StackPane sp3=new StackPane();
        sp3.getChildren().add(btnpa);
        
        Button btnsa=new Button("Salir");
        StackPane sp4=new StackPane();
        sp4.getChildren().add(btnsa);
        
        VBox vb=new VBox(20);
        vb.getChildren().addAll(sp,sp2,sp3,sp4);
        Scene sc=new Scene(vb,300,300);
        btnsa.setOnMouseClicked((MouseEvent e)->
            st.setScene(scp)
        );
        
        btnpa.setOnMouseClicked((MouseEvent e)->
            st.setScene(abastecimiento(u,st,sc))
        );
        
        return sc;
    }
    
    
    public static Scene abastecimiento(User u,Stage st,Scene scp){
        ControlStock cs=new ControlStock();
        VBox vb=new VBox(20);
        Label lbtit=new Label("PEDIDO ABASTECIMIENTO");
        StackPane sp=new StackPane();
        sp.getChildren().add(lbtit);
        
        Label lbbus=new Label("Criterio de busqueda:"); 
        TextField txtbus=new TextField();
        Button buscar=new Button("Buscar");
        HBox hb=new HBox(20);
        ArrayList<String> lc=new ArrayList<>();
        lc.add("Todos");
        lc.add("Por nombre");
        lc.add("Por categoria");
        lc.add("Por descripcion");
        ComboBox cb=new ComboBox(FXCollections.observableList(lc));
        cb.getSelectionModel().selectFirst();
        
        hb.getChildren().addAll(lbbus,cb,txtbus,buscar);
        TableView tvP=new TableView();
        
        TableColumn<String, Producto> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        
        TableColumn<String, Producto> column2 = new TableColumn<>("Nombre");
        column2.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<String, Producto> column3 = new TableColumn<>("Descripcion");
        column3.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<String, Producto> column4 = new TableColumn<>("Precio");
        column4.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        TableColumn<String, Producto> column5 = new TableColumn<>("Cantidad Disponible");
        column5.setCellValueFactory(new PropertyValueFactory<>("cantDisp"));
        column5.setPrefWidth(125);
        TableColumn<String, Producto> column6 = new TableColumn<>("Categoria");
        column6.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        
        TableColumn<String, Producto> column7 = new TableColumn<>("Cantidad a Pedir");
        column7.setCellValueFactory(new PropertyValueFactory<>("cantdeseada"));    
        column7.setPrefWidth(125);
        TableColumn<String, Producto> column8 = new TableColumn<>("Seleccionar");
        column8.setCellValueFactory(new PropertyValueFactory<>("isdeseado"));
        
        tvP.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7,column8);
        
        Button btnmenu=new Button("Menu");
        Button btnped=new Button("Crear Pedido");
        HBox hb2=new HBox(20);
        hb2.getChildren().addAll(btnmenu,btnped);
        buscar.setOnMouseClicked((MouseEvent e)->{
            String parametro=txtbus.getText();
            tvP.setItems(FXCollections.observableList(cs.obtenerProductos(parametro, (String)cb.getSelectionModel().getSelectedItem())));
        
        });
        
        btnped.setOnMouseClicked((MouseEvent e)->{
            cs.crearPedido(tvP.getItems(), u);
            tvP.refresh();
        });
        hb.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(sp,hb,tvP,hb2);
         btnmenu.setOnMouseClicked((MouseEvent e)->
            st.setScene(scp)
        );
         return new Scene(vb,725,500);
    }
    
}
