package com.library.LibraryManagment.resource;

import com.library.LibraryManagment.entity.Obra;
import com.library.LibraryManagment.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/obras")
public class ObraResource {

    @Autowired
    ObraRepository repository;

    @GetMapping
    public ResponseEntity<List<Obra>> findAll(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> findById(@PathVariable("id") Integer id){
        Optional<Obra> obj = repository.findById(id);
        return ResponseEntity.ok().body(obj.get());
    }

    @PostMapping
    public ResponseEntity<Obra> insert(@RequestBody Obra obj){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(repository.save(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
       repository.deleteById(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> alter(@PathVariable("id") Integer id, @RequestBody Obra obj) {
        Obra newObra = repository.getById(id);
        newObra.setTitulo(obj.getTitulo());
        newObra.setFoto(obj.getFoto());
        newObra.setEditora(obj.getEditora());
        newObra.setAutor(obj.getAutor());

        return ResponseEntity.ok().body(repository.save(newObra));

    }

}
