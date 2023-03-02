package tn.esprit.spring.service;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.excel.RowMapper;
//import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import tn.esprit.spring.entities.Product;

import javax.swing.tree.RowMapper;


//@Configuration
//@ComponentScan
//@EnableBatchProcessing
public class BatchConfiguration {

    /*
        @Autowired
        private JobBuilderFactory jobBuilderFactory;

        @Autowired
        private StepBuilderFactory stepBuilderFactory;

        @Value("classpath:products.xlsx")
        private Resource inputResource;

        @Bean
        public ItemReader<Row> productItemReader() {
            ExcelItemReader<Row> reader = new ExcelItemReader<>();
            reader.setResource(inputResource);
            reader.setRowMapper(new RowMapper());
            return reader;
        }

        @Bean
        public ProductProcessor productItemProcessor() {
            return new ProductProcessor();
        }

        @Bean
        public ProductItemWriter productItemWriter() {
            return new ProductItemWriter();
        }

        @Bean
        public Step productStep() {
            return stepBuilderFactory.get("productStep")
                    .<Row, Product>chunk(10)
                    .reader(productItemReader())
                    .processor(productItemProcessor())
                    .writer(productItemWriter())
                    .build();
        }

        @Bean
        public Job importProductsJob() {
            return jobBuilderFactory.get("importProductsJob")
                    .incrementer(new RunIdIncrementer())
                    .flow(productStep())
                    .end()
                    .build();
        }
    }*/















    /*
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;


    /*
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/
    /*
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.example.batchdemo.domain");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }


    @Bean
    public JobBuilderFactory jobBuilderFactory(DataSource dataSource, TransactionManager transactionManager) {
        return new JobBuilderFactory(new DataSourceTransactionManager(dataSource))

                .dataSource(dataSource)
                .transactionManager(transactionManager);
    }
   /*
    @Bean
    public ItemReader<Product> excelProductReader() {
        // Configure reader to read from Excel file
        ExcelItemReader<Product> reader = new ExcelItemReader<>();
        //PoiItemReader<Product> reader = new PoiItemReader<>();
        reader.setResource(new ClassPathResource("products.xls"));
        reader.setRowMapper(new ProductRowMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<Product, Product> productProcessor() {
        // Processor to add some logic to the product before writing to database
        return new ProductProcessor();
    }*/
     /*
    @Bean
    public ItemWriter<Product> productWriter() {
        // Writer to write products to database
        JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO produit (idProduct, description, nom, image, prix) " +
                "VALUES (:idProduct, :description, :nom, :image, :prix)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }

    @Bean
    public Step productStep() {
        // Step to read, process and write products
        return stepBuilderFactory.get("productStep")
                .<Product, Product>chunk(10)
                .reader(excelProductReader())
                .processor(productProcessor())
                .writer(productWriter())
                .build();
    }

    @Bean
    public Job importProductsJob() {
        // Job to execute productStep
        return jobBuilderFactory.get("importProductsJob")
                .incrementer(new RunIdIncrementer())
                .flow(productStep())
                .end()
                .build();
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager());
        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        factory.setTablePrefix("BATCH_");
        factory.setMaxVarCharLength(1000);
        return factory.getObject();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }*/
}

