package com.example.shopro.endtity;

import com.example.shopro.constant.ItemSellStatus;
import com.example.shopro.endtity.base.BaseEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Item")
public class Item extends BaseEntity {


    @Id
    @Column(name = "item_id")       //테이블에서 매핑될 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        //상품코드

    @Column(nullable = false, length = 50)
    private String itemNm;      //상품명

    @Column(name = "price", nullable = false)
    private int price;          //가겨

    @Column(nullable = false)
    private int stockNumber;        //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;      //상품설명

    @Enumerated(EnumType.STRING)    //enum 가지고 만들 YES/NO, Sell/sold_out
    private ItemSellStatus itemSellStatus; //상품 판매 상태

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<ItemImg> itemImgList;


}
