///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package classpkg;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import javax.crypto.SecretKey;
//
//
//
///**
// *
// * @author Yug
// */
//public class keygenerator {
//    public static String gentoken(String username){
//        SecretKey sk;
//        sk = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        return Jwts.builder().setSubject(username).signWith(sk).compact();
//    }
//    
//    public static boolean verifytoken(String token){
//        //String secretkey = "mykey";
//         SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        Jws<Claims> c = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//        return true;
//    }
//}
