# EasyRouter

**EasyRouter是基于编译时原理的简单路由框架，使用简单，功能丰富。**

- EasyRouter是一种简易的路由框架，使用起来是相当简单的。

  1. 首先，将项目的RouteEasy、router-note、ERoute-Process复制到项目中。

     1. RouterEasy是主要的工具类库
     2. router-note是需要用到的注解
     3. ERoute-Process核心的注解处理器

  2. 然后再build.gradle中添加依赖关系

     ```
     implementation project(path: ':RouteEasy')
     annotationProcessor project(path: ':ERoute-Process')
     ```

     这样就导入成功了。

- 使用起来也是很简单，EasyRouter支持启动三种活动Activity，Fragment，Action（自定义跳转）

  - 当跳转Fragment时，需要被跳转的Fragment继承于CommonAbstractFragment。
  - 当跳转自定义Action时，该类需要实现IGotoAction接口。

- **配置路径** ：直接在需要配置路径的Activity、Fragment或Action上添加EAction注解传入path即可配置成功。

  ```kotlin
  @EAction(path = "register/action")
  class RegisterActivity : AppCompatActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_register)
      }
  }
  ```

- **跳转方法** ：通过Router工具中的静态方法进行跳转。如：

  ```kotlin
  Router.startAction("login/activity")
  ```

- **支持跳转拦截器** ：com.realize.routeeasy.interfaces.Interceptor

  ```kotlin
  Router.startAction("login/activity", object : Interceptor {
      override fun interceptor(action: String?, bundle: Bundle?): Boolean {
           //这里是跳转前的拦截 return true代表继续跳转  返回false代表拦截该请求
           Log.e("test", bundle.toString())
           return true
      }
  })
  ```

- **该本白暂不支持startActivityForResult功能，将在下个版本实现改功能**

