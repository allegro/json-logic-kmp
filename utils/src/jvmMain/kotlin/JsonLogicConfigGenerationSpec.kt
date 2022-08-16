import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec

private val configObjectFile = FileSpec.builder(packageName = "pl.allegro.mobile", fileName = "JsonLogicConfig").addType(
    TypeSpec.objectBuilder(name = "JsonLogicConfig").addProperty(name = "Version", type = String::class, modifiers = listOf(KModifier.CONST)).build()
)

private fun createVersionConstField(version: String) = PropertySpec.builder(name = "Version", type = String::class, modifiers = listOf(KModifier.CONST)).mutable(false).initializer("%S", version) // TODO pass version parameter here
