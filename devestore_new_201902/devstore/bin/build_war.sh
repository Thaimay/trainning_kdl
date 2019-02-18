./mvnw dependency:resolve
./mvnw compile
./mvnw package -Dmaven.test.skip=true
