package com.example.demo.repository;


import com.example.demo.model.entity.Item;
import com.example.demo.model.enumclass.ItemStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("ItemRepository 테스트")
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item= new Item();
        item.setName("애플 노트북");
        item.setTitle("애플 노트북 A100");
        item.setBrandName("애플");
       // item.setPrice(100000);
        item.setContent("2020 맥 노트");
        item.setStatus(ItemStatus.REGISTERED);
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
       // item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
    }
    @Test
    public void read(){
        Long id =1L;

        Optional<Item> item= itemRepository.findById(id);

        Assertions.assertTrue(item.isPresent());
    }
}
