plugins {
    id("java")
    id("org.hidetake.ssh") version "2.11.1"
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// Define the output path for your server's plugins folder (local copy)
val serverPluginsDir = file(System.getProperty("user.home") + "/plugins")

tasks.register<Copy>("copyToServer") {
    dependsOn(tasks.named("build"))
    from(tasks.named("jar")) // Use the output jar file
    into(serverPluginsDir) // Destination folder
}

tasks.named("build") {
    finalizedBy("copyToServer") // Ensure this task runs after the build
}

// Configure the SSH plugin
val remoteHost = "your.server.com"
val remoteUser = "yourUsername"
val remotePath = "/path/on/server/"
val privateKeyPath = file(System.getProperty("user.home") + "/.ssh/id_rsa")

// Task to upload the plugin jar to the remote server
tasks.register("uploadToServer") {
    dependsOn(tasks.named("build"))
    doLast {
        org.hidetake.groovy.ssh.Ssh.newService().run {
            session(org.hidetake.groovy.ssh.Ssh.remotes {
                create("myServer") {
                    host = remoteHost
                    user = remoteUser
                    identity = privateKeyPath // Or set  if using a password
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

// Optionally chain the remote upload to the build process
tasks.named("build") {
    finalizedBy("uploadToServer")
}

