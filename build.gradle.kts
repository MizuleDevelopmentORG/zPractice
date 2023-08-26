plugins {
    id("zpractice.paper-conventions")
}

dependencies {
    compileOnly(libs.paper)
    compileOnly(libs.annotations)
    api(libs.bstats.bukkit)
    compileOnly(libs.placeholderapi)
    api(libs.configurate.yaml)
    compileOnly(libs.miniplaceholders)
    implementation("org.mongodb:mongo-java-driver:3.12.12")
}

applyJarMetadata("com.mizuledevelopment.zpractice")
