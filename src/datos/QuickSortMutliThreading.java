package datos;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class QuickSortMutliThreading extends RecursiveAction {

	int start, end;
	int[] arr;

	
	// Método para encontrar un pivote aleatorio y particionar el array
	// recibe como parámetros: ínidice de inicio, ínidice de fin y el array a particionar
	private int partition(int start, int end, int[] arr) {
		int i = start, j = end;

		// Se genera un índice de pivote aleatorio
		int pivote = new Random().nextInt(j - i) + i;

		// Intercambia el pivote con el último elemento del array
		int t = arr[j]; // Se usa la variable t como auxiliar, y almacena el valor de arr[j] (último elemento)
		arr[j] = arr[pivote]; // en arr[j] se guarda el pivote
		arr[pivote] = t; // en la posición del pivote se guarda t
		j--; // Decrementa j para no incluir al pivote en la partición

		// Se realiza la partición con un ciclo while de manera que los índices no se crucen
		while (i <= j) {
			
			// Si el elemento en la posición i es menor o igual al pivote, el elemento 
			// ya está en el lado correcto (izquierda) y no se necesita intercambiar
			if (arr[i] <= arr[end]) {
				i++; // se mueve el índice a la derecha
				continue; // Salta al siguiente ciclo del bucle
			}

			// Si el elemento en la posición j es mayor o igual al pivote, el elemento 
			// ya está en el lado correcto (derecha) y no se necesita intercambiar
			if (arr[j] >= arr[end]) {
				j--; // se mueve el índice a la izquierda
				continue; // Salta al siguiente ciclo del bucle
			}
			
			// Si las condiciones anteriores no se cumplen los elementos están en el lado incorrecto
			// se deben intercambiar arr[i] y arr[j]
			t = arr[j]; // se usa la variable auxiliar t para guardar arr[j]
			arr[j] = arr[i]; // guarda arr[i] en arr[j]
			arr[i] = t; // guarda t en arr[i]
			j--; // Se decrementa j para moverse a la siguiente posición de la izquierda
			i++; // Se incrementa i para moverse a la siguiente posición de la derecha
		}

		// En este punto todos los elementos a la izq de j son menores o iguales al pivote
		// y todos los elementos a la derecha de i son mayores o iguales al pivote (están en el lado correcto)
		
		// Intercambia el pivote guardado en la última posición a la posición correcta
		t = arr[j + 1]; // guarda el valor siguiente a j en la variable auxiliar t
		arr[j + 1] = arr[end]; // guarda el pivote en la posición j+1
		arr[end] = t; // guarda el valor de t en la ultima posición
		return j + 1; // retorna el índice del pivote en la posición correcta
	}

	// Constructor del método QuickSort
	public QuickSortMutliThreading(int start, int end, int[] arr) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	
	// El método compute() ejecuta el ordenamiento QuickSort
	// de manera concurrente y recursiva
	@Override
	protected void compute() {
		
		// Caso base: si el índice de inicio es mayor o igual al índice final 
		// el subarray es de tamaño 0 o negativo y retorna null
		if (start >= end) {


			// Se llama al método partition() para encontrar la posición
			// del pivote y dividir el array en dos partes
			int p = partition(start, end, arr); // p es la posición del pivote

			// Se divide el array en subproblemas/subarrays creando 
			// dos instancias de la clase QuickSortMutliThreading
			QuickSortMutliThreading left = new QuickSortMutliThreading(start, p - 1, arr); // p - 1 porque el pivote ya está en la posición correcta
			QuickSortMutliThreading right = new QuickSortMutliThreading(p + 1, end, arr); // p + 1 para ignorar al pivote y empezar desde el siguiente elemento

			invokeAll(left, right);

			// No se envía nada como retorno
		}
	}
}
