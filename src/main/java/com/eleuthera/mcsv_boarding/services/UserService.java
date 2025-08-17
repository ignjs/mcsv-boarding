package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.User;
import com.eleuthera.mcsv_boarding.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad User.
 * Contiene la lógica de negocio para la gestión de usuarios.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Guarda un nuevo usuario o actualiza uno existente.
     * @param user El objeto User a guardar.
     * @return El usuario guardado.
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su ID.
     * @param id El UUID del usuario.
     * @return Un Optional que contiene el usuario si se encuentra.
     */
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    /**
     * Obtiene una lista de todos los usuarios.
     * @return Una lista de objetos User.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Elimina un usuario por su ID.
     * @param id El UUID del usuario a eliminar.
     */
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
