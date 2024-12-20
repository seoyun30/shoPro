package com.example.shopro.repository;

import com.example.shopro.endtity.Item;
import com.example.shopro.endtity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //제품명으로 검색 제품명은 동일한 이름이 있을 수 있으니
    //여러개 출력가능 List 사용

    public List<Item> findByItemNm (String itemNm);

    @Query("select i from Item i where i.itemNm=:itemNm")
    public  List<Item> selectwhereItemNm(String itemNm);

    //제품명으로 검색, 비슷하면 맞게
    public List<Item> findByItemNmContaining(String itemNm);

    @Query("select i from Item i where i.itemNm like concat('%', :itemNm, '%') ")
    public List<Item> selectWItemNmLike(String itemNm);

    //상세설명으로 검색
    public List<Item> findByItemDetailContaining(String itemDetail);

    //가격으로 검색
    public List<Item> findByPriceLessThan(Integer price);
    public List<Item> findByPriceGreaterThan(Integer price);
    public List<Item> findByPriceGreaterThanOrderByPriceAsc(Integer price);

    //페이징처리된 초과이면서 같은거
    public List<Item> findByPriceGreaterThanEqual(Integer price, Pageable pageable);


    //정렬까지 추가
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //nativeQuery 사용
    @Query(value = "select * from Item where i.itemNm = :itemNm", nativeQuery = true)
    List<Item> nativeQueryselectwhereNamelike(@Param("itemNm") String itemNm, Pageable pageable);

    //생성날짜
    //판매 많은 순
    //별점 높은 순
    //등록일순
    //~금액 이하
    //





}


