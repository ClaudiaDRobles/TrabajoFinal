import java.util.Scanner;

public class Prueba2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contadorVentas = 1;
        String IDproducto;
        String NombreCliente = "";
        String MediodePago; // No es necesario inicializar aquí
        String ComprobantedePago; // No es necesario inicializar aquí
        String numeroFactura = "";
        String numeroBoleta = "";
        String IDcliente = "C" + String.format("%03d", contadorVentas);

        System.out.print("Ingresar nombre del cliente: ");
        NombreCliente = sc.next();

        //  número de factura
        numeroFactura = "F001-" + String.format("%04d", contadorVentas);

        // número de Boleta
        numeroBoleta = "B001-" + String.format("%04d", contadorVentas);

        double totalFactura = 0;
        boolean realizarOtraVenta = true;

        while (realizarOtraVenta) {
            boolean agregarProducto = true;
            totalFactura = 0;

            while (agregarProducto) {
                System.out.print("Ingresar ID del producto (o '0' para finalizar la factura): ");
                IDproducto = sc.next();

                if (IDproducto.equals("0")) {
                    agregarProducto = false;
                } else {

                    System.out.print("Ingresar Medio de Pago (BCP o IBK): ");
                    MediodePago = sc.next();

                    System.out.print("Ingresar Comprobante de Pago (Factura o Boleta): ");
                    ComprobantedePago = sc.next();

                    System.out.println("ID del Cliente: " + IDcliente);

                    String comprobante = ComprobantedePago.equals("Factura") ? "Factura" : "Boleta";
                    String Producto = CalcularProducto(IDproducto);
                    double Importe = CalcularImporte(Producto);
                    double Descuento = CalcularDescuento(MediodePago, Importe);
                    double IGV = CalcularIGV(ComprobantedePago, Importe, Descuento);
                    double Total = CalcularImporteTotal(Importe, Descuento, IGV);

                    System.out.println("Número de Comprobante: " + (comprobante.equals("Factura") ? numeroFactura : numeroBoleta));
                    System.out.println("El producto es: " + Producto);
                    System.out.println("El importe del Producto: " + Importe);
                    System.out.println("Descuento aplicado: " + Descuento);
                    System.out.printf("Importe del IGV es: %.2f%n", IGV);
                    System.out.printf("Importe Total es: %.2f%n", Total);

                    totalFactura += Total; // Agregar al total de la factura
                }
            }

            // Imprimir el total de la factura
            System.out.printf("Total de la factura:: %.2f%n", totalFactura);

            // Preguntar al usuario si desea realizar otra venta
            System.out.print("¿Desea realizar otra venta? (S/N): ");
            String respuesta = sc.next();
            realizarOtraVenta = respuesta.equalsIgnoreCase("S");

            if (realizarOtraVenta) {
                contadorVentas++; // Incrementar el contador de ventas
                IDcliente = "C" + String.format("%03d", contadorVentas); // Generar nuevo ID de cliente
                System.out.print("Ingresar nombre del cliente: ");
                NombreCliente = sc.next();
            }
        }

    }

    static public String CalcularProducto(String IDproducto) {
        String Producto = "";

        if (IDproducto.equals("3786")) {
            Producto = "Laptop";
        } else if (IDproducto.equals("4011")) {
            Producto = "TV Samsung";
        } else if (IDproducto.equals("9132")) {
            Producto = "Iphone12 Pro-max";
        } else if (IDproducto.equals("5794")) {
            Producto = "audifonos lenovo";
        } else if (IDproducto.equals("3141")) {
            Producto = "Funda de laptop";
        }

        return Producto;

    }


    static public double CalcularImporte(String Producto) {
        double Importe = 0;

        if (Producto.equals("Laptop")) {
            Importe = 1350.00;
        } else if (Producto.equals("TV Samsung")) {
            Importe = 2650.00;
        } else if (Producto.equals("Iphone12 Pro-max")) {
            Importe = 3440.75;
        } else if (Producto.equals("audifonos lenovo")) {
            Importe = 255.00;
        } else if (Producto.equals("Funda de laptop")) {
            Importe = 100.00;
        } else {
            Importe = 0;
        }

        return Importe;

    }

    static public double CalcularDescuento(String MediodePago, double Importe) {
        double Descuento = 0;


        if (MediodePago.equals("BCP")) {
            Descuento = Importe * 0.10;
        } else if (MediodePago.equals("IBK")) {
            Descuento = Importe * 0.05;
        } else {
            Descuento = 0;
        }

        return Descuento;

    }

    static public double CalcularIGV(String ComprobantedePago, double Importe, double Descuento) {
        double IGV = 0;

        if (ComprobantedePago.equals("Factura")) {
            IGV = (Importe - Descuento) * 0.18;
        }
        if (ComprobantedePago.equals("Boleta")) {
            IGV = (Importe - Descuento) * 0.18;

        }
        return IGV;

    }

    static public double CalcularImporteTotal(double Importe, double Descuento, double IGV) {
        double Total = 0;

        Total = (Importe - Descuento) + IGV;
        return Total;
    }
}

