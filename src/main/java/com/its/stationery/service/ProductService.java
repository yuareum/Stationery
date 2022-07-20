package com.its.stationery.service;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;

    public Long save(ProductDTO productDTO) throws IOException {
        MultipartFile productFile = productDTO.getProductFile();
        String productFileName = productFile.getOriginalFilename();
        productFileName = System.currentTimeMillis() + "_" + productFileName;
        String savePath = "C:\\springboot_img\\" + productFileName;
        if(!productFile.isEmpty()){
            productFile.transferTo(new File(savePath));
        }
        productDTO.setProductFileName(productFileName);
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(productDTO.getProductAdmin());
        if(optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if(memberEntity.getMemberId() == "admin"){
                Long saveId = productRepository.save(ProductEntity.toSaveEntity(productDTO, memberEntity)).getId();
                return saveId;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public Page<ProductDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        page = (page == 1)? 0: (page-1);
        Page<ProductEntity> productEntities = productRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<ProductDTO> productList = productEntities.map(

            product -> new ProductDTO(product.getId(),
                    product.getProductName(),
                    product.getProductBrand(),
                    product.getProductPrice(),
                    product.getProductFileName()
            ));
        return productList;
    }

    public ProductDTO detail(Long id) {
        productRepository.productHits(id);
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){

            return ProductDTO.toProductDTO(optionalProductEntity.get());
        }
        else {
            return null;
        }
    }
}
