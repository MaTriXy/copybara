# Copyright 2025 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

load("@rules_java//java:defs.bzl", "java_library")

package(default_visibility = ["//visibility:public"])

licenses(["notice"])

java_library(
    name = "util",
    srcs = glob(["*.java"]),
    javacopts = [
        "-Xlint:unchecked",
    ],
    runtime_deps = ["//third_party:tukaani_xz"],
    deps = [
        "//java/com/google/copybara:base",
        "//java/com/google/copybara/exception",
        "//java/com/google/copybara/remotefile/extractutil",
        "//java/com/google/copybara/util",
        "//third_party:apache_commons_compress",
        "//third_party:guava",
        "//third_party:jsr305",
    ],
)
