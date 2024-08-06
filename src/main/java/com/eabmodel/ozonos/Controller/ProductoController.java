package com.eabmodel.ozonos.Controller;

import com.eabmodel.ozonos.Model.Producto;
import com.eabmodel.ozonos.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin
public class ProductoController {
    @Autowired
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getProdcutos() {
        List<Producto> productos = productoService.listAll();
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron Prodcutos!");
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createProductos(@RequestBody Producto producto) {
        try {
            Producto createProducto = productoService.createProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado correctamente!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creando el producto! :  " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Integer id, @RequestBody Producto newProducto) {
        try {
            Producto updateProducto = productoService.updateProducto(id, newProducto);
            return ResponseEntity.ok("Producto modificado correctamente!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error modificando el Producto!" + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error eliminando el Producto! : " + e.getMessage());
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}

