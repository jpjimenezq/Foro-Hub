package api.forohub.forohub.controller;


import api.forohub.forohub.domain.user.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name="bearer-key")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity <Page<UserDataList>> listOfUsers(@PageableDefault(size=10)Pageable paged){
        return ResponseEntity.ok(userRepository.findByActiveTrue(paged).map(UserDataList::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUserData (@RequestBody @Valid UserDataUpdate userDataUpdate){
        User user = userRepository.getReferenceById(userDataUpdate.id());
        user.updateData(userDataUpdate);
        return ResponseEntity.ok(new UserResponseData(user.getId(),user.getName()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        user.diactivateUser();
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <UserResponseData> userDataReturn(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        var dUser = new UserResponseData(user.getId(), user.getName());
        return ResponseEntity.ok(dUser);
    }

}
