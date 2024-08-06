package com.eabmodel.ozonos.Service;

import com.eabmodel.ozonos.Model.Producto;
import com.eabmodel.ozonos.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {
    @Autowired
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    //Create Producto
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);

    }

    //Get by id
    public Producto getById(Integer id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        return productoRepository.getById(id);
    }

    //List all
    public List<Producto> listAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }


    //Update By Id
    public Producto updateProducto(Integer id, Producto newProducto) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto existingProducto = productoOptional.get();
            existingProducto.setNombre(newProducto.getNombre());
            existingProducto.setDescripcion(newProducto.getDescripcion());
            existingProducto.setCategoria(newProducto.getCategoria());
            existingProducto.setTalla(newProducto.getTalla());
            existingProducto.setPrecio(newProducto.getPrecio());
            existingProducto.setEstado(newProducto.getEstado());
            return productoRepository.save(existingProducto);
        } else {
            throw new RuntimeException("Producto no encontrado con el id: " + id);
        }
    }
    public void deleteProducto(Integer id){
        productoRepository.deleteById(id);
    }
}
