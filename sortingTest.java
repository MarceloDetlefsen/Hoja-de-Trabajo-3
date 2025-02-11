import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de datos
 * Ing. Douglas Barrios
 * Colaboradores: 
 * Marcelo Detlefsen - 24554
 * Luis Pedro Figueroa - 24087
 * Luis Pedro Hernández - 24337
 * Fecha: 10/02/2025
 * Descripción: Clase que hace el test de los metodos de ordenamiento.
 */

public class sortingTest {
   /**
 * 
 */
@Test // Test de la funcion de insertioSort
    public void insertionSortTest(){
        int[] arreglo ={ 3, 5, 7, 2}; // definir la variable arreglo con una lista predeterminada para ahorranos pasos

        Sorting.insertionSort(arreglo);
        assertArrayEquals(new int[]{2, 3, 5, 7}, arreglo);
    }
    /**
     * 
     */
    @Test // Test de la funcion de radixSort
    public void radixSortTest(){
        int[] arreglo ={  5, 4, 3, 2, 1,};
        Sorting.radixSort(arreglo);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arreglo);
    }
    /**
     * 
     */
    @Test
    public void bucketSortTest(){
        int[] arreglo = {5, 4, 3, 2, 1,};
        Sorting.bucketSort(arreglo);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arreglo);
    }
    /**
     * 
     */
    @Test
    public void mergeSortTEst(){
        int[] arreglo = {5, 4, 3, 2, 1,};
        Sorting.mergeSort(arreglo, 0, arreglo.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arreglo);
    }
    /**
     * 
     */
    @Test
    public void quickSortTest(){
        int[] arreglo = {5, 4, 3, 2, 1,};
        Sorting.quickSort(arreglo, 0, arreglo.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arreglo);
    }

  
}
