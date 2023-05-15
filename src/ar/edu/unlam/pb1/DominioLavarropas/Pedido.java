package ar.edu.unlam.pb1.DominioLavarropas;

import java.time.LocalTime;

public class Pedido {

	private String nombre = "";
	private TiposDeRopa tipo; // agregar enum de tipo de tela
	private Integer fecha;
	private Integer numeroDePedido = 0;
	private Double kgRopa = 0.0;
	private Double precio = 0.0;
	private Double imnportePagar = 0.0;
	public static final double PESO_MINIMO_MINIMO = 0.0, PESO_MINIMO_MAXIMO = 4, PESO_MEDIANO_MAXIMO = 7,
			PESO_MAXIMO_MAXIMO = 10, PRECIO_MINIMO = 1000.0, PRECIO_MEDIANO = 1500.0, PRECIO_MAXIMO = 2000.0;

	public Pedido(String nombre, TiposDeRopa tipo, Double kgRopa, Double imnportePagar) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.kgRopa = kgRopa;
		this.imnportePagar = imnportePagar;
		this.fecha = LocalTime.now().getSecond();
		this.numeroDePedido = (int) Math.round(Math.random() * (100 - 0 + 1) + 0);
//																max-min        min
		this.precio = definirPrecio(kgRopa);
	}

	private Double definirPrecio(Double kgRopa) {
		if (kgRopa > PESO_MINIMO_MINIMO && kgRopa <= PESO_MINIMO_MAXIMO) {
			this.precio = PRECIO_MINIMO;
		}
		if (kgRopa > PESO_MINIMO_MAXIMO && kgRopa <= PESO_MEDIANO_MAXIMO) {
			this.precio = PRECIO_MEDIANO;
		}
		if (kgRopa > PESO_MEDIANO_MAXIMO && kgRopa <= PESO_MAXIMO_MAXIMO) {
			this.precio = PRECIO_MAXIMO;
		}
		return this.precio;
	}

	public boolean pagarPedido() {
		boolean pegarPedido = false;
		if (this.imnportePagar >= this.precio) {
			pegarPedido = true;
		}
		return pegarPedido;
	}
	public Double getImnportePagar() {
		return imnportePagar;
	}

	public void setImnportePagar(Double imnportePagar) {
		this.imnportePagar = imnportePagar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TiposDeRopa getTipoDeTela() {
		return tipo;
	}

	public void setTipoDeTela(TiposDeRopa tipoDeTela) {
		this.tipo = tipoDeTela;
	}

	public Integer getFecha() {
		return fecha;
	}

	public void setFecha(Integer fecha) {
		this.fecha = fecha;
	}

	public Integer getNumeroDePedido() {
		return numeroDePedido;
	}

	public void setNumeroDePedido(Integer numeroDePedido) {
		this.numeroDePedido = numeroDePedido;
	}

	public Double getKgRopa() {
		return kgRopa;
	}

	public void setKgRopa(Double kgRopa) {
		this.kgRopa = kgRopa;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public double getPESO_MINIMO_MINIMO() {
		return PESO_MINIMO_MINIMO;
	}

	public double getPESO_MINIMO_MAXIMO() {
		return PESO_MINIMO_MAXIMO;
	}

	public double getPESO_MEDIANO_MAXIMO() {
		return PESO_MEDIANO_MAXIMO;
	}

	public double getPESO_MAXIMO_MAXIMO() {
		return PESO_MAXIMO_MAXIMO;
	}

	public double getPRECIO_MINIMO() {
		return PRECIO_MINIMO;
	}

	public double getPRECIO_MEDIANO() {
		return PRECIO_MEDIANO;
	}

	public double getPRECIO_MAXIMO() {
		return PRECIO_MAXIMO;
	}

	@Override
	public String toString() {
		return " \n ***** Numero De Pedido: " + numeroDePedido + "*****" + "\n ***** Fecha: " + fecha + "    *****"
				+ "\n ***** Nombre: " + nombre + "*****" + "\n ***** Tipo De Tela: " + tipo + "*****"
				+ "\n ***** Kg Ropa: " + kgRopa + "*****" + "\n ***** Precio: $" + precio;
	}

}