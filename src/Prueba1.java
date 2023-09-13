import java.util.Scanner;

public class Prueba1 {

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

        // Generar el número de factura
        numeroFactura = "F001-" + String.format("%04d", contadorVentas);

        // Generar el número de Boleta
        numeroBoleta = "B001-" + String.format("%04d", contadorVentas);

        double totalFactura = 0;
        boolean agregarProducto = true;

        while (agregarProducto) {
            System.out.print("Ingresar ID del producto (o '0' para finalizar la factura): ");
            IDproducto = sc.next();

            if (IDproducto.equals("0")) {
                agregarProducto = false; // El usuario ha finalizado la factura
            } else {

                // Aquí debes obtener y asignar los valores de MediodePago y ComprobantedePago
                System.out.print("Ingresar Medio de Pago (BCP o IBK): ");
                MediodePago = sc.next();

                System.out.print("Ingresar Comprobante de Pago (Factura o Boleta): ");
                ComprobantedePago = sc.next();

                System.out.println("ID del Cliente: " + IDcliente);
                contadorVentas++;

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
            // Imprimir el total de la factura
                System.out.printf("Total de la factura:: %.2f%n", totalFactura);

        }
    }

            // IDProducto

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
            } else {
                Producto = "No especificado";
            }

            return Producto;

        }

        // Productos
        // Importe x nombre de productos...

        static public double CalcularImporte(String Producto) {
            double Importe = 0;

            if (Producto.equals("Laptop")) {
                Importe = 135.00;
            } else if (Producto.equals("TV Samsung")) {
                Importe = 165.00;
            } else if (Producto.equals("Iphone12 Pro-max")) {
                Importe = 144.75;
            } else if (Producto.equals("audifonos lenovo")) {
                Importe = 155.00;
            } else if (Producto.equals("Funda de laptop")) {
                Importe = 100.00;
            } else {
                Importe = 0;
            }

            return Importe;

        }

        // Medio de pago y Descuento

        static public double CalcularDescuento(String MediodePago, double Importe) {
            String Entonces = "";
            double Descuento = 0;


            if (MediodePago.equals("BCP")) {
                Entonces = "Aplica Descuento del 10%";
                Descuento = Importe * 0.10;
            } else if (MediodePago.equals("IBK")) {
                Entonces = "Aplica Descuento del 5%";
                Descuento = Importe * 0.05;
            } else {
                Entonces = "No procede Descuento";
                Descuento = 0;
            }

            return Descuento;

        }

        // IGV
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

