package com.its.stationery.service;

import com.its.stationery.common.PagingConst;
import com.its.stationery.config.WebConfig;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.OrderRepository;
import com.its.stationery.repository.ProductRepository;
import com.its.stationery.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

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
            if(Objects.equals(memberEntity.getMemberId(), "admin")){
                Long saveId = productRepository.save(ProductEntity.toSaveEntity(productDTO)).getId();
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
    @Transactional
    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(ProductEntity product: productEntityList){
            productDTOList.add(ProductDTO.toProductDTO(product));
        }
        return productDTOList;
    }
    public Page<ProductDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        page = (page == 1)? 0: (page-1);
        Page<ProductEntity> productEntities = productRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<ProductDTO> productList = productEntities.map(
            product -> new ProductDTO(product.getId(),
                    product.getProductName(),
                    product.getProductPrice(),
                    product.getProductFileName(),
                    product.getCategoryId()
            ));

        return productList;
    }
    @Transactional
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
    

    public List<ProductDTO> categoryList(Long categoryId) {
        List<ProductEntity> productEntityList = productRepository.findByCategoryId(categoryId);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(ProductEntity productEntity: productEntityList){
            productDTOList.add(ProductDTO.toProductDTO(productEntity));
        }
        return productDTOList;
    }
    @Transactional
    public Page<ProductDTO> search(String q, Pageable pageable) {
        int page = pageable.getPageNumber();
        page  = (page == 1) ? 0 : (page -1);
        Page<ProductEntity> searchEntity = null;
        productRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
            searchEntity = productRepository.findByProductNameContainingOrProductBrandContainingIgnoreCase(q,q,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<ProductDTO> productList = searchEntity.map(
                product -> new ProductDTO(product.getId(),
                        product.getProductName(),
                        product.getProductBrand(),
                        product.getProductPrice()
                ));

        return productList;
    }

    public ProductDTO findById(Long id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            return ProductDTO.toProductDTO(optionalProductEntity.get());
        }
        else {
            return null;
        }
    }

    public Long update(ProductDTO productDTO) {
        ProductEntity productEntity = ProductEntity.toUpdateEntity(productDTO);
        Long id = productRepository.save(productEntity).getId();
        return id;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    public Long countsUpdate(ProductDTO productDTO, int counts) {
      ProductEntity productEntity = ProductEntity.toUpdateCounts(productDTO, counts);
      Long id = productRepository.save(productEntity).getId();
      return id;
    }
}
