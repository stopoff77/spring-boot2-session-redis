# spring-boot2-session-redis
session-redis

```
Listener 등록시 spring-session사용한다면
ServletListenerRegistrationBean<HttpSessionListener>을 이용하여 등록하는 방법은 안됨
해당 listener를 Bean으로 등록하는 방법을 사용해야 함
```
