package com.techshop.web.controller;

import com.techshop.web.dto.ProductDto;
import com.techshop.web.model.Producto;
import com.techshop.web.services.implementations.CloudinaryService;
import com.techshop.web.services.interfaces.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.plaf.metal.MetalDesktopIconUI;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("techshop/web/v1")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    private ProductServiceI productService;

    @PostMapping(value = "/product/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody ProductDto request,
                                  @RequestParam MultipartFile multipartFile) throws IOException {

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
                return new ResponseEntity("Imagen no válida", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);

        request.setImagenUrl((String)result.get("url"));
        request.setImagenId((String)result.get("public_id"));

        productService.save(request);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable("id") int id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping(value = "/product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id,
                                                @RequestBody ProductDto request,
                                                @RequestParam MultipartFile multipartFile) throws IOException {

        cloudinaryService.delete(request.getImagenId());

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity("Imagen no válida", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);

        request.setImagenUrl((String)result.get("url"));
        request.setImagenId((String)result.get("public_id"));

        productService.update(request, id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") int id,
                                             @RequestBody String imagenUrl) throws IOException {
        cloudinaryService.delete(imagenUrl);
        productService.deletedById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
