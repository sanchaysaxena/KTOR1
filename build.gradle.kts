
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-auth-jwt")

    implementation("org.litote.kmongo:kmongo:4.5.1")
    implementation("org.litote.kmongo:kmongo-coroutine:4.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")

    implementation ("org.jetbrains.exposed:exposed-core:0.31.1")
    implementation ("org.jetbrains.exposed:exposed-dao:0.31.1")
    implementation ("org.jetbrains.exposed:exposed-jdbc:0.31.1")
    implementation ("org.postgresql:postgresql:42.7.2")
    implementation ("com.zaxxer:HikariCP:4.0.3")
}
//postgres - provides jdbc driver(java database connectivity)
//hikari - used to configure the DB
//exposed - to access the DB