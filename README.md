# 规范
- 参考阿里Java手册 
- 把数据处理逻辑内聚在`Vo值对象`或者`model实体`里面
- 接口命名使用restful风格
- 写请求尾缀`Command`
- 读请求尾缀`Query`
- 展示返回类尾缀`Representation`
- 使用Swagger2作为接口文档
- 依赖在父module中维护版本号，子module中不用写版本号,为了清楚知道所有的依赖，父module中也必须写上依赖的信息

### Controller
controller职责

1. 提供请求入口
2. 校验参数
3. 返回data字段的结果
4. swagger接口文档


请求参数必须用对象承接，使用Validation来进行单个参数验证,已有的不满足可以进行注解自定义

### Service
数据转换相关操作，必须要在`Representation`或`Vo`中操作,例如:数据库中查询到信息之后，从实体信息中转换到`Representation`

---


# common包功能
### 接口版本控制

### Log打印

日志分级，如:阻碍主流程业务严重的警告，info日志可以按格式使用sls进行管理

日志的打印使用了Spring自带的`LogBack`,格式定义都在`logback-spring.xml`下

主要是打印控制台，写文件，输出sql等，线上不打debug日志,info跟error日志分开，并且使用`MDC`进行链路追踪。(更好的方案，分布式链路追踪)

特别的,类`WebLog`是一个AOP类，请求IP、路径、方法、参数、返回结果和消耗时间进行打印，
```
 ========================== Start ===============================
 IP             : 0:0:0:0:0:0:0:1
 URL            : http://localhost:8888/order/1
 HTTP Method    : GET
 Class Method   : com.wenkchan.web.order.OrderController.getOrder
 Request Args   : ["1"]
 Response Args  : {"orderId":"1","productId":"5555"}
 Time Consuming : 150 ms`
 ============================ End ================================
```
而切点是以下四个注解类

- @GetMapping
- @PostMapping
- @PutMapping 
- @DeleteMapping

所以使用controller的时候要使用这个四个类进行注解才能打出日志


---



### 异常处理
异常处理相关的处理的类都在`common`下的`exception`包

```
exception
├── BizException.java 
├── CommonErrno.java
├── DefaultRestErrorController.java
├── Errno.java
└── GlobalExceptionHandler.java
```

整体的流程是`GlobalExceptionHandler`异常捕捉类，使用Spring提供的`@ExceptionHandler`和`@ControllerAdvice`对三类异常进行捕捉，
- 业务异常(BizException)
- 参数验证异常(BindException javax.validation)，
- 和系统异常(未能处理到的异常情况)

所有的业务异常都由`BizException`抛出，其中参数`Errno`继承`Errno`定义对应领域的错误码进行赋值，`CommonErrno`是一些通用的错误码

异常捕捉完成之后会统一封装成Spring提供的`ResponseEntity`并包装`ApiResult`进行返回,错误后都会去访问`/error`，即`DefaultRestErrorController`里面进行统一的返回，并额外对404的情况进行了处理

拦截器异常注意自己处理

### 正常返回
结果的处理在`common`下的`result`

```
result
├── ApiResult.java
├── GlobalResultHandler.java
├── OriginalResponse.java
├── ResultConverterConfiguration.java
└── ResultMessageConverter.java

```
`Controller`中返回的结果只会影响到`data`字段，`GlobalResultHandler`会把ApiResult中除`data`剩下的所有字段都自动生成，统一返回。如果不想被包装成ApiResult，可以使用`OriginalResponse`进行注释

`ResultConverterConfiguration`和`ResultMessageConverter`都是对结果进行转换

---

### 统一返回格式
返回的格式都包含在`common`里面实体类`ApiResult`里面

- requestId 本次请求的Id,通过logback的MDC实现
- path 访问路径
- timestamp 时间戳
- success 是否成功
- status 状态码
- message 异常描述
- data 异常相关信息

```
{
    requestId: "97769c8f11674834be41512ae2404d96",
    path: "/order",
    timestamp: 1579873540717,
    success: false,
    status: 500,
    message: "system error",
    data: {
        detail: "Request method 'GET' not supported"
    }
}
```

统一返回，包括`异常处理和正常返回`

# generator包功能
根据数据库表生成对应的service，mapper，controller，entity
修改对应包名，数据库相关信息，表前缀，表名，


#数字相关
涉及到钱相关的，数据库统一以分做单位，用整数的形式保存到数据库，而且必须使用BigDecimal做运算,
两位精度不够的情况下，有规定位数的继续用int，

# 引用技术
- SpringBoot
- SpringMVC
- Mybatis
- Logback
- Jackson
- Lombok
- Swagger2
- Mybatis Plus
