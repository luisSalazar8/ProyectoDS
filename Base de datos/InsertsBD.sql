use DBproyecto;

#Valores introducidos en tablas

Insert into Cuentas values (1,"dyance","espol1",0),
							(2,"lsalazar","espol2",0),
							(3,"csanchez","espol3",0),
							(4,"lpod","espol4",0),
							(5,"podcast","espol5",0);
                            
Insert into clientes values (1,"Bryan","Segovia",20,"1234","b@espol.com","Florida",0),
							(2,"Marco","Tulio",50,"12345","m@espol.com","Guasmo",0),
							(3,"Alicia","Maravilla",40,"8534","a@espol.com","Albonor",0);
                            
Insert into empleados values ("0943842997","Diego","Yance",22,1),
							("0920920920","Luis","Salazar",21,2),
							("0910910910","Carlos","Sanchez",22,3),
							("0940940940","Lucas","Pod",23,4),
							("0950950950","Lapras","Yoshi",57,5);
                            
Insert into sucursal values (1,"Negocio Suc","Sauces 1",0),
							(2,"Negocio Matr","Alborada 11",1);
                            
Insert into bodega values (1,"Costa Este");							

Insert into administrador values ("0943842997",1);

Insert into gerente values ("0920920920",1);

Insert into vendedor values ("0910910910",1);

Insert into jefebodega values ("0940940940",1);

Insert into repartidor_bodega values ("0950950950",1);

Insert into producto values (1,"Aromatel","Higiene","Producto para limpiar",1.55,0),
							(2,"Kchitos","Alimentos","Comestible para fiestas",0.25,0),
							(3,"Galak","Alimentos","Comestible dulce",0.70,0),
							(4,"Mi Lechera","Alimentos","Bebida lactea",1.30,0),
							(5,"Club Social","Alimentos","Comestible para fiestas",0.15,0);
Insert into pedido values (1,"0950950950","Entregado",'10:00','11:15',0);

Insert into venta values (1,1,1,"efectivo",'2019-08-15',0);

Insert into factura values (1,1,4.00);

Insert into cotizacion values (1,1,1,'2019-08-13');

Insert into productos_local values (1,1,20),(2,1,15),(3,1,25),(4,1,30),(5,1,35);

Insert into productos_pedido values (1,1,2),(2,1,1),(3,1,2);

Insert into pedido_cliente values (1,1);

Insert into pedido_sucursal values (1,1);

Insert into productos_bodega values (1,1,200),(2,1,150),(3,1,250),(4,1,300),(5,1,350);

Insert into productos_factura values (2,1,10),(4,1,10);
							