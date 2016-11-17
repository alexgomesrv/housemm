package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = value.replaceAll("[()-]", "");
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		return object.toString();
	}

}
