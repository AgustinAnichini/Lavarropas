package ar.edu.unlam.pb1InterfazLavarropas;

import java.util.Scanner;

import ar.edu.unlam.pb1.DominioLavarropas.Lavarropas;
import ar.edu.unlam.pb1.DominioLavarropas.Pedido;
import ar.edu.unlam.pb1.DominioLavarropas.SistemaPedidos;
import ar.edu.unlam.pb1.DominioLavarropas.TiposDeRopa;

public class TestLavarropas {

	static Scanner teclado = new Scanner(System.in);
	private static final int ENCENDER = 0, APAGAR = 9, SALIR = 5, ALGODON = 1, DELICADO = 2, LANA = 3, SINTETICO = 4,
			LAVAR = 1, ENJUAGAR = 2, CENTRIFUGAR = 3, OPCION_400_Rpm = 1, OPCION_600_Rpm = 2, OPCION_800_Rpm = 3,
			OPCION_1200_Rpm = 4, ACCESO_ADMIN = 1, ACCESO_USUARIO = 2, CONTRASENIA = 1234, HACER_PEDIDO = 1,
			VER_PEDIDO = 2, RETIRAR_PEDIDO = 3, VOLVER_MENU_PRINCIPAL = 9, USAR_LAVARROPAS = 1, VER_DINERO = 2,
			PROMEDIO_LAVADOS = 3, VER_TODOS_PEDIDOS = 4;
	private static char ingresoJabon, ingresoSuavisante;
	private static boolean sePudoAlgodon = false, sePudoDelicado = false, sePudoLana = false, sePudoSintetico = false;
	private static double ingresoKGdeRopa;
	private static final String USUARIO = "Administrador";
	private static final char INGRSO_JABON = 'j', INGRSO_SUAVISANTE = 's';
	static SistemaPedidos sistema = new SistemaPedidos();

	public static void main(String[] args) {
		Integer encender, opcionPrincipal, opcionAcceso, opcionAdministrador, contrasenia;
		String usuario;
		do {
			menuAcceso();
			opcionAcceso = teclado.nextInt();
			switch (opcionAcceso) {
			case ACCESO_ADMIN:
				verificacionAdministrador();
				opcionesAdmin();
				break;
			case ACCESO_USUARIO:
				opcionesUsuario();
				break;
			case APAGAR:
				mostrarMensaje("Apagando...");
				break;

			default:
				mostrarMensaje("Opcion incorrecta");
				break;
			}
		} while (opcionAcceso != APAGAR);

	}

	private static void opcionesUsuario() {
		int opcionUsuario;
		do {
			menuUsuario();
			opcionUsuario = teclado.nextInt();

			switch (opcionUsuario) {
			case HACER_PEDIDO:
				menuPrecios();
				crearPedido();
				break;
			case VER_PEDIDO:
				verPedido();
				break;
			case RETIRAR_PEDIDO:
				retirarPedido();
				break;
			case VOLVER_MENU_PRINCIPAL:
				mostrarMensaje("Volviendo al menu principal....");
				break;

			default:
				mostrarMensaje("Opcion incorrecta, intente nuevamente\n");
				break;
			}
		} while (opcionUsuario != VOLVER_MENU_PRINCIPAL);
	}

	private static void opcionesAdmin() {
		Integer opcionAdministrador;
		do {
			menuAdministrador();
			opcionAdministrador = teclado.nextInt();
			switch (opcionAdministrador) {
			case USAR_LAVARROPAS:
				usarLavarropas();
				break;
			case VER_DINERO:
				dineroRecaudado(sistema);
				break;
			case PROMEDIO_LAVADOS:
				promedioLavados(sistema);
				break;
			case VER_TODOS_PEDIDOS:
				verPedidosHechos(sistema);
				break;
			case VOLVER_MENU_PRINCIPAL:
				mostrarMensaje("Volviendo al menu principal...\n");
				break;
			default:
				mostrarMensaje("Opcion incorrecta, intente nuevamente\n");
				break;
			}
		} while (!opcionAdministrador.equals(VOLVER_MENU_PRINCIPAL));
	}

