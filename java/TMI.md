## 개발시.. 소스코드에 대해 모르는부분에 대한 추가적으로 찾아보고.. 설명을 적은 파일입니다...

# JWTFilter에 OncePerRequestFilter 를 상속 받는 이유 
- 쉽게 말해, 내부적으로 프로젝트의 다른 API에 요청할 때마다 모든 API가 동일한 보안 필터를 갖기 때문에 동일한 인증이 다시 발생하게 된다.
이를 막기 위해서 OncePerRequest를 상속받아 AuthenticationFilter를 구현하는 것이 차선택이라고 생각해서 
// 참고 https://velog.io/@wellsy1012/SpringSecurity-OncePerRequestFilter%EC%99%80-GenericFilterBean%EC%9D%98-%EC%B0%A8%EC%9D%B4


#JwtGenerator
- Jwts.SIG.HS256.key().build();
JJWT maintainer 측에서는 개발자들이 아무런 고민 없이 문자열 값으로 JWT의 키를 넣는것을 상당히 경계하고 있습니다. 
이런 경우에는 보안 취약점이 될 수 있기 때문에 무작위 키 값을 만드는 빌더 클레스를 제공해 주고 있습니다.
// 참고 https://velog.io/@jh9/jjwt
- SecretKeySpec : 이 클래스는 공급자에 독립적인 방식으로 비밀 키를 지정합니다.
//참고 https://docs.oracle.com/javase/8/docs/api/javax/crypto/spec/SecretKeySpec.html