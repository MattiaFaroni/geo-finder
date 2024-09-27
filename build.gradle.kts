import java.util.*

plugins {
    war
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.diffplug.spotless") version "6.25.0"
    id("io.sentry.jvm.gradle") version "4.11.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.geocode.search"
version = "1.0.1"

springBoot {
    buildInfo()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    maven { url = uri("https://repo.osgeo.org/repository/release/") }
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    implementation("org.geotools:gt-geojson:32.0")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
    testImplementation("org.mockito:mockito-core:5.12.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.war {
    manifest {
        archiveFileName.set("${project.name}.war")
    }
}

tasks.jar {
    manifest {
        archiveFileName.set("${project.name}-no-dependencies.jar")
    }
}

tasks.shadowJar {
    manifest {
        archiveFileName.set("${project.name}.jar")
    }
}

val generatedVersionDir = "${layout.buildDirectory.get()}/generated-version"
tasks.create("generateVersionProperties") {
    doLast {
        val propertiesFile = File("$generatedVersionDir/build.properties")
        propertiesFile.parentFile.mkdirs()
        val properties = Properties()
        properties.setProperty("version", rootProject.version.toString())
        properties.setProperty("build-date", System.currentTimeMillis().toString())
        properties.store(propertiesFile.writer(), "")
    }
}

val markdownDir = "${layout.buildDirectory.get()}/markdown"
tasks.create("addMarkdownFile") {
    doLast {
        File("$rootDir/CHANGELOG.md").copyTo(File(markdownDir).resolve("CHANGELOG.md"), true)
        File("$rootDir/LICENSE.md").copyTo(File(markdownDir).resolve("LICENSE.md"), true)
        File("$rootDir/README.md").copyTo(File(markdownDir).resolve("README.md"), true)
    }
}

sourceSets {
    main {
        output.dir(generatedVersionDir, "builtBy" to "generateVersionProperties")
        output.dir(markdownDir, "builtBy" to "addMarkdownFile")
    }
}

spotless {
    java {
        target("**/*.java")
        toggleOffOn()
        palantirJavaFormat()
        removeUnusedImports()
        trimTrailingWhitespace()
        indentWithTabs()
        endWithNewline()
    }
}

afterEvaluate{
    tasks.named("build") {
        dependsOn("spotlessApply")
    }
}