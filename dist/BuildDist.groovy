import java.nio.file.Files
import java.nio.file.Paths

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING

def distDir = Paths.get("${properties.getProperty("baseDir")}")
def appJar = Paths.get("../app/target/${properties.getProperty("appName")}-${properties.getProperty("appVersion")}.jar")
def dockerTarget = Paths.get("../docker/target/")
Files.copy(appJar.relativize(distDir), distDir, REPLACE_EXISTING)
Files.copy(dockerTarget.relativize(distDir), distDir, REPLACE_EXISTING)