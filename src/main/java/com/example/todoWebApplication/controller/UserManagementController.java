package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.dto.ReqRes;
import com.example.todoWebApplication.dto.UpdateUserDTO;
import com.example.todoWebApplication.entity.OurUsers;
import com.example.todoWebApplication.model.AnaGorev;
import com.example.todoWebApplication.repository.UsersRepo;
import com.example.todoWebApplication.service.UsersManagementService;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserManagementController {
    @Autowired
    private UsersRepo usersRepo;
    
    @Autowired
    private UsersManagementService usersManagementService;
    
    @PutMapping("api/profile/update/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UpdateUserDTO updateUserDTO, Authentication authentication) {
        try {
            OurUsers user = usersRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Base64 avatar verisini byte[] formatına çevir
            if (updateUserDTO.getAvatar() != null) {
                byte[] avatarBytes = Base64.getDecoder().decode(updateUserDTO.getAvatar());
                user.setAvatar(avatarBytes);
            }

            user.setName(updateUserDTO.getName());
            user.setEmail(updateUserDTO.getEmail());
            user.setCity(updateUserDTO.getCity());
            usersRepo.save(user);

            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }



    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }


}
