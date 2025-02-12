import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de datos
 * Ing. Douglas Barrios
 * Colaboradores: 
 * Marcelo Detlefsen - 24554
 * Luis Pedro Figueroa - 24087
 * Luis Pedro Hernández - 24337
 * Fecha: 10/02/2025
 * Descripción: Clase que ejecuta todos los sortings 2 veces de manera corrida, y es la forma más correcta testear el tiempo de ejecución con el Profiler.
 */

public class Prueba
{
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] tamanos = {10, 100, 1000, 3000};
        
        for (int tamano : tamanos) {
            System.out.println("\nEjecutando con tamaño: " + tamano);
            ejecutarSorting(tamano, "InsertionSort");
            ejecutarSorting(tamano, "MergeSort");
            ejecutarSorting(tamano, "QuickSort");
            ejecutarSorting(tamano, "RadixSort");
            ejecutarSorting(tamano, "BucketSort");
        }
    }

    public static void ejecutarSorting(int tamano, String metodo) {
        String archivo = "numeros_" + tamano + ".txt";
        generadorNumeros(archivo, tamano);
        int[] numeros = leerNumeros(archivo);
        System.out.println("\nEjecutando " + metodo + " con lista desordenada.");
        aplicarSorting(numeros, metodo);
        guardarNumeros("numeros_ordenados_" + tamano + "_" + metodo + ".txt", numeros);
        System.out.println("Ejecutando " + metodo + " con lista ya ordenada.");
        aplicarSorting(numeros, metodo);
    }

    // Generar un archivo con cantidadNumeros números aleatorios
    /**
     * 
     */
    public static void generadorNumeros(String nombreArchivo, int cantidadNumeros) {
        Random random = new Random();
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < cantidadNumeros; i++) {
                int numero = random.nextInt(10000);
                file.write(numero + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + nombreArchivo);
        }
    }

    //Leer los números de un archivo y devolverlos en un arreglo
    /**
     * @param nombreArchivo
     * @return
     */
    public static int[] leerNumeros(String nombreArchivo) {
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

        int[] arreglo = new int[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            arreglo[i] = numeros.get(i);
        }
        return arreglo;
    }

    // Guardar los números en un archivo
    /**
     * @param nombreArchivo
     * @param arreglo
     */
    public static void guardarNumeros(String nombreArchivo, int[] arreglo) {
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            for (int numero : arreglo) {
                file.write(numero + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + nombreArchivo);
        }
    }

    public static void aplicarSorting(int[] numeros, String metodo) {
        switch (metodo) {
            case "InsertionSort":
                insertionSort(numeros);
                break;
            case "MergeSort":
                mergeSort(numeros, 0, numeros.length - 1);
                break;
            case "QuickSort":
                quickSort(numeros, 0, numeros.length - 1);
                break;
            case "RadixSort":
                radixSort(numeros);
                break;
            case "BucketSort":
                bucketSort(numeros);
                break;
        }
    }

    // Ordenar el arreglo usando el algoritmo de Insertion Sort
    /**
     * @param arreglo
     */
    public static void insertionSort(int[] arreglo) {
        for (int i = 1; i <arreglo.length; i++){
            int key = arreglo[i]; // La variable key toma el valor del elemento que se esta comparando
            int j = i - 1; // la variable j se usa para evaluar los elementos de la lista atras de Key
            while (j >=0 && arreglo[j] > key){
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = key;
        }
    }

    // Ordenar el arreglo usando el algoritmo de Merge Sort
    /**
     * @param arreglo
     * @param izq
     * @param der
     */
    public static void mergeSort(int[] arreglo, int izq, int der) {
        if (izq < der) {
            int mitad = (izq + der) / 2;
            mergeSort(arreglo, izq, mitad);
            mergeSort(arreglo, mitad + 1, der);
            merge(arreglo, izq, mitad, der);
        }
    }

    //Método que fusiona los subarrays ordenadamente
    /**
     * @param arreglo
     * @param izq
     * @param mitad
     * @param der
     */
    public static void merge(int[] arreglo, int izq, int mitad, int der) {
        int[] aux = Arrays.copyOfRange(arreglo, izq, der + 1);
        int i = 0, j = mitad - izq + 1, k = izq;
        while (i <= mitad - izq && j < aux.length) {
            arreglo[k++] = (aux[i] <= aux[j]) ? aux[i++] : aux[j++];
        }
        while (i <= mitad - izq) arreglo[k++] = aux[i++];
    }

    // Ordenar el arreglo usando el algoritmo de Quick Sort
    /**
     * @param arreglo
     * @param izq
     * @param der
     */
    public static void quickSort(int[] arreglo, int izq, int der) {
        if (izq < der) {
            // Obtiene el índice del pivote
            int pivote = particion(arreglo, izq, der);
            // Ordena las dos mitades recursivamente
            quickSort(arreglo, izq, pivote - 1);
            quickSort(arreglo, pivote + 1, der);
        }
    }

    /**
     * @param arreglo
     * @param izq
     * @param der
     * @return
     */
    public static int particion(int[] arreglo, int izq, int der) {
        // Selecciona el último elemento como pivote
        int pivote = arreglo[der];
        int i = izq - 1; // Indice del elemento más pequeño

        // Recorre el arreglo y organiza los elementos en torno al pivote
        for (int j = izq; j < der; j++) {
            // Si el elemento actual es menor o igual al pivote
            if (arreglo[j] <= pivote) {
                i++;
                // Intercambia los elementos
                cambio(arreglo, i, j);
            }
        }
        cambio(arreglo, i + 1, der);
        return i + 1; // Devuelve el índice del pivote
    }

    /**
     * @param arreglo
     * @param i
     * @param j
     */
    public static void cambio(int[] arreglo, int i, int j) {
        int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
    

    // Ordenar el arreglo usando el algoritmo de Radix Sort
    /**
     * @param arreglo
     */
    public static void radixSort(int[] arreglo) {
        // Loop que crea el array de ordenamiento
        ArrayList<ArrayList<Integer>> radixArray = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++){
            radixArray.add(new ArrayList<Integer>());
        }

        // Encontrar el valor máximo del arreglo
        int valorMax = Arrays.stream(arreglo).max().getAsInt();;
        int exponente = 1;

        while (valorMax / exponente > 0) {
            // Separar los elementos en buckets según el digito
            for (int i = 0; i < arreglo.length; i++) {
                int numero = arreglo[i];
                int digito = (numero / exponente) % 10;
                radixArray.get(digito).add(numero);
            }

            // Reordenar el arreglo
            int index = 0;
            for (int o = 0; o < 10; o++) {
                for (int num : radixArray.get(o)) {
                    arreglo[index++] = num;
                }
                radixArray.get(o).clear();
            }

            // Pasar al siguiente digito
            exponente *= 10;
        }
    }

    // Ordenar el arreglo usando el algoritmo de Bucket Sort
    /**
     * @param arreglo
     */
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