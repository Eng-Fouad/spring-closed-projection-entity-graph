package io.fouad.spring.demos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.projection.CollectionAwareProjectionFactory;

import java.util.function.Supplier;

@SpringBootApplication
public class SpringDemoApplication implements CommandLineRunner {
    
    private final Level1EntityRepository level1EntityRepository;
    
    public SpringDemoApplication(Level1EntityRepository level1EntityRepository) {
        this.level1EntityRepository = level1EntityRepository;
    }
    
    @Override
    public void run(String... args) {

        System.out.println("\n==================================================\n");

        System.out.println("Calling level1EntityRepository.queryLevel1EntityWithEntityGraphById(1):");
        var level1Entity = level1EntityRepository.queryLevel1EntityWithEntityGraphById(1);
        System.out.print("level1EntityRepository.queryLevel1EntityWithEntityGraphById(1) -> level1Entity.level2.name: ");
        printValue(() -> level1Entity.getLevel2().getName(), "level1Entity.level2.name");
        System.out.print("level1EntityRepository.queryLevel1EntityWithEntityGraphById(1) -> level1Entity.level2.level3.name: ");
        printValue(() -> level1Entity.getLevel2().getLevel3().getName(), "level1Entity.level2.level3.name");

        System.out.println("\n==================================================\n");

        System.out.println("Calling level1EntityRepository.queryLevel1EntityWithoutEntityGraphById(1):");
        var level1Entity2 = level1EntityRepository.queryLevel1EntityWithoutEntityGraphById(1);
        System.out.print("level1EntityRepository.queryLevel1EntityWithoutEntityGraphById(1) -> level1Entity2.level2.name: ");
        printValue(() -> level1Entity2.getLevel2().getName(), "level1Entity2.level2.name");
        System.out.print("level1EntityRepository.queryLevel1EntityWithoutEntityGraphById(1) -> level1Entity2.level2.level3.name: ");
        printValue(() -> level1Entity2.getLevel2().getLevel3().getName(), "level1Entity2.level2.level3.name");

        System.out.println("\n==================================================\n");

        System.out.println("Calling level1EntityRepository.queryLevel1EntityWithJpqlFetchById(1):");
        var level1Entity3 = level1EntityRepository.queryLevel1EntityWithJpqlFetchById(1);
        System.out.print("level1EntityRepository.queryLevel1EntityWithJpqlFetchById(1) -> level1Entity3.level2.name: ");
        printValue(() -> level1Entity3.getLevel2().getName(), "level1Entity3.level2.name");
        System.out.print("level1EntityRepository.queryLevel1EntityWithJpqlFetchById(1) -> level1Entity3.level2.level3.name: ");
        printValue(() -> level1Entity3.getLevel2().getLevel3().getName(), "level1Entity3.level2.level3.name");

        System.out.println("\n==================================================\n");

        System.out.println("Calling level1EntityRepository.queryLevel1ProjectionWithEntityGraphById(1):");
        var level1Projection = level1EntityRepository.queryLevel1ProjectionWithEntityGraphById(1);
        System.out.print("level1EntityRepository.queryLevel1ProjectionWithEntityGraphById(1) -> level1Projection.level2.name: ");
        printValue(() -> level1Projection.getLevel2().getName(), "level1Entity.level2.name"); // this works because of the projection not because of the entity graph
        System.out.print("level1EntityRepository.queryLevel1ProjectionWithEntityGraphById(1) -> level1Projection.level2.level3.name: ");
        printValue(() -> level1Projection.getLevel2().getLevel3().getName(), "level1Projection.level2.level3.name");

        System.out.println("\n==================================================\n");

        System.out.println("Calling level1EntityRepository.queryLevel1ProjectionWithoutEntityGraphById(1):");
        var level1Projection2 = level1EntityRepository.queryLevel1ProjectionWithoutEntityGraphById(1);
        System.out.print("level1EntityRepository.queryLevel1ProjectionWithoutEntityGraphById(1) -> level1Projection2.level2.name: ");
        printValue(() -> level1Projection2.getLevel2().getName(), "level1Projection2.level2.name"); // this works because of the projection
        System.out.print("level1EntityRepository.queryLevel1ProjectionWithoutEntityGraphById(1) -> level1Projection2.level2.level3.name: ");
        printValue(() -> level1Projection2.getLevel2().getLevel3().getName(), "level1Projection2.level2.level3.name");

        System.out.println("\n==================================================\n");

        System.out.println("Calling level1EntityRepository.queryLevel1ProjectionWithJpqlFetchById(1):");
        var level1Projection3 = level1EntityRepository.queryLevel1ProjectionWithJpqlFetchById(1);
        System.out.print("level1EntityRepository.queryLevel1ProjectionWithJpqlFetchById(1) -> level1Projection3.level2.name: ");
        printValue(() -> level1Projection3.getLevel2().getName(), "level1Projection3.level2.name");
        System.out.print("level1EntityRepository.queryLevel1ProjectionWithJpqlFetchById(1) -> level1Projection3.level2.level3.name: ");
        printValue(() -> level1Projection3.getLevel2().getLevel3().getName(), "level1Projection3.level2.level3.name");

        System.out.println("\n==================================================\n");

        // workaround solution
        System.out.println("Calling new CollectionAwareProjectionFactory().createProjection(Level1EntityProjection.class, level1Entity):");
        var level1EntityProjection = new CollectionAwareProjectionFactory().createProjection(Level1EntityProjection.class, level1Entity);
        System.out.print("new CollectionAwareProjectionFactory().createProjection(Level1EntityProjection.class, level1Entity) -> level1EntityProjection.level2.name: ");
        printValue(() -> level1EntityProjection.getLevel2().getName(), "level1EntityProjection.level2.name");
        System.out.print("new CollectionAwareProjectionFactory().createProjection(Level1EntityProjection.class, level1Entity) -> level1EntityProjection.level2.level3.name: ");
        printValue(() -> level1EntityProjection.getLevel2().getLevel3().getName(), "level1EntityProjection.level2.level3.name");

        System.out.println("\n==================================================\n");
    }

    private static <T> void printValue(Supplier<T> valueSupplier, String valuePath) {
        try {
            System.out.println(valueSupplier.get());
        } catch (Exception e) {
            System.out.printf("Failed to load value (%s)%n", valuePath);
        }
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }
}