package com.laioffer.staybooking.model;

//data transfer object
public record UserDto(
        Long id,
        String username,
        UserRole role
) {
    public UserDto(UserEntity entity) {
        this(
                entity.getId(),
                entity.getUsername(),
                entity.getRole()
        );//to sift out user password-sensitive info
    }
}
