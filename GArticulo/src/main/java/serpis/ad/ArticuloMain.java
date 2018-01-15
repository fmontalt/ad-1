package serpis.ad;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class ArticuloMain {

	public enum Option {Salir, Nuevo, Editar, Eliminar, Consultar, Listar};
	public enum State {Vacio, Medio, Lleno};
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static class Articulo {
		private long id;
		private String nombre;
		private BigDecimal precio;
		private long categoria;
	}
	
	public static void main(String[] args) {
		
		//showFields(String.class);
		
		Articulo articulo = new Articulo();
		
		new Menu()
			.add("Salir",	null)
			.add("Nuevo", 	() -> nuevo())
			.add("Editar", 	() -> editar())
			.run();
		
//		while (true) {
//			Option option = scanOption();
//			Runnable runnable = null;
//			if (option == Option.Salir)
//				break;
//			else if (option == Option.Nuevo)
//				runnable = () -> nuevo();
//			else if (option == Option.Editar)
//				;
//			else if (option == Option.Eliminar)
//				;
//			else if (option == Option.Consultar)
//				;
//			else //(option == Option.Listar)
//				;
//			runnable.run();
//		}
			
	}
	
	private static void showFields(Class<?> type) {
		for (Field field : type.getDeclaredFields() )
			System.out.printf("%s %s\n", field.getName(), field.getType().getName());
	}
	
	private static void nuevo() {
		//TODO implementar
		System.out.println("Ejecución de nuevo");
	}
	
	private static void editar() {
		//TODO implementar
		System.out.println("Ejecución de editar");
	}
	
	public static <T extends Enum<T>> T scan(Class<T> enumType) {
		T[] constants = enumType.getEnumConstants(); 
		for(int index = 0; index < constants.length; index++)
			System.out.printf("%s - %s\n", index, constants[index]);
		String options = String.format("^[0-%s]$", constants.length - 1);
		while (true) {
			System.out.print("Elige opción: ");
			String line = scanner.nextLine();
			if (line.matches(options))
				return constants[Integer.parseInt(line)];
			System.out.println("Opción inválida. Vuelve a introducir.");
		}
	}
	
	public static int scanInt(String label) {
		while (true) {
			try {
				System.out.print(label);
				String line = scanner.nextLine();
				return Integer.parseInt(line);
			} catch (NumberFormatException ex) {
				System.out.println("Debe ser un número. Vuelve a introducir.");
			}
		}
	}
	
	public static Option scanOption() {
		for (int index = 0; index < Option.values().length; index ++)
			System.out.printf("%s - %s\n", index, Option.values()[index]);
		String options = String.format("^[0-%s]$", Option.values().length - 1);
		while (true) {
			System.out.print("Elige opción: ");
			String line = scanner.nextLine();
			if (line.matches(options))
				return Option.values()[Integer.parseInt(line)];
			System.out.println("Opción inválida. Vuelve a introducir.");
		}
	}

	public static State scanState() {
		for (int index = 0; index < State.values().length; index ++)
			System.out.printf("%s - %s\n", index, State.values()[index]);
		String options = String.format("^[0-%s]$", State.values().length - 1);
		while (true) {
			System.out.print("Elige opción: ");
			String line = scanner.nextLine();
			if (line.matches(options))
				return State.values()[Integer.parseInt(line)];
			System.out.println("Opción inválida. Vuelve a introducir.");
		}
	}
}
