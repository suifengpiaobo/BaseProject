# 关于BaseProject
---
BaseProject是一个快速构建app工程的开源项目，目的是为了更加方便的初始化一个工程，省去编写或者导入BaseActivity，BaseFragment，网络请求，工具类等基础又实用的代码。让你更加专注去实现自己产品需求，业务逻辑，而不是浪费时间在重复的工作上！

# 如何依赖BaseProject
---
[![](https://jitpack.io/v/flyzend/BaseProject.svg)](https://jitpack.io/#flyzend/BaseProject)
1. 在project.gradle 文件中添加 maven { url "https://jitpack.io" } 如下所示
```JAVA
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

2. 在app.gradle 文件中添加依赖，如下所示  
```JAVA
dependencies {
    compile 'com.github.flyzend:BaseProject:V1.0.1'
}
```

# 详细的使用方式与介绍

**[简书传送门：http://www.jianshu.com/p/d5ad3f127ebf](http://www.jianshu.com/p/d5ad3f127ebf)**  
**[CSDN传送门：http://blog.csdn.net/u010302764/article/details/71170540#](http://blog.csdn.net/u010302764/article/details/71170540#)**

# 关于我
---
本人王灿，Android开发芸芸众生当中的一个新手，正在学习的路上不断爬坑！
**不论文章还是代码，当然肯定有很多不够好的地方，希望各位大神不吝赐教，我一定虚心学习，可以评论或者在github上提issue，欢迎关注转发！转发请带上原文链接！谢谢**  
QQ：306217025  
简书：[http://www.jianshu.com/users/0d03dcfbfc36/timeline](http://www.jianshu.com/users/0d03dcfbfc36/timeline)  
CSDN: [http://blog.csdn.net/u010302764](http://blog.csdn.net/u010302764)
