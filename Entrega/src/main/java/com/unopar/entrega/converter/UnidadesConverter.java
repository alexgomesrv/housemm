package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Unidade;
import com.unopar.entrega.repository.Unidades;

@FacesConverter(forClass = Unidade.class)
public class UnidadesConverter implements Converter {

	@Inject
	private Unidades unidades;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Unidade retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.unidades.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Unidade unidade = (Unidade) value;
			return unidade.getIdUnidade() == 0 ? null : String.valueOf(((Unidade) value).getIdUnidade());
		}
		return null;
	}
}
