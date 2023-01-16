Pod::Spec.new do |spec|
  spec.name         = "JsonLogicKMP"
  spec.version      = "1.1.5"
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
                        :sha256 => "50992e0adb4a2b007664a5487b9ae729ced3249196017b42f9d7af5317d53ccc"
                      }

  spec.vendored_frameworks = "#{spec.name}.xcframework"
end
