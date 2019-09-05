package cl.go.sport.api.config.security.response;

import java.io.Serializable;

import cl.go.sport.api.controllers.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author carlos
 *
 */
@Data
@Builder
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;
    private UserDTO user;
    private String token;
}
