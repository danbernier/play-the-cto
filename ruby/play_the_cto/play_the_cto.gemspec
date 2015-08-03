# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'play_the_cto/version'

Gem::Specification.new do |spec|
  spec.name          = "play_the_cto"
  spec.version       = PlayTheCto::VERSION
  spec.authors       = ["Dan Bernier"]
  spec.email         = ["danbernier@gmail.com"]

  spec.summary       = %q{Play the CTO! A tiny CLI game}
  spec.description   = %q{Invest in features, and gain users. But ignore infrastructure, and risk a system crash!}
  spec.homepage      = "https://github.com/danbernier/play-the-cto"

  # Prevent pushing this gem to RubyGems.org by setting 'allowed_push_host', or
  # delete this section to allow pushing this gem to any host.
  if spec.respond_to?(:metadata)
    spec.metadata['allowed_push_host'] = "TODO: Set to 'http://mygemserver.com'"
  else
    raise "RubyGems 2.0 or newer is required to protect against public gem pushes."
  end

  spec.files         = `git ls-files -z`.split("\x0").reject { |f| f.match(%r{^(test|spec|features)/}) }
  spec.bindir        = "exe"
  spec.executables   = spec.files.grep(%r{^exe/}) { |f| File.basename(f) }
  spec.require_paths = ["lib"]

  spec.add_development_dependency "bundler", "~> 1.9"
  spec.add_development_dependency "rake", "~> 10.0"
end