	private static void verPedidosHechos(SistemaPedidos sistema2) {
		Pedido verPedidos[] = new Pedido[SistemaPedidos.CANTIDAD_PEDIDOS];
		sistema.ordenarPedidos();
		verPedidos = sistema.getPedidos();
		for (int i = 0; i < verPedidos.length; i++) {
			if (verPedidos[i] != null) {
				mostrarMensaje("Todos los pedidos hechos son: ");
				mostrarMensaje(verPedidos[i].toString() + "\n");
			}
		}
	}

	private static void promedioLavados(SistemaPedidos sistema2) {
		double promedioLavado = 0.0;
		promedioLavado = sistema.promedioLavados();
		mostrarMensaje("El promedio de lavados es: " + promedioLavado + "Kg de ropa\n");
	}

	private static void dineroRecaudado(SistemaPedidos sistema2) {
		double dineroRecaudado = 0.0;
		dineroRecaudado = sistema.dineroRecaudado();
		mostrarMensaje("El dinero recaudado es: $" + dineroRecaudado + "\n");
	}

	private static void retirarPedido() {
		String nombre = "";
		Integer numeroDePedido = 0;
		boolean pedidoRetirado = false;

		mostrarMensaje("Ingrese nombre de quien hizo el pedido");
		nombre = teclado.next().toLowerCase();
		mostrarMensaje("Ingrese el numero del pedido");
		numeroDePedido = teclado.nextInt();
		pedidoRetirado = sistema.retirarPedido(nombre, numeroDePedido);
		mostrarPedidoRetirado(pedidoRetirado);

	}

	private static void mostrarPedidoRetirado(boolean pedidoRetirado) {
		if (pedidoRetirado) {
			mostrarMensaje("Su pedido fue retirado\n");
		} else {
			mostrarMensaje("No realizo un pedido\n");
		}
	}

	private static void verPedido() {
		String nombre = "";
		Integer numeroDePedido = 0;
		Pedido pedidoEncontrado = null;

		mostrarMensaje("Ingrese nombre de quien hizo el pedido");
		nombre = teclado.next().toLowerCase();
		mostrarMensaje("Ingrese el numero del pedido");
		numeroDePedido = teclado.nextInt();
		pedidoEncontrado = sistema.verPedido(nombre, numeroDePedido);
		mostrarPedidoBuscado(pedidoEncontrado);
	}

	private static void menuPrecios() {
		mostrarMensaje("----- TABLA DE PRECIOS ------");
		mostrarMensaje("Hasta 4 Kg de ropa ---> 1000$");
		mostrarMensaje("De 4 a 7 Kg de ropa ---> 1500$");
		mostrarMensaje("De 7 a 10 Kg de ropa ---> 2000$\n");
	}

	private static void mostrarPedidoBuscado(Pedido pedidoEncontrado) {
		if (pedidoEncontrado != null) {
			mostrarMensaje("*****************************************");
			mostrarMensaje("---- Su pedido es: ----\n" + pedidoEncontrado.toString());
			mostrarMensaje("*****************************************\n");
		} else {
			mostrarMensaje("No realizo un pedido");
		}
	}

	private static void menuUsuario() {
		mostrarMensaje("---- BIENVENIDO AL MENU DE USUARIO ----");
		mostrarMensaje("Ingrese 1 para hacer pedido");
		mostrarMensaje("Ingrese 2 para ver el pedido");
		mostrarMensaje("Ingrese 3 para retirar el pedido");
		mostrarMensaje("Ingrese 9 para salir");
	}

