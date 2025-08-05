package com.tuespotsolutions.service.impl;
import com.tuespotsolutions.entity.Hr;
import com.tuespotsolutions.entity.Roles;
import com.tuespotsolutions.entity.User;
import com.tuespotsolutions.models.HrRequest;
import com.tuespotsolutions.models.HrResponse;
import com.tuespotsolutions.repository.HrRepository;
import com.tuespotsolutions.repository.RolesRepository;
import com.tuespotsolutions.repository.UserRepository;
import com.tuespotsolutions.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HrServiceImpl implements HrService {
    @Autowired
    private HrRepository hrRepository;
    @Autowired
    private RolesRepository roleRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public HrResponse saveHr(HrRequest hrRequest) {
        Hr hr=new Hr();
        hr.setName(hrRequest.getName());
        hr.setEmail(hrRequest.getEmail());
        hr.setPassword(hrRequest.getPassword());
        hr.setStatus(hrRequest.isStatus());
        hr.setCreatedAt(LocalDateTime.now());

        Hr savedHr = hrRepository.save(hr);
        if (userRepository.existsByUsername(hrRequest.getEmail())) {
            throw new RuntimeException("User with this email already exists!");
        }
        //create user for login
        User user = new User();
        user.setApplicantName(hrRequest.getName());
        user.setEmail(hrRequest.getEmail());
        user.setUsername(hrRequest.getEmail());//using email as user name
        user.setPassword(passwordEncoder.encode(hrRequest.getPassword()));
        //user.setMobileNumber(hrRequest.getMobileNumber());
        user.setUserType("HR");
        user.setStatus(hrRequest.isStatus());
        user.setTypeId(savedHr.getId());
        user.setCreatedOn(new java.sql.Date(System.currentTimeMillis()));
        //user.setCreatedAt(LocalDateTime.now());
        Roles hrRole = roleRepository.findByName("ROLE_HR")
                .orElseThrow(() -> new RuntimeException("ROLE_HR not found in roles table"));
        user.setRoles(Set.of(hrRole));
        userRepository.save(user);
        return mapToResponse(savedHr);
    }

    @Override
    public List<HrResponse> getAllHrs() {
        return hrRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HrResponse getHrById(Long id) {
        Optional<Hr> optionalHr = hrRepository.findById(id);
        return optionalHr.map(this::mapToResponse).orElse(null);
    }

    @Override
    public HrResponse updateHr(Long id, HrRequest hrRequest) {
        Optional<Hr> optionalHr = hrRepository.findById(id);
        if (optionalHr.isPresent()) {
            Hr hr = optionalHr.get();
            hr.setName(hrRequest.getName());
            hr.setEmail(hrRequest.getEmail());
            hr.setPassword(hrRequest.getPassword());
            hr.setStatus(hrRequest.isStatus());
            hr.setModifiedAt(LocalDateTime.now());
            Hr updatedHr = hrRepository.save(hr);
            return mapToResponse(updatedHr);
        }
        return null;
    }

    @Override
    public void deleteHr(Long id) {
        hrRepository.deleteById(id);
    }
    // Helper method to convert entity to response DTO
    private HrResponse mapToResponse(Hr hr) {
        HrResponse response = new HrResponse();
        response.setId(hr.getId());
        response.setName(hr.getName());
        response.setEmail(hr.getEmail());
        response.setPassword(hr.getPassword());
        response.setStatus(hr.isStatus());
        response.setCreatedAt(hr.getCreatedAt());
        response.setModifiedAt(hr.getModifiedAt());
        return response;
    }

}
