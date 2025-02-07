
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sorting
{
    public static void main(String[] args) {
        //Generar los numeros e imprimirlos para demostrar que estan desordenados,
        //luego ir a un menú donde se seleccione con que método se quiere ordenar y volver a imprimirlos una vez termine el sorting.
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

    // Ordenar el arreglo usando el algoritmo de Insertion Sort
    public static void insertionSort(int[] arreglo) {
        
    }

    // Ordenar el arreglo usando el algoritmo de Merge Sort
    public static void mergeSort(int[] arreglo, int izq, int der) {
        if (izq < der) {
            int mitad = (izq + der) / 2;
            mergeSort(arreglo, izq, mitad);
            mergeSort(arreglo, mitad + 1, der);
            merge(arreglo, izq, mitad, der);
        }
    }

    //Método que fusiona los subarrays ordenadamente
    public static void merge(int[] arreglo, int izq, int mitad, int der) {
        int[] aux = Arrays.copyOfRange(arreglo, izq, der + 1);
        int i = 0, j = mitad - izq + 1, k = izq;
        while (i <= mitad - izq && j < aux.length) {
            arreglo[k++] = (aux[i] <= aux[j]) ? aux[i++] : aux[j++];
        }
        while (i <= mitad - izq) arreglo[k++] = aux[i++];
    }

    // Ordenar el arreglo usando el algoritmo de Quick Sort
    public static void quickSort(int[] arreglo, int bajo, int alto) {
        
    }

    // Ordenar el arreglo usando el algoritmo de Radix Sort
    public static void radixSort(int[] arreglo) {

    }

    // Ordenar el arreglo usando el algoritmo de Bucket Sort
    public static void bucketSort(int[] arreglo) {
        int max = Arrays.stream(arreglo).max().getAsInt();
        int numBuckets = 10;
        List<Integer>[] buckets = new List[numBuckets];
        for (int i = 0; i < numBuckets; i++) buckets[i] = new ArrayList<>();
        for (int num : arreglo) buckets[(num * numBuckets) / (max + 1)].add(num);
        int idx = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) arreglo[idx++] = num;
        }
    }
}