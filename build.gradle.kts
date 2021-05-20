import com.google.gson.JsonParser
import java.io.InputStreamReader
import java.net.URL
import java.time.Duration

plugins {
    java
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.4.0"
}

group = "net.wolvhaven"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://ci.ender.zone/plugin/repository/everything/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://jitpack.io") // Leave at the very bottom.
}

buildscript {
    dependencies {
        classpath("com.google.code.gson:gson:2.8.0")
    }
}

dependencies {
    // Paper
    // -----
    compileOnlyApi("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")

    // Plugins
    // -------
    compileOnlyApi("net.luckperms:api:5.3")
    compileOnlyApi("com.github.mbax:VanishNoPacket:0cb428ff27")
    compileOnlyApi("me.clip:placeholderapi:2.10.9")
    compileOnlyApi("net.ess3:EssentialsX:2.18.2")

    // Libraries
    // ---------

    // Cloud - Command Manager
    api("cloud.commandframework:cloud-paper:1.4.0")
    // Configurate - Config
    api("org.spongepowered:configurate-hocon:4.1.1")
    // Adventure stuff
    api("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
    api("net.kyori:adventure-text-serializer-plain:4.7.0")
    api("net.kyori:adventure-text-serializer-gson:4.7.0")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }

    build {
        dependsOn(shadowJar)
    }

    register<JavaExec>("runServer") {
        workingDir = projectDir.resolve("run")
        standardInput = System.`in`
        dependsOn(shadowJar)
        val paperJar = workingDir.resolve("paperclip.jar")
        classpath(paperJar)
        args = listOf("nogui")
        systemProperty("disable.watchdog", true)

        doFirst {
            // Create working dir and plugins dir if needed
            if (!workingDir.exists()) workingDir.mkdir()
            val pluginsDir = workingDir.resolve("plugins")
            if (!pluginsDir.exists()) pluginsDir.mkdir()

            // Copy plugin jar
            val pluginJar = pluginsDir.resolve("whcore.jar")
            shadowJar.get().archiveFile.get().asFile.copyTo(pluginJar, overwrite = true)

            // Download latest Paperclip if we don't have one or if it's older than 3 days
            val paperVersion = "1.16.5"
            if (!paperJar.exists() || paperJar.lastModified() < System.currentTimeMillis() - Duration.ofDays(3).toMillis()) {

                val jsonParser = JsonParser()
                logger.lifecycle("Fetching latest Paper {} builds...", paperVersion)
                val builds = jsonParser.parse(InputStreamReader(URL("https://papermc.io/api/v2/projects/paper/versions/$paperVersion").openStream())).asJsonObject["builds"].asJsonArray
                val latest = builds.last().asString
                logger.lifecycle("Downloading Paper {} build {}...", paperVersion, latest)
                val jarUrl = URL("https://papermc.io/api/v2/projects/paper/versions/$paperVersion/builds/$latest/downloads/paper-$paperVersion-$latest.jar")
                paperJar.writeBytes(jarUrl.readBytes())
                logger.lifecycle("Done downloading Paper {} build {}.", paperVersion, latest)
            }
        }
    }

    // Force J16 because jitpack sucks
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(16))
        }
    }

}

bukkit {
    name = "WolvHaven-Core"
    version = project.version as String
    main = "net.wolvhaven.core.internal.WhCorePlugin"
    apiVersion = "1.16"
    author = "Underscore11"
    depend = listOf("LuckPerms", "Essentials", "VanishNoPacket", "PlaceholderAPI")
}
