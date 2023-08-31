# spring-boot2-session-redis
session-redis

```
Listener 등록시 spring-session사용한다면
ServletListenerRegistrationBean<HttpSessionListener>을 이용하여 등록하는 방법은 안됨
해당 listener를 Bean으로 등록하는 방법을 사용해야 함
```

```
/**
 * Copyright 2020 장동선.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
```
