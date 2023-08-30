import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.flywaydb.flyway") version "6.0.0-beta2"
    war
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
    kotlin("plugin.jpa") version "1.4.30"
    kotlin("kapt") version "1.3.61"
}

group = "com.app"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15
configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

flyway {
    url = "jdbc:postgresql://localhost:5432/norutin-db"
    //todo use when deploying to docker
    //url = "jdbc:postgresql://postgres:5432/norutin-db"
    user = "postgres"
    password = "postgres"
    schemas = arrayOf("public")
    cleanDisabled = true
}

repositories {
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    implementation("org.jeasy:easy-random-core:5.0.0")
    implementation("io.jsonwebtoken:jjwt:0.2")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.0")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.5.0")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.0")

    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
    compile("org.postgresql:postgresql:42.2.6")
    compileOnly("org.projectlombok:lombok")

    runtimeOnly("org.postgresql:postgresql")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}