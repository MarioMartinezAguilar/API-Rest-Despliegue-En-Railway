package com.mario.apirest.apirest.Controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mario.apirest.apirest.Repositories.ProductoRepository;
import com.mario.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    // hacer una inyection de una instancia en el repositorio
    @Autowired
    private ProductoRepository productoRepository;

    // Método Get Http
    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();

    }

    // Método Get individual por Id Http
    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }

    // Método Post Http
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }
    // Método Put Http
     @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails){
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        
        return productoRepository.save(producto);

    } 
    // Método  Delete Http
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productoRepository.delete(producto);
        return " El producto con el ID: " + id + " fue eliminado correctamente";    

    }





}
