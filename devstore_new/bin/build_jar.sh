./mvnw clean install -U -Dmaven.test.skip=true
./mvnw package -Dmaven.test.skip=true
cp target/stored-0.0.1.jar bin/spring-boot.jar
