package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cpfCnpjFormatadoConverter")
public class CpfCnpjFormatadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = "(" + value.substring(0, 2) + ") " + value.substring(2, 7) + "-" + value.substring(7, 11);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		String cpfcnpj = object.toString();
		if (cpfcnpj != null && !cpfcnpj.isEmpty()) {
			if (cpfcnpj.length() == 11) {
				cpfcnpj = cpfcnpj.substring(0, 3) + "." + cpfcnpj.substring(3, 6) + "." + cpfcnpj.substring(6, 9) + "-"
						+ cpfcnpj.substring(9, 11);
			} else if (cpfcnpj.length() == 14) {
				cpfcnpj = cpfcnpj.substring(0, 2) + "." + cpfcnpj.substring(2, 5) + "." + cpfcnpj.substring(5, 8) + "/"
						+ cpfcnpj.substring(8, 12) + "-" + cpfcnpj.substring(12, 14);
			}
		}

		return cpfcnpj;
	}

}
