import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

tasks.withType<Test> {
    useJUnitPlatform()
}
