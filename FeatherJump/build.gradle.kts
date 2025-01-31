plugins {
    id("java")
   //"wq" id("org.hidetake.ssh") version "2.11.1"
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// Define the local output path for your server's plugins folder
val serverPluginsDir = file(System.getProperty("user.home") + "/plugins")

tasks.register<Copy>("copyToServer") {
    dependsOn(tasks.named("build"))
    from(tasks.named("jar")) // Use the output jar file
    into(serverPluginsDir) // Destination folder
}

tasks.named("build") {
    finalizedBy("copyToServer") // Ensure this task runs after the build
}
/*
// Configure SFTP upload to your server
val remoteHost = "d1253.ggn.io"
val remotePort = 2022
val remoteUser = "swiftcristi.787c0f29"
val remotePath = "/plugins/" // Update to your desired upload directory
val privateKeyPath = file(System.getProperty("user.home") + "/.ssh/id_rsa") // Replace with your actual private key file if necessary

tasks.register("uploadToServer") {
    dependsOn(tasks.named("build"))
    doLast {
        org.hidetake.groovy.ssh.Ssh.newService().run {
            session(org.hidetake.groovy.ssh.Ssh.remotes {
                create("myServer") {
                    host = remoteHost
                    port = remotePort
                    user = remoteUser
                    password = 9988 // Use password authentication if private key is not used
                }
            }) {
                put(
                    from = file("build/libs/your-plugin.jar"),
                    into = remotePath
                )
            }
        }
    }
}

tasks.named("build") {
    finalizedBy("uploadToServer")
}
/*
