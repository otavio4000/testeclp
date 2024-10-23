plugins {
	kotlin("jvm") version "1.8.0" // Ou a versão mais recente do Kotlin
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
	id("application") // Add this line
}

group = "com.clp"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.exposed:exposed-core:0.37.2") // Ou a versão mais recente
    implementation("org.jetbrains.exposed:exposed-dao:0.37.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.37.2") // Para trabalhar com LocalDateTime
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0") // Para reflexão em Kotlin
    // Outras dependências que você possa precisar

	// Ensure version consistency for Exposed
	implementation("org.jetbrains.exposed:exposed-core:0.55.0")
	implementation("org.jetbrains.exposed:exposed-dao:0.55.0")
	implementation("org.jetbrains.exposed:exposed-jdbc:0.55.0")
	implementation("org.jetbrains.exposed:exposed-java-time:0.55.0")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

application {
	mainClass.set("com.clp.models.MainKt") // Specify your main class here
}