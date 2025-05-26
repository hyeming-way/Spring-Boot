package net.skhu; // 이 프로그램이 'net.skhu'라는 이름의 폴더 안에 들어있어요.
                   // 마치 컴퓨터에서 파일을 특정 폴더에 넣어 정리하듯이, 프로그램 코드도 이렇게 '패키지'라는 폴더에 묶어서 관리해요.
                   // Spring Legacy 프로젝트에서도 'src/main/java' 아래에 패키지 폴더들을 만들었듯이, Java의 기본적인 정리 방식입니다.
                   // (Legacy MVC와 동일한 개념)


//'SpringApplication'은 우리 스프링 부트 프로그램을 '시동'하는 데 필요한 핵심 도구예요.
// 마치 자동차를 운전하기 전에 시동을 걸고, 필요한 부품들을 준비하는 것과 같아요.
// 이 도구 덕분에 Spring Boot는 내장된 웹 서버(Tomcat)를 띄우고, 필요한 초기 설정을 알아서 해줄 수 있답니다.
// (Legacy MVC에서는 'web.xml'에 DispatcherServlet을 설정하고,
//  서버(Tomcat 등)에 배포하여 실행하는 과정이 필요했지만,
//  Spring Boot는 이 클래스를 통해 애플리케이션을 직접 실행합니다.)
import org.springframework.boot.SpringApplication;

//이 '@SpringBootApplication'이라는 '마법의 표시'가 정말 중요해요!
// 얘는 아래의 세가지 어노테이션을 하나로 합친 중요한 역할을 한 번에 해줘요.
// 1. @Configuration : "이 클래스가 Spring의 각종 설정을 담고 있어!" 라고 알려줘요.
//    (Legacy MVC의 XML 설정 파일들, 예를 들어 'root-context.xml'이나
//										   'servlet-context.xml'에 <bean> 등록 등으로 하던 역할을
//     Java 클래스 기반으로 할 수 있게 합니다.)
// 2. @EnableAutoConfiguration :
//    "내가 필요한 모든 설정을 자동으로 찾아 해줄게!" 라고 말하며, 여러분이 'pom.xml'에 추가한 라이브러리들을 보고
//    웹 서버 설정, 데이터베이스 연결 설정 등을 **자동으로** 다 해줍니다.
//    (Legacy MVC에서 '<mvc:annotation-driven/>' 설정,
//      ViewResolver 설정, DataSource 빈 등록, SqlSessionFactoryBean 등록 등
//     수많은 설정을 XML 파일에 일일이 명시해야 했던 부분을
//     Spring Boot가 라이브러리 의존성을 기반으로 자동 구성해줍니다.
//     예를 들어, 'spring-boot-starter-web'이 있으면 웹 애플리케이션 관련 설정을 자동으로 해주고,
//     'spring-boot-starter-data-jpa'가 있으면 JPA 관련 설정을 자동으로 해줍니다.)
// 3. @ComponentScan :
//    "이 폴더(패키지)와 그 아래에 있는 모든 Spring 관련 코드들(컨트롤러, 서비스, 레포지토리 등)을 찾아내서 준비시켜!" 라고 명령해요.
//    (Legacy MVC에서 '<context:component-scan base-package="net.skhu"/>' 태그로 스캔할 패키지를
//     XML 설정 파일에 지정했던 부분을 얘가 자동으로 처리해 줍니다.
//      @SpringBootApplication이 붙은 클래스가 있는 패키지를 기준으로 스캔합니다.)

// 요약하면, 이 @SpringBootApplication애노테이션 하나로
// Spring Legacy의 수많은 초기 설정 (web.xml, servlet-context.xml, root-context.xml 등)을
// Spring Boot가 **최소한의 설정**으로 **자동으로** 처리해주는 겁니다!
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication // 바로 위에서 설명한 '마법의 표시'를 이 'Hello2Application' 클래스에 붙여줘요.
                       // 그래서 이 클래스가 우리 스프링 부트 프로그램의 가장 중요한 시작점이자, 모든 자동 설정의 기준이 됩니다.
                       // (Legacy MVC에서는 설정 파일(XML 또는 JavaConfig)을 여러 개 두고 역할을 분담했지만,
                       //  Spring Boot에서는 이 애노테이션 하나로 기본적인 설정이 집약됩니다.)

public class Hello2Application { // 이 프로그램의 '메인 클래스' 이름은 'Hello2Application'이에요.
                                 // 모든 Java 프로그램은 'main'이라는 특별한 시작점을 가지고 있어야 해요.
                                 // (Legacy MVC 프로젝트도 Java 기반이므로 클래스가 있지만,
								//   보통 웹 애플리케이션의 진입점은 web.xml에서 DispatcherServlet으로 시작됩니다.)

	public static void main(String[] args) { // 이 'main' 메서드는 프로그램을 실행했을 때 가장 먼저 작동하는 곳이에요.
                                             // (Legacy MVC 웹 애플리케이션은 보통 main 메서드를 직접 실행하지 않고,
											//   Tomcat 같은 WAS(Web Application Server)가 web.xml을 읽어들여 실행합니다.)


		 // 이 한 줄의 코드가 바로 우리의 스프링 부트 프로그램을 '실행'시키는 핵심 명령입니다!
        // - `Hello2Application.class`: "이 프로그램의 진짜 시작점은 'Hello2Application'이야!" 라고 알려줘요.
        //   (@SpringBootApplication이 붙은 클래스를 지정하여, 여기서부터 설정을 읽고 애플리케이션을 구성합니다.)
        // - `args`: 프로그램을 실행할 때 추가로 전달할 수 있는 정보들이 담겨요. (예: 특정 설정을 바꿀 때 사용)
        // 이 명령이 실행되면, Spring Boot는 자동으로 내장된 웹 서버(기본적으로 Tomcat)를 켜고,
        // `@SpringBootApplication` 덕분에 필요한 모든 설정을 마친 후,
        // 여러분의 웹사이트가 인터넷에 접속할 수 있도록 준비시킵니다.
        // **Spring Legacy와 달리, 외부 Tomcat 설치 및 복잡한 배포 과정 없이
		//   이 코드만으로도 웹사이트를 바로 띄울 수 있게 되는 거죠!**
        // (Legacy MVC에서는 개발한 애플리케이션을 WAR 파일로 빌드한 후,
		//  별도로 설치된 Tomcat의 webapps 폴더에 배포하고 Tomcat을 실행해야 했습니다.
        //  Spring Boot는 이 모든 과정을 `SpringApplication.run()` 한 줄로 단순화했습니다.)
		SpringApplication.run(Hello2Application.class, args);
	}

}




