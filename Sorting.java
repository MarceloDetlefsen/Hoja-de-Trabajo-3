
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Sorting
{
    public static void main(String[] args) {
        
    }

    public static void generadorNumeros() {
        // Generar un archivo con cantidadNumeros números aleatorios
        String nombreArchivo = "numeros.txt";
        int cantidadNumeros = 3000;
        Random random = new Random();

        try (FileWriter file = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < cantidadNumeros; i++) {
                int numero = random.nextInt(10000);
                file.write(numero + "\n");
            }
            System.out.println("Se han generado " + cantidadNumeros + " números aleatorios en el archivo " + nombreArchivo);
        } 
        catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + nombreArchivo);
        }
    }

    public static int[] leerNumeros(String nombreArchivo) {
        // Leer los números de un archivo y devolverlos en un arreglo
        return null;
    }

    public static void medidorSort(String nombreArchivo) {
        // Medir el tiempo que toma ordenar un arreglo de números
    } 

    public static void insertionSort(int[] arreglo) {
        // Ordenar el arreglo usando el algoritmo de Insertion Sort
    }

    public static void mergeSort(int[] arreglo) {
        // Ordenar el arreglo usando el algoritmo de Merge Sort
    }

    public static void quickSort(int[] arreglo) {
        // Ordenar el arreglo usando el algoritmo de Quick Sort
    }

    public static void radixSort(int[] arreglo) {
        // Ordenar el arreglo usando el algoritmo de Radix Sort
    }

    public static void timSort(int[] arreglo) {
        // Ordenar el arreglo usando el algoritmo de Tim Sort
    }
}