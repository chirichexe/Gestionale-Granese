package inventario.model;

import java.util.Objects;

public class Scaffale implements Comparable<Scaffale>{
	private int numero;
	private char reparto;
	
	public Scaffale(int numero, char reparto) {
		if (numero==0 || numero<0 ) throw new IllegalArgumentException("Gli scaffali sono numeri maggiori o uguali di 1");
		this.numero = numero;
		this.reparto = reparto;
	}
	
	public static Scaffale of(int numero, char reparto) {
		return new Scaffale(numero, reparto);
	}

	public int getNumero() {
		return numero;
	}

	public char getReparto() {
		return reparto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero, reparto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scaffale other = (Scaffale) obj;
		return numero == other.numero && reparto == other.reparto;
	}

	@Override
	public String toString() {
		return "Reparto " + reparto + " - Scaffale " + numero;
	}


	@Override
	public int compareTo(Scaffale o) {
		return this.getNumero()-o.getNumero();
	}
	
	
}
