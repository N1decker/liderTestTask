package ru.nidecker.liderTestTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.service.SportTypeService;

import java.util.List;

@RestController
@RequestMapping("api/types_of_sports")
@RequiredArgsConstructor
public class SportTypeRestController {

    private final SportTypeService sportTypeService;

    @GetMapping
    public List<SportType> findAll() {
        return sportTypeService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SportType create(String name) {
        return sportTypeService.create(name);
    }

    @PutMapping("/{id}")
    public SportType update(@PathVariable long id,
                            String name) {
        return sportTypeService.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        sportTypeService.delete(id);
    }
}
