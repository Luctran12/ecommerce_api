package org.example.apispring.service;

import lombok.extern.slf4j.Slf4j;
import org.example.apispring.dto.request.ProductCreationReq;
import org.example.apispring.dto.request.ProductUpdateReq;
import org.example.apispring.dto.response.ProductDetailResponse;
import org.example.apispring.dto.response.ProductResponse;
import org.example.apispring.mapper.ProductMapper;
import org.example.apispring.model.Category;
import org.example.apispring.model.Product;
import org.example.apispring.model.Store;
import org.example.apispring.repository.CategoryRepo;
import org.example.apispring.repository.ProductCustomRepo;
import org.example.apispring.repository.ProductRepo;
import org.example.apispring.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCustomRepo productCustomRepo;

    @Autowired
    private FileStorageService fileStorageService;

    public ProductResponse createProduct(ProductCreationReq req) throws IOException {
        Store store = storeRepo.findById(req.getStoreId()).orElseThrow(() -> new RuntimeException("Store not found"));

        Category category;

        if(req.getCategoryId() != null && !req.getCategoryId().isEmpty()) {
            //user chon category co san
            category = categoryRepo.findById(req.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        } else if (req.getCategoryName() != null && !req.getCategoryName().isEmpty()) {
            //user tao category moi
            //kiem tra ton tai
            category = categoryRepo.findByName(req.getCategoryName())
                    .orElseGet(() -> {
                        // Nếu chưa có, tạo category mới
                        Category newCategory = new Category();
                        newCategory.setName(req.getCategoryName());
                        return categoryRepo.save(newCategory); // Lưu category mới vào database
                    });

        } else {
            throw new RuntimeException("Category name is empty");
        }

        //tao product
        Product product = new Product();
        product.setName(req.getName());
        product.setDescription(req.getDescription());
        product.setPrice(req.getPrice());
        product.setStock(req.getStock());
        product.setHeight(req.getHeight());
        product.setWeight(req.getWeight());
        product.setWidth(req.getWidth());
        product.setLength(req.getLength());
        product.setStore(store);
        product.setCategory(category); // Gán category đã tìm được hoặc mới tạo

        //save product's image

        List<String> images = new ArrayList<>();

        if(req.getImages() != null && !req.getImages().isEmpty()){
            for(MultipartFile imageFile : req.getImages()) {
                try{
                    String imageUrl = fileStorageService.uploadImage(imageFile);
                    images.add(imageUrl);
                }catch (IOException e){
                    log.error("====> image service error: {}", e.getMessage());
                }
            }
        }

        product.setImageUrl(images);

        // Lưu sản phẩm vào database
        Product savedProduct = productRepo.save(product);

        // Chuyển đổi sang DTO trả về
        return productMapper.toProductResponse(savedProduct);
    }

    public Page<ProductResponse> getProducts(String storeId, String categoryId, Double minPrice, Double maxPrice, int page, int size) {
        // Giá mặc định nếu không nhập
        double min = (minPrice != null) ? minPrice : 0;
        double max = (maxPrice != null) ? maxPrice : Double.MAX_VALUE;

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"price"));
//        Page<Product> productPage = productRepo.findByFilters(storeId, categoryId, min, max, pageable);
        Page<Product> productPage = productCustomRepo.findProducts(storeId, categoryId, min, max, pageable);

        System.out.println("Min price:" + minPrice + "MaxPrice: " + maxPrice);

        return productPage.map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .storeId(product.getStore().getId())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .rating(product.getRating())
                .sold(product.getSold())
                .build());
    }

    public ProductDetailResponse getProductDetail(String id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toProductDetailResponse(product);
    }

    public ProductResponse updateProduct(ProductUpdateReq req) {
        Product product = productRepo.findById(req.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        if(req.getName() != null && !req.getName().isEmpty()) {
            product.setName(req.getName());
        }
        if(req.getDescription() != null && !req.getDescription().isEmpty()) {
            product.setDescription(req.getDescription());
        }
        if (req.getStock() != 0){
            product.setStock(req.getStock());
        }
        if(req.getPrice() != 0){
            product.setPrice(req.getPrice());
        }
        if(req.getImage() != null && !req.getImage().isEmpty()) {
            product.setImageUrl(req.getImage());
        }
        productRepo.save(product);
        return productMapper.toProductResponse(product);
    }

    // Hàm chuẩn hóa chuỗi tiếng Việt
    private String normalizeString(String str) {
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized)
                .replaceAll("")
                .toLowerCase()
                .replaceAll("đ", "d");
    }

    public List<ProductResponse> searchProducts(String name) {
        String keyword = normalizeString(name);

        // Lấy toàn bộ sản phẩm từ MongoDB
        List<Product> products = productRepo.findAll();

        // Lọc lại danh sách bằng Java
        List<Product> filteredProducts = products.stream()
                .filter(product -> normalizeString(product.getName()).contains(keyword))
                .collect(Collectors.toList());

        // Chuyển đổi sang DTO
        return filteredProducts.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }


}
