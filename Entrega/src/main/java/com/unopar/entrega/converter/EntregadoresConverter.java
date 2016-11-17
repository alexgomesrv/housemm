package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Entregador;
import com.unopar.entrega.repository.Entregadores;

@FacesConverter(forClass = Entregador.class)
public class EntregadoresConverter implements Converter {
	@Inject
	private Entregadores entregadores;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Entregador retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.entregadores.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Entregador entregador = (Entregador) value;
			return entregador.getIdEntregador() == 0 ? null : String.valueOf(((Entregador) value).getIdEntregador());
		}
		return null;
	}
}
