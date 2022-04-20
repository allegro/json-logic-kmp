Pod::Spec.new do |spec|
  spec.name         = "JsonLogicKMP"
  spec.version      = "0.2.2"
  spec.summary      = "Kotlin multiplatform JsonLogic"
  spec.description  = <<-DESC
  * Kotlin multiplatform JsonLogic expressions evaluation engine
                   DESC
  spec.homepage     = "https://github.com/allegro/json-logic-kmp"
  spec.license      = { :type => "The Apache License, Version 2.0", :file => "LICENSE" }
  spec.author       = { "Allegro" => "opensource@allegro.pl" }

  spec.platform = :ios
  spec.ios.deployment_target = "13.0"

  spec.source       = { :http => "#{spec.homepage}/releases/download/#{spec.version}/#{spec.name}_xcframeworks.zip",
                        :sha256 => ""
                      }

  spec.subspec 'OperationsApi' do |operationsApi|
    operationsApi.vendored_frameworks = "JsonLogicOperationsApi.xcframework"
  end

  spec.subspec 'OperationsStdlib' do |operationsStdlib|
    operationsStdlib.vendored_frameworks = "JsonLogicOperationsStdlib.xcframework"
  end

  spec.subspec 'Core' do |core|
    core.vendored_frameworks = "JsonLogicCore.xcframework"
  end

end
