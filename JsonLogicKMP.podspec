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
                        :sha256 => "780f0c63a01853eb76f4e193cfa85ffbd2d333a199a418fac9f83421a9a2fcc4"
                      }

  spec.vendored_frameworks = "#{spec.name}.xcframework"
end
