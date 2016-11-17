package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Cidade;
import com.unopar.entrega.repository.Cidades;

@FacesConverter(forClass = Cidade.class)
public class CidadesConverter implements Converter {
	@Inject
	private Cidades cidades;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cidade retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.cidades.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cidade unidade = (Cidade) value;
			return unidade.getIdCidade() == 0 ? null : String.valueOf(((Cidade) value).getIdCidade());
		}
		return null;
	}
}
