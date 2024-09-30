package es.uhu.sesion2b;

public class Empleado {

	public enum TipoEmpleado {
		VENDEDOR, ENCARGADO;
	}

	float salarioBase;
	float primas;
	float horasExtras;

	public float calculoNominaBruta(TipoEmpleado tipo, float ventasMes, float horasExtras) {

		if (tipo == TipoEmpleado.ENCARGADO) {
			salarioBase = 2500;
		} else if (tipo == TipoEmpleado.VENDEDOR) {
			salarioBase = 2000;
		}

		if (ventasMes >= 1500) {
			primas = 200;
		} else if (ventasMes >= 1000) {
			primas = 100;
		}

		this.horasExtras = horasExtras * 30;
		
		return salarioBase + primas + this.horasExtras;
		
	}

	public float calculoNominaNeta(float nominaBruta) {
		
		float retencion = 0;
		if(nominaBruta >2500) {
			retencion = 0.18f;
		}else if(nominaBruta>2000) {
			retencion = 0.15f;
		}
	
		return nominaBruta*(1-retencion);
	}

}
