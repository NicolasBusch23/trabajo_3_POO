package Ejercicio_8_2;

/**
 * Esta clase contiene la lógica matemática para procesar un arreglo 
 * de notas estudiantiles, calculando su promedio, desviación 
 * estándar, nota mayor y nota menor.
 */
public class ProcesadorNotas {
    private double[] notas;

    public ProcesadorNotas(double[] notas) {
        this.notas = notas;
    }

    public double calcularPromedio() {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.length;
    }

    public double calcularDesviacionEstandar() {
        double promedio = calcularPromedio();
        double sumaDiferenciasCuadrado = 0;
        for (double nota : notas) {
            sumaDiferenciasCuadrado += Math.pow(nota - promedio, 2);
        }
        return Math.sqrt(sumaDiferenciasCuadrado / notas.length);
    }

    public double obtenerMayorNota() {
        double mayor = notas[0];
        for (double nota : notas) {
            if (nota > mayor) {
                mayor = nota;
            }
        }
        return mayor;
    }

    public double obtenerMenorNota() {
        double menor = notas[0];
        for (double nota : notas) {
            if (nota < menor) {
                menor = nota;
            }
        }
        return menor;
    }
}