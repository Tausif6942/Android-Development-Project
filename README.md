Make sure to enable databinding in the build.gradle.kts file in the app module:

android{
  ...
  buildFeatures
  {
      dataBinding=true
  }
}

Add this buildFeatures block inside the android block.
