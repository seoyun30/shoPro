package com.example.shopro.service;

import com.example.shopro.endtity.Item;
import com.example.shopro.endtity.ItemImg;
import com.example.shopro.repository.ItemImgRepository;
import com.example.shopro.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ItemImgService {

    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;
    private final ItemRepository itemRepository;
    //이미지 등록
    public void saveImg(Long id, List<MultipartFile> multipartFile) throws IOException {
        //이미지 등록은 어디에 무엇을 할것인가
        //이미지는 아이템꺼
        //아이템 pk 이미지 파일 경로를 잘라서
        //결롸와 함께 이름 저장

        log.info("아이템이미지서비스로 들어온 id"+ id);
        if (multipartFile !=null){
            for (MultipartFile img : multipartFile){
                if (img.isEmpty()){
                    log.info("아이템이미지서비스로 들어온 이미지"+img.getOriginalFilename());
                    //물리적인 저장
                   String savedFileName = //uuid가 포함된 물리적인 파일 이름
                    fileService.uploadFile(img);

                    //db저장
                    //엔티티를 가져왔다면 중복코드를 사용할 필요가 없어진다.해볼것
                    Item item=
                    itemRepository.findById(id).get();

                    String imgUrl = "/images/item/" + savedFileName;

                    ItemImg itemImg = new ItemImg();
                    itemImg.setItem(item);          //본문 //이미지 달리 아이템
                    itemImg.setImgName(savedFileName);           //uuid포함 저장될 이름
                    itemImg.setImgUrl(imgUrl);            //경로
                    itemImg.setOriImgName(img.getOriginalFilename());        //원래 이름
                    //대표이미지 여부 확인
                    if (multipartFile.indexOf(img)==0) {
                        itemImg.setRepimgYn("Y");          //대표이미지
                    }else{
                        itemImg.setRepimgYn("N");           //대표이미지 아님
                    }
                    itemImgRepository.save(itemImg);
                }
            }
        }
        log.info("아이템이미지서비스로 들어온 이미지"+ id);
    }
}