	private static void crearPedido() {
		String nombre = "";
		TiposDeRopa tipo = null;
		Double kgRopa = 0.0;
		Double importePagar = 0.0;
		boolean sePudoPagarPedido = false;

		mostrarMensaje("---- HACER PEDIDO ----");
		mostrarMensaje("Ingresar nombre: ");
		nombre = teclado.next().toLowerCase();
		mostrarMensaje("Ingrese tipo de tela: ");
		tipo = TiposDeRopa.valueOf(teclado.next().toUpperCase());
		do {
			mostrarMensaje("Ingrese Kg de ropa: ");
			kgRopa = teclado.nextDouble();
			precioPorKg(kgRopa);
		} while (kgRopa < 0 || kgRopa > 10);

		do {
			mostrarMensaje("Ingrese importe a pagar: ");
			importePagar = teclado.nextDouble();
			mostrarMensaje("Su vuelto es: " + sistema.calculoVuelto(importePagar, kgRopa));
		} while (importePagar < 0);
		Pedido nuevoPedido = new Pedido(nombre, tipo, kgRopa, importePagar);
		sePudoPagarPedido = nuevoPedido.pagarPedido();
		informarSiSePagoPedido(sePudoPagarPedido, nuevoPedido);
		mostrarMensaje("Su numero de pedido es:" + nuevoPedido.getNumeroDePedido() + "\n");
		mostrarMensaje("*****************************************");
		mostrarMensaje("---- Su pedido es: ----\n" + nuevoPedido.toString());
		mostrarMensaje("*****************************************\n");
	}

	private static void informarSiSePagoPedido(boolean sePudoPagarPedido, Pedido nuevoPedido) {
		if (sePudoPagarPedido) {
			mostrarMensaje("Pago registrado");
			sistema.agregarPedido(nuevoPedido);
		} else {
			mostrarMensaje("No fue posible pagar el pedido");
		}
	}

	private static void precioPorKg(Double kgRopa) {
		if (kgRopa > 0 && kgRopa <= 4) {
			mostrarMensaje("El importe a pagar es: " + Pedido.PRECIO_MINIMO);
		}
		if (kgRopa > 4 && kgRopa <= 7) {
			mostrarMensaje("El importe a pagar es: " + Pedido.PRECIO_MEDIANO);
		}
		if (kgRopa > 7 && kgRopa <= 10) {
			mostrarMensaje("El importe a pagar es: " + Pedido.PRECIO_MAXIMO);
		}
		if (kgRopa < 0 && kgRopa > 10) {
			mostrarMensaje("Peso no permitido\n");
		}
	}

	private static void usarLavarropas() {
		Integer encender;
		Integer opcionPrincipal;
		Lavarropas lavarropa = new Lavarropas();
		mostrarMensaje("Ingrese 0 para encender el Lavarropas");
		encender = teclado.nextInt();
		if (encender == ENCENDER) {

			do {
				menuAdmin();
				opcionPrincipal = teclado.nextInt();
				switch (opcionPrincipal) {
				case LAVAR:
					opcionLavar(lavarropa);
					break;
				case ENJUAGAR:
					opcionEnjuagar(lavarropa);
					break;
				case CENTRIFUGAR:
					opcionCentrifugar(lavarropa);
					break;
				case APAGAR:
					mostrarMensaje("Apagando...");
					break;
				default:
					mostrarMensaje("Opcion Incorrecta");
					break;
				}
			} while (opcionPrincipal != APAGAR);

		}
	}

	private static void menuAdministrador() {
		mostrarMensaje("----BIENVENIDO ADMINISTRADOR----");
		mostrarMensaje("Ingrese 1 para usar el lavarropas");
		mostrarMensaje("Ingrese 2 para dinero recaudado");
		mostrarMensaje("Ingrese 3 para ver el promedio de ropa lavada");
		mostrarMensaje("Ingrese 4 para ver pedidos hechos");
		mostrarMensaje("Ingrese 9 para SALIR");
	}

	private static void verificacionAdministrador() {
		Integer contrasenia;
		String usuario;
		do {
			mostrarMensaje("Ingrese contraseña");
			contrasenia = teclado.nextInt();
			mostrarMensaje("Ingrese Usuario");
			usuario = teclado.next();
		} while (!contrasenia.equals(CONTRASENIA) && !usuario.equals(USUARIO));
	}

