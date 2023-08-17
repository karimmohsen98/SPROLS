package sprols.internship.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sprols.internship.Entities.Utilisateur;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="7415d5ce7e750ac9a3b80b9c3345f6dedbd77b36c03bc058bb2ddcc68237344e";

    public String extractuserNumMatricule(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolv){
        final Claims claims = extractAllClaims(token);
        return claimsResolv.apply(claims);
    }

    public String generateToken(Utilisateur user){
        return generateToken(new HashMap<>(),user);
    }

    public String generateToken(Map<String,Object> extraClaims, Utilisateur user){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getNumMatricule())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Boolean isTokenValid(String Token,Utilisateur user){
        final String userNumMatricule = extractuserNumMatricule(Token);
        return (userNumMatricule.equals(user.getNumMatricule()) && !isTokenExpired(Token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) //verify the authenticiy of token aka not compromised
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}