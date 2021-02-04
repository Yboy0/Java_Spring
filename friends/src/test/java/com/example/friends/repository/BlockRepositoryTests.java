package com.example.friends.repository;

import com.example.friends.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BlockRepositoryTests {
    @Autowired
    private BlockRepository blockRepository;

    @Test
    public void crud(){
        Block block = Block.builder()
                .name("Martin")
                .reason("notfriendly")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();
        blockRepository.save(block);

        List<Block> blocks = blockRepository.findAll();

        assertThat(blocks.get(0)).isEqualTo(block);
    }

}