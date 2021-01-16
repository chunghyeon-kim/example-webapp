package kim.chunghyeon.webapp.common.api.dto;

import lombok.Getter;

@Getter
public class CreationResponseDto {
    private long id;

    public CreationResponseDto(long id) {
        this.id = id;
    }
}
