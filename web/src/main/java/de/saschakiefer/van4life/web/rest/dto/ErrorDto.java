package de.saschakiefer.van4life.web.rest.dto;

public record ErrorDto(String target, int code, String message) {
}