	private static void menuAcceso() {
		mostrarMensaje("--- BIENVENIDO ----");
		mostrarMensaje("Ingrese 1 para acceder como administrador");
		mostrarMensaje("Ingrese 2 para acceder como usuario");
		mostrarMensaje("Ingrese 9 para APAGAR");
	}
// --------------------- METODOS ----------------------------

	private static void opcionCentrifugar(Lavarropas lavarropa) {
		elegirVelocidad(lavarropa);
		trabaAbrir(lavarropa);
		do {
			ingresoKGdeRopa = ingresarKilos("Ingrese peso en KG de la prenda");
		} while (ingresoKGdeRopa <= Lavarropas.CAPACIDAD_MINIMA_EN_KG);
		trabaCerrar(lavarropa);
		boolean sePudoCentrifugar = lavarropa.Centrifugar(ingresoKGdeRopa);
		if (sePudoCentrifugar) {
			for (int funcionCentrifugar = 0; funcionCentrifugar <= 1; funcionCentrifugar++) {
				switch (funcionCentrifugar) {
				case 0:
					mostrarMensaje("El lavarropas esta Centrifugando");
					break;
				case 1:
					mostrarMensaje("El lavarropas esta Desagotando\n");
					break;
				}
			}
		} else {
			mostrarMensaje("Excedio la cantidad de Kg, Intentelo nuevamente");
		}
		mostrarMensaje("Fin del Centrifugado\n");
		trabaAbrir(lavarropa);
		mostrarMensaje("Puede retirar las prendas\n");
	}

	private static void elegirVelocidad(Lavarropas lavarropa) {
		int opcionCentrifugar;
		do {
			menuCentrifugar();
			opcionCentrifugar = teclado.nextInt();
			switch (opcionCentrifugar) {
			case OPCION_400_Rpm:
				mostrarMensaje("Se va a centrifugar a 400 Rpm\n");
				lavarropa.Centrifugar(ingresoKGdeRopa);
				break;
			case OPCION_600_Rpm:
				mostrarMensaje("Se va a centrifugar a 600 Rpm\n");
				lavarropa.Centrifugar(ingresoKGdeRopa);
				break;
			case OPCION_800_Rpm:
				mostrarMensaje("Se va a centrifugar a 800 Rpm\n");
				lavarropa.Centrifugar(ingresoKGdeRopa);
				break;
			case OPCION_1200_Rpm:
				mostrarMensaje("Se va a centrifugar a 1200 Rpm\n");
				lavarropa.Centrifugar(ingresoKGdeRopa);
				break;

			default:
				mostrarMensaje("Opcion incorrecta\n");
				break;
			}
		} while (!(opcionCentrifugar >= 1) || !(opcionCentrifugar <= 4));
	}

	private static void opcionEnjuagar(Lavarropas lavarropa) {
		trabaAbrir(lavarropa);
		do {
			mostrarMensaje("Agregue Suavisante al lavarropas y aprete la S");
			ingresoSuavisante = teclado.next().toLowerCase().charAt(0);
			ingresoKGdeRopa = ingresarKilos("Ingrese peso en KG de la prenda");
		} while (!(ingresoSuavisante == INGRSO_SUAVISANTE) && ingresoKGdeRopa <= Lavarropas.CAPACIDAD_MINIMA_EN_KG);
		trabaCerrar(lavarropa);
		boolean sePudoEnjuagar = lavarropa.Enjuagar(ingresoSuavisante, ingresoKGdeRopa);
		if (sePudoEnjuagar) {
			for (int funcionEnjuagar = 0; funcionEnjuagar <= 2; funcionEnjuagar++) {
				switch (funcionEnjuagar) {
				case 0:
					mostrarMensaje("El lavarropas esta en Remojando");
					break;
				case 1:
					mostrarMensaje("El lavarropas esta Enjuagando");
					break;
				case 2:
					mostrarMensaje("El lavarropas esta Desagotando\n");
					break;
				}
			}
		} else {
			mostrarMensaje("Excedio la cantidad de Kg o no incorporo suavizante, Intentelo nuevamente");
		}
		mostrarMensaje("Fin del Enjuague\n");
		trabaAbrir(lavarropa);
		mostrarMensaje("Puede retirar las prendas\n");
	}

