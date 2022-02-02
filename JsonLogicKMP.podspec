Pod::Spec.new do |spec|
  spec.name         = "JsonLogicKMP"
  spec.version      = "0.0.3"
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
                        :sha256 => "25e9d356bee0dcd9093cd6146c67f2e9b20ac893a85651391c34b1ccffbe9b06"
                      }

  spec.vendored_frameworks = "#{spec.name}.xcframework"
end
