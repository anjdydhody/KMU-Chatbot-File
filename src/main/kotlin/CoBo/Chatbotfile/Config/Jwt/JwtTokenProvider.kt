package CoBo.Chatbotfile.Config.Jwt

import CoBo.Chatbotfile.Data.Enum.RegisterStateEnum
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Objects

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret-key}")
    val secretKey: String
) {

    fun getUserId(token: String):Int{
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
            .get("userId", Int::class.java)
    }

    fun getUserRole(token: String):String{
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
            .get("userRole", String::class.java)
    }

    fun isActiveState(token: String):Boolean{
        return Objects.equals(Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body.get("userState", String::class.java), RegisterStateEnum.ACTIVE.name)
    }

    fun isAccessToken(token: String): Boolean{
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .header.get("type").toString() == "access"
    }
}