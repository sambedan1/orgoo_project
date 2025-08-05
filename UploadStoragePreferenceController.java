package com.tuespotsolutions.controller;

import com.tuespotsolutions.entity.UploadStoragePreference;
import com.tuespotsolutions.enums.StorageType;
import com.tuespotsolutions.models.UploadStoragePreferenceDTO;
import com.tuespotsolutions.service.UploadStoragePreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage-preference")
@CrossOrigin("*")
public class UploadStoragePreferenceController {

    @Autowired
    private UploadStoragePreferenceService storagePreferenceService;

    @GetMapping("/all")
    public ResponseEntity<List<UploadStoragePreference>> getAllStoragePreferences() {
        try {
            List<UploadStoragePreference> preferences = storagePreferenceService.getAllStoragePreference();
            System.out.println(preferences);
            return ResponseEntity.ok(preferences);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStoragePreference(@PathVariable Long id) {
        try {
            storagePreferenceService.updateStoragePreference(id);
            return ResponseEntity.ok("Storage preference updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating storage preference: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStoragePreference(@PathVariable Long id)
    {
        try {
            storagePreferenceService.deleteStoragePreference(id);
            return ResponseEntity.ok("Storage preference deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting storage preference: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStoragePreference(@RequestBody UploadStoragePreferenceDTO uploadStoragePreferenceDTO)
    {
        try {
            UploadStoragePreferenceDTO newStorage = storagePreferenceService.addStoragePreference(uploadStoragePreferenceDTO);
            System.out.println(newStorage);
            return ResponseEntity.ok(newStorage);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error adding new storage preference: " + e.getMessage());
        }
    }
}
