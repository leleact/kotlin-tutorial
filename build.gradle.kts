import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

group = "com.github.leleact"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    mainClassName = "com.github.leleact.kotlin.tutorial.MainKt"
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        manifest {
            attributes["Main-Class"] = application.mainClassName
        }
        from(configurations.runtime.get().map { if (it.isDirectory) it else zipTree(it) })
    }
}
