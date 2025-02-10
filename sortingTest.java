import static org.junit.Assert.*;
import org.junit.Test;

public class sortingTest {
   /**
 * 
 */
@Test // Test de la funcion de insertioSort
    public void insertionSortTest(){
        Comparable[] arreglo ={ 3, 5, 7, 2}; // definir la variable arreglo con una lista predeterminada para ahorranos pasos

        Sorting.insertionSort(arreglo);
        assertArrayEquals(new Comparable[]{2, 3, 5, 7}, arreglo);
    }
    /**
     * 
     */
    @Test // Test de la funcion de radixSort
    public void radixSortTest(){
        Integer[] arreglo ={  5, 4, 3, 2, 1,};
        Sorting.radixSort(arreglo);
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, arreglo);
    }
    /**
     * 
     */
    @Test
    public void bucketSortTest(){
        Integer[] arreglo = {5, 4, 3, 2, 1,};
        Sorting.bucketSort(arreglo);
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, arreglo);
    }
    /**
     * 
     */
    @Test
    public void mergeSortTEst(){
        Comparable[] arreglo = {5, 4, 3, 2, 1,};
        Sorting.mergeSort(arreglo, 0, arreglo.length - 1);
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, arreglo);
    }
    /**
     * 
     */
    @Test
    public void quickSortTest(){
        Comparable[] arreglo = {5, 4, 3, 2, 1,};
        Sorting.quickSort(arreglo, 0, arreglo.length - 1);
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, arreglo);
    }

  
}
