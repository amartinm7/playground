import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version = "2.3.0"
val ScalaBinary = "2.13"
val group = "com.example"
val version = "0.0.1-SNAPSHOT"

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.graalvm.buildtools.native") version "0.9.20"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    // kotlin("kapt") version "1.8.22"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-websockets:$ktor_version")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // akka dependencies
    implementation("com.typesafe.akka:akka-actor_2.13:2.8.2")
    implementation("com.typesafe.akka:akka-actor-typed_2.13:2.8.2")
    implementation("com.typesafe.akka:akka-actor-testkit-typed_2.13:2.8.2")
    implementation("com.typesafe.akka:akka-stream_2.13:2.8.2")
    implementation("com.typesafe.akka:akka-testkit_2.13:2.8.2")

    // https://mvnrepository.com/artifact/com.enragedginger/akka-quartz-scheduler
    implementation("com.enragedginger:akka-quartz-scheduler_3:1.9.3-akka-2.6.x")
}

tasks.withType(Test::class).configureEach {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString()))
    }
}
repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString()))
    }
}

tasks.withType(KotlinCompile::class).configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}