import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.names.DuplicateTestNameMode

object ProjectTestsConfig : AbstractProjectConfig() {
    override val duplicateTestNameMode = DuplicateTestNameMode.Silent
}
