/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.org.springframework.boot.spring.boot.starter.web)
    api(libs.javax.persistence.javax.persistence.api)
    api(libs.org.springframework.boot.spring.boot.starter.data.jpa)
    api(libs.org.mapstruct.mapstruct)
    runtimeOnly(libs.mysql.mysql.connector.java)
    testImplementation(libs.org.springframework.boot.spring.boot.starter.test)
    compileOnly(libs.org.projectlombok.lombok)
    compileOnly(libs.org.mapstruct.mapstruct.processor)
}

group = "pedidosyventas"
version = "0.0.1-SNAPSHOT"
description = "pedidosyventasdemo"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
