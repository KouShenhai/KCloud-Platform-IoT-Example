package org.laokou.flyway;

import lombok.RequiredArgsConstructor;
import org.laokou.flyway.domain.T;
import org.laokou.flyway.mapper.TMysqlMapper;
import org.laokou.flyway.mapper.TMysqlService;
import org.laokou.flyway.mapper.TPostgresqlMapper;
import org.laokou.flyway.mapper.TPostgresqlService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class FlywayPostgresqlApp implements CommandLineRunner {

    private final TMysqlService tMysqlMapper;
    private final TPostgresqlService tPostgresqlService;

    public static void main(String[] args) {
        SpringApplication.run(FlywayPostgresqlApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<T> list = new ArrayList<>();
        for (long i = 0; i < 100000; i++) {
            list.add(new T(i, "name" + i));
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            List<T> l = list.subList(i * 10000, (i + 1) * 10000);
            tMysqlMapper.saveBatch(l);
        }
        System.out.println(System.currentTimeMillis() - start);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            List<T> l = list.subList(i * 10000, (i + 1) * 10000);
            tPostgresqlService.saveBatch(l);
        }
        System.out.println(System.currentTimeMillis() - start1);
    }
}
