package ar.edu.unlam.pb2.Parcial01;

import java.util.Comparator;

public class OrdenarPorRazonSocialDescendente implements Comparator<Cliente>{

	@Override
	public int compare(Cliente o1, Cliente o2) {
		return o1.getRazonSocial().compareTo(o2.getRazonSocial());
	}

}
