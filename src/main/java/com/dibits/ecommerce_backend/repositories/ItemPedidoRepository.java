package com.dibits.ecommerce_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dibits.ecommerce_backend.domain.ItemPedido;
import com.dibits.ecommerce_backend.domain.ItemPedidoPK;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
// Aqui, ItemPedido é a entidade que estamos manipulando e ItemPedidoPK é o tipo da chave primária.
//apesar de indicar erro, e correto usar Integer inves de ItemPedidoPK pois o jpa entende que 
//o id e um conjunto de duas classes 