package portal.backend.app.controller;

import portal.backend.app.payload.LoginRequest;
import portal.backend.app.payload.LoginResponse;
import portal.backend.app.security.JwtTokenUtil;
import portal.backend.app.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService,
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/api/auth", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> createAuthToken(@RequestBody LoginRequest user) throws Exception {

        authenticate(user.getUsername(), user.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        LoginResponse response = new LoginResponse(userDetails.getUsername(), token, userDetails.getAuthorities());
        return ResponseEntity.ok(response);
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username, password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException ex) {
            throw new Exception("disabled user", ex);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("invalid credentials", ex);
        }
    }
}
