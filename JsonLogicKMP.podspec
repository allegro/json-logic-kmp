Pod::Spec.new do |spec|
  spec.name         = "JsonLogicKMP"
  spec.version      = "0.2.8"
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
                        :sha256 => "00fe31cb83e34212fca577f7159d585559b29fccf0a3e5c96572af275eea2afd"
                      }

  spec.vendored_frameworks = "#{spec.name}.xcframework"
end
