
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorting
{
    public static void main(String[] args) {
        
    }

    // Generar un archivo con cantidadNumeros números aleatorios
    public static void generadorNumeros() {
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

    //Leer los números de un archivo y devolverlos en un arreglo
    public static List<Integer> leerNumeros(String nombreArchivo) {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                numeros.add(Integer.valueOf(linea));
            }
        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo " + nombreArchivo);
        }
        return numeros;
    }

    // Guardar los números en un archivo
    public static void guardarNumeros(String nombreArchivo, List<Integer> numeros) {
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            for (int numero : numeros) {
                file.write(numero + "\n");
            }
        } 
        catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + nombreArchivo);
        }
    }

    public static void medidorSort(String nombreArchivo) {
        // Medir el tiempo que toma ordenar un arreglo de números con un PROFILER
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