package by.tomal.sw.domain.entity.exception

class AppException(val type: AppExceptionType = AppExceptionType.UNKNOWN): Throwable()