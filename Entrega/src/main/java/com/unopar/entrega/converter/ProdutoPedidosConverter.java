package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.ProdutoPedido;
import com.unopar.entrega.repository.ProdutoPedidos;

@FacesConverter(forClass = ProdutoPedido.class)
public class ProdutoPedidosConverter implements Converter {
	@Inject
	private ProdutoPedidos produtoPedidos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ProdutoPedido retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.produtoPedidos.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			ProdutoPedido produtoPedido = (ProdutoPedido) value;
			return produtoPedido.getIdProdutoPedido() == 0 ? null
					: String.valueOf(((ProdutoPedido) value).getIdProdutoPedido());
		}
		return null;
	}
}
