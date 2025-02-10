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

public class Sorting
{
    /**
     * @param args
     */
    public static void main(String[] args) {
        // Generar los números
        generadorNumeros();
        
        // Leer los números del archivo
        String archivo = "numeros.txt";
        Comparable[] numeros = leerNumeros(archivo);
        
        // Imprimir los números desordenados
        System.out.println("Números desordenados:");
        System.out.println(Arrays.toString(numeros));
        
        // Menú para seleccionar el algoritmo de ordenamiento
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelecciona el método de ordenamiento:");
        System.out.println("1. InsertionSort");
        System.out.println("2. MergeSort");
        System.out.println("3. QuickSort");
        System.out.println("4. RadixSort");
        System.out.println("5. BucketSort");
        int opcion = scanner.nextInt();
        
        // Ordenar dependiendo de la opción seleccionada
        switch (opcion) {
            case 1:
                insertionSort(numeros);
                break;
            case 2:
                mergeSort(numeros, 0, numeros.length - 1);
                break;
            case 3:
                quickSort(numeros, 0, numeros.length - 1);
                break;
            case 4:
                // Convertir Comparable[] a Integer[] para radixSort
                Integer[] numerosInteger = Arrays.copyOf(numeros, numeros.length, Integer[].class);
                radixSort(numerosInteger);
                numeros = Arrays.copyOf(numerosInteger, numerosInteger.length, Comparable[].class);
                break;
            case 5:
                // Convertir Comparable[] a Integer[] para bucketSort
                Integer[] numerosInteger2 = Arrays.copyOf(numeros, numeros.length, Integer[].class);
                bucketSort(numerosInteger2);
                numeros = Arrays.copyOf(numerosInteger2, numerosInteger2.length, Comparable[].class);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
        
        // Imprimir los números ordenados
        System.out.println("\nNúmeros ordenados:");
        System.out.println(Arrays.toString(numeros));
        
        // Guardar los números ordenados en un archivo
        guardarNumeros("numeros_ordenados.txt", numeros);
    }

    // Generar un archivo con cantidadNumeros números aleatorios
    /**
     * 
     */
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
    /**
     * @param nombreArchivo
     * @return
     */
    public static Integer[] leerNumeros(String nombreArchivo) {
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

        Integer[] arreglo = new Integer[numeros.size()];
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
    public static void guardarNumeros(String nombreArchivo, Comparable[] arreglo) {
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            for (Comparable numero : arreglo) {
                file.write(numero + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + nombreArchivo);
        }
    }

    // Ordenar el arreglo usando el algoritmo de Insertion Sort
    /**
     * @param arreglo
     */
    public static void insertionSort(Comparable[] arreglo) {
        for (int i = 1; i <arreglo.length; i++){
            Comparable key = arreglo[i]; // La variable key toma el valor del elemento que se esta comparando
            int j = i - 1; // la variable j se usa para evaluar los elementos de la lista atras de Key
            while (j >= 0 && arreglo[j].compareTo(key) > 0){
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
    public static void mergeSort(Comparable[] arreglo, int izq, int der) {
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
    public static void merge(Comparable[] arreglo, int izq, int mitad, int der) {
        // Copia los elementos del rango especificado en un arreglo auxiliar
        Comparable[] aux = Arrays.copyOfRange(arreglo, izq, der + 1);
    
        int i = 0; // Índice para la primera mitad del arreglo auxiliar
        int j = mitad - izq + 1; // Índice para la segunda mitad del arreglo auxiliar
        int k = izq; // Índice para el arreglo original
    
        // Combina las dos mitades en el arreglo original
        while (i <= mitad - izq && j < aux.length) {
            if (aux[i].compareTo(aux[j]) <= 0) {
                arreglo[k++] = aux[i++];
            } else {
                arreglo[k++] = aux[j++];
            }
        }
    
        // Copia los elementos restantes de la primera mitad, si los hay
        while (i <= mitad - izq) {
            arreglo[k++] = aux[i++];
        }
    }

    // Ordenar el arreglo usando el algoritmo de Quick Sort
    /**
     * @param arreglo
     * @param izq
     * @param der
     */
    public static void quickSort(Comparable[] arreglo, int izq, int der) {
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
    public static int particion(Comparable[] arreglo, int izq, int der) {
        // Selecciona el último elemento como pivote
        Comparable pivote = arreglo[der];
        int i = izq - 1; // Indice del elemento más pequeño

        // Recorre el arreglo y organiza los elementos en torno al pivote
        for (int j = izq; j < der; j++) {
            // Si el elemento actual es menor o igual al pivote
            if (arreglo[j].compareTo(pivote) <= 0) {
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
    public static void cambio(Comparable[] arreglo, int i, int j) {
        Comparable temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
    
    

    // Ordenar el arreglo usando el algoritmo de Radix Sort
    /**
     * @param arreglo
     */
    public static void radixSort(Integer[] arreglo) { // Radix sort solo funciona para enteros
        // Loop que crea el array de ordenamiento
        ArrayList<ArrayList<Integer>> radixArray = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++){
            radixArray.add(new ArrayList<Integer>());
        }

        // Encontrar el valor máximo del arreglo
        int valorMax = Arrays.stream(arreglo).max(Integer::compareTo).orElse(0);
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
    public static void bucketSort(Integer[] arreglo) { // Bucket sort solo sirve para números reales
        int max = Arrays.stream(arreglo).max(Integer::compareTo).orElse(0);
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