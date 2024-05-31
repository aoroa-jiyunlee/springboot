package spring.boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spring.boot.model.item.Book;
import spring.boot.model.item.Item;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    public static void createOrderItem(Book book1, int i, int i1) {
    }
}
