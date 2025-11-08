package ru.java.pro.logging;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ResponseBodyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true; // Разрешить дальнейшую обработку запроса
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView mav) throws Exception {
        logResponse(request, response, "result");
        // Для REST-запросов, скорее всего, mvc будет равен null, проверим наличие возвращаемого значения
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            MethodParameter returnType = method.getReturnType();
            Class<?> returnClass = returnType.getParameterType();

            // Проверяем, возвращает ли метод обычный объект (не View)
            if (!returnClass.equals(ModelAndView.class)) {
                if (handler instanceof HandlerMethod) {
                    HandlerMethod hm = (HandlerMethod) handler;
                    Class<?> clazz = method.getBean().getClass();
                    Method targetMethod = clazz.getDeclaredMethod(
                            method.getMethod().getName(),
                            method.getMethod().getParameterTypes()
                    );
                    // Извлекаем аннотацию ResponseBody, если она присутствует
                    ResponseBody annotation = targetMethod.getAnnotation(ResponseBody.class);
                    if (annotation != null) { // Логируем только JSON-ответы
                        // Определяем аргументы метода
//                        List<Object> argsList = resolveArguments(hm, request, response);
                        // Получаем ссылку на исходный метод
//                        Method method = hm.getMethod();

                        // Выполняем метод с соответствующими аргументами
//                        Object result = targetMethod.invoke(method.getBean(), argsList.get(0));
                        // Регистрация результата
//                        logResponse(request, response, "result");

                    }
                }
            }
        }
    }

    private void logResponse(HttpServletRequest request, HttpServletResponse response, Object result) {
        int statusCode = response.getStatus();
        log.info("В интерцепторе Ответ: HTTP {} - {} result - {}", statusCode, request.getRequestURI(), result);
    }

//    /**
//     * Автоматически определяет и разрешает аргументы метода
//     */
//    private List<Object[]> resolveArguments(HandlerMethod handlerMethod, HttpServletRequest request,
//                                            HttpServletResponse response) {
//        Map<String, ?> paramsMap = handlerMethod.resolveHandlerArguments(request, response);
//
//        // Получаем имена параметров метода
//        String[] parameterNames = parameterNameDiscoverer.getParameterNames(handlerMethod.getMethod());
//
//        // Формируем массив аргументов
//        Object[] argsArray = new Object[parameterNames.length];
//        for (int i = 0; i < parameterNames.length; i++) {
//            String paramName = parameterNames[i];
//            argsArray[i] = paramsMap.get(paramName);
//        }
//
//        return List.of(argsArray);
//    }


//    @Autowired
//    private HandlerMethodArgumentResolverComposite argumentResolvers;
//
//    private List<Object> resolveArguments(HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        NativeWebRequest webRequest = new ServletWebRequest(request, response);
//        ModelAndViewContainer mavContainer = new ModelAndViewContainer();
//        WebDataBinderFactory binderFactory = new ServletRequestDataBinderFactory(null, null);
//
//        List<Object> args = new ArrayList<>();
//        for (MethodParameter parameter : handlerMethod.getMethodParameters()) {
//            args.add(argumentResolvers.resolveArgument(parameter, mavContainer, webRequest, binderFactory));
//        }
//        return args;
//    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object
            handler, Exception ex) throws Exception {
        // Завершение обработки контроллера
        logResponse(request, response, response.getStatus());
    }
}

