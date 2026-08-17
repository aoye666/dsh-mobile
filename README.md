# dsh-mobile
DeepSeek Harness Mobile 是 DeepSeek Harness 的 Android 原生应用，将原本仅支持 PC 浏览器的 AI Agent 框架完整迁移至移动端。采用 Kotlin + Jetpack Compose 开发，内置 Node.js ARM64 二进制和 dsh CLI，实现零依赖安装——下载 APK 即可运行，无需 Root、Termux 或额外环境配置。应用覆盖网页端全部核心功能：多会话管理、流式对话、插件市场、模型设置等，并针对竖屏触控体验深度优化。技术上采用 MVVM 架构、Dagger Hilt 依赖注入、Room 本地数据库，后台 Foreground Service 确保引擎持续运行，支持离线场景和弱网环境自动重连。
