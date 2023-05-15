package ar.edu.unlam.pb1.DominioLavarropas;

public class Lavarropas {

	private boolean estadoPuerta;
	private static final char INGRESO_JABON = 'j';
	private static final char INGRESO_SUAVISANTE = 's';
	private static final int CAPACIDAD_MAXIMA_EN_KG = 10;
	public static final int CAPACIDAD_MINIMA_EN_KG = 1;
	public static final int TEMPERATURA_ALGODON = 40;
	public static final int TEMPERATURA_SINTETCO = 60;
	public static final int TEMPERATURA_LANA = 30;
	public static final int TEMPERATURA_DELICADO = 10;
	public static final int TIEMPO_LAVADO_ALGODON = 120;
	public static final int TIEMPO_LAVADO_SINTETICO = 80;
	public static final int TIEMPO_LAVADO_LANA = 30;
	public static final int TIEMPO_LAVADO_DELICADO = 45;

	public Lavarropas() {
		this.estadoPuerta = false;
	}

	public boolean lavarAlgodon(char ingresoJabon, char ingresoSuavisante, double ingresoKGdeRopa) {

		if (this.estadoPuerta == false && INGRESO_JABON == ingresoJabon && INGRESO_SUAVISANTE == ingresoSuavisante
				&& ingresoKGdeRopa <= CAPACIDAD_MAXIMA_EN_KG) {
			return true;
		}
		return false;
	}

	public boolean lavarDelicado(char ingresoJabon, char ingresoSuavisante, double ingresoKGdeRopa) {
		if (this.estadoPuerta == false && INGRESO_JABON == ingresoJabon && INGRESO_SUAVISANTE == ingresoSuavisante
				&& ingresoKGdeRopa <= CAPACIDAD_MAXIMA_EN_KG) {
			return true;
		}
		return false;
	}

	public boolean lavarSintetico(char ingresoJabon, char ingresoSuavisante, double ingresoKGdeRopa) {
		if (this.estadoPuerta == false && INGRESO_JABON == ingresoJabon && INGRESO_SUAVISANTE == ingresoSuavisante
				&& ingresoKGdeRopa <= CAPACIDAD_MAXIMA_EN_KG) {
			return true;
		}
		return false;
	}

	public boolean lavarLana(char ingresoJabon, char ingresoSuavisante, double ingresoKGdeRopa) {
		if (this.estadoPuerta == false && INGRESO_JABON == ingresoJabon && INGRESO_SUAVISANTE == ingresoSuavisante
				&& ingresoKGdeRopa <= CAPACIDAD_MAXIMA_EN_KG) {
			return true;
		}
		return false;
	}

	public boolean Enjuagar(char suavisante, double ingresoKGdeRopa) {
		if (this.estadoPuerta == false && INGRESO_SUAVISANTE == suavisante
				&& ingresoKGdeRopa <= CAPACIDAD_MAXIMA_EN_KG) {
			return true;
		}
		return false;
	}

	public boolean Centrifugar(double ingresoKGdeRopa) {
		if (this.estadoPuerta == false && ingresoKGdeRopa >= CAPACIDAD_MINIMA_EN_KG
				&& ingresoKGdeRopa <= CAPACIDAD_MAXIMA_EN_KG) {
			return true;
		}
		return false;
	}

	public void trabaAbrir() {
	 this.estadoPuerta = true;

	}

	public void trabaCerrar() {
		 this.estadoPuerta = false;
	}

	public boolean estadoPuerta() {
		return this.estadoPuerta;
	}
}
