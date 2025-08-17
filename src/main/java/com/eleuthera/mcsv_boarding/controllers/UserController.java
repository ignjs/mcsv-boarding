package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.UserDTO;
import com.eleuthera.mcsv_boarding.models.User;
import com.eleuthera.mcsv_boarding.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controlador REST para la entidad User.
 * Proporciona endpoints para la gestión de usuarios.
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Gestión de usuarios de la aplicación")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Obtiene una lista de todos los usuarios.
     * @return Una lista de UserDTO.
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un usuario por su ID.
     * @param id El UUID del usuario.
     * @return ResponseEntity con el UserDTO o 404 Not Found.
     */
    @Operation(summary = "Obtener un usuario por ID", description = "Busca un usuario específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@Parameter(description = "ID único del usuario") @PathVariable UUID id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> ResponseEntity.ok(new UserDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo usuario.
     * @param userDTOUserDTO El DTOUserDTO del usuario a crear.
     * @return ResponseEntity con el UserDTO creado y el estado 201 Created.
     */
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTOUserDTO del usuario a crear") @RequestBody UserDTO userDTOUserDTO) {
        User user = new User();
        // Mapeo del DTOUserDTO a la entidad
        user.setName(userDTOUserDTO.getUsername());
        user.setEmail(userDTOUserDTO.getEmail());
        User savedUser = userService.save(user);
        return new ResponseEntity<>(new UserDTO(savedUser), HttpStatus.CREATED);
    }

    /**
     * Actualiza un usuario existente.
     * @param id El UUID del usuario a actualizar.
     * @param userDTOUserDTO El DTOUserDTO del usuario con los datos actualizados.
     * @return ResponseEntity con el UserDTO actualizado o 404 Not Found.
     */
    @Operation(summary = "Actualizar un usuario existente", description = "Actualiza la información de un usuario basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Parameter(description = "ID del usuario a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTOUserDTO con los datos actualizados del usuario") @RequestBody UserDTO userDTOUserDTO) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDTOUserDTO.getUsername());
            user.setEmail(userDTOUserDTO.getEmail());
            User updatedUser = userService.save(user);
            return ResponseEntity.ok(new UserDTO(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un usuario por su ID.
     * @param id El UUID del usuario a eliminar.
     * @return ResponseEntity con el estado 204 No Content.
     */
    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID del usuario a eliminar") @PathVariable UUID id) {
        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
