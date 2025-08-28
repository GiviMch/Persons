package ru.netology.persons.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Secured("ROLE_READ")
    @GetMapping("/demo/read")
    public String readOnly() {
        return "Данные доступны только для пользователей с ролью READ";
    }

    @RolesAllowed("ROLE_WRITE")
    @GetMapping("/demo/write")
    public String writeOnly() {
        return "Данные доступны только для пользователей с ролью WRITE";
    }

    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("/demo/write-or-delete")
    public String writeOrDelete() {
        return "Данные доступны для WRITE или DELETE";
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/demo/user-check")
    public String checkUser(@RequestParam String username, Authentication authentication) {
        return "Привет, " + authentication.getName() + "! Ты запросил данные для " + username;
    }
}