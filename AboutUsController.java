package com.tuespotsolutions.controller;

import com.tuespotsolutions.entity.AboutUs;
import com.tuespotsolutions.models.AboutUsDTO;
import com.tuespotsolutions.service.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping("/about-us")
@RestController
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    @PostMapping("/sections")
    public ResponseEntity<AboutUsDTO> addSection(@RequestBody AboutUsDTO aboutUsDTO)
    {
        AboutUsDTO insertedDTO = aboutUsService.addSection(aboutUsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedDTO);
    }

    @GetMapping("/sections")
    public ResponseEntity<List<AboutUsDTO>> getAllSections()
    {
        List<AboutUsDTO> allSectionList = aboutUsService.getAllSections();
        return ResponseEntity.status(HttpStatus.OK).body(allSectionList);
    }

    @GetMapping("/sections/{id}")
    public ResponseEntity<AboutUsDTO> getSectionById(@PathVariable Long id)
    {
        AboutUsDTO aboutUsDTO = aboutUsService.getSectionById( id);
        return ResponseEntity.status(HttpStatus.OK).body(aboutUsDTO);
    }

    @PutMapping("/sections/{id}")
    public ResponseEntity<AboutUsDTO> updateSection(@RequestBody AboutUsDTO aboutUsDTO, @PathVariable Long id)
    {
        AboutUsDTO aboutUsDTO1 = aboutUsService.updateSection(aboutUsDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(aboutUsDTO1);
    }

    @DeleteMapping("/sections/{id}")
     public ResponseEntity<Map<String, String>> deleteSectionById(@PathVariable Long id)
     {
         Map<String, String> response = aboutUsService.deleteSectionById(id);
         return ResponseEntity.ok(response);
     }
}
