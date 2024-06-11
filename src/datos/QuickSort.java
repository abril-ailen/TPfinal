package datos;

public class QuickSort {

	public static void quicksort(int[] arreglo, int izq, int der) {

		// Se elige el primer elemento del subarreglo como pivote
		int pivote = arreglo[izq];

		// Los elementos mayores al pivote van a la derecha, los menores a la izquierda
		// Se usan dos variables auxiliares: i, j
		int i = izq;
		int j = der;
		
		// Se define una variable temporal para intercambiar elementos
		int swap;

		// Proceso de partición
		while(i < j) {
			
			// Mientras el elemento en la posición i sea menor o igual al pivote se incrementa i
			// Cuando se deja de cumplir la condición el elemento en la posición i es mayor
			// y debe ir a la derecha:
			while(arreglo[i] <= pivote && i < j) {
				i++;
			}
			
			// Mientras el elemento en la posición j sea mayor al pivote se decrementa j
			// Cuando se deja de cumplir la condición el elemento está en el lado incorrecto (es menor)
			// y debe ir a la izquierda:
			while(arreglo[j] > pivote) {
				j--;
			}
			
			// El elemento mayor que el pivote en la posición i se intercambia 
			// con el elemento menor o igual que el pivote en la posición j
			if(i < j) {
				swap = arreglo[i];
				arreglo[i] = arreglo[j];
				arreglo[j] = swap;
			}
		}
		// Se coloca al pivote en la posición correcta
		arreglo[izq] = arreglo[j];
		arreglo[j] = pivote;

		// Se llama de manera recursiva al método quicksort:
		// Se verifica que haya más de un elemento en el subarreglo izquierdo
		if(izq < j - 1) {
			// Si hay más de un elemento se llama al método
			quicksort(arreglo, izq, j - 1);
		}
		// Se verifica si hay más de un elemento en el subarreglo derecho
		if(j + 1 < der) {
			// Si se cumple se llama al método
			// y se envía como parámetro j+1 (índice inicial), der (índice final) 
			quicksort(arreglo, j + 1, der);
		}   
	}
}