package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Cliente;
import com.unopar.entrega.repository.Clientes;

@FacesConverter(forClass = Cliente.class)
public class ClientesConverter implements Converter {
	@Inject
	private Clientes clientes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.clientes.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cliente cliente = (Cliente) value;
			return cliente.getIdCliente() == 0 ? null : String.valueOf(((Cliente) value).getIdCliente());
		}
		return null;
	}
}
