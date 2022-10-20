package web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

  // Метод, указывающий на класс конфигурации
  @Override
  protected Class<?>[] getRootConfigClasses() {
    logger.info("\u001B[33m root config start. \u001B[0m");
    return new Class<?>[] {
        AppConfig.class
    };
  }

  // Добавление конфигурации, в которой инициализируем ViewResolver, для
  // корректного отображения jsp.
  @Override
  protected Class<?>[] getServletConfigClasses() {
    logger.info("\u001B[33m servlet config start. \u001B[0m");
    return new Class<?>[] {
        WebConfig.class
    };
  }

  /* Данный метод указывает url, на котором будет базироваться приложение */
  @Override
  protected String[] getServletMappings() {
    logger.info("\u001B[33m Set servlet path start. \u001B[0m");
    return new String[] { "/" };
  }

}
