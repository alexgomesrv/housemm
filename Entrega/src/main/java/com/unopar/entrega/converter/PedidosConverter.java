package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Pedido;
import com.unopar.entrega.repository.Pedidos;

@FacesConverter(forClass = Pedido.class)
public class PedidosConverter implements Converter {
	@Inject
	private Pedidos pedidos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.pedidos.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getIdPedido() == 0 ? null : String.valueOf(((Pedido) value).getIdPedido());
		}
		return null;
	}
}
