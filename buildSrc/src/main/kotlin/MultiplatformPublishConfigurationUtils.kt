import org.gradle.api.publish.maven.MavenPublication

fun MavenPublication.setFullModuleArtifactId() {
    artifactId = "${LibConfig.artifactName}-$artifactId"
}
