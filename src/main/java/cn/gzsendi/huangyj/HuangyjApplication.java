package cn.gzsendi.huangyj;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.gzsendi.huangyj.mapper")
@ComponentScan("cn.gzsendi")
public class HuangyjApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuangyjApplication.class, args);
    }

}
