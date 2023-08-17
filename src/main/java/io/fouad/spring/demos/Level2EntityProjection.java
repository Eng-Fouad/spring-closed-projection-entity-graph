package io.fouad.spring.demos;

public interface Level2EntityProjection {
    Integer getId();
    String getName();
    Level3EntityProjection getLevel3();
}