import java.util.Date;

class Cliente {
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

class Barco {
    private String matricula;
    private double eslora;
    private int añoFabricacion;

    public Barco(String matricula, double eslora, int añoFabricacion) {
        this.matricula = matricula;
        this.eslora = eslora;
        this.añoFabricacion = añoFabricacion;
    }

    public double calcularCostoAlquiler(int dias) {
        return dias * 25000;
    }

    public String toString() {
        return "Matrícula: " + matricula + ", Eslora: " + eslora + "m, Año de fabricación: " + añoFabricacion;
    }
}

class Velero extends Barco {
    private int numMastiles;

    public Velero(String matricula, double eslora, int añoFabricacion, int numMastiles) {
        super(matricula, eslora, añoFabricacion);
        this.numMastiles = numMastiles;
    }

    public String toString() {
        return "Velero - " + super.toString() + ", Número de mástiles: " + numMastiles;
    }
}

class yate extends Barco {
    private int potencia;

    public yate(String matricula, double eslora, int añoFabricacion, int potencia) {
        super(matricula, eslora, añoFabricacion);
        this.potencia = potencia;
    }

    public String toString() {
        return "Yate - " + super.toString() + ", Potencia: " + potencia + "CV";
    }
}

class Amarre {
    private int posicion;
    private Barco barco;
    private Date fechaInicio;
    private Date fechaFin;
    private Cliente cliente;

    public Amarre(int posicion, Barco barco, Date fechaInicio, int diasAlquiler, Cliente cliente) {
        this.posicion = posicion;
        this.barco = barco;
        this.fechaInicio = fechaInicio;
        this.fechaFin = new Date(fechaInicio.getTime() + diasAlquiler * 24 * 60 * 60 * 1000);
        this.cliente = cliente;
    }

    public double calcularCostoAlquiler() {
        long diferencia = fechaFin.getTime() - fechaInicio.getTime();
        int dias = (int) (diferencia / (1000 * 60 * 60 * 24));
        return barco.calcularCostoAlquiler(dias);
    }

    public void imprimirRecibo() {
        System.out.println("-------- Recibo --------");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Barco: " + barco);
        System.out.println("Fecha de inicio: " + fechaInicio);
        System.out.println("Fecha de fin: " + fechaFin);
        System.out.println("Costo de alquiler: $" + calcularCostoAlquiler());
        System.out.println("Posición: " + posicion);
    }
}

public class Puerto {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Juan Pérez");
        Cliente cliente2 = new Cliente("María Gómez");

        Barco velero = new Velero("ABC123", 10.5, 2005, 2);
        Barco yate = new yate("DEF456", 8.0, 2010, 150);

        Date fechaInicioVelero = new Date();
        int diasAlquilerVelero = 5;

        Date fechaInicioYate = new Date(); // Fecha actual
        int diasAlquilerYate = 10;

        Amarre amarre1 = new Amarre(1, velero, fechaInicioVelero, diasAlquilerVelero, cliente1);
        Amarre amarre2 = new Amarre(2, yate, fechaInicioYate, diasAlquilerYate, cliente2);

        amarre1.imprimirRecibo();
        amarre2.imprimirRecibo();
    }
}
