package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("telefoneFormatadoConverter")
public class TelefoneFormatadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = "("
					+ value.substring(0, 2)
					+ ") "
					+ value.substring(2, 7)
					+ "-"
					+ value.substring(7, 11);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		String telefone = object.toString();
		telefone = "("
				+ telefone.substring(0, 2)
				+ ") "
				+ telefone.substring(2, 7)
				+ "-"
				+ telefone.substring(7, 11);
		return telefone;
	}

}
