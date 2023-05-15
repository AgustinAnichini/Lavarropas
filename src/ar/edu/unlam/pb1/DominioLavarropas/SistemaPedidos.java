package ar.edu.unlam.pb1.DominioLavarropas;

public class SistemaPedidos {
	public static final int CANTIDAD_PEDIDOS = 10;
	private Pedido pedidos[];
	public static double vuelto;

	public SistemaPedidos() {
		this.pedidos = new Pedido[CANTIDAD_PEDIDOS];
		this.vuelto = 0.0;
	}

	public Pedido[] getPedidos() {
		return pedidos;
	}

	public void setPedidos(Pedido[] pedidos) {
		this.pedidos = pedidos;
	}

	public boolean agregarPedido(Pedido nuevoPedido) {
		boolean sePudoAgregarPedido = false;
		int indice = 0;
		for (indice = 0; indice < pedidos.length; indice++) {
			if (pedidos[indice] == null && !sePudoAgregarPedido) { // ==
				pedidos[indice] = nuevoPedido;
				sePudoAgregarPedido = true;
			}
		}

		return sePudoAgregarPedido;

	}

	public Pedido verPedido(String nombre, Integer numeroDePedido) {
		Pedido pedidoEncontrado = null;
		boolean seEncontroPedido = false;

		for (int i = 0; i < this.pedidos.length; i++) {
			if (pedidos[i] != null && !seEncontroPedido && this.pedidos[i].getNombre().equals(nombre)
					&& this.pedidos[i].getNumeroDePedido().equals(numeroDePedido)) {
				pedidoEncontrado = this.pedidos[i];
				seEncontroPedido = true;
			}
		}
		return pedidoEncontrado;
	}

	public boolean retirarPedido(String nombre, Integer numeroDePedido) {
		boolean sePudoRetirar = false;
		for (int i = 0; i < pedidos.length; i++) {
			if (pedidos[i] != null && !sePudoRetirar && this.pedidos[i].getNombre().equals(nombre)
					&& this.pedidos[i].getNumeroDePedido().equals(numeroDePedido)) {

				this.pedidos[i] = null;
				sePudoRetirar = true;
			}
		}

		return sePudoRetirar;
	}

	public double dineroRecaudado() {
		double dineroRecaudado = 0.0;
		for (int i = 0; i < pedidos.length; i++) {
			if (pedidos[i] != null) {
				dineroRecaudado += pedidos[i].getImnportePagar();
				dineroRecaudado -= vuelto;
			}
		}

		return dineroRecaudado;
	}

	public double promedioLavados() {
		double promedioLavados = 0.0;
		int contadorLavados = 0;
		double acumuladorKgRopa = 0.0;

		for (int i = 0; i < pedidos.length; i++) {
			if (pedidos[i] != null) {
				acumuladorKgRopa += pedidos[i].getKgRopa();
				contadorLavados++;
			}
			promedioLavados = acumuladorKgRopa / contadorLavados;
		}
		return promedioLavados;
	}

	public void ordenarPedidos() {
		Pedido auxiliar = null;
		for (int j = 0; j < pedidos.length; j++) {
			for (int i = 0; i < pedidos.length - 1; i++) {
				if (pedidos[i] != null && pedidos[i + 1] != null && pedidos[i].getFecha() < pedidos[i + 1].getFecha()) {
					auxiliar = pedidos[i + 1];
					pedidos[i + 1] = pedidos[i];
					pedidos[i] = auxiliar;
				}
			}
		}
	}

	public static double calculoVuelto(Double importePagar, Double kgRopa) {
		if (kgRopa > 0 && kgRopa <= 4) {
			vuelto = importePagar - Pedido.PRECIO_MINIMO;
		}
		if (kgRopa > 4 && kgRopa <= 7) {
			vuelto = importePagar - Pedido.PRECIO_MEDIANO;
		}
		if (kgRopa > 7 && kgRopa <= 10) {
			vuelto = importePagar - Pedido.PRECIO_MAXIMO;
		}

		return vuelto;
	}
}
