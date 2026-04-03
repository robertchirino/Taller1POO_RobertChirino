

//Robert Chirino Miranda 21.370.498-2


package All;
import java.io.*;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static String[] usuarios = new String[10];
	static String[] actividades = new String[300];
	static String[] fecha = new String[300];
	static String[] id = new String[300];
	static int[] horas = new int[300];
	static int registroContador;
	static String[] nombresUsuarios = new String[10];
	static String[] passwordsUsuarios = new String[10];
	static int totalUsuarios = 0;
	static String usuarioActual = "";	
	static String contrasenaActual = "";

	public static void main(String[] args) throws FileNotFoundException {
		cargarRegistros();
		cargarUsuarios();

		do {
			System.out.println("--- Menu Principal ---");
			System.out.println("1) Menu Usuario");
			System.out.println("2) Menu Analisis");
			System.out.println("3) Salir");
			System.out.print("Opcion: ");
			String op = sc.nextLine().trim();
			switch (op) {
			case "1":
				boolean valido = false;
				do {
					System.out.print("usuario: ");
					String us = sc.nextLine();
					if (us.equals("0"))
						return;
					System.out.print("contraseña: ");
					String cs = sc.nextLine();
					valido = validarUsuario(us, cs);
				} while (valido == false);
				menuUsuario();
				break;
			case "2":
				menuAnalisis();
				break;
			case "3":
				System.out.println("Garcias");
				return;
			}

		} while (true);

	}

	private static void menuAnalisis() {
		String opc;
		do{
		System.out.println("Bienvenido al menu de analisis!\r\n" + "\r\n" + "Que deseas realizar?\r\n" + "\r\n"
				+ "1) Actividad mas realizada\r\n" + "2) Actividad mas realizada por cada usuario\r\n"
				+ "3) Usuario con mayor procastinacion\r\n" + "4) Ver todas las actividades\r\n" + "5) volver");
		opc = sc.nextLine();
		switch (opc) {
		case "1":
			actividadMaisRealizada();
			break;
		case "2":
			maisRealizadaCdaUsuario();
			break;
		case "3":
			maisProcrastinador();
			break;
		case "4":
			verActividades();
			break;
		case "5":

			break;
		}
	}while(!opc.equals("5"));

	}

	private static void verActividades() {
		System.out.println("Todas las Actividades ");
  
  if (registroContador == 0) {
      System.out.println("no hay ninguna actividad :c");
      return;
  }

  for (int i = 0; i < registroContador; i++) {
      System.out.println((i + 1) + ") Usuario: " + id[i] + 
                         " | Fecha: " + fecha[i] + 
                         " | Duracion: " + horas[i] + " hora/s" + 
                         " | Actividad: " + actividades[i]);
  }
  
  System.out.println("-----------------------------------------");
	}

	private static void maisProcrastinador() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'masProcrastinador'");
	}

	private static void maisRealizadaCdaUsuario() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'masRealizadaCdaUsuario'");
	}

	private static void actividadMaisRealizada() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actividadMaisRealizada'");
	}

	private static void menuUsuario() throws FileNotFoundException {
		String opcion;
		do {
			System.out.println(
					"" + "Que deseas realizar?\r\n" + "1) Registrar actividad\r\n" + "2) Modificar actividad\r\n"
							+ "3) Eliminar actividad\r\n" + "4) Cambiar contraseña\r\n" + "5) volver");
			System.out.print("opcion: ");
			opcion = sc.nextLine();
			switch (opcion) {
			case "1":
				agregarActividad();
				break;
			case "2":
				modificarActividad();
				break;
			case "3":
				eliminarActividad();
				break;
			case "4":
				cambiarContraseña();
				break;
			case "5":
				break;

			}
		} while (!opcion.equals("5"));

	}

	private static void cambiarContraseña() {
		System.out.print("escriba su contraseña nueva: ");
		String niuContra = sc.nextLine();
		System.out.print("reescriba su contraseña nueva: ");
		String aux = sc.nextLine();
		if (niuContra.equals(aux)) {
			for (int i = 0; i < totalUsuarios; i++) {
	            if (nombresUsuarios[i].equals(usuarioActual)) {
	                passwordsUsuarios[i] = niuContra;
	                break;
	            }
	        }
	        guardarUsuarios(); 
	        System.out.println("contraseña cambiada con exito");
		}
	}

	private static void guardarUsuarios() {
		try {
	        PrintWriter escritor = new PrintWriter(new BufferedWriter(new FileWriter("Usuarios.txt", false)));

	        for (int i = 0; i < totalUsuarios; i++) {
	            escritor.println(nombresUsuarios[i] + ";" + passwordsUsuarios[i]);
	        }
	        escritor.close();
	    } catch (IOException e) {
	        System.out.println("error al actualizar la informacion: " + e.getMessage());
	    }		
	}

	private static void eliminarActividad() {
		System.out.println("que actividad deseas eliminar?");
		int[] listaIndices = new int[300];
		int contador = 0;
		for (int i = 0; i != registroContador; i++) {
			if (id[i].equals(usuarioActual)) {
				System.out
						.println(contador + 1 + ") " + actividades[i] + ", " + fecha[i] + ", " + horas[i] + " hora/s");
				listaIndices[contador] = i;
				contador++;
			}
		}
		System.out.println("0 para regresar");
		System.out.print("opcion: ");
		int inpu = Integer.parseInt(sc.nextLine()) - 1;
		if (inpu == -1)
			return;

		if (inpu >= 0 && inpu < contador) {
			int indiceAEliminar = listaIndices[inpu];

			for (int i = indiceAEliminar; i < registroContador - 1; i++) {
				id[i] = id[i + 1];
				fecha[i] = fecha[i + 1];
				horas[i] = horas[i + 1];
				actividades[i] = actividades[i + 1];
			}

			registroContador--;

			guardarRegistros();

			System.out.println("actividad eliminada");
		} else {
			System.out.println("opcion invalida.");
		}
	}

	private static void modificarActividad() {
		System.out.println("que actividad deseas modificar?");
		int[] listaIndices = new int[300];
		int contador = 0;
		for (int i = 0; i != registroContador; i++) {
			if (id[i].equals(usuarioActual)) {
				System.out
						.println(contador + 1 + ") " + actividades[i] + ", " + fecha[i] + ", " + horas[i] + " hora/s");
				listaIndices[contador] = i;
				contador++;
			}
		}
		System.out.println("0 para regresar");
		System.out.print("opcion: ");
		int inpu = Integer.parseInt(sc.nextLine()) - 1;
		if (inpu == -1)
			return;
		if (inpu >= 0 && inpu < contador) {
			int indiceReal = listaIndices[inpu];
			System.out
					.println("que deseas modificar?\r\n" + "1) Fecha\r\n" + "2) Duracion\r\n" + "3) Tipo de actividad");
			String opcionMod = sc.nextLine();
			switch (opcionMod) {
			case "1":
				System.out.print("ingrese nueva fecha (DD/MM/AAAA): ");
				fecha[indiceReal] = sc.nextLine();
				break;
			case "2":
				System.out.print("ingrese nueva duracion (horas): ");
				horas[indiceReal] = Integer.parseInt(sc.nextLine());
				break;
			case "3":
				System.out.print("ingrese nuevo tipo de actividad: ");
				actividades[indiceReal] = sc.nextLine();
				break;
			default:
				System.out.println("opcion no valida.");
				return;
			}
			guardarRegistros();
			System.out.println("actividad modificada");
		} else {
			System.out.println("opcion no valida");
		}

	}

	private static void guardarRegistros() {
		try {
			PrintWriter escritor = new PrintWriter(new BufferedWriter(new FileWriter("Registros.txt", false)));

			for (int i = 0; i < registroContador; i++) {
				escritor.println(id[i] + ";" + fecha[i] + ";" + horas[i] + ";" + actividades[i]);
			}

			escritor.close();
		} catch (IOException e) {
			System.out.println("error al guardar los registros: " + e.getMessage());
		}
	}

	private static void agregarActividad() {
		if (registroContador >= 300) {
	        System.out.println("se ha alcanzado el limite de 300 actividades");
	        return;
	    }

	    System.out.println(" Registrar Nueva Actividad");
	    id[registroContador] = usuarioActual;
	    System.out.print("ingrese la fecha (DD/MM/AAAA): ");
	    fecha[registroContador] = sc.nextLine();
	    int horasIngresadas = 0;
	    boolean horaValida = false;
	    while (!horaValida) {
	        try {
	            System.out.print("ingrese la cantidad de horas: ");
	            horasIngresadas = Integer.parseInt(sc.nextLine());
	            horaValida = true;
	        } catch (NumberFormatException e) {
	            System.out.println("ingrese un numero valido de horas.");
	        }
	    }
	    horas[registroContador] = horasIngresadas;

	    System.out.print("ingrese la descripcion de la actividad: ");
	    actividades[registroContador] = sc.nextLine();
	    registroContador++;
	    guardarRegistros();
	    System.out.println("actividad registrada");
	}

	private static void cargarRegistros() throws FileNotFoundException {
		Scanner lec = new Scanner(new File("Registros.txt"));
		int contador = 0;
		do {
			String linea = lec.nextLine();
			String[] partes = linea.split(";");
			id[contador] = partes[0];// roerdoor
			fecha[contador] = partes[1];// afecha
			horas[contador] = Integer.parseInt(partes[2]);// horas
			actividades[contador] = partes[3];// acitvidad
			contador++;
			registroContador++;
		} while (lec.hasNextLine());
		System.out.println("se cargaron " + registroContador + " actividades con exito...");
		lec.close();
	}
	private static void cargarUsuarios() throws FileNotFoundException {
	    File archivo = new File("Usuarios.txt");
	    if (!archivo.exists()) return;

	    Scanner lec = new Scanner(archivo);
	    int i = 0;
	    while (lec.hasNextLine() && i < 10) {
	        String[] partes = lec.nextLine().split(";");
	        nombresUsuarios[i] = partes[0];
	        passwordsUsuarios[i] = partes[1];
	        i++;
	    }
	    totalUsuarios = i;
	    lec.close();
	}
	private static boolean validarUsuario(String usuario, String contrasena){
		for (int i = 0; i < totalUsuarios; i++) {
	        if (nombresUsuarios[i].equals(usuario) && passwordsUsuarios[i].equals(contrasena)) {
	            usuarioActual = usuario; 
	            System.out.println("Acceso correcto\r\n"
	            		+ "Bienvenido " + usuario);
	            return true;
	        }
	    }
	    System.out.println("Acceso incorrecto.");
	    return false;
	}

}
