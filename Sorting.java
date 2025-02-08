
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
    public static void main(String[] args) {
        // Generar los números
        generadorNumeros();
        
        // Leer los números del archivo
        String archivo = "numeros.txt";
        int[] numeros = leerNumeros(archivo);
        
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
                break;
            case 2:
                mergeSort(numeros, 0, numeros.length - 1);
                break;
            case 3:
                quickSort(numeros, 0, numeros.length - 1);
                break;
            case 4:
                radixSort(numeros);
                break;
            case 5:
                bucketSort(numeros);
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
    public static void guardarNumeros(String nombreArchivo, int[] arreglo) {
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            for (int numero : arreglo) {
                file.write(numero + "\n");
            }
        } catch (IOException e) {
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
    public static void quickSort(int[] arreglo, int izq, int der) {
        if (izq < der) {
            // Obtiene el índice del pivote
            int pivote = particion(arreglo, izq, der);
            // Ordena las dos mitades recursivamente
            quickSort(arreglo, izq, pivote - 1);
            quickSort(arreglo, pivote + 1, der);
        }
    }

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

    public static void cambio(int[] arreglo, int i, int j) {
        int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
    

    // Ordenar el arreglo usando el algoritmo de Radix Sort
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