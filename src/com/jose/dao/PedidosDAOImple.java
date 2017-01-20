package com.jose.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jose.modelo.Pedido;

@Repository
public class PedidosDAOImple implements PedidosDAO {

		@Autowired
		private SessionFactory sessionFactory;

		public PedidosDAOImple() {
			
		}
		
		public PedidosDAOImple(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		
		@Transactional
		public List<Pedido> list() {
			@SuppressWarnings("unchecked")
			List<Pedido> listPedido = (List<Pedido>) sessionFactory.getCurrentSession()
					.createCriteria(Pedido.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return listPedido;
		}

		@Transactional
		public void saveOrUpdate(Pedido pedido) {
			sessionFactory.getCurrentSession().saveOrUpdate(pedido);
		}

		@Transactional
		public void delete(int id) {
			Pedido pedidoToDelete = new Pedido();
			pedidoToDelete.setId(id);
			sessionFactory.getCurrentSession().delete(pedidoToDelete);
		}

		@Transactional
		public Pedido get(int id) {
			String hql = "from Pedido where id=" + id;
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Pedido> listPedido = (List<Pedido>) query.list();
			
			if (listPedido != null && !listPedido.isEmpty()) {
				return listPedido.get(0);
			}
			return null;
		}
}