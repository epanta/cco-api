package br.com.sabesp.cco.authentication.utils;

import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.exceptions.NegocioException;
import br.com.sabesp.cco.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenUtil {

    @Value(value = "${jwt.secret}")
    private final String secret = "$4b3$p#jwt%s3cr37";

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000* 60 * 60 *4))
                .signWith(getSignInKey(),Jwts.SIG.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                // the token will be expired in 10 hours
                .expiration(new Date(System.currentTimeMillis() + 1000* 60 * 60 *8))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Usuario validateToken(String token, UsuarioService loginRepository) {
        try {
            final String usuarioToken = getUsernameFromToken(token);

            Usuario loginBD = loginRepository.findByMatriculaOrEmail(usuarioToken);
            return (!ObjectUtils.isEmpty(loginBD) && !isTokenExpired(token)) ? loginBD : null;
        } catch (Exception e) {
            throw new NegocioException("Token enviado inválido!");
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
}
