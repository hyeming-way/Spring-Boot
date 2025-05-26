package net.skhu;

import org.springframework.boot.builder.SpringApplicationBuilder;
// 'SpringApplicationBuilder'는 Spring Boot 애플리케이션을 설정하고 구성을 도와주는 클래스입니다.
// 이 클래스는 애플리케이션이 실행될 때 필요한 설정을 처리하고, 웹 서버를 설정하는 역할을 합니다.
// Spring Boot 애플리케이션을 외부 웹 서버에서 실행할 때 이 클래스를 사용하여 애플리케이션의 초기화를 설정합니다.
// (Legacy MVC에서는 web.xml에서 ContextLoaderListener나 DispatcherServlet 설정을 통해
//   애플리케이션 컨텍스트를 구성했습니다.
//  Spring Boot를 WAR로 배포 시, 이 Builder가 유사한 역할을 수행하여
//   Spring Boot 애플리케이션 설정을 외부 서블릿 컨테이너에 맞게 구성합니다.)
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// 'SpringBootServletInitializer'는 Spring Boot 애플리케이션을 외부 Servlet 컨테이너(예: Tomcat, Jetty)에서 실행할 수 있도록 지원하는 클래스입니다.
// Spring Boot 애플리케이션은 기본적으로 내장된 Tomcat 서버를 사용하지만,
//  WAR 파일로 패키징 후 외부 서버에 배포할 경우 이 클래스를 상속받아야 합니다.
// 이 클래스를 통해 외부 서버에서 Spring Boot 애플리케이션을 실행할 수 있도록 설정합니다.
// (Legacy MVC는 기본적으로 WAR 파일로 배포되어 외부 서블릿 컨테이너에서 실행되는 방식이었습니다.
//  Spring Boot에서 이와 유사한 방식으로 배포하려면 이 클래스가 필요합니다.
//  이 클래스는 Legacy MVC의 `web.xml` 파일에서
//  `org.springframework.web.WebApplicationInitializer` 인터페이스를 구현하여
//  프로그래매틱하게 서블릿 컨텍스트를 설정하는 것과 유사한 역할을 합니다.
//  `web.xml`의 `<listener>` (ContextLoaderListener) 및
//   `<servlet>` (DispatcherServlet) 설정을 Java 코드로 대체하는 현대적인 방식의 시작점입니다.)

public class ServletInitializer extends SpringBootServletInitializer { //
    // 'ServletInitializer' 클래스는 Spring Boot 애플리케이션을 외부 서버에서 실행할 수 있게 도와주는 클래스입니다.
    // Spring Boot 애플리케이션은 기본적으로 내장 서버(Tomcat 등)를 사용하지만, 외부 서버에서 실행하려면 이 클래스를 사용하여 설정해야 합니다.
    // 'SpringBootServletInitializer' 클래스를 상속받아서 WAR 파일로 애플리케이션을 배포하고,
	 // 외부 서버에서 이를 실행할 수 있도록 합니다.
    // (Legacy MVC 프로젝트가 외부 WAS에 배포될 때, WAS는 `web.xml`을 읽어 Spring 컨텍스트를 초기화했습니다.
    //  Spring Boot를 WAR로 배포할 경우, 이 `ServletInitializer`가 그 역할을 대신하여
	//  외부 WAS가 Spring Boot 애플리케이션을 로드하고 실행할 수 있도록 합니다.)

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { //
        // 'configure' 메서드는 Spring Boot 애플리케이션을 외부 웹 서버에서 실행할 때의 초기화 작업을 설정하는 메서드입니다.
        // 이 메서드는 'SpringBootServletInitializer'에서 상속받은 메서드로,
    	//  외부 서버에서 실행될 때 애플리케이션을 어떻게 구성할지 지정합니다.
        // SpringApplicationBuilder는 애플리케이션을 설정하고 실행하는 데 사용되는 빌더입니다.
        // (Legacy MVC의 `web.xml`에서 `contextConfigLocation` 파라미터로 Spring 설정 파일(root-context.xml, servlet-context.xml 등)의 위치를 지정했던 것과 유사합니다.
        //  이 메서드는 Spring Boot 애플리케이션의 메인 설정 클래스(`Hello2Application.class`)를 지정하여,
        //  외부 서블릿 컨테이너가 이를 기반으로 Spring 애플리케이션 컨텍스트를 생성하도록 안내합니다.)

        // application.sources(Hello2Application.class)는 애플리케이션을 시작할 때
    	//'Hello2Application' 클래스를 메인 애플리케이션 클래스로 지정합니다.
        // 'Hello2Application.class'는 Spring Boot 애플리케이션의 시작점을 나타내는 클래스입니다.
        // 이 설정으로 외부 서버(Tomcat 등)에서 Spring Boot 애플리케이션이 실행될 때 'Hello2Application' 클래스가 시작됩니다.
        // (Legacy MVC에서 `web.xml`의 `ContextLoaderListener`가 `root-context.xml`을 로드하고,
        //  `DispatcherServlet`이 `servlet-context.xml`을 로드하여 각각 애플리케이션 컨텍스트를 구성했던 것처럼,
        //  여기서는 `Hello2Application.class` ( @SpringBootApplication이 붙은 클래스)를 통해 모든 설정을 로드합니다.)
        return application.sources(Hello2Application.class); //
        // 'application.sources(Hello2Application.class)'는 Spring Boot 애플리케이션의 설정을 수행한 후,
        // 애플리케이션을 실행할 클래스를 지정합니다. 여기서 'Hello2Application.class'는 실제로 애플리케이션을 실행하는 메인 클래스입니다.
        // 이 메서드는 Spring Boot 애플리케이션을 외부 서버에서 실행하는 데 필요한 설정을 완료한 후, 애플리케이션을 실행할 준비를 합니다.
    }
    // 'configure' 메서드를 오버라이드(재정의)하여 Spring Boot 애플리케이션의 설정을 외부 서버에 맞게 구성합니다.
}

