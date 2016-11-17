package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Endereco;
import com.unopar.entrega.repository.Enderecos;

@FacesConverter(forClass = Endereco.class)
public class EnderecosConverter implements Converter {
	@Inject
	private Enderecos enderecos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Endereco retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.enderecos.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Endereco endereco = (Endereco) value;
			return endereco.getIdEndereco() == 0 ? null : String.valueOf(((Endereco) value).getIdEndereco());
		}
		return null;
	}
}