	private static void trabaCerrar(Lavarropas lavarropa) {
		lavarropa.trabaCerrar();

		boolean sepudoCerrar = lavarropa.estadoPuerta();
		if (!sepudoCerrar) {
			mostrarMensaje("La puerta esta: CERRADA");
		}

	}

	private static void trabaAbrir(Lavarropas lavarropa) {
		lavarropa.trabaAbrir();
		boolean sepudoAbrir = lavarropa.estadoPuerta();
		if (sepudoAbrir) {
			mostrarMensaje("La puerta esta: ABIERTA");
		}
	}

	private static void opcionLavar(Lavarropas lavarropa) {
		int opcion;
		opcion = SALIR;

		do {
			menuLavado();
			opcion = teclado.nextInt();

			switch (opcion) {
			case ALGODON:
				mostrarMensaje("Se va a lavar a: " + Lavarropas.TEMPERATURA_ALGODON + "Grados");
				mostrarMensaje("Duracion de lavado: " + Lavarropas.TIEMPO_LAVADO_ALGODON + "Minutos");
				lavarAlgodon(lavarropa);
				break;
			case DELICADO:
				mostrarMensaje("Se va a lavar a: " + Lavarropas.TEMPERATURA_DELICADO + "Grados");
				mostrarMensaje("Duracion de lavado: " + Lavarropas.TIEMPO_LAVADO_DELICADO + "Minutos\n");
				lavarDelicado(lavarropa);
				break;
			case LANA:
				mostrarMensaje("Se va a lavar a: " + Lavarropas.TEMPERATURA_LANA + "Grados");
				mostrarMensaje("Duracion de lavado: " + Lavarropas.TIEMPO_LAVADO_LANA + "Minutos\n");
				lavarLana(lavarropa);
				break;
			case SINTETICO:
				mostrarMensaje("Se va a lavar a : " + Lavarropas.TEMPERATURA_SINTETCO + "Grados");
				mostrarMensaje("Duracion de lavado : " + Lavarropas.TIEMPO_LAVADO_SINTETICO + "Minutos\n");
				lavarSintetico(lavarropa);
				break;
			case SALIR:

				break;
			default:
				mostrarMensaje("Opcion incorrecta\n");
				break;
			}
		} while (opcion != SALIR);
	}

	private static void jabonSuavisanteyPeso() {
		do {
			mostrarMensaje("Agregue Jabon al lavarropas y aprete la J");
			ingresoJabon = teclado.next().toLowerCase().charAt(0);
			mostrarMensaje("Agregue Suavisante al lavarropas y aprete la S");
			ingresoSuavisante = teclado.next().toLowerCase().charAt(0);
			ingresoKGdeRopa = ingresarKilos("Ingrese peso en KG de la prenda");
		} while (ingresoJabon != INGRSO_JABON || ingresoSuavisante != INGRSO_SUAVISANTE
				|| ingresoKGdeRopa <= Lavarropas.CAPACIDAD_MINIMA_EN_KG);
	}

	private static void lavarSintetico(Lavarropas lavarropa) {
		trabaAbrir(lavarropa);
		jabonSuavisanteyPeso();
		trabaCerrar(lavarropa);
		sePudoSintetico = lavarropa.lavarSintetico(ingresoJabon, ingresoSuavisante, ingresoKGdeRopa);
		if (sePudoSintetico) {
			mostrarMensaje(estadoLavados(sePudoSintetico, lavarropa));
		} else {
			mostrarMensaje("Excedio la cantidad de KG, le recordamos que el maximo permitdo es de 10 KG ");
			mostrarMensaje("Intentelo Nuevamente");
		}
	}

	private static void lavarLana(Lavarropas lavarropa) {
		trabaAbrir(lavarropa);
		jabonSuavisanteyPeso();
		trabaCerrar(lavarropa);
		sePudoLana = lavarropa.lavarLana(ingresoJabon, ingresoSuavisante, ingresoKGdeRopa);
		if (sePudoLana) {
			mostrarMensaje(estadoLavados(sePudoLana, lavarropa));
		} else {
			mostrarMensaje("Excedio la cantidad de KG, le recordamos que el maximo permitdo es de 10 KG ");
			mostrarMensaje("Intentelo Nuevamente");
		}

	}

