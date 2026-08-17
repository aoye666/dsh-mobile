# 贡献指南

感谢你对 DeepSeek Harness Mobile 的关注！本文档提供了贡献指南。

## 快速开始

1. **Fork 仓库** - 在 GitHub 上 Fork 本项目
2. **克隆到你的本地**：
   ```bash
   git clone https://github.com/<your-username>/dsh-mobile.git
   cd dsh-mobile
   ```
3. **创建功能分支**：
   ```bash
   git checkout -b feature/your-feature-name
   ```

## 开发环境配置

### 前置条件
- Android Studio Ladybug 或更高版本
- JDK 17+
- Android SDK API 34
- Gradle 8.5+

### 首次设置
```bash
# 安装依赖
./gradlew dependencies

# 构建 Debug APK
./gradlew assembleDebug

# 运行测试
./gradlew test
```

## 代码规范

### Kotlin 约定
- 遵循 [Kotlin 编码规范](https://kotlinlang.org/docs/coding-conventions.html)
- 使用 **4 空格缩进**
- 优先使用 `val` 而非 `var`
- 使用 Kotlin 属性访问语法
- 遵循 Material Design 3 指南

### 提交信息规范
- 使用 [Conventional Commits](https://www.conventionalcommits.org/) 格式：
  ```
  feat: 添加新的聊天功能
  fix: 修复引擎启动崩溃问题
  docs: 更新 README 构建说明
  refactor: 简化导航代码
  test: 为 ViewModel 添加单元测试
  ```
- 第一行：最多 50 个字符
- 使用祈使语气（"添加" 而非 "已添加"）
- 涉及相关 Issue 时请注明编号

## Pull Request 流程

1. **更新文档**（如需要）
2. **为新功能添加测试**
3. **提交前运行所有测试**：
   ```bash
   ./gradlew test
   ./gradlew lintDebug
   ```
4. **提交前 rebase 到 main**：
   ```bash
   git fetch origin
   git rebase origin/main
   ```
5. **创建 Pull Request**，包含：
   - 清晰的变更说明
   - 相关 Issue 引用
   - UI 变更请附截图

## 审查流程

- 所有 PR 需要至少一人审查
- CI 必须通过才能合并
- 合并前请整理提交历史

## 新功能开发指南

### 架构规范
- 使用 **MVVM** 模式（ViewModel + Compose）
- 通过 **Hilt** 注入依赖
- 业务逻辑放在 **Repository** 层
- 使用 **Flow** 进行响应式数据流

### UI 规范
- 遵循 **Material Design 3**
- 支持 **深色模式**
- 优化 **竖屏体验**
- 新界面使用 **Compose** 开发

## Bug 报告

请提供以下信息：
- 设备型号和 Android 版本
- 复现步骤
- 预期行为与实际行为的对比
- 如有 logcat 输出请一并提供

## 功能请求

请描述：
- 要解决的问题
- 建议的解决方案
- 对用户的价值

## 许可证

贡献即表示你同意你的贡献将在 MIT 许可证下发布。
