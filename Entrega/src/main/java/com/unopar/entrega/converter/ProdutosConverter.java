package com.unopar.entrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.unopar.entrega.model.Produto;
import com.unopar.entrega.repository.Produtos;

@FacesConverter(forClass = Produto.class)
public class ProdutosConverter implements Converter {
	@Inject
	private Produtos produtos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.produtos.porId(Integer.parseInt(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getIdProduto() == 0 ? null : String.valueOf(((Produto) value).getIdProduto());
		}
		return null;
	}
}
