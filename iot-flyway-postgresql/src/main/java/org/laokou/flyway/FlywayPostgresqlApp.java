package org.laokou.flyway;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.RequiredArgsConstructor;
import org.laokou.flyway.domain.T;
import org.laokou.flyway.mapper.TMysqlMapper;
import org.laokou.flyway.mapper.TMysqlService;
import org.laokou.flyway.mapper.TPostgresqlMapper;
import org.laokou.flyway.mapper.TPostgresqlService;
import org.laokou.flyway.utils.MybatisUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class FlywayPostgresqlApp implements CommandLineRunner {

    private final TMysqlService tMysqlMapper;
    private final TPostgresqlService tPostgresqlService;
    private final MybatisUtil mybatisUtil;
    private final TransactionTemplate transactionTemplate;

    public static void main(String[] args) {
        SpringApplication.run(FlywayPostgresqlApp.class, args);
    }

    @Override
    public void run(String... args) {
        List<T> list = new ArrayList<>();
        for (long i = 0; i < 100000; i++) {
            list.add(new T(i, "name" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i, "" + i));
        }
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            List<T> l = list.subList(i * 10000, (i + 1) * 10000);
//            tMysqlMapper.saveBatch(l);
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        long start1 = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            List<T> l = list.subList(i * 10000, (i + 1) * 10000);
//            tPostgresqlService.saveBatch(l);
//        }
//        System.out.println(System.currentTimeMillis() - start1);

        transactionTemplate.executeWithoutResult(rollback -> {
            try {
                long start = System.currentTimeMillis();
                mybatisUtil.batch(list, TPostgresqlMapper.class, TPostgresqlMapper::insert);
                System.out.println(System.currentTimeMillis() - start);
                throw new RuntimeException();
            } catch (Exception e) {
                rollback.setRollbackOnly();
            }
        });
    }
}
