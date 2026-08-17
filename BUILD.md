# DeepSeek Harness Mobile 构建指南

本文档说明如何构建 DeepSeek Harness Mobile Android 应用。

## 环境要求

- Android Studio Ladybug 或更高版本
- JDK 17 或更高版本
- Android SDK API 34
- Gradle 8.5+

## 构建步骤

### 第一步：克隆仓库

```bash
git clone https://github.com/<your-org>/dsh-mobile.git
cd dsh-mobile
```

### 第二步：准备引擎二进制文件

应用需要内嵌的 Node.js 和 dsh CLI 二进制文件。你有两种选择：

#### 方案 A：从源码构建（推荐）

1. 克隆 DeepSeek Harness 源码：
   ```bash
   git clone https://github.com/deepseek-ai/deepseek-harness.git
   cd deepseek-harness
   npm install
   npm run build
   ```

2. 构建 ARM64 Android 版 Node.js：
   ```bash
   # 下载预编译的 Android ARM64 Node.js
   curl -L https://nodejs.org/dist/v20.11.0/node-v20.11.0-android-arm64.tar.gz -o node-android.tar.gz
   tar -xzf node-android.tar.gz
   cp node-v20.11.0-android-arm64/bin/node app/src/main/assets/engine/
   ```

3. 复制 dsh CLI 二进制文件：
   ```bash
   cp dist/dsh app/src/main/assets/engine/
   chmod +x app/src/main/assets/engine/*
   ```

#### 方案 B：使用预编译二进制文件

如果你已有预编译的二进制文件，将它们放入：
```
app/src/main/assets/engine/node      # ARM64 Node.js 二进制
app/src/main/assets/engine/dsh       # DeepSeek Harness CLI
```

### 第三步：构建应用

```bash
# 构建 Debug APK
./gradlew assembleDebug

# 构建 Release APK
./gradlew assembleRelease

# 或使用 Android Studio
# File > Open > 选择 dsh-mobile 文件夹
# Build > Build Bundle(s) / APK(s) > Build APK(s)
```

### 第四步：安装到设备

```bash
# 安装 Debug 版本
adb install app/build/outputs/apk/debug/dsh-mobile-debug.apk

# 安装 Release 版本（需要签名配置）
adb install app/build/outputs/apk/release/dsh-mobile-release.apk
```

## 项目结构

```
dsh-mobile/
├── app/src/main/
│   ├── assets/
│   │   └── engine/           # Node.js + dsh CLI 二进制（必需）
│   │       ├── node          # ARM64 Node.js 二进制
│   │       └── dsh           # DeepSeek Harness CLI
│   ├── java/com/deepseek/dshmobile/
│   │   ├── di/               # Dagger Hilt 依赖注入模块
│   │   ├── service/          # 引擎管理服务
│   │   ├── database/         # Room 数据库
│   │   ├── repository/       # 数据仓库
│   │   ├── ui/               # Compose UI
│   │   └── util/             # 工具类
│   └── res/                  # 资源文件
├── gradle/
│   └── wrapper/              # Gradle 包装器
└── build.gradle.kts          # 根构建配置
```

## 故障排除

### 引擎二进制文件未找到
- 确保 `node` 和 `dsh` 二进制文件在 `app/src/main/assets/engine/` 目录中
- 检查二进制文件是否具有可执行权限
- 确认二进制文件是为 ARM64 架构构建的

### 构建失败
- 确保已安装 JDK 17 并设置了 JAVA_HOME
- 在构建前运行 `./gradlew clean`
- 检查 Android Studio SDK Manager 是否安装了 API 34

### 应用启动崩溃
- 查看 logcat 日志获取错误信息
- 确认引擎二进制文件已正确提取
- 确保已授予前台服务权限
