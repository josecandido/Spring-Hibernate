package com.jose.dao;

import java.util.List;

import com.jose.modelo.Pedido;

public interface PedidosDAO {

		public List<Pedido> list();
		
		public Pedido get(int id);
		
		public void saveOrUpdate(Pedido pedido);
		
		public void delete(int id);
		
}
	

