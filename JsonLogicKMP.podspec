Pod::Spec.new do |spec|
  spec.name         = "JsonLogicKMP"
  spec.version      = "1.1.3"
  spec.summary      = "Kotlin multiplatform JsonLogic"
  spec.description  = <<-DESC
  * Kotlin multiplatform JsonLogic expressions evaluation engine
                   DESC
  spec.homepage     = "https://github.com/allegro/json-logic-kmp"
  spec.license      = { :type => "The Apache License, Version 2.0", :file => "LICENSE" }
  spec.author       = { "Allegro" => "opensource@allegro.pl" }

  spec.platform = :ios
  spec.ios.deployment_target = "13.0"

  spec.source       = { :http => "#{spec.homepage}/releases/download/#{spec.version}/#{spec.name}.xcframework.zip",
                        :sha256 => "1d82b9128bd4047da56f942dfa899961e0fa3ade3f9d5465c69ace0a570c08d6"
                      }

  spec.vendored_frameworks = "#{spec.name}.xcframework"
end
