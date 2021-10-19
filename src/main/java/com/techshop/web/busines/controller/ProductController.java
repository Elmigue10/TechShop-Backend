package com.techshop.web.busines.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techshop.web.model.dto.ProductDto;
import com.techshop.web.busines.services.CloudinaryService;
import com.techshop.web.busines.services.interfaces.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
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

    @PostMapping(value = "/product/save")
    public ResponseEntity<?> save(@RequestParam String request,
                                  @RequestParam("file") MultipartFile file) throws IOException{

        BufferedImage bi = ImageIO.read(file.getInputStream());
        if(bi == null){
                return new ResponseEntity("Imagen no válida", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(file);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        ProductDto producto = gson.fromJson(request, ProductDto.class);

        producto.setImagenUrl((String)result.get("url"));
        producto.setImagenId(result.get("public_id").toString());

        productService.save(producto);

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

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id,
                                                @RequestParam String request,
                                                @RequestParam("file") MultipartFile file) throws IOException {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        ProductDto producto = gson.fromJson(request, ProductDto.class);

        cloudinaryService.delete(producto.getImagenId());

        BufferedImage bi = ImageIO.read(file.getInputStream());
        if(bi == null){
            return new ResponseEntity("Imagen no válida", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(file);

        producto.setImagenUrl((String)result.get("url"));
        producto.setImagenId((String)result.get("public_id"));

        productService.update(producto, id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") int id,
                                             @RequestBody String imagenId) throws IOException {
        cloudinaryService.delete(imagenId);
        productService.deletedById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
