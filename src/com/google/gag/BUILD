java_library (
  name = "gag",
  srcs = glob([
    "annotation/**/*.java",
    "enumeration/**/*.java",
  ]),
  deps = [
  ],
)

java_library(
  name = "gag-instrument",
  srcs = glob([ "instrument/**/*.java" ]),
  deps = [
    "//experimental/users/leodeegan/lib:asm-3.1",
    "//experimental/users/leodeegan/lib:google-collect-1.0",
  ],
)

java_binary(
  name = "gag-agent",
  main_class = "com.google.gag.agent.GagTransformer",
  srcs = glob([ "agent/**/*.java" ]) + [ ":gag-instrument", ],
  deps = [
    ":gag",
  ],
  deploy_manifest_lines = [
    "Premain-Class: com.google.gag.agent.GagTransformer",
    "Class-Path: asm-3.1.jar asm-commons-3.1.jar google-collect-1.0.jar",
  ],
)
