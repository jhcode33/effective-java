package me.whiteship.chapter01.item01;

import me.whiteship.hello.ChineseHelloService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.ServiceLoader;

public class HelloServiceFactory {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //== 어떤 임의의 구현체가 올지 모르는 코드 ==//
        // ServiceLoader은 Iterable을 구현함
        ServiceLoader<HelloService> loader = ServiceLoader.load(HelloService.class);
        // 구현체가 없을 수도 있으므로 Optional을 사용
        Optional<HelloService> helloServiceOptional = loader.findFirst();
        // 존재하면 -> 출력
        helloServiceOptional.ifPresent(h -> {
            System.out.println(h.hello());
        });
        // 굳이 아래처럼 직접 호출하지 않는 이유는 -> ChineseHelloService에 의존적이지 않게 하기 위해 위의 ServiceLoader을 사용함
        HelloService helloService = new ChineseHelloService();
        System.out.println(helloService.hello());

//        Class<?> aClass = Class.forName("me.whiteship.hello.ChineseHelloService");
//        Constructor<?> constructor = aClass.getConstructor();
//        HelloService helloService = (HelloService) constructor.newInstance();
//        System.out.println(helloService.hello());
    }

}