	private static void lavarDelicado(Lavarropas lavarropa) {
		trabaAbrir(lavarropa);
		jabonSuavisanteyPeso();
		trabaCerrar(lavarropa);
		sePudoDelicado = lavarropa.lavarDelicado(ingresoJabon, ingresoSuavisante, ingresoKGdeRopa);
		if (sePudoDelicado) {
			mostrarMensaje(estadoLavados(sePudoDelicado, lavarropa));
		} else {
			mostrarMensaje("Excedio la cantidad de KG, le recordamos que el maximo permitdo es de 10 KG ");
			mostrarMensaje("Intentelo Nuevamente");
		}

	}

	private static void lavarAlgodon(Lavarropas lavarropa) {
		trabaAbrir(lavarropa);
		jabonSuavisanteyPeso();
		trabaCerrar(lavarropa);
		sePudoAlgodon = lavarropa.lavarAlgodon(ingresoJabon, ingresoSuavisante, ingresoKGdeRopa);
		if (sePudoAlgodon) {
			mostrarMensaje(estadoLavados(sePudoAlgodon, lavarropa));
		} else {
			mostrarMensaje("Excedio la cantidad de KG, le recordamos que el maximo permitdo es de 10 KG ");
			mostrarMensaje("Intentelo Nuevamente");
		}
	}

	private static String estadoLavados(Boolean sePudo, Lavarropas lavarropa) {
		if (sePudo) {
			for (int funcion = 0; funcion <= 4; funcion++) {
				switch (funcion) {
				case 0:
					mostrarMensaje("El lavarropas esta en Remojo");
					break;
				case 1:
					mostrarMensaje("El lavarropas esta Lavando");
					break;
				case 2:
					mostrarMensaje("El lavarropas esta Enjuagando");
					break;
				case 3:
					mostrarMensaje("El lavarropas esta Desagotando");
					break;
				case 4:
					mostrarMensaje("El lavarropas esta Centrifugando");
					break;
				}

			}
		} else {
			mostrarMensaje("Excedio la cantidad de Kg");
		}
		mostrarMensaje("Fin del lavado\n");
		trabaAbrir(lavarropa);
		return "Puede retirar las prendas\n";
	}

	private static void menuLavado() {
		mostrarMensaje(" *******Bienvenidos al panel de lavado*******");
		mostrarMensaje("Ingrese 1 para Lavar Algodon");
		mostrarMensaje("Ingrese 2 para Lavar Delicado");
		mostrarMensaje("Ingrese 3 para lavar Lana");
		mostrarMensaje("Ingrese 4 para lavar Sintéticos");
		mostrarMensaje("Ingrese 5 para volver al Menu Principal\n");
	}

	public static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);

	}

	public static void menuAdmin() {
		mostrarMensaje("*******BIENVENIDO al panel del lavarropas******");
		mostrarMensaje("*******CAPACIDAD MAXIMA : 10 KG ******");
		mostrarMensaje("Ingrese 1 para Lavar");
		mostrarMensaje("Ingrese 2 para Enjuagar");
		mostrarMensaje("Ingrese 3 para Centrifugar");
		mostrarMensaje("Ingrese 9 para Apagar");
	}

	public static void menuCentrifugar() {
		mostrarMensaje("*******BIENVENIDO al panel de Centrifugado******");
		mostrarMensaje("Ingrese 1 para centrifugar a 400 Rpm");
		mostrarMensaje("Ingrese 2 para centrifugar a 600 Rpm");
		mostrarMensaje("Ingrese 3 para centrifugar a 800 Rpm");
		mostrarMensaje("Ingrese 4 para centrifugar a 1200 Rpm");
	}

	public static double ingresarKilos(String mensaje) {
		mostrarMensaje(mensaje);
		return teclado.nextDouble();
	}
}