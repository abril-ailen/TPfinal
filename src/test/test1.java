package test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import datos.QuickSortMutliThreading;
import datos.QuickSort;


public class test1 {

	// Código de prueba
	public static void main(String args[]) {
		
		int n = 1000000; // Se define el tamaño del array
		int[] arrConcurrente = new int[n]; // Inicializa el array para el Quicksort concurrente
		int[] arrSecuencial = new int[n];  // Inicializa el array para el Quicksort secuencial

		// Generar números aleatorios para los arreglos
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			int num = rand.nextInt(100); // Genera un número aleatorio entre 0 y 99
			arrConcurrente[i] = num;
			arrSecuencial[i] = num;
		}
		
		long tiempoInicio, tiempoFin;
		double tiempoTotal;
		
		/**
		 * QUICKSORT CONCURRENTE
		 * */
		
		// Se usa ForkJoinPool para crear los hilos según los recursos
		ForkJoinPool pool = ForkJoinPool.commonPool();

		// Empieza a contar el tiempo antes de iniciar el primer hilo
		tiempoInicio = System.nanoTime();
		
		// Se inicia el primer hilo en el pool de fork-join para el rango 0, n-1
		pool.invoke(new QuickSortMutliThreading(0, n - 1, arrConcurrente));

		// Toma el tiempo final
		tiempoFin = System.nanoTime();
	
		
		// Calcula e imprime el tiempo total convertido a milisegundos
		tiempoTotal = (tiempoFin - tiempoInicio) / 1e6;
		System.out.println("Tiempo de ejecución Quicksort Concurrente: " + tiempoTotal + " ms\n");
		
		/**
		 * QUICKSORT SECUENCIAL	
		 * */
		tiempoInicio = System.nanoTime();
		QuickSort.quicksort(arrSecuencial, 0, n-1);
		tiempoFin = System.nanoTime();
	
        tiempoTotal = (tiempoFin - tiempoInicio) / 1e6;
        System.out.println("Tiempo de ejecución Quicksort Secuencial: " + tiempoTotal + " ms\n");
        
		
	}
}

