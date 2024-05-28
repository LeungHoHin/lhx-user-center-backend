# 用户管理系统

1. 选用**MyBatis + MyBatis-Plus**进行数据访问层开发，复用大多数通用方法，并且通过继承定制了自己的 通用操作模板 ，大幅提升了项目开发效率。
2. 为了明确接口的返回，自定义统一的错误码，并封装了**全局异常处理器**，从而规范了异常返回、屏蔽了项目冗余的报错细节。
3. 对于项目中的 JSON 格式化处理对象，采用**双检锁单例模式**进行管理，从而复用对象，避免了重复创建对象的开销，便于集中维护管理。


## 自定错误码

    SUCCESS(200,"success",""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统内部异常", "");

## 全局异常处理器实现
  @RestControllerAdvice
  
  @Slf4j
  
  public class GlobalExceptionHandler {
  
      @ExceptionHandler(BusinessException.class)
      public BaseResponse<?> businessExceptionHandler(BusinessException e) {
          log.error("BusinessException: " + e.getMessage(), e);
          return Result.error(e.getCode(), e.getMessage(), e.getDescription());
      }
  
      @ExceptionHandler(RuntimeException.class)
      public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
          log.error("runtimeException", e);
          return Result.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
      }
  
    }

  @Getter
  
  public class BusinessException extends RuntimeException {
  
      private final int code;
  
      private final String description;
  
      public BusinessException(String message, int code, String description) {
          super(message);
          this.code = code;
          this.description = description;
      }
  
  
      public BusinessException(ErrorCode errorCode) {
          super(errorCode.getMessage());
          this.code = errorCode.getCode();
          this.description = errorCode.getDescription();
      }
  
      public BusinessException(ErrorCode errorCode, String description) {
          super(errorCode.getMessage());
          this.code = errorCode.getCode();
          this.description = description;
      }
  }
