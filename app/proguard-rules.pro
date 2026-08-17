# Add project specific ProGuard rules here.
-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Annotation
-keepattributes EnclosingMethod

# Dagger Hilt
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class dagger.hilt.** { *; }
-dontwarn dagger.hilt.**
-keep class com.deepseek.dshmobile.** { *; }

# Room
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# Kotlin Coroutines
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <fields>;
    public <methods>;
}
